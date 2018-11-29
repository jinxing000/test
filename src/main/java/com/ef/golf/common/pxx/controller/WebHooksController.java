package com.ef.golf.common.pxx.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.MineVo;
import com.pingplusplus.model.*;
import com.pingplusplus.model.Order;
import com.pingplusplus.model.Recharge;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * ping++的webhooks通知，用于接收用户支付、提现等的异步通知
 * create by xzw
 * 2018年1月22日17:22:15
 *  demo 地址 https://github.com/PingPlusPlus/pingpp-java/blob/master/src/test/java/com/pingplusplus/order/OrderTest.java#L139
 */
@Controller
@RequestMapping("/pingNotice")
public class WebHooksController {

    @Resource
    private OrderService orderService;
    @Resource
    private AccountService accountService;
    @Resource
    private FlowService flowService;
    @Resource
    private RechargeService rechargeService;
    @Resource
    private HopeService hopeService;
    @Resource
    private DebitRecordService debitRecordService;
    @Resource
    private SystemTransferService systemTransferService;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private MerchantOrderService merchantOrderService;
    @Resource
    private SystemPayService systemPayService;
    @Resource
    private HopeRecordService hopeRecordService;
    @Resource
    private InfromMsgService infromMsgService;
    @Resource
    private UserService userService;
    @Resource
    private SiteOrderTdService siteOrderTdService;
    @Resource
    private UserRoleService userRoleService;
    @Autowired
    private SyslogService syslogService;
    @Resource
    private HttpServletRequest request;

    @Value("${recharge.success}")
    private String rechargeSuccessUrl;
    @Value("${urlHead}")
    private String urlHead;
    @Value("${workOrders}")
    private String workOrders;
    @Value("${coachSuccessTitle}")
    private String coachSuccessTitle;
    @Value("${coachSuccessContent}")
    private String coachSuccessContent;
    @Value("${courseSuccessTitle}")
    private String courseSuccessTitle;
    @Value("${courseSuccessContent}")
    private String courseSuccessContent;
    @Value("${reserveCourseOrder}")
    private String reserveCourseOrder;
    @Value("${reserveCoachOrder}")
    private String reserveCoachOrder;
    @Value("${templateId.course.pay}")
    private String templateIdCoursePay;
    @Value("${templateId.coach.pay}")
    private String templateIdCoachPay;
    @Value("${wishDetails}")
    private String wishDetails;
    @Value("${hopeTitle}")
    private String hopeTitle;
    @Value("${hopeContent}")
    private String hopeContent;
    @Value("${wish}")
    private String wish;
    @Value("${hopeComeTrue}")
    private String hopeComeTrue;

    @RequestMapping(value = "/getNotice",method = RequestMethod.POST)
    @com.ef.golf.aspectj.Syslog(title = "webHooks", methods = "getNotice", description = "webHooks通知")
    public void getNotice(HttpServletRequest request, HttpServletResponse response){
        System.out.println("《《=============进入ping++的WebHooks异步通知处理=====================》》");
        System.out.println("===============================处理开始=======================================");
        //从request获取头部所有信息
        Enumeration headerNames=request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key=(String)headerNames.nextElement();
            String value=request.getHeader(key);
            System.out.println("======================================="+key+":"+value);
        }

        //获取http body里内容
        BufferedReader reader= null;
        StringBuffer sb=new StringBuffer();
        try {
            reader = request.getReader();
            String string;
            while ((string=reader.readLine())!=null){
                sb.append(string);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //解析异步通知数据
        Event event= Webhooks.eventParse(sb.toString());
        String httpType=event.getType();
        System.out.println("=====================httpType=============================="+httpType);
        System.out.println("=====================httpType=============================="+httpType.length());
        if("order.succeeded".equals(httpType)){//订单支付成功
            int num=updateOrderState(event);
            if(num>0){
                System.out.println("《《=================订单已完成======================》》");
            }
            response.setStatus(200);
        }else if ("order.refunded".equals(httpType)){//订单退款成功
            orderRefund(event);
            response.setStatus(200);
        }else if ("balance.withdrawal.succeeded".equals(httpType)){//提现成功
            response.setStatus(200);
        }else if ("balance.withdrawal.failed".equals(httpType)){//提现失败
            response.setStatus(200);
        }else if("recharge.succeeded".equals(httpType)){//充值成功
            int i = rechargeGive(event);
            System.out.println("《《=================充值方法返回======================》》"+i);
            System.out.println("《《=================充值成功回调======================》》");
            response.setStatus(200);
        }else if ("recharge.refund.succeeded".equals(httpType)){//充值失败
            System.out.println("《《=================充值失败回调======================》》");
            response.setStatus(200);
        }else if("balance.withdrawal.succeeded".equals(httpType)){//提现成功
            withdrawal(event);
            response.setStatus(200);
        }else if("balance.withdrawal.failed".equals(httpType)){//提现失败
            response.setStatus(200);
        }else if("transfer.succeeded".equals(httpType)){//企业付款成功
            System.out.println("《《=================企业付款成功回调======================》》");
            SystemTransfer(event);
            response.setStatus(200);
        }else if("transfer.failed".equals(httpType)){//企业付失败
            System.out.println("《《=================企业付失败回调======================》》");
            response.setStatus(200);
        }else if("order.refunded".equals(httpType)){
            System.out.println("《《=================订单退款回调======================》》");
            orderRefund(event);
            response.setStatus(200);
        }
        else{
            System.out.println("《《=================WEBHOOKS通知异常======================》》");
            response.setStatus(500);
        }

    }



    /**
     * 更新订单状态
     * 交易类型(1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 红包 11-赠送 12预约课程 13预约教练 14单次课程费)
     * @param event
     */

    public int updateOrderState(Event event){
        int count = 0;
       /* try{*/
           count =  orderService.updateByOrderNo(event);
        /*}catch (Exception e){
            e.printStackTrace();
        }*/
        return count;
    }
    public Object orderRefund(Event event){
        System.out.println("《《=================订单退款成功webhooks触发=======================》》");
        Order order  = (Order) event.getData().getObject();
        com.ef.golf.pojo.Order bdOrder = orderService.getOrderByNo(order.getMerchantOrderNo());
        System.out.println("==========获取到本地order"+bdOrder.toString());
        System.out.println("==========pingOrder对象"+order);
        /** 获取本地order对象 */
        /** 获取用户余额信息 */
        Account account =  accountService.getUserBalance(Integer.valueOf(order.getUid()));
        /** 支付方式为余额 退款需要更新本地余额 */
        if("balance".equals(order.getChargeEssentials().getChannel())){
            System.out.println("========================订单退款（支付方式balance执行）================================");
                /** 获取消费记录 */
                DebitRecord debitRecord = debitRecordService.getDebitRecord(order.getMerchantOrderNo());
                if(null!=debitRecord){
                    if(null!=debitRecord.getSrBalance()){
                        accountService.updateUserSrBalance(Integer.valueOf(order.getUid()),account.getSrBalance()+debitRecord.getSrBalance());
                        System.out.println("退款++++++++++"+"="+account.getSrBalance()+debitRecord.getSrBalance());
                    }
                    if(null!=debitRecord.getZsBalance()){
                        accountService.updateUserZsBalance(Integer.valueOf(order.getUid()),account.getZsBalance()+debitRecord.getZsBalance());
                        System.out.println("退款++++++++++"+"="+account.getZsBalance()+debitRecord.getZsBalance());
                    }
                    if(null!=debitRecord.getCzBalance()){
                        accountService.updateUserCzBalance(Integer.valueOf(order.getUid()),account.getCzBalance()+debitRecord.getCzBalance());
                        System.out.println("退款++++++++++"+"="+account.getCzBalance()+debitRecord.getCzBalance());
                    }
                }
        }

        try {
            JiaoYiHuizong jiaoYiHuizongChu = new JiaoYiHuizong
                    (null,Integer.valueOf(order.getUid()),"3",new Date(),
                            1,Double.valueOf(AmountUtils.changeF2Y(order.getAmountRefunded()+"")),order.getMerchantOrderNo(),bdOrder.getModifyUser(),bdOrder.getChannel());
            systemPayService.insertSelective(jiaoYiHuizongChu);
            System.out.println("=============交易记录退款插入================="+bdOrder.getChannel()+"=========="+bdOrder.getModifyUser()+"==========="+bdOrder.getCreateUser());
        } catch (Exception e) {
            e.printStackTrace();
        }


        SiteOrderTd siteOrderTd = new SiteOrderTd();
        System.out.println("退款金额"+Double.valueOf(AmountUtils.changeY2F(order.getAmountRefunded()+"")));
        siteOrderTd.setRefundMoney(Double.valueOf(AmountUtils.changeY2F(order.getAmountRefunded()+"")));
        siteOrderTd.setOrderNo(order.getMerchantOrderNo());
        siteOrderTdService.insertSelective(siteOrderTd);
        System.out.println("退款结束");
        return siteOrderTd;
    }


    /**
     * 充值赠送
     * @param event   description="1" 普通充值   ="2" 充值转账  body传接收者id   y,hopeId
     */
    public int rechargeGive(Event event){

        try{

            System.out.println("《《=================充值完成记录、更新=======================》》");
            Recharge recharge = (Recharge) event.getData().getObject();

            /** 取请求时传的参数 */
            Double oldMoney = 0.0;

            String recipient="";
            String hopeId = "";
            String phone = "";
            String type="";
            long merchantOrderId = 0;
            if(!"-9999".equals(recharge.getCharge().getBody())){
                if(StringUtils.isNotBlank(recharge.getCharge().getBody())) {
                    /** 解析bodyMap */
                    JSONObject jsonObject = JSONObject.parseObject(recharge.getCharge().getBody());
                    //json对象转Map
                    Map<String, Object> bodyMap = (Map<String, Object>) jsonObject;
                    System.out.println("《《=================bodyMap数据=======================》》"+bodyMap);
                    type = (String) bodyMap.get("type");
                    recipient = (String)bodyMap.get("recipient");
                    phone = (String)bodyMap.get("uid");
                    hopeId = (String)bodyMap.get("hopeId");
                    if ("3".equals(type)) {
                        oldMoney = Double.valueOf(bodyMap.get("oldMoney").toString()) ;
                        merchantOrderId = Long.valueOf(bodyMap.get("merchantOrderId").toString());
                    }
                }
            }
            //获取充值用户
            String userId  = recharge.getUser();

            System.out.println("《《=================充值用户userId=======================》》"+userId);
            if("2".equals(recharge.getDescription())&&!"4".equals(type)) {
                System.out.println("================充值转账处理=================");
                /** 用户余额查询 */
                Account account =  accountService.getUserBalance(Integer.valueOf(userId));
                /** 用户充值余额 */
                accountService.updateUserCzBalance(Integer.valueOf(userId),Double.valueOf(AmountUtils.changeF2Y(recharge.getAmount().toString()))+account.getCzBalance());

                String zzOrderNo = recharge.getCharge().getOrderNo();

                System.out.println("《《=================recipient=======================》》"+recipient);
                BalanceTransfer balanceTransfer = pingUtil.balanceTransfer(recharge.getAmount(), "转账支付", userId, recipient, zzOrderNo);

                System.out.println("《《=================转账状态=======================》》"+balanceTransfer.getStatus());
                /** 转账记录 */

                /** 交易记录 */
                if(balanceTransfer.getStatus().equals("succeeded")){
                    /** 转出方账户详细 */
                    Account zcAccount = accountService.getUserBalance(Integer.valueOf(userId));
                    /** 接收方账户详细 */
                    Account jsAccount = accountService.getUserBalance(Integer.valueOf(balanceTransfer.getRecipient()));
                    /** 更新转出方充值余额 */
                    System.out.println("转出方余额开始更新");
                    accountService.updateUserCzBalance(Integer.valueOf(userId),zcAccount.getCzBalance()-(balanceTransfer.getAmount()/100));
                    /** 更新接收方收入余额 */
                    System.out.println("接收方余额开始更新");
                    accountService.updateUserSrBalance(Integer.valueOf(balanceTransfer.getRecipient()),jsAccount.getSrBalance()+(balanceTransfer.getAmount()/100));

                    /** 流水 */
                    Flow flow = new Flow();
                    flow.setUserId(Integer.valueOf(userId));
                    flow.setMoney(Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")));
                    flow.setSequenceNumber(balanceTransfer.getOrderNo());
                    //（1球童 2教练 3商家 4愿望 ）
                    flow.setFlowType(type);
                    flow.setCreateTime(new Date());
                    int i = flowService.insertSelective(flow,"");
                    System.out.println("充值转账流水记录:"+i);
                    MerchantOrder merchantOrder = merchantOrderService.selectByPrimaryKey(merchantOrderId);
                    if(null!=merchantOrder){
                        //转款成功后写入商家订单支付方式
                        merchantOrder.setChannel(recharge.getCharge().getChannel());
                        merchantOrderService.updateByPrimaryKeySelective(merchantOrder);
                    }
                    insertDebitRecord(balanceTransfer,zcAccount,recharge.getCharge().getChannel());
                    insertSystem_pay(balanceTransfer,type,oldMoney,recharge.getCharge().getChannel());
                }
            }else if(!"".equals(hopeId)&&null!=hopeId&&"4".equals(type)){
                System.out.println("《==============愿望充值转账处理==============》");

                /** 执行转款操作 */
                System.out.println("《==============愿望充值转账开始执行==============》");
                //String zzOrderNo = orderNoUtil.serialNoGenerate("06",phone);
                String zzOrderNo = recharge.getCharge().getOrderNo();
                BalanceTransfer balanceTransfer = pingUtil.balanceTransfer(recharge.getAmount(), "愿望转账支付", userId, "0", zzOrderNo);
                System.out.println("《《=================转账状态:"+balanceTransfer.getStatus()+"===============》》");
                if(balanceTransfer.getStatus().equals("succeeded")){
                    System.out.println("《《================容联云愿望推送消息开始================》》");
                    String receiver[] = {balanceTransfer.getRecipient()};
                    Map<String,Object>info = new HashMap<>();
                    info.put("type","1");
                    info.put("title","我支持了你的愿望");
                    info.put("subtitle","为你构筑愿望"+Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+""))+"元");
                    info.put("wishId",hopeId);
                    ServicePushUtils.servicePush(balanceTransfer.getUser(),"101",receiver,info.toString(),null);

                    System.out.println("《《================容联云愿望推送消息结束================》》");
                    /** 转出方账户详细 */
                    Account zcAccount = accountService.getUserBalance(Integer.valueOf(userId));
                    /** 流水 */
                    Flow flow = new Flow();
                    flow.setUserId(Integer.valueOf(userId));
                    flow.setMoney(Double.valueOf(AmountUtils.changeF2Y((long)balanceTransfer.getAmount())));
                    flow.setSequenceNumber(balanceTransfer.getOrderNo());
                    flow.setFlowType(type);
                    flow.setCreateTime(new Date());
                    int i = flowService.insertSelective(flow,"");
                    System.out.println("充值转账流水记录:"+i);
                    insertDebitRecord(balanceTransfer,zcAccount,recharge.getCharge().getChannel());
                    insertSystem_pay(balanceTransfer,type,oldMoney,recharge.getCharge().getChannel());
                    System.out.println("《==============愿望已实现余额开始更新==============》");
                    /** 查询愿望信息 */
                    Hope hope = hopeService.selectByPrimaryKey(Integer.valueOf(hopeId));
                    /** 更新愿望已实现金额 */
                    hope.setHopeRealMoney(hope.getHopeRealMoney()+Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")));
                    if(hope.getHopeRealMoney() >= hope.getHopeMoney()){
                    hope.setHopeState("2");
                        hope.setGetStauts("0");
                        hope.setRealizeTime(new Date());

                        /** 愿望实现发短信、推送 */
                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",hopeTitle);
                        mobMap.put("content",hopeContent);
                        mobMap.put("url",urlHead+wishDetails+hopeId);
                        MobPushUtil.MobPush(2,hopeContent,new String[]{hope.getUserId()+""},"1",JSON.toJSONString(mobMap));

                        InfromMsg infromMsg = new InfromMsg(null,Long.valueOf(hope.getUserId()),"0","12", "0", Long.valueOf(hopeId), new Date(),
                                new Date(),"sys",hope.getUserId()+"",hopeTitle,hopeContent,urlHead+wishDetails+hopeId,wish);

                        infromMsgService.insert(infromMsg);
                        MineVo mineVo = userService.getInfo(hope.getUserId());
                        SmsUtil.sendSMS(mineVo.getPhone(),hopeComeTrue);

                        List<Integer> userIds = userRoleService.selectUserIdByRoleId("12");
                        for (Integer goEasy: userIds
                                ) {
                            GoEasyUtil.pushMessage(goEasy+"","有用户愿望已实现,请及时处理!");
                        }

                    }
                    hopeService.updateByPrimaryKeySelective(hope);
                    /** 增加一条支持愿望记录 */
                    Hope_record hope_record = new Hope_record(null,Integer.valueOf(userId),Integer.valueOf(hopeId),
                            Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")),"2",new Date(),balanceTransfer.getOrderNo(),null,null);
                    hopeRecordService.insertSelective(hope_record);
                    System.out.println("《==============愿望充值转账执行结束==============》");
                }

            }else{//普通充值处理
                System.out.println("================普通充值处理=================");
                /** 用户余额查询 */
                Account account = accountService.getUserBalance(Integer.parseInt(userId));
                System.out.println("================用户账户查=================");
                /** 处理 */

                Double rechargeMoney = Double.valueOf(AmountUtils.changeF2Y(recharge.getAmount()+""));

                /** 用户流水信息更新 */
                Flow flow = new Flow();
                flow.setUserId(Integer.valueOf(userId));
                /*flow.setMoney(Double.valueOf(AmountUtils.changeF2Y(recharge.getAmount()+"")));*/
                flow.setMoney(rechargeMoney);
                flow.setSequenceNumber(recharge.getCharge().getOrderNo());
                flow.setFlowType("2222");
                flow.setCreateTime(new Date());
                /** 余额 */
                /*String moneyF2Y = AmountUtils.changeF2Y((long)recharge.getAmount());*/
                String moneyF2Y = AmountUtils.changeF2Y(recharge.getAmount()+"");
                flow.setBalance(account.getCzBalance()+Double.valueOf(moneyF2Y));
                int w = flowService.insertSelective(flow,recharge.getCharge().getChannel());
                System.out.println("================交易流水完成=================");
                System.out.println("普通充值流水记录:"+w);
                System.out.println(recharge.getAmount()+"---------");
                System.out.println(account.getCzBalance()+"-------");
                /** 用户充值余额 */
                accountService.updateUserCzBalance(Integer.valueOf(userId),(Double.valueOf(AmountUtils.changeF2Y(recharge.getAmount()+"")))+account.getCzBalance());
                /**用户充值记录*/
                com.ef.golf.pojo.Recharge userRecharge = new com.ef.golf.pojo.Recharge();
                userRecharge.setAccountId(account.getAccountId());
                userRecharge.setFlowId(flow.getFlowId());
                userRecharge.setMoney(rechargeMoney);
                userRecharge.setState(recharge.getSucceeded()==true?"2":"3");
                userRecharge.setOrderNo(recharge.getCharge().getOrderNo());
                userRecharge.setFlowId(flow.getFlowId());
                userRecharge.setPingRechargeId(recharge.getId());
                userRecharge.setCreateTime(new Date());
                userRecharge.setModifyTime(new Date());
                userRecharge.setChannel(recharge.getCharge().getChannel());

                int e = rechargeService.insertSelective(userRecharge,Integer.valueOf(recharge.getUser()));

                if(0<e){
                    String url = rechargeSuccessUrl;
                    Map<String,Object>map1 = new HashMap<>();
                    map1.put("rechargeId",recharge.getId());
                    map1.put("state","state");
                    String map = HttpUtil.httpPost(url, map1);//用队列？？？
                    Map<String,Object>resultMap = JSONObject.parseObject(map);
                    System.out.println("=？？？？？？？？？？？=========rechargeSuccess接口调用==================="+resultMap.toString());
                }

                System.out.println("普通充值记录:"+e);
            }

        }catch (Exception e){
            e.printStackTrace();
            /*new SystemException(Constant.ERR_SYSTEM);*/
        }finally {
            System.out.println("1111");
        }
        return 0;
        }


        /** 提现 */
        public void withdrawal(Event event){
            System.out.println("《《=================提现完成记录、更新=======================》》");
            try{
                /*Withdrawal withdrawal = (Withdrawal)event.getData().getObject();
                String userId = withdrawal.getUser();
                *//** 用户余额查询 *//*
                Account acct =  accountService.getUserBalance(Integer.valueOf(userId));
                *//**用户流水信息更新*//*
                Flow flow = new Flow();
                flow.setUserId(Integer.valueOf(userId));
                flow.setMoney(Double.valueOf(withdrawal.getAmount()));
                flow.setSequenceNumber(withdrawal.getOrderNo());
                flow.setFlowType("3333");
                flow.setCreateTime(new Date());
                *//** 余额 *//*
                String moneyF2Y = AmountUtils.changeF2Y((long)withdrawal.getAmount());
                flow.setBalance(acct.getSrBalance()+Double.valueOf(moneyF2Y));
                flowService.insertSelective(flow);
                *//**用户账户信息更新*//*
                Account account = new Account();
                account.setUserId(Long.valueOf(withdrawal.getUser()));
                *//** 取充值金额转元 *//*
                account.setBalance(acct.getSrBalance()+Integer.valueOf(moneyF2Y));
                account.setIsAlive("1");
                account.setCreateTime(new Date());
                account.setModifyTime(new Date());
                accountService.saveAccount(account);*/
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    /** 企业付 */
    public void SystemTransfer(Event event){
        System.out.println("《《=================企业付处理=======================》》");
        try{
            Transfer transfer = (Transfer) event.getData().getObject();

            Syslog syslog = new Syslog();
            String userId = transfer.getRecipient();

            System.out.println("企业付userId========"+userId);
            /** 用户余额查询 */
            Account account =  accountService.getUserBalance(Integer.valueOf(userId));
            /** 收入钱 */
            String moneyF2Y = AmountUtils.changeF2Y(transfer.getAmount()+"");
            /** 更新用户充值余额 红包10-->充值余额  教练结算-->收入  奖励金5-->收入*/
            if("5".equals(transfer.getDescription())||"6".equals(transfer.getDescription())) {
                int i = accountService.updateUserSrBalance(Integer.valueOf(userId), Double.valueOf(moneyF2Y) + account.getSrBalance());
                System.out.println("=================================企业付收入余额更新"+i);
                System.out.println("================================================企业付执行");
                syslog.setCreateDate(new Date());
                syslog.setBizType("update");
                StringBuilder sb = new StringBuilder();
                sb.append("用户id:").append(userId).append("账户id:")
                        .append(account.getAccountId()).append("description:")
                        .append(transfer.getDescription()).append("收入影响行:").append(i).append("金额:").append(transfer.getAmount());
                syslog.setLogTitle(sb.toString());
                syslog.setRequestUri(request.getRequestURI());
                syslog.setRemoteAddr(request.getRemoteAddr());
                syslog.setServerAddr(request.getLocalAddr());

            }else{
                int i = accountService.updateUserCzBalance(Integer.valueOf(userId), Double.valueOf(moneyF2Y) + account.getCzBalance());
                System.out.println("=================================企业付充值余额更新"+i);
                System.out.println("================================================企业付执行");
                syslog.setCreateDate(new Date());
                syslog.setBizType("update");
                StringBuilder sb = new StringBuilder();
                sb.append("用户id:").append(userId).append("账户id:")
                        .append(account.getAccountId()).append("description:")
                        .append(transfer.getDescription()).append("充值影响行:").append(i).append("金额:").append(transfer.getAmount());
                syslog.setLogTitle(sb.toString());
                syslog.setRequestUri(request.getRequestURI());
                syslog.setRemoteAddr(request.getRemoteAddr());
                syslog.setServerAddr(request.getLocalAddr());
            }
            syslogService.insertSelective(syslog);
            /** 企业账户转出记录 */
            SystemTransfer systemTransfer = new SystemTransfer();
            systemTransfer.setAmount((long)transfer.getAmount());
            systemTransfer.setCreateTime(new Date());
            systemTransfer.setDescription(transfer.getDescription());
            systemTransfer.setOrderNo(transfer.getOrderNo());
            systemTransfer.setStatus(transfer.getStatus());
            Date date = new Date();
            date.setTime(transfer.getTimeTransferred());
            systemTransfer.setTimeTransferred(date);
            systemTransfer.setTransactionNo(transfer.getTransaction_no());
            systemTransfer.setRecipient(transfer.getRecipient());
            systemTransferService.insertSelective(systemTransfer);
            /*if("01".equals(transfer.getDescription())){

            }*/
            System.out.println("《《=================企业付处理结束=======================》》");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


 /*   public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }*/



    /** 转账记录 */
    protected void insertSystem_pay(BalanceTransfer balanceTransfer,String type,Double oldMoney,String channel) throws Exception {
        /** 转账记录 */
        System_pay systemPay = new System_pay();
        systemPay.setUserId(Integer.valueOf(balanceTransfer.getUser()));
        systemPay.setTakeId(Integer.valueOf(balanceTransfer.getRecipient()));
        /** 分转元 */
        String moneyF2Y = AmountUtils.changeF2Y(balanceTransfer.getAmount().toString());
        systemPay.setMoney(Double.valueOf(moneyF2Y));
        systemPay.setCreateTime(new Date());
        systemPay.setModifyTime(new Date());
        systemPay.setType(type);
        systemPay.setState(balanceTransfer.getStatus().equals("succeeded")?"1":"2");
        systemPay.setOrderNo(balanceTransfer.getOrderNo());
        systemPay.setChannel(channel);

        int i = systemPayService.insertSelective(systemPay,balanceTransfer.getOrderNo(),oldMoney);
        System.out.println("充值转账记录:"+i);
    }
    /** 账户转出记录 余额更新 */
    protected void insertDebitRecord(BalanceTransfer balanceTransfer,Account account,String channel) throws Exception {
        DebitRecord debitRecord = new DebitRecord();
        debitRecord.setAccountId(account.getAccountId());
        debitRecord.setUserId(account.getUserId().intValue());
        debitRecord.setCzBalance(Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")));
        debitRecord.setPayForm("1");
        debitRecord.setCreateTime(new Date());
        debitRecord.setPingId(balanceTransfer.getOrderNo());
        debitRecord.setChannel(channel);
        /** 保存此次消费记录 */
        int i = debitRecordService.insertSelective(debitRecord);
        System.out.println("充值消费记录:"+i);
    }

}
