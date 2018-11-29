package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Account;
import com.ef.golf.pojo.DebitRecord;
import com.ef.golf.pojo.System_pay;
import com.ef.golf.pojo.Trading;
import com.ef.golf.service.*;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.vo.MineVo;
import com.pingplusplus.model.BalanceTransfer;
import com.pingplusplus.model.Order;
import com.pingplusplus.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by wxc
 * 2018年5月3日11:269
 * 转账操作
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransferAction extends AbstractService {

    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private DebitRecordService debitRecordService;
    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;
    @Resource
    private SystemPayService systemPayService;
    @Resource
    private UserService userService;
    @Resource
    private TradingService tradingService;

    private String userId;//转出方
    private String recipient;//接收方
    private String type;//1球童 2教练 3商家
    private String orderId;
    @Override
    public Object doService() throws Exception {
        Map<String,Object>map = new HashMap<>();
        BalanceTransfer balanceTransfer = null;
        /** 获取用户信息 */
        MineVo mineVo = userService.getInfo(Integer.valueOf(userId));
        /** 获取ping id */
        String pingOrderNo=orderService.getPingOrderId(orderId);
        if(StringUtils.isEmpty(pingOrderNo)){
            throw new SystemException(Constant.ERR_QUERY-2);
        }
        try{
            /** 查询ping order */
            Order order=Order.retrieve(pingOrderNo);
            if(StringUtils.isNotEmpty(order.getObject())){
                /** 查询转出方账户余额 */
                User user = pingUtil.userBalacnce(userId);
                long balance = user.getAvailableBalance();
                long zcUserBalance = Integer.valueOf(AmountUtils.changeF2Y(balance));
                /** 拿到转账金额 */
                Double F2YMoney = Double.valueOf(AmountUtils.changeF2Y(order.getAmount()+""));
                /** 转账 转账 */
                    if(zcUserBalance>=F2YMoney){


                        String TransferOrderNo = orderNoUtil.serialNoGenerate("06",mineVo.getPhone());
                        /** 转账 */
                        balanceTransfer = pingUtil.balanceTransfer(order.getAmount(),"转账",userId,recipient,TransferOrderNo);



                        if(balanceTransfer!=null&&balanceTransfer.getStatus().equals("succeeded")){
                            /** 账户转出记录存储 各账户余额更新*/
                            insertDebitRecord(balanceTransfer,pingOrderNo);
                            /** 转账记录 */
                            System_pay systemPay = new System_pay();
                            systemPay.setUserId(Integer.valueOf(balanceTransfer.getUser()));
                            systemPay.setTakeId(Integer.valueOf(balanceTransfer.getRecipient()));
                            /** 分转元 */
                            String moneyF2Y = AmountUtils.changeF2Y((long)balanceTransfer.getAmount());
                            systemPay.setMoney(Double.valueOf(moneyF2Y));
                            systemPay.setCreateTime(new Date());
                            systemPay.setModifyTime(new Date());
                            systemPay.setType(type);
                            systemPay.setState(balanceTransfer.getStatus().equals("succeeded")?"1":"2");
                            systemPayService.insertSelective(systemPay,null,0.0);
                            /** 交易记录 */
                            Trading trading = new Trading();
                            trading.setCreateTime(new Date());
                            trading.setModifyTime(new Date());
                            trading.setMoney(Double.valueOf(moneyF2Y));
                            trading.setOrderId(Integer.valueOf(orderId));
                            trading.setUserId(Long.valueOf(userId));
                            tradingService.insertSelective(trading);
                            map.put("message","转账成功");
                            return map;
                        }
                    }else{
                        throw new SystemException(Constant.ERR_BalanceTransfer);
                    }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw  new SystemException(Constant.ERR_BalanceTransfer);
        }
        return null;
    }

    /** 账户转出记录 */
    public void insertDebitRecord(BalanceTransfer balanceTransfer,String pingOrderNo) throws Exception {
        /** 转出方账户详细 */
        Account zcAccount = accountService.getUserBalance(Integer.valueOf(userId));
        /** 接收方账户详细 */
        Account jsAccount = accountService.getUserBalance(Integer.valueOf(recipient));
        /** 转款金额 */
        Double F2YPayMoney = Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+""));
        DebitRecord debitRecord = new DebitRecord();
        if(zcAccount.getCzBalance()>=F2YPayMoney){//充值余额
            debitRecord.setCzBalance(F2YPayMoney);
            /** 更新转出方预存余额... */
            accountService.updateUserCzBalance(Integer.valueOf(userId),zcAccount.getCzBalance()-F2YPayMoney);
        }else if (zcAccount.getCzBalance()<F2YPayMoney&&zcAccount.getCzBalance()+zcAccount.getSrBalance()>F2YPayMoney){//充值余额-->充值余额+收入余额
            debitRecord.setCzBalance(zcAccount.getSrBalance());
            debitRecord.setSrBalance(F2YPayMoney-zcAccount.getCzBalance().intValue());
            /** 更新转出方预存、收入余额 */
            accountService.updateUserCzBalance(Integer.valueOf(userId),0.00);
            accountService.updateUserSrBalance(Integer.valueOf(userId),zcAccount.getCzBalance()+zcAccount.getSrBalance()-F2YPayMoney);
        }else if (zcAccount.getCzBalance()<F2YPayMoney&&zcAccount.getCzBalance()+zcAccount.getSrBalance()<F2YPayMoney&&
                zcAccount.getCzBalance()+zcAccount.getSrBalance()+zcAccount.getZsBalance()>F2YPayMoney){//充值余额-->充值余额+收入余额-->充值余额+收入余额+赠送余额
            debitRecord.setCzBalance(zcAccount.getCzBalance());
            debitRecord.setSrBalance(zcAccount.getSrBalance());
            debitRecord.setZsBalance(F2YPayMoney-zcAccount.getCzBalance().intValue()-zcAccount.getSrBalance().intValue());
            /** 更新转出方预存、收入、赠送余额 */
            accountService.updateUserCzBalance(Integer.valueOf(userId),zcAccount.getCzBalance());
            accountService.updateUserSrBalance(Integer.valueOf(userId),zcAccount.getSrBalance());
            accountService.updateUserZsBalance(Integer.valueOf(userId),zcAccount.getCzBalance()+zcAccount.getSrBalance()+zcAccount.getZsBalance()-F2YPayMoney);
        }
        /** 更新接收方收入余额 */
        accountService.updateUserSrBalance(Integer.valueOf(recipient),jsAccount.getSrBalance()+F2YPayMoney);
        debitRecord.setAccountId(zcAccount.getAccountId());
        debitRecord.setUserId(Integer.valueOf(userId));
        debitRecord.setPayForm("1");
        debitRecord.setCreateTime(new Date());
        debitRecord.setPingId(pingOrderNo);
        /** 保存此次消费记录 */
        debitRecordService.insertSelective(debitRecord);
    }
    public void setUserId(String userId) {
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
