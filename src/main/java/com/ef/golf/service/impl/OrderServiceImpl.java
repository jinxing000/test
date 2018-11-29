package com.ef.golf.service.impl;

import com.alibaba.fastjson.JSON;
import com.ef.golf.common.Constant;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.MallException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.aspectj.Syslog;
import com.ef.golf.mapper.*;
import com.ef.golf.pojo.*;
import com.ef.golf.service.OrderService;
import com.ef.golf.util.*;
import com.ef.golf.vo.*;

import com.pingplusplus.model.Charge;
import com.pingplusplus.model.ChargeCollection;
import com.pingplusplus.model.Event;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

import java.util.*;
import java.util.Calendar;

/**
 * Created by xzw on 2017/9/23.
 */


@Repository
public class OrderServiceImpl implements OrderService {


    @Autowired
    ProductMapper productMapper;
;
    @Autowired
    GoodsMapper goodsMapper;
    @Resource
    private com.ef.golf.common.OrderStatus orderStatus;
    @Autowired
    Order_logMapper logMapper;
    @Autowired
    OrderItemMapper orderItemsMapper;
    @Autowired
    EsProductStoreMapper esProductStoreMapper;
    @Autowired
    StoreLogMapper storeLogMapper;
    @Resource
    private User_roleMapper user_roleMapper;
    @Resource
    private Site_orderMapper siteOrderMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private Coach_orderMapper coach_orderMapper;
    @Resource
    private  Course_orderMapper course_orderMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private EsOrderMapper esOrderMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private EsPaymentLogsMapper esPaymentLogsMapper;
    @Resource
    private PaymentDetailMapper paymentDetailMapper;
    @Resource
    private OrderLogMapper orderLogMapper;
    @Resource
    private TicketsMapper ticketsMapper;
    @Resource
    private DebitRecordMapper debitRecordMapper;
    @Resource
    private UserTicketMapper userTicketMapper;
    @Resource
    private InfromMsgMapper infromMsgMapper;
    @Resource
    private TradingMapper tradingMapper;
    @Resource
    private JiaoYiHuizongMapper jiaoYiHuizongMapper;

    @Autowired
    private CustomServiceMapper customServiceMapper;

    //教练预定
   public Order saveCoachOrder(Coach_order coach_order,String ticketId,String orderNo,String pingOrderId,Double amount,double totalPrice,double couponAmount) throws LoginException, SystemException {
            int accountID = getAccountId(Integer.valueOf(coach_order.getCreateUser()));

            Order order = new Order();
            order.setOrderNo(orderNo);
            order.setAccountId(accountID);
            order.setPingId(pingOrderId);
            order.setOrderType("2");        //教练订单
            order.setOrderState("14");        //待支付
            order.setOrderMoeny(amount);
            order.setCreateTime(coach_order.getCreateTime());
            order.setCreateUser(coach_order.getCreateUser());
            order.setModifyTime(coach_order.getModifyTime());
            order.setModifyUser(coach_order.getModifyUser());
            order.setIsDel(false);
            if (StringUtils.isNotEmpty(ticketId)&&!"0".equals(ticketId)) {
                order.setTicketId(Integer.valueOf(ticketId));
            }
            order.setAmount(totalPrice);
            order.setCouponAmount(couponAmount);
       if (orderMapper.insertSelective(order)<=0)
           throw new SystemException(Constant.ERR_SYSTEM-1);
       coach_order.setOrderId(Long.valueOf(order.getOrderId()));
       if(coach_orderMapper.insertSelective(coach_order)<=0)
           throw new SystemException(Constant.ERR_SYSTEM-1);
       return order;
    }
    /** 课程订单 */
    @Override
    public Order saveCourseOrder(Course_order course_order, String orderNo, String pingOrderId, Double amount,double totalPrice,double couponAmount,String ticketId) throws LoginException, SystemException {
       Integer accountId = getAccountId(Integer.valueOf(course_order.getCreateUser()));
            Order order = new Order();
            order.setOrderNo(orderNo);
            order.setAccountId(accountId);
            order.setPingId(pingOrderId);
            order.setOrderType("9");        //课程订单
            order.setOrderState("21");        //待支付
            order.setOrderMoeny(amount);
            order.setCreateTime(course_order.getCreateTime());
            order.setCreateUser(course_order.getCreateUser());
            order.setModifyTime(course_order.getModifyTime());
            order.setModifyUser(course_order.getModifyUser());
            order.setIsDel(false);
            order.setCouponAmount(couponAmount);
            order.setAmount(totalPrice);
            if(StringUtils.isNotBlank(ticketId)&&!"0".equals(ticketId)){
                order.setTicketId(Integer.valueOf(ticketId));
            }
        if (orderMapper.insertSelective(order)<=0)
            throw new SystemException(Constant.ERR_SYSTEM-1);
        course_order.setOrderId(Long.valueOf(order.getOrderId()));
        if(course_orderMapper.insertSelective(course_order)<=0)
            throw new SystemException(Constant.ERR_SYSTEM-1);
       return order;
    }
    /** 愿望订单 */
    @Override
    public Order saveHopeOrder(Integer uid,String pingOrderNo, String orderId,Double helpMoney) throws LoginException, SystemException {
        int accountID = getAccountId(uid);
            Order order = new Order();
            order.setOrderNo(orderId);
            order.setAccountId(accountID);
            order.setPingId(pingOrderNo);
            order.setOrderType("10");
            order.setOrderState("1");
            order.setOrderMoeny(helpMoney);
            order.setCreateUser(uid+"");
            order.setCreateTime(new Date());
            order.setModifyTime(new Date());
            order.setModifyUser(uid+"");
            order.setIsDel(false);
        if (orderMapper.insertSelective(order)<=0)
            throw new SystemException(Constant.ERR_SYSTEM-1);
        return order;
    }
    /** 商家订单 */
    @Override
    public Order saveMerchantOrder(Integer userId,String pingOrderNo, String pingId, Double amount, Double totalPrice, Double couponAmount, String ticketId) throws LoginException, SystemException {
             Integer accountId = getAccountId(userId);
             Order order = new Order();
             order.setOrderNo(pingOrderNo);
             order.setAccountId(accountId);
             order.setPingId(pingId);
             order.setOrderType("6");
             order.setOrderState("1");
             order.setOrderMoeny(amount);
             order.setCreateTime(new Date());
             order.setCreateUser(userId+"");
             order.setModifyUser(userId+"");
             order.setModifyTime(new Date());
             order.setIsDel(false);
             order.setCouponAmount(couponAmount);
             order.setTicketId(Integer.valueOf(ticketId));
             order.setAmount(totalPrice);
        if (orderMapper.insertSelective(order)<=0)
            throw new SystemException(Constant.ERR_SYSTEM-1);

       return order;
    }


    //场地预定
    public Order saveSiteOrder(Site_order siteOrder,String orderNo,String pingOrderId,double totalPrice,double couponAmount) throws SystemException, LoginException {

        int accountID = getAccountId(Integer.valueOf(siteOrder.getCreateUser()));

        //String orderNo=getSiteOrderNo(siteOrder.getCreateUser());

        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setAccountId(accountID);
        order.setPingId(pingOrderId);
        order.setOrderType("1");        //场地订单
        order.setOrderState("1");        //待支付
        order.setOrderMoeny(siteOrder.getRealPayMoney());
        order.setCreateTime(new Date());
        order.setCreateUser(siteOrder.getCreateUser());
        order.setModifyTime(new Date());
        order.setModifyUser(siteOrder.getModifyUser());
        order.setIsDel(false);
        order.setTicketId(siteOrder.getTicketId());
        order.setCouponAmount(couponAmount);
        order.setAmount(totalPrice);
        if (orderMapper.insertSelective(order)<=0)
            throw new SystemException(Constant.ERR_SYSTEM-1);
        siteOrder.setOrderId(order.getOrderId());
        if(siteOrderMapper.insertSelective(siteOrder)<=0)
            throw new SystemException(Constant.ERR_SYSTEM-1);
        return order;
    }

    private int getAccountId(Integer uid) throws LoginException {
        Integer accountID =  accountMapper.getAccountIdByUid(uid);
        if(accountID==null)
            throw new LoginException(Constant.ERR_USER - 3);
        return accountID;
    }

    public SiteOrderVo getSiteOrder(Order order) throws LoginException {
        int accountID = getAccountId(Integer.valueOf(order.getCreateUser()));
        order.setAccountId(accountID);
        return orderMapper.getSiteOrder(order);
    }

    public List<OrderVo> getMyAllOrder( Order order) {
        List<OrderVo> myOrderList= siteOrderMapper.getMyAllOrderListPage(order);
        return myOrderList;
    }

    @Value("${shopticketId}")
    private String shopticketId;


    @Value("${couponTitle}")
    private String couponTitle;
    @Value("${couponContent}")
    private String couponContent;
    @Value("${couponList}")
    private String couponList;
    @Value("${ticketIco}")
    private String ticketIco;
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

    @Override
    @Syslog(title = "update",methods = "updateByOrderNo",description = "webHooks更新订单")
    public int updateByOrderNo(Event event){
        int count=1;
        try{
            //获取Charge对象
            com.pingplusplus.model.Order order=(com.pingplusplus.model.Order) event.getData().getObject();
            ChargeCollection chargeCollection=order.getCharges();
            List<Charge> chargeList=chargeCollection.getData();
            String orderNo=null;
            int num=0;

            for (Charge charge:chargeList) {
                if (charge.getPaid()) {
                    orderNo = charge.getOrderNo();
                    /** 拿到支付方式 */
                    String channel = charge.getChannel();
                    /** 取userId查账户余额 */
                    Integer userId = Integer.valueOf(order.getUid());
                    Account account = accountMapper.getUserBalance(userId);
                    Map<String, Object> map = new HashMap<>();
                    /*try {*/
                    if ("balance".equals(channel)) {
                        /** 判断从哪个账户扣钱 */
                        Double payMoney = Double.valueOf(AmountUtils.changeF2Y(charge.getAmount() + ""));
                        DebitRecord debitRecord = new DebitRecord();
                        if (account.getCzBalance() >= payMoney) {//充值余额
                            debitRecord.setCzBalance(payMoney);
                            /** 更新预存余额... */
                            map.put("userId", userId);
                            map.put("czBalance", account.getCzBalance() - payMoney);
                            accountMapper.updateUserCzBalance(map);
                        } else if (account.getCzBalance() < payMoney && account.getCzBalance() + account.getSrBalance() > payMoney) {//充值余额-->充值余额+收入余额
                            debitRecord.setCzBalance(account.getSrBalance());
                            debitRecord.setSrBalance(payMoney - account.getCzBalance().intValue());
                            /** 更新预存、收入余额 */
                            map.put("userId", userId);
                            map.put("czBalance", 0.00);
                            accountMapper.updateUserCzBalance(map);
                            map.put("userId", userId);
                            map.put("srBalance", account.getCzBalance() + account.getSrBalance() - payMoney);
                            accountMapper.updateUserSrBalance(map);
                        } else if (account.getCzBalance() < payMoney && account.getCzBalance() + account.getSrBalance() < payMoney &&
                                account.getCzBalance() + account.getSrBalance() + account.getZsBalance() > payMoney) {//充值余额-->充值余额+收入余额-->充值余额+收入余额+赠送余额
                            debitRecord.setCzBalance(account.getCzBalance());
                            debitRecord.setSrBalance(account.getSrBalance());
                            debitRecord.setZsBalance(payMoney - account.getCzBalance().intValue() - account.getSrBalance().intValue());
                            /** 更新预存、收入、赠送余额 */
                            map.put("userId", userId);
                            map.put("czBalance", 0.00);
                            accountMapper.updateUserCzBalance(map);
                            map.put("userId", userId);
                            map.put("srBalance", 0.0);
                            accountMapper.updateUserSrBalance(map);
                            map.put("userId", userId);
                            map.put("zsBalance", account.getCzBalance() + account.getSrBalance() + account.getZsBalance() - payMoney);
                            accountMapper.updateUserZsBalance(map);
                        }
                        debitRecord.setAccountId(account.getAccountId());
                        debitRecord.setUserId(userId);
                        debitRecord.setPayForm("0");
                        debitRecord.setCreateTime(new Date());
                        debitRecord.setPingId(charge.getOrderNo());
                        /** 保存此次消费记录 */
                        int h = debitRecordMapper.insertSelective(debitRecord);
                        System.out.println("流水记录:" + h);
                    }
                    String moneyF2Y = AmountUtils.changeF2Y(charge.getAmount() + "");
                    /** 交易记录 */
                    Trading trading = new Trading();
                    trading.setCreateTime(new Date());
                    trading.setModifyTime(new Date());
                    trading.setMoney(Double.valueOf(moneyF2Y));
                    trading.setOrderId(orderMapper.selectOrderIdByPingId(order.getId()));
                    trading.setUserId(userId.longValue());
                    int j = tradingMapper.insertSelective(trading);
                    System.out.println("交易记录:" + j);
                    /** 流水 */
                    Flow flow = new Flow();
                    flow.setUserId(userId);
                    flow.setMoney(Double.valueOf(moneyF2Y));
                    flow.setSequenceNumber(order.getMerchantOrderNo());
                    flow.setFlowType("1111");//订单支付
                    flow.setCreateTime(new Date());
                    flow.setBalance(account.getSrBalance() + Double.valueOf(moneyF2Y));
                    JiaoYiHuizong jiaoYiHuizong = new JiaoYiHuizong(null, flow.getUserId(), "1", flow.getCreateTime(), 2, flow.getMoney(), flow.getSequenceNumber(), null, channel);
                    jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
                    //根据ping++订单支付状态更新本地系统订单支付状态
                    com.ef.golf.pojo.Order myOrder = orderMapper.getOrderByNo(orderNo);
                    List<CustomService>customServiceAll = customServiceMapper.findCustomServiceAll(null);


                    /** 推送 */
                    if ("2".equals(myOrder.getOrderType())) {
                        System.out.println("======================给教练推送========================");

                        Map<String, Object> mobMap = new HashMap<>();
                        mobMap.put("title", coachSuccessTitle);
                        mobMap.put("content", coachSuccessContent);
                        mobMap.put("url", urlHead + workOrders);
                        Coach_order coach_order = coach_orderMapper.getCoachOrderDetails(myOrder.getOrderId());
                        MobPushUtil.MobPush(2, coachSuccessContent, new String[]{coach_order.getCoachId().toString()}, "1", JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null, Long.valueOf(myOrder.getCreateUser()), "0", "5", "9", myOrder.getOrderId().longValue(), new Date(),
                                new Date(), myOrder.getCreateUser(), coach_order.getCoachId().toString(), coachSuccessTitle, coachSuccessContent, urlHead + workOrders, reserveCoachOrder);
                        infromMsgMapper.insert(infromMsg);
                        MineVo mineVo = userMapper.getInfo(coach_order.getCoachId());
                        SmsUtil.sendSMS(mineVo.getPhone(), templateIdCoachPay);
                    } else if ("9".equals(myOrder.getOrderType())) {
                        System.out.println("========================课程给教练推送========================");

                        Map<String, Object> mobMap = new HashMap<>();
                        mobMap.put("title", courseSuccessTitle);
                        mobMap.put("content", courseSuccessContent);
                        mobMap.put("url", urlHead + workOrders);
                        Course_order course_order = course_orderMapper.getCourseOrderDetails(myOrder.getOrderId());
                        MobPushUtil.MobPush(2, courseSuccessContent, new String[]{course_order.getCoachId().toString()}, "1", JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null, Long.valueOf(myOrder.getCreateUser()), "0", "6", "14", myOrder.getOrderId().longValue(), new Date(),
                                new Date(), myOrder.getCreateUser(), course_order.getCoachId().toString(), courseSuccessTitle, courseSuccessContent, urlHead + workOrders, reserveCourseOrder);
                        infromMsgMapper.insert(infromMsg);
                        MineVo mineVo = userMapper.getInfo(course_order.getCoachId());
                        SmsUtil.sendSMS(mineVo.getPhone(), templateIdCoursePay);
                    } else if ("1".equals(myOrder.getOrderType())) {
                        List<Integer> userIds = user_roleMapper.selectHuserIdByPermission("场地订单");
                        for (Integer goEasy : userIds
                                ) {
                            GoEasyUtil.pushMessage(goEasy + "", "有客人订场,请及时处理!");
                        }

                        SiteOrderListVo solv = siteOrderMapper.getSiteOrderDetails(myOrder.getOrderId());
                        if(null!=solv){
                            String [] sms = {solv.getSiteName(),solv.getPlayName(),solv.getContactsPhone(),solv.getHeadcount()
                                    ,solv.getPlayDate()+" "+solv.getPlayTime(),solv.getOrderNo(),myOrder.getOrderMoeny()+""};
                            for (CustomService cs : customServiceAll){
                                if("1".equals(cs.getType().toString())){
                                    SmsUtil.sendSMS(cs.getPhone(),cs.getTemplateid(),sms);
                                }
                            }
                        }
                    } else if ("7".equals(myOrder.getOrderType())) {
                        GoEasyUtil.pushMessage("7-shop", "有新的商城订单,请及时处理!");
                        String [] sms = {myOrder.getOrderNo(),myOrder.getOrderMoeny()+""};
                        for (CustomService cs : customServiceAll){
                            if("7".equals(cs.getType().toString())){
                                SmsUtil.sendSMS(cs.getPhone(),cs.getTemplateid(),sms);
                            }
                        }
                    }//  id/shop

                    String type = myOrder.getOrderType();
                    String status = (String) orderStatus.getOrderStatus().get(type);
                    //String orderNo,String status,String tradeNo,String payment_account
                    String tradeNo = order.getChargeEssentials().getTransactionNo();
                    //String channel=order.getChargeEssentials().getChannel();
                    String payment_account = "";//暂时为空


                    /*Order order = orderMapper.getOrderByNo(orderNo);*/
                    //更新主订单状态
                    count = orderMapper.updateByOrderNo(orderNo, status, channel);

                    if ("7".equals(myOrder.getOrderType())) {
                        //根据订单号查询订单类型，如果是商城的订单 还需要把es_order的状态
                        //更新status字段为2，支付状态pay_status为 2


                        //此处更新商城主订单 es_order 实付金额
                        Integer orderId = esOrderMapper.getShopOrderIdByMainOrderNo(orderNo);


                        //此处是该笔订单赠券信息
                        List<ShopSendTicketVo> shopSendTicketVos = esOrderMapper.getShopOrderSendTicket(orderId + "");

                        //查询ticket模板id
                        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
                        //调用赠券计算工具
                        Map<String, Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList, shopSendTicketVos);
                        shopSendTicket(sendMap, myOrder.getCreateUser());


                        if (orderId != null) {
                      /* EsOrder esOrder=new EsOrder();
                       esOrder.setOrderId(orderId);
                       esOrder.setStatus(2);
                       esOrder.setPayStatus(2);
                       count=esOrderMapper.updateByPrimaryKey(esOrder);*/
                            this.paySuccess(orderId, tradeNo, payment_account, channel);
                        }
                    }
                    if("balance".equals(charge.getChannel()))
                        break;
                }
            }

        }catch (Exception ex){
            count=0;
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
        }
        return count;
    }


    public void shopSendTicket(Map<String,Object>map,String userId){

        List<UserTicket>ticketsList = new ArrayList<>();
        //当天0点0分0秒
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(new Date());
        beginTime.set(Calendar.HOUR_OF_DAY, 0);
        beginTime.set(Calendar.MINUTE, 0);
        beginTime.set(Calendar.SECOND, 0);
        //三个月后的
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime.getTime());
        calendar.add(Calendar.MONTH,3);

            List<ShopSendTicketVo>list = (List<ShopSendTicketVo>) map.get("sstv");

            for (ShopSendTicketVo sstv:list
                 ) {
                for (int i=0;i<sstv.getCount();i++){

                    UserTicket userticket = new UserTicket();
                        Date date = new Date();
                        userticket.setUserId(Long.valueOf(userId));
                        userticket.setCreateTime(date);
                        userticket.setModifyTime(date);
                        userticket.setCreateUser("0");
                        userticket.setModifyUser("0");
                        userticket.setState(3);
                        userticket.setEffectiveTime(beginTime.getTime());
                        userticket.setExpiryTime(calendar.getTime());
                        userticket.setTicketId(Integer.valueOf(sstv.getTicketId()));
                        userticket.setLocation(1);
                    ticketsList.add(userticket);
                }
            }
            if(ticketsList.size()>0){
                int i = userTicketMapper.insertUserTickets(ticketsList);
                if(i>0){
                    Map<String,Object>mobMap = new HashMap<>();
                    mobMap.put("title",couponTitle);
                    mobMap.put("content",couponContent);
                    mobMap.put("url",urlHead+couponList);
                    MobPushUtil.MobPush(2,couponContent,new String[]{userId},"1", JSON.toJSONString(mobMap));
                    InfromMsg infromMsg = new InfromMsg(null,Long.valueOf(0),"0","15", "28", Long.valueOf(userId), new Date(),
                            new Date(),"0",userId,couponTitle,couponContent,urlHead+couponList,ticketIco);
                    infromMsgMapper.insertSelective(infromMsg);
                }
            }

    }

    public void paySuccess(Integer orderId, String tradeno, String payment_account,String channel) {
        EsOrder order  = esOrderMapper.selectByPrimaryKey(orderId);
        if (order.getPayStatus() == OrderStatus.PAY_NO) {
            if( order.getPayStatus().intValue()== OrderStatus.PAY_YES ){ //如果是已经支付的，不要再支付
                return ;
            }

            this.payConfirm(order);

            try{
                //添加支付详细对象 @author LiFenLong
                Double needPayMoney= order.getNeedPayMoney(); //在线支付的金额

                System.out.println("needPayMoney=========================="+needPayMoney);
                PaymentLog paymentLog = esPaymentLogsMapper.getPaymentLogId(orderId);
                System.out.println("paymentLog==========="+paymentLog);
                PaymentDetail paymentdetail=new PaymentDetail();

                paymentdetail.setAdmin_user("系统");
                paymentdetail.setPay_date(new Date().getTime());
                paymentdetail.setPay_money(needPayMoney);
                paymentdetail.setPayment_id(paymentLog.getPayment_id());
                paymentDetailMapper.insertSelective(paymentdetail);
                System.out.println("paymentDetailMapper=======插入");
              //  PayCfg payCfg = this.paymentManager.get(pluginId);

                //修改订单状态为已付款付款
               /* this.daoSupport.execute("update es_payment_logs set paymoney=paymoney+? , pay_method=? ,trasaction_id = ? where payment_id=?",
                        needPayMoney,payCfg.getName(),tradeno,paymentid);*/

                paymentLog.setPaymoney(paymentLog.getPaymoney()+needPayMoney);
                paymentLog.setPay_method(channel);//支付渠道
                paymentLog.setTrasaction_id(tradeno);//支付渠道对应的交易流水号

                esPaymentLogsMapper.update(paymentLog);

                //更新订单的已付金额
                /*this.daoSupport.execute("update es_order set paymoney=paymoney+?,payment_account=?,payment_id=?,payment_name=?,payment_type=? where order_id=?",
                        needPayMoney,payment_account,payCfg.getId(),payCfg.getName(),payCfg.getType(),order.getOrder_id());*/
                order.setPaymoney(order.getPaymoney()+needPayMoney);
                order.setPaymentAccount(payment_account);
                order.setPaymentType(channel);
                String name = "";
                int id = 0;
                if("wx".equals(channel)){
                    name = "微信支付";
                    id = 2;
                }else if("alipay".equals(channel)){
                    name = "支付宝支付";
                    id = 1;
                }else if("upacp".equals(channel)){
                    name = "银联支付";
                    id = 4;
                }else if("balance".equals(channel)){
                    name = "余额支付";
                    id = 3;
                }
                order.setPaymentName(name);
                order.setPaymentId(id);
                esOrderMapper.updateByPrimaryKey(order);

            }catch(Exception e){
                e.printStackTrace();

            }
        }

    }
    //
    public EsOrder payConfirm(EsOrder order){
        /**
         * 计算订单的状态，如果累计支付金额等于订单金额，则为已确认付款
         */
        int payStatus = OrderStatus.PAY_YES;// 已付款
        int orderStatus = OrderStatus.ORDER_PAY;

        //判断是否已收货，如果已经收货，则更新订单状态为已完成
        if(order.getShipStatus() == OrderStatus.SHIP_ROG){
            orderStatus =OrderStatus.ORDER_COMPLETE;
        }

      //  String  sql = "update es_order set status="+orderStatus+",pay_status="+payStatus+"  where order_id=?";
        order.setStatus(orderStatus);
        order.setPayStatus(payStatus);
       esOrderMapper.updateByPrimaryKey(order);



        String opuser = "系统";
        /*if(adminUser!=null){
            opuser  = adminUser.getUsername()+"["+adminUser.getRealname()+"]";
        }*/

      /*  sql="update es_payment_logs set status=2,pay_date=?,admin_user=? where order_id=?";//核销应收
        this.daoSupport.execute(sql,DateUtil.getDateline(),opuser,order.getOrder_id());*/

        PaymentLog paymentLog=new PaymentLog();

        System.out.println("payConfirm  的id========"+order.getOrderId());

        paymentLog.setOrder_id(order.getOrderId());
        paymentLog.setStatus(2);
        paymentLog.setPay_date(new Date().getTime());
        paymentLog.setAdmin_user(opuser);

        esPaymentLogsMapper.update(paymentLog);



       /* if(adminUser!=null){
            orderManager.addLog(orderId, "确认付款");
        }else{
            orderManager.addLog(orderId, "确认付款", "系统");
        }*/
       OrderLog orderLog=new OrderLog();
       orderLog.setOrderId(order.getOrderId());
       orderLog.setMessage("确认付款");
       orderLogMapper.insertSelective(orderLog);
        return order;

    }
    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public String getPingOrderId(String orderId) {
        return orderMapper.getPingOrderId(orderId);
    }

    @Override
    public int getUserAccountId(String orderId) {
        return orderMapper.getUserAccountId(orderId);
    }

    @Override
    public Integer selectOrderIdByPingId(String pingId) {
        return orderMapper.selectOrderIdByPingId(pingId);
    }

    @Override
    public Order getOrderByNo(String orderNo) {
        return orderMapper.getOrderByNo(orderNo);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        int count=0;
        try{


        //对于其他模块的订单只修改ef_order中的状态即可，但是对于商城的订单还需要修改es_order 的状态，所以需要在一起处理控制在事务内
        //商城的订单取消支付的ef_order 对应的状态是34,  es_order对应的状态是6
        if("7".equals(record.getOrderType())){//商城的主订单
            //根据主订单的order_no 查询对应的商城订单的order_id
            Integer orderId= esOrderMapper.getShopOrderIdByMainOrderNo(record.getOrderNo());
            //同时把货品的可使用库存恢复，因为下单时把库存给占用了
            //根据主订单的
            count= cancel(orderId, "用户取消",Integer.parseInt(record.getCreateUser()));
           /* EsOrder esOrder=new EsOrder();


            esOrder.setOrderId(orderId);
            esOrder.setStatus(6);//取消支付
          count=  esOrderMapper.updateByPrimaryKey(esOrder);*/

        }

            //更新主订单的状态
            count =orderMapper.updateByPrimaryKeySelective(record);
        }catch (Exception ex){
            count=0;
            ex.printStackTrace();
        }

        return count;
    }

    public int cancel(Integer order_id, String reason,Integer userId) throws MallException {
        int count=0;
       EsOrder order = esOrderMapper.selectByPrimaryKey(order_id);
        if(order == null){
            throw new MallException(Constant.GOODSORDER_NOT_EXIST);
        }
        if(order.getStatus() == null || order.getStatus().intValue()>OrderStatus.ORDER_PAY){
            throw new MallException(Constant.GOODSORDER_CANNOT_CANCLE);
        }




        User user= userMapper.selectByPrimaryKey(new Long(userId));
        //记录日志
        if(user==null){
            count=this.addLog(order.getOrderId(), "取消订单", "system");
        }else{
            count=this.addLog(order.getOrderId(), "取消订单", user.getNickname());
        }

        count=this.onCanel(order);
        order.setStatus(OrderStatus.ORDER_CANCELLATION);
        order.setCancelReason(reason);
        count= esOrderMapper.updateByPrimaryKeySelective(order);
        return count;
    }

    private int  onCanel(EsOrder order) {
        int count=0;
        int orderid  = order.getOrderId();
        List<OrderItem> itemList   = this.orderItemsMapper.getItemList(orderid);
        for (OrderItem orderItem : itemList) {
            int goodsid  = orderItem.getGoodsId();
            int productid  = orderItem.getProductId();
            int num = orderItem.getNum();
            int depotid = order.getDepotid();
            String name = orderItem.getName();

            //记录库存日志
            StoreLog storeLog = new StoreLog();
            storeLog.setDateline(DateUtil.getDateline());
            storeLog.setDepotType(0);
            storeLog.setDepotid(depotid);
            storeLog.setGoodsid(goodsid);
            storeLog.setGoodsname(name);
            storeLog.setNum(0);
            storeLog.setEnableStore(num);
            storeLog.setRemark("取消订单["+order.getSn()+"],增加可用库存");
            storeLog.setOpType(StoreLogType.cancel_order.getType());
            storeLog.setProductid(productid);
            storeLog.setUserid(0);
            storeLog.setUsername("系统");
            count=this.storeLogMapper.insertSelective(storeLog);

            count= this.increaseEnable(goodsid, productid, depotid, num);

        }
        return count;
    }

    public int  addLog(Integer order_id, String message, String op_name) {
        int count=0;
        Order_log orderLog = new Order_log();
        orderLog.setMessage(message);
        orderLog.setOpId(0);
        orderLog.setOpName(op_name);
        orderLog.setOpTime(DateUtil.getDateline());
        orderLog.setOrderId(order_id);
        count=logMapper.insertSelective(orderLog);
        return count;
    }
    public int  increaseEnable(int goodsid, int productid, int depotid,int num) {
        int count=0;
        /**
         * 多店系统不维护分仓库存,  但是自营又需要维护分仓库存
         */
//		if(!"b2b2c".equals(EopSetting.PRODUCT)){
        EsProductStore esProductStore=esProductStoreMapper.selectProductStore(depotid,goodsid,productid);
       /* esProductStore.setDepotid(depotid);
        esProductStore.setGoodsid(goodsid);
        esProductStore.setProductid(productid);*/

        if(this.checkExists(goodsid, depotid)){

            esProductStore.setEnableStore(esProductStore.getEnableStore()+num);
            count= esProductStoreMapper.updateByPrimaryKeySelective(esProductStore);
            //this.daoSupport.execute("update es_product_store set enable_store =enable_store+? where goodsid=? and depotid=? and productid=?", num,goodsid,depotid,productid);
        }else{
            esProductStore.setEnableStore(num);
            count= esProductStoreMapper.insertSelective(esProductStore);
            //this.daoSupport.execute("insert into es_product_store(goodsid,productid,depotid,enable_store)values(?,?,?,?)",goodsid,productid, depotid,num);

        }
//		}

        // this.daoSupport.execute("update es_product set enable_store=enable_store+? where product_id=?", num,productid);
        Product product=productMapper.selectByPrimaryKey(productid);

        product.setEnableStore(product.getEnableStore()+num);

        //更新
        count= productMapper.updateByPrimaryKeySelective(product);


        Goods goods=goodsMapper.selectByPrimaryKey(goodsid);

        goods.setEnableStore(goods.getEnableStore()+num);
        goods.setMarketEnable(1);//自动上架

        count= goodsMapper.updateByPrimaryKeySelective(goods);

       /* this.daoSupport.execute("update es_goods set enable_store=enable_store+? where goods_id=?", num,goodsid);

        this.daoSupport.execute("update es_goods set market_enable=? where goods_id=?", 1,goodsid);//自动上架*/
    return count;
    }
    /**
     * 查询某个仓库的商品库存是否存在
     * @param goodsid
     * @param depotid
     * @return
     */
    private boolean checkExists(int goodsid,int depotid){
        int count=esProductStoreMapper.selectAmountByGoodsIdAndDepotId(goodsid,depotid);

        return count==0?false:true;
    }
    @Override
    public WorkOrderDeatilsVo getOrderDetails(String orderNo,String orderType) {
        Map<String,Object> map = new HashMap<>();
        map.put("orderNo",orderNo);
        map.put("orderType",orderType);
        return orderMapper.getOrderDetails(map);
    }

    @Override
    public int updateWorkOrderStauts(Map map) {
        return orderMapper.updateWorkOrderStauts(map);
    }

    @Override
    public int delWorkOrder(Map map) {
        return orderMapper.delWorkOrder(map);
    }

    @Override
    public Order selectByPrimaryKey(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    private static String getSiteOrderNo(String phone){
       String dateTime = String.valueOf(new Date().getTime());
              //01 - 场地类订单 + 时间戳 + 下单用户手机后四位
       String dateStr = dateTime.substring(2, dateTime.length());
       String phoneStr = phone.substring(7,phone.length());
       StringBuffer sb = new StringBuffer("01");

       return String.valueOf(sb.append(dateStr).append(phoneStr));
   }

    @Override
    public int updateOrderIsDel(Integer orderId) {

        int count=1;

        try{
        //更新主订单的状态为删除
          count=  orderMapper.updateOrderIsDel(orderId);

        //根据orderId查询主订单查询order_no

      Order order=  orderMapper.selectByPrimaryKey(orderId);

        //根据order_no查询es_order商城订单
        Integer esOrderId=esOrderMapper.getShopOrderIdByMainOrderNo(order.getOrderNo());

        //更新商城订单disabled为1    0代表未删除   1代表删除
        EsOrder esOrder=new EsOrder();
        esOrder.setOrderId(esOrderId);
        esOrder.setDisabled("1");
        esOrderMapper.updateByPrimaryKey(esOrder);

        }catch (Exception ex){
            count=0;
            ex.printStackTrace();
        }
        return count;
    }

    @Override
    public int confirmGetOrder(Integer efOrderId, Integer shopOrderId) {
        int count=1;
        try{
            //更新主订单的状态为31
            Order order=new Order();
            order.setOrderId(efOrderId);
            order.setOrderState("31");
            count=orderMapper.updateByPrimaryKeySelective(order);
            //更新商城订单状态，发货状态
            EsOrder esOrder=new EsOrder();
            esOrder.setOrderId(shopOrderId);
            esOrder.setStatus(5);
            esOrder.setShipStatus(2);//收货状态为2
            count=esOrderMapper.updateByPrimaryKeySelective(esOrder);
        }catch (Exception ex){
            count=0;
            ex.printStackTrace();
        }

        return count;
    }

    @Override
    public int updateCaddieOrderState() {
        return orderMapper.updateCaddieOrderState();
    }

    @Override
    public int updateSiteOrderState() {
        return orderMapper.updateSiteOrderState();
    }

    @Override
    public int chaoshiOrder() {
        int count = 0;
        try{

            List<Order>list = orderMapper.chaoshiOrder();
            Date date = new Date();
            for (Order o:list) {
                if("1".equals(o.getOrderType())){
                    o.setOrderState("6");
                    o.setModifyTime(date);
                    o.setModifyUser("system");
                }else if("2".equals(o.getOrderType())){
                    o.setOrderState("20");
                    o.setModifyTime(date);
                    o.setModifyUser("system");
                }else if("9".equals(o.getOrderType())){
                    o.setOrderState("27");
                    o.setModifyTime(date);
                    o.setModifyUser("system");
                }else if("7".equals(o.getOrderType())){
                    o.setOrderState("34");
                    o.setModifyUser("system");
                    o.setModifyTime(date);
                    //根据主订单的order_no 查询对应的商城订单的order_id
                    Order order=  orderMapper.selectByPrimaryKey(o.getOrderId());
                    Integer orderId= esOrderMapper.getShopOrderIdByMainOrderNo(order.getOrderNo());
                    //同时把货品的可使用库存恢复，因为下单时把库存给占用了
                    //根据主订单的
                    cancel(orderId, "用户取消",Integer.parseInt("0"));
                }
                orderMapper.updateByPrimaryKeySelective(o);
                count++;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int findByTickId(String ticketid) {
        return orderMapper.findByTickId( ticketid);
    }

    @Override
    public int siteOrderTuiDin(Integer orderId, String userId) throws SystemException {

        Date date = new Date();
        com.ef.golf.pojo.Order order = orderMapper.selectByPrimaryKey(orderId);
        SiteOrderListVo siteOrderListVo= siteOrderMapper.getSiteOrderDetails(order.getOrderId());
        if(!DateUtil.checkTime(siteOrderListVo.getPlayDate()+" "+siteOrderListVo.getPlayTime(),86400000)){
            throw new SystemException(Constant.ERR_TIME_TUIDIN);
        }

        order.setOrderId(orderId);
        order.setOrderState("37");
        order.setModifyTime(date);
        order.setModifyUser(userId);
        int i = orderMapper.updateByPrimaryKeySelective(order);

        return i;
    }
}
