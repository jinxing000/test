package com.ef.golf.controller;

import com.ef.golf.aspectj.Syslog;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.pojo.Account;
import com.ef.golf.pojo.DebitRecord;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.Poundage;
import com.ef.golf.service.*;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.MineVo;
import com.pingplusplus.model.Order;
import com.pingplusplus.model.OrderRefund;
import com.pingplusplus.model.OrderRefundCollection;
import com.pingplusplus.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 09:16
 */
@Controller
@RequestMapping("/manage")
public class PayManageController {

    @Resource
    private FlowService flowService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;
    @Resource
    private DebitRecordService debitRecordService;
    @Resource
    private UserService userService;
    @Resource
    private PoundageService poundageService;
    @Resource
    private WithdrawalService withdrawalService;

    @Resource
    private PingUtil pingUtil;

    @ResponseBody/** 交易记录 */
    @RequestMapping(value = "/record",method = RequestMethod.POST)
    public Object tradingRecord
            (String uid, String sid,@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        try{
            Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
            PageBean pageBean =  flowService.jiaoYiJiLu(userId.longValue(),pageNo,pageSize);

            return IfunResult.ok(pageBean);
        }catch (Exception e){
           e.printStackTrace();
          return IfunResult.build(1,"查询失败!");
        }
    }

    @ResponseBody/** 提现记录 */
    @RequestMapping(value = "/withdraw",method = RequestMethod.POST)
    public Object withdraw(Integer userId,@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        try{
            PageBean pageBean = withdrawalService.getWithdrawalRecordByUserId(userId,"2",pageNo,pageSize);
            return IfunResult.ok(pageBean);
        }catch (Exception e){
            e.printStackTrace();
            return IfunResult.build(1,"查询失败!");
        }
    }
    @ResponseBody/** 提现余额 */
    @RequestMapping(value = "/txBalance",method = RequestMethod.POST)
    public Object txBalance(Integer userId){

        Map<String,Object>map = new HashMap<>();
        try{
            MineVo mineVo = userService.getInfo(userId);
            //获取用户提现手续费,天数
            Poundage poundage = poundageService.getAllPoundage(mineVo.getUserType());
            //用户账户
            Account account = accountService.getAccount(userId);
            PageBean pageBean = withdrawalService.getWithdrawalRecordByUserId(userId,"2",1,5);
            List<com.ef.golf.pojo.Withdrawal> withdrawalList = pageBean.getResultList();
            //查询七天收入
            Date date = null;
            if(withdrawalList.size()>0){
                if(null!=withdrawalList&&null!=withdrawalList.get(0).getCreateTime()){
                    date = withdrawalList.get(0).getCreateTime();
                }else{
                    date = new Date();
                }
            }else{
                date = new Date();
            }

            Double qiShouru = accountService.getUserTxBalance(date,mineVo.getUserType(),userId);
            if(null==qiShouru){
                qiShouru=0.0;
            }
            //所有可提现余额
            map.put("allTxBalance",account.getSrBalance());
            //当前时间可提现余额
            if(null!=qiShouru&&null!=poundage&&null!=poundage.getPoint()){
                Double money = account.getSrBalance()-qiShouru;
                if(money<0){
                    money = 0.0;
                }
                map.put("canTxBalance",money);
                Double serviceCharge = money*(poundage.getPoint()/10000.0);
                if(serviceCharge<0){
                    serviceCharge = 0.0;
                }
                //当前时间最终提现到账余额
                map.put("lastTxBalance",money-serviceCharge);
                map.put("day",poundage.getDay());
                DecimalFormat df = new DecimalFormat("0%");
                map.put("scale",df.format((double)poundage.getPoint()/10000.0));
            }else{
                map.put("canTxBalance",0.00);
                map.put("lastTxBalance",0.00);
                map.put("day",0);
                map.put("scale","0%");
            }

            return IfunResult.build(0,"",map);
        }catch (Exception e){
            e.printStackTrace();
            return IfunResult.build(1,"查询失败!");
        }
    }

    @ResponseBody/** 用户余额 */
    @RequestMapping(value = "/balance",method = RequestMethod.POST)
    public Object getBalance(String uid, String sid){
                try{

                    Map<String,Object>map = new HashMap<>();
                    Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
                    Account account = accountService.getAccount(userId);

                    User user =  pingUtil.userBalacnce(userId.toString());
                    Long balance = user.getAvailableBalance();
                    String money = AmountUtils.changeF2Y(balance);
                    map.put("message","余额账户(元)");
                    map.put("balance",money.replaceAll(",",""));
                    map.put("yucun",account.getCzBalance());
                    map.put("zenson",account.getZsBalance());
                    map.put("shouru",account.getSrBalance());
                    return IfunResult.ok(map);
                }catch (Exception e){
                    e.printStackTrace();
                    return IfunResult.build(1,"-9999");
                }
    }

    @ResponseBody/** 支付状态 */
    @RequestMapping(value = "/payStatus",method = RequestMethod.POST)
    public Object payStatus(String pingId){

        Boolean flag = false;
        try{
            flag = pingUtil.payStatus(pingId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return IfunResult.build(0,"正常",flag);
    }

    @Syslog(title = "refund",methods = "getBalance",description = "订单退款")
    @ResponseBody/** 订单退款 order */
    @RequestMapping(value = "/orderRefund",method = RequestMethod.POST)
    public Object getBalance(String orderId){
        Map<String,Object>map = new HashMap<>();
        try{
            /** 获取本地order对象 */
            com.ef.golf.pojo.Order myOrder = orderService.selectByPrimaryKey(Integer.valueOf(orderId));
            /** 获取用户余额信息 */
            Account account =  accountService.getUserBalance(Integer.valueOf(myOrder.getCreateUser()));
            /** 获取ping order */
            Order order=Order.retrieve(myOrder.getPingId());
            /** 支付方式为余额 退款需要更新本地余额 */
            if("balance".equals(order.getChargeEssentials().getChannel())){
                if(!myOrder.getIsDel()){
                    /** 获取消费记录 */
                    DebitRecord debitRecord = debitRecordService.getDebitRecord(myOrder.getOrderNo());
                    if(null!=debitRecord){
                        if(null!=debitRecord.getSrBalance()){
                            accountService.updateUserSrBalance(Integer.valueOf(myOrder.getCreateUser()),account.getSrBalance()+debitRecord.getSrBalance());
                        }
                        if(null!=debitRecord.getZsBalance()){
                            accountService.updateUserZsBalance(Integer.valueOf(myOrder.getCreateUser()),account.getZsBalance()+debitRecord.getZsBalance());
                        }
                        if(null!=debitRecord.getCzBalance()){
                            accountService.updateUserCzBalance(Integer.valueOf(myOrder.getCreateUser()),account.getCzBalance()+debitRecord.getCzBalance());
                        }
                    }
                }else{
                    map.put("status",1);
                    map.put("message","无效订单");
                }
            }
            Map<String,Object>params = new HashMap<>();
            params.put("description","订单退款");
            OrderRefundCollection orderRefund = OrderRefund.create(order.getId(),params);
            /*List<Refund>list = orderRefund.getData();
            for (Refund ef:list
                 ) {
                map.put("ee",ef);
            }*/
            map.put("o",orderRefund);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
