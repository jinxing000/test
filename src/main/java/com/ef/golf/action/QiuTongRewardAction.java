package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Account;
import com.ef.golf.pojo.DebitRecord;
import com.ef.golf.pojo.System_pay;
import com.ef.golf.service.AccountService;
import com.ef.golf.service.DebitRecordService;
import com.ef.golf.service.SystemPayService;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.HttpGetIpUtil;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.pingplusplus.model.BalanceTransfer;
import com.pingplusplus.model.Recharge;
import com.pingplusplus.model.User;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QiuTongRewardAction extends AbstractService {

    private String description="打赏充值";
    private String subject="";
    private String body;
    @Resource
    private SystemPayService systemPayService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private AccountService accountService;
    @Resource
    private DebitRecordService debitRecordService;

    private String uid;
    private String sid;

    private String takeId;//被打赏人id
    private double money;//打赏金额

    @Override
    public Object doService() throws Exception {
        Map<String,Object>map = new HashMap<>();
       Integer userId =  redisLoginVerifyUtil.longinVerifty(sid,uid);
       if(userId!=null){
           User user = pingUtil.userBalacnce(userId.toString());
           Long balance = user.getAvailableBalance();
           /**
            * 企业付:转账，打赏等功能
            * @param amount 金额（分）
            * @param orderNo 编号
            * @param recipient 收款者账号
            * @param channel 支付方式
            * @return Transfer 对象
            */
           if(balance>0&&balance>money){
               try{
                   /** 本地余额查询 */
                   Account account1 = accountService.getUserBalance(userId);
                   Account account2 = accountService.getUserBalance(Integer.parseInt(takeId));
                    /** 转账 */
                   String orderNo = orderNoUtil.orderNoGenerate("03",uid);
                   /** 元转分 */
                   String moneyY2F =AmountUtils.changeY2F((long)money);
                   BalanceTransfer balanceTransfer = pingUtil.balanceTransfer(Integer.valueOf(moneyY2F),description,userId.toString(),takeId,orderNo);
                   insertDebitRecord(userId.toString(),balanceTransfer,balanceTransfer.getOrderNo());
                       /** 转账记录 */
                       System_pay systemPay = new System_pay();
                       systemPay.setUserId(Integer.valueOf(balanceTransfer.getUser()));
                       systemPay.setTakeId(Integer.valueOf(balanceTransfer.getRecipient()));
                       /** 分转元 */
                       String moneyF2Y = AmountUtils.changeF2Y((long)balanceTransfer.getAmount());
                       systemPay.setMoney(Double.valueOf(moneyF2Y));
                       systemPay.setCreateTime(new Date());
                       systemPay.setModifyTime(new Date());
                       systemPay.setType("1");
                       systemPay.setState(balanceTransfer.getStatus().equals("succeeded")?"1":"2");
                       systemPayService.insertSelective(systemPay,balanceTransfer.getOrderNo(),0.0);
                       return systemPay;
               }catch (Exception e){
                   e.printStackTrace();
                   new SystemException(Constant.ERR_SYSTEM);
               }
           }
       }
        map.put("message","余额不足!");
        return map;

    }

    /** 账户转出记录 */
    public void insertDebitRecord(String userId,BalanceTransfer balanceTransfer,String pingOrderNo) throws Exception {
        /** 转出方账户详细 */
        Account zcAccount = accountService.getUserBalance(Integer.valueOf(userId));
        /** 接收方账户详细 */
        Account jsAccount = accountService.getUserBalance(Integer.valueOf(takeId));
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
        accountService.updateUserSrBalance(Integer.valueOf(takeId),jsAccount.getSrBalance()+F2YPayMoney);
        debitRecord.setAccountId(zcAccount.getAccountId());
        debitRecord.setUserId(Integer.valueOf(userId));
        debitRecord.setPayForm("1");
        debitRecord.setCreateTime(new Date());
        debitRecord.setPingId(pingOrderNo);
        /** 保存此次消费记录 */
        debitRecordService.insertSelective(debitRecord);
    }


    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setTakeId(String takeId) {
        this.takeId = takeId;
    }

    public void setMoney(double money) {
        this.money = money;
    }

  /*  public void setChannel(String channel) {
        this.channel = channel;
    }*/

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
