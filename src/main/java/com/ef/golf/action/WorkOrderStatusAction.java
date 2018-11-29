package com.ef.golf.action;


import com.alibaba.fastjson.JSON;
import com.ef.golf.common.Constant;
import com.ef.golf.common.OrderStatus;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.DateUtil;
import com.ef.golf.util.SmsUtil;
import com.ef.golf.vo.MineVo;
import com.pingplusplus.model.OrderRefund;
import com.pingplusplus.model.OrderRefundCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工单状态更新查询
 * 1.待支付 2.球位确认中 3.确认成功 4.待评价 5.已完成 6.取消支付
 * 7.已退订 8.待回应 9.成功预约 10.已取消 11.未接受
 * 12.待发货 13.已发货  14.申请退货中 15.待寄件 16.退货中 17.已退货
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WorkOrderStatusAction extends AbstractService {

    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;
    @Resource
    private OrderStatus orderStatus;
    @Resource
    private Qiutong_orderService qiutongOrderService;
    @Resource
    private CourseService courseService;
    @Resource
    private CourseSignService courseSignService;
    @Resource
    private CoachOrderService coachOrderService;
    @Resource
    private InfromMsgService infromMsgService;
    @Resource
    private DebitRecordService debitRecordService;
    @Resource
    private AccountService accountService;

    //@NotNull(message = "当前用户ID必须")
    private Integer userId;
    //@NotNull(message = "订单id必须")
    private Integer orderId;
    //@NotNull(message = "订单编号必须")
    private String orderNo;
    private String stauts;//1-->接收,2-->不接受,3-->删除,4-->取消预约,5-->确认赴约,6-->上课签到(无用)
    private String appointTime;//打球时间 时分


    /* @NotNull(message = "当前处理工单人uid不可为空!")
    private String uid;*/
   /* @NotNull(message = "用户类型必须!教练，2/球童，3,-1,")
    private String userType;
     private String coachWorkType;//教练工单类型 1,陪打 2，教学
    */

    @Value("${urlHead}")
    private String urlHead;
    @Value("${caddieYesTitle}")
    private String caddieYesTitle;
    @Value("${caddieYesContent}")
    private String caddieYesContent;
    @Value("${orders}")
    private String orders;
    @Value("${caddieNoTitle}")
    private String caddieNoTitle;
    @Value("${caddieNoContent}")
    private String caddieNoContent;
    @Value("${cancelTitle}")
    private String cancelTitle;
    @Value("${cancelContent}")
    private String cancelContent;
    @Value("${coachYesTitle}")
    private String coachYesTitle;
    @Value("${coachYesContent}")
    private String coachYesContent;
    @Value("${coachNoTitle}")
    private String coachNoTitle;
    @Value("${coachNoContent}")
    private String coachNoContent;
    @Value("${successYesTitle}")
    private String successYesTitle;
    @Value("${successYesContent}")
    private String successYesContent;
    @Value("${courseYesTitle}")
    private String courseYesTitle;
    @Value("${courseYesContent}")
    private String courseYesContent;
    @Value("${courseNoTitle}")
    private String courseNoTitle;
    @Value("${courseNoContent}")
    private String courseNoContent;
    @Value("${reserveCaddieOrder}")
    private String reserveCaddieOrder;
    @Value("${reserveCourseOrder}")
    private String reserveCourseOrder;
    @Value("${reserveCoachOrder}")
    private String reserveCoachOrder;

    @Value("${templateId.course.coach}")
    private String templateIdCourseCoach;
    @Value("${templateId.course.no}")
    private String templateIdCourseNo;
    @Value("${templateId.course.yes}")
    private String templateIdCourseYes;
    @Value("${templateId.coach.coach}")
    private String templateIdCoachCoach;
    @Value("${templateId.coach.yes}")
    private String templateIdCoachYes;
    @Value("${templateId.caddie.yes}")
    private String templateIdCaddieYes;
    @Value("${templateId.caddie.no}")
    private String templateIdCaddieNo;
    @Value("${templateId.coach.yesYue}")
    private String templateIdCoachYesYue;
    @Value("${templateId.caddie.cancel}")
    private String templateIdCaddieCancel;


    @Override
    public Object doService() throws Exception {
        OrderRefundCollection orderRefund=null;
        /** 获取orderState */
        /*orderService.getOrderByNo(orderNo);*/
        Order order = orderService.selectByPrimaryKey(orderId);
        String type = order.getOrderType();
        MineVo mineVo1= userService.getInfo(userId);
        String userType = mineVo1.getUserType();
        //String orderState = (String) orderStatus.getOrderStatus().get(type);
        System.out.println(order.getOrderState());
        String orderState = order.getOrderState();
        if (userType.equals("-1")) {
            return "";
        }
        /**==========================================陪打工单处理=========================================== */
        /** 教练订单状态 15.待回应 16.成功预约 18.已完成 17.待评价 20.已取消 19.未接受 */
        /*if (userType.equals("2") && coachWorkType.equals("1")) {*/
            if (userType.equals("2") && "2".equals(type)) {
                Coach_order coach_order = coachOrderService.getCoachOrderDetails(orderId);
            try {
                //待回应 接受
                if (orderState.equals("15") && stauts.equals("1")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "16");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.updateWorkOrderStauts(sqlMap);

                    if (i == 1) {
                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",coachYesTitle);
                        mobMap.put("content",coachYesContent);
                        mobMap.put("url",urlHead+orders+"2");
                        MobPushUtil.MobPush(2,coachYesContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","5", "10", orderId.longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),coachYesTitle,coachYesContent,urlHead+orders+"2",reserveCoachOrder);
                        infromMsgService.insert(infromMsg);
                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCoachYes);
                        return IfunResult.ok("状态更新为接受!");
                    }
                }
                //待回应 不接受
                if (orderState.equals("15") && stauts.equals("2")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "19");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.updateWorkOrderStauts(sqlMap);
                    if (i == 1) {

                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",coachNoTitle);
                        mobMap.put("content",coachNoContent);
                        mobMap.put("url",urlHead+orders+"2");
                        MobPushUtil.MobPush(2,coachNoContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","5", "11", orderId.longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),coachNoTitle,coachNoContent,urlHead+orders+"2",reserveCoachOrder);
                        infromMsgService.insert(infromMsg);
                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCourseNo);

                        /** 如果不接受或者取消(双方)全额退款 原路退回 */
                        try{
                            /** 获取本地order对象 */
                            /** 获取用户余额信息 */
                            Account account =  accountService.getUserBalance(Integer.valueOf(order.getCreateUser()));
                            /** 获取ping order */
                            com.pingplusplus.model.Order pingOrder= com.pingplusplus.model.Order.retrieve(order.getPingId());
                            /** 支付方式为余额 退款需要更新本地余额 */
                            /*if("balance".equals(pingOrder.getChargeEssentials().getChannel())){
                                if(!order.getIsDel()){
                                    *//** 获取消费记录 *//*
                                    DebitRecord debitRecord = debitRecordService.getDebitRecord(order.getOrderNo());
                                    if(null!=debitRecord){
                                        if(null!=debitRecord.getSrBalance()){
                                            accountService.updateUserSrBalance(Integer.valueOf(order.getCreateUser()),account.getSrBalance()+debitRecord.getSrBalance());
                                        }
                                        if(null!=debitRecord.getZsBalance()){
                                            accountService.updateUserZsBalance(Integer.valueOf(order.getCreateUser()),account.getZsBalance()+debitRecord.getZsBalance());
                                        }
                                        if(null!=debitRecord.getCzBalance()){
                                            accountService.updateUserCzBalance(Integer.valueOf(order.getCreateUser()),account.getCzBalance()+debitRecord.getCzBalance());
                                        }
                                    }
                                }
                            }*/
                            Map<String,Object>params = new HashMap<>();
                            params.put("description","教练订单退款");
                            orderRefund = OrderRefund.create(pingOrder.getId(),params);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                       /* return IfunResult.ok("状态更新为不接受!");*/
                        return IfunResult.build(0,"状态更新为不接受!",orderRefund);
                    }
                }
                //已拒绝 已取消
                //逻辑删除 (1. 已删除 0.未删除)
                if (orderState.equals("16") || orderState.equals("19") || orderState.equals("20")) {
                    if("3".equals(stauts)){


                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.delWorkOrder(sqlMap);
                    if (i == 1) {
                        return IfunResult.ok("删除成功!");
                    }
                    }
                }
                //成功预约 确认赴约
                if (orderState.equals("16") && stauts.equals("5")) {
                    try {
                        Map<String, Object> sqlMap = new HashMap<>();
                        sqlMap.put("orderState", "16");
                        sqlMap.put("modifyUser", userId);
                        sqlMap.put("modifyTime", new Date());
                        sqlMap.put("orderId", orderId);
                        sqlMap.put("orderNo", orderNo);
                        int i = orderService.updateWorkOrderStauts(sqlMap);

                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",successYesTitle);
                        mobMap.put("content",successYesContent);
                        mobMap.put("url",urlHead+orders+"2");
                        MobPushUtil.MobPush(2,successYesContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","5", "13", orderId.longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),successYesTitle,successYesContent,urlHead+orders+"2",reserveCoachOrder);
                        infromMsgService.insert(infromMsg);
                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCoachYesYue);

                        return IfunResult.ok("确认赴约!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new SystemException(Constant.ERR_UNKNOW);
                    }

                }
                //成功预约 取消预约
                //true 可取消
                if (orderState.equals("16") && stauts.equals("4")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "20");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);

                    if (DateUtil.checkTime(coach_order.getPlayDate()+""+coach_order.getPlayTime(),86400000)) {

                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",cancelTitle);
                        mobMap.put("content",cancelContent);
                        mobMap.put("url",urlHead+orders+"2");
                        MobPushUtil.MobPush(2,cancelContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","5", "12", orderId.longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),cancelTitle,cancelContent,urlHead+orders+"2",reserveCoachOrder);
                        infromMsgService.insert(infromMsg);

                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCoachCoach);

                        /** 如果不接受或者取消(双方)全额退款 原路退回 */
                        try{
                            /** 获取本地order对象 */
                            /** 获取用户余额信息 */
                            Account account =  accountService.getUserBalance(Integer.valueOf(order.getCreateUser()));
                            /** 获取ping order */
                            com.pingplusplus.model.Order pingOrder= com.pingplusplus.model.Order.retrieve(order.getPingId());
                            /** 支付方式为余额 退款需要更新本地余额 */
                            /*if("balance".equals(pingOrder.getChargeEssentials().getChannel())){
                                if(!order.getIsDel()){
                                    *//** 获取消费记录 *//*
                                    DebitRecord debitRecord = debitRecordService.getDebitRecord(order.getOrderNo());
                                    if(null!=debitRecord){
                                        if(null!=debitRecord.getSrBalance()){
                                            accountService.updateUserSrBalance(Integer.valueOf(order.getCreateUser()),account.getSrBalance()+debitRecord.getSrBalance());
                                        }
                                        if(null!=debitRecord.getZsBalance()){
                                            accountService.updateUserZsBalance(Integer.valueOf(order.getCreateUser()),account.getZsBalance()+debitRecord.getZsBalance());
                                        }
                                        if(null!=debitRecord.getCzBalance()){
                                            accountService.updateUserCzBalance(Integer.valueOf(order.getCreateUser()),account.getCzBalance()+debitRecord.getCzBalance());
                                        }
                                    }
                                }
                            }*/
                            Map<String,Object>params = new HashMap<>();
                            params.put("description","教练订单退款");
                            orderRefund = OrderRefund.create(pingOrder.getId(),params);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        int i = orderService.updateWorkOrderStauts(sqlMap);
                        if (i == 1) {
                            return IfunResult.build(0,"已取消!",orderRefund);
                        }
                    } else {
                        return IfunResult.build(1, "24小时内不可取消！");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new SystemException(Constant.ERR_SYSTEM);
            }
        }
        /**==========================================课程工单处理=========================================== */
        /** 课程订单状态 22.待回应 23.成功预约 25.已完成 24.待评价 27.已取消 26.未接受 */
        /*if (userType.equals("3") && coachWorkType.equals("2")) {*/
            if (userType.equals("2") && "9".equals(type)) {
                Course_order course_order = courseService.getCourseOrderDetails(orderId);
            try {
                /** 待回应 接收 */
                if (orderState.equals("22") && stauts.equals("1")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "23");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.updateWorkOrderStauts(sqlMap);
                    /** 生成签到记录 */
                    String status = "0";
                    /*Course_order course_order = courseService.getCourseSignMsg(orderId);*/
                    List<CourseSign> list = new ArrayList<>();
                    for (int j = 0; j < course_order.getCourseNum(); j++) {
                        CourseSign courseSign = new CourseSign();
                        courseSign.setCourseId(course_order.getCourseId());
                        courseSign.setStuId(Integer.valueOf(course_order.getCreateUser()));
                        courseSign.setCaochId(course_order.getCoachId());
                        courseSign.setSignNum(0);
                        courseSign.setOrderId(orderId);
                        if (j != 0) {
                            status = "2";
                        }
                        courseSign.setCoachSignStauts(status);
                        courseSign.setStuSignStauts(status);
                        list.add(courseSign);
                    }
                    courseSignService.insertCourseRecord(list);

                    if (i == 1) {

                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",courseYesTitle);
                        mobMap.put("content",courseYesContent);
                        mobMap.put("url",urlHead+orders+"3");
                        MobPushUtil.MobPush(2,courseYesContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","6", "15", orderId.longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),courseYesTitle,courseYesContent,urlHead+orders+"3",reserveCourseOrder);
                        infromMsgService.insert(infromMsg);
                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCourseYes);

                        return IfunResult.ok("状态更新为接受!");
                    }
                }
                /** 待回应 不接收 */
                if (orderState.equals("22") && stauts.equals("2")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "26");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.updateWorkOrderStauts(sqlMap);
                    if (i == 1) {

                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",courseNoTitle);
                        mobMap.put("content",courseNoContent);
                        mobMap.put("url",urlHead+orders+"3");
                        MobPushUtil.MobPush(2,courseNoContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","6", "16", orderId.longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),courseNoTitle,courseNoContent,urlHead+orders+"3",reserveCourseOrder);
                        infromMsgService.insert(infromMsg);

                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCourseNo);

                        /** 如果不接受或者取消(双方)全额退款 原路退回 */
                        try{
                            /** 获取本地order对象 */
                            /** 获取用户余额信息 */
                            Account account =  accountService.getUserBalance(Integer.valueOf(order.getCreateUser()));
                            /** 获取ping order */
                            com.pingplusplus.model.Order pingOrder= com.pingplusplus.model.Order.retrieve(order.getPingId());
                            /** 支付方式为余额 退款需要更新本地余额 */
                            /*if("balance".equals(pingOrder.getChargeEssentials().getChannel())){
                                if(!order.getIsDel()){
                                    *//** 获取消费记录 *//*
                                    DebitRecord debitRecord = debitRecordService.getDebitRecord(order.getOrderNo());
                                    if(null!=debitRecord){
                                        if(null!=debitRecord.getSrBalance()){
                                            accountService.updateUserSrBalance(Integer.valueOf(order.getCreateUser()),account.getSrBalance()+debitRecord.getSrBalance());
                                        }
                                        if(null!=debitRecord.getZsBalance()){
                                            accountService.updateUserZsBalance(Integer.valueOf(order.getCreateUser()),account.getZsBalance()+debitRecord.getZsBalance());
                                        }
                                        if(null!=debitRecord.getCzBalance()){
                                            accountService.updateUserCzBalance(Integer.valueOf(order.getCreateUser()),account.getCzBalance()+debitRecord.getCzBalance());
                                        }
                                    }
                                }
                            }*/
                            Map<String,Object>params = new HashMap<>();
                            params.put("description","课程订单退款");
                            orderRefund = OrderRefund.create(pingOrder.getId(),params);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        return IfunResult.build(0,"状态更新为不接受!",orderRefund);
                    }
                }
                /** 逻辑删除 */
                if (orderState.equals("23") || orderState.equals("26") || orderState.equals("27") && stauts.equals("3")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.delWorkOrder(sqlMap);
                    if (i == 1) {
                        return IfunResult.ok("删除成功!");
                    }
                }
                /** 成功预约 取消预约 */
                if (orderState.equals("23") && stauts.equals("4")) {
                    try {
                        if (DateUtil.checkTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getCreateTime()),86400000)) {
                            Map<String, Object> sqlMap = new HashMap<>();
                            sqlMap.put("orderState", "27");
                            sqlMap.put("modifyUser", userId);
                            sqlMap.put("modifyTime", new Date());
                            sqlMap.put("orderId", orderId);
                            sqlMap.put("orderNo", orderNo);
                            int i = orderService.updateWorkOrderStauts(sqlMap);
                            if (i == 1) {

                                Map<String,Object>mobMap = new HashMap<>();
                                mobMap.put("title",cancelTitle);
                                mobMap.put("content",cancelContent);
                                mobMap.put("url",urlHead+orders+"3");
                                MobPushUtil.MobPush(2,cancelContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                                InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","6", "17", orderId.longValue(), new Date(),
                                        new Date(),userId.toString(),order.getCreateUser(),cancelTitle,cancelContent,urlHead+orders+"3",reserveCoachOrder);
                                infromMsgService.insert(infromMsg);

                                MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                                SmsUtil.sendSMS(mineVo.getPhone(),templateIdCourseCoach);

                                /** 如果不接受或者取消(双方)全额退款 原路退回 */
                                try{
                                    /** 获取本地order对象 */
                                    /** 获取用户余额信息 */
                                    Account account =  accountService.getUserBalance(Integer.valueOf(order.getCreateUser()));
                                    /** 获取ping order */
                                    com.pingplusplus.model.Order pingOrder= com.pingplusplus.model.Order.retrieve(order.getPingId());
                                    /** 支付方式为余额 退款需要更新本地余额 */
                                    if("balance".equals(pingOrder.getChargeEssentials().getChannel())){
                                        if(!order.getIsDel()){
                                            /** 获取消费记录 */
                                            DebitRecord debitRecord = debitRecordService.getDebitRecord(order.getOrderNo());
                                            if(null!=debitRecord){
                                                if(null!=debitRecord.getSrBalance()){
                                                    accountService.updateUserSrBalance(Integer.valueOf(order.getCreateUser()),account.getSrBalance()+debitRecord.getSrBalance());
                                                }
                                                if(null!=debitRecord.getZsBalance()){
                                                    accountService.updateUserZsBalance(Integer.valueOf(order.getCreateUser()),account.getZsBalance()+debitRecord.getZsBalance());
                                                }
                                                if(null!=debitRecord.getCzBalance()){
                                                    accountService.updateUserCzBalance(Integer.valueOf(order.getCreateUser()),account.getCzBalance()+debitRecord.getCzBalance());
                                                }
                                            }
                                        }
                                    }
                                    Map<String,Object>params = new HashMap<>();
                                    params.put("description","课程订单退款");
                                    orderRefund = OrderRefund.create(pingOrder.getId(),params);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                return IfunResult.build(0,"",orderRefund);
                            }
                        } else {
                            return IfunResult.build(1, "24小时内不可取消!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return IfunResult.build(1, "推送消息/发送短信失败");
                    }
                }
            } catch (Exception e) {
                new SystemException(Constant.ERR_SYSTEM);
            }
        }
        /**==========================================球童工单处理=========================================== */
        /** 球童订单状态 8.待回应 9.成功预约 11.已完成 10.待评价 13.已取消 12.未接受 */

        if (userType.equals("3")&&"3".equals(type)) {
            Qiutong_order qiutongOrder = qiutongOrderService.getQiuTongOrderDetails(orderId);
            try {
                //待回应 接受
                if (orderState.equals("8") && stauts.equals("1")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "9");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.updateWorkOrderStauts(sqlMap);
                    if (i == 1) {
                        /**获取教练真实姓名*/
                        /*String realname = userService.getRealname(userId);*/

                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",caddieYesTitle);
                        mobMap.put("content",caddieYesContent);
                        mobMap.put("url",urlHead+orders+"1");
                        MobPushUtil.MobPush(2,caddieYesContent,new String[]{order.getCreateUser()},"1", JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","4", "6", order.getOrderId().longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),caddieYesTitle,caddieYesContent,urlHead+orders+"1",reserveCaddieOrder);
                        infromMsgService.insert(infromMsg);

                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCaddieYes);

                        return IfunResult.ok();
                    }
                }
                //待回应 不接受
                if (orderState.equals("8") && stauts.equals("2")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "12");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.updateWorkOrderStauts(sqlMap);
                    if (i == 1) {
                        /*String id = userService.getUserId(uid);*/
                        Map<String,Object>mobMap = new HashMap<>();
                        mobMap.put("title",caddieNoTitle);
                        mobMap.put("content",caddieNoContent);
                        mobMap.put("url",urlHead+orders+"1");
                        MobPushUtil.MobPush(2,caddieNoContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                        InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","4", "7", order.getOrderId().longValue(), new Date(),
                                new Date(),userId.toString(),order.getCreateUser(),caddieNoTitle,caddieNoContent,urlHead+orders+"1",reserveCaddieOrder);
                        infromMsgService.insert(infromMsg);

                        MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                        SmsUtil.sendSMS(mineVo.getPhone(),templateIdCaddieNo);

                        return IfunResult.ok();
                    }
                }
                //未接收 已取消
                //逻辑删除 (1. 已删除 0.未删除)
                if (orderState.equals("9") || orderState.equals("12") || orderState.equals("13") && stauts.equals("3")) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    int i = orderService.delWorkOrder(sqlMap);
                    if (i == 1) {
                        return IfunResult.ok();
                    }
                }

                //成功预约 到达预约时间
                /** 拿到预约时间 */
                Integer ss = qiutongOrderService.getDateStatus(orderId);
                if (orderState.equals("9") && ss == 0) {
                    Map<String, Object> sqlMap = new HashMap<>();
                    sqlMap.put("orderState", "10");
                    sqlMap.put("modifyUser", userId);
                    sqlMap.put("modifyTime", new Date());
                    sqlMap.put("orderId", orderId);
                    sqlMap.put("orderNo", orderNo);
                    orderService.updateWorkOrderStauts(sqlMap);
                }

                //成功预约 取消预约
                if (orderState.equals("9") && stauts.equals("4")) {
                    try {
                        if (DateUtil.checkTime(qiutongOrder.getPlayDate()+" "+qiutongOrder.getPlayTime(),86400000)) {
                            Map<String, Object> sqlMap = new HashMap<>();
                            sqlMap.put("orderState", "13");
                            sqlMap.put("modifyUser", userId);
                            sqlMap.put("modifyTime", new Date());
                            sqlMap.put("orderId", orderId);
                            sqlMap.put("orderNo", orderNo);
                            int i = orderService.updateWorkOrderStauts(sqlMap);
                            if (i == 1) {
                               /* String id = userService.getUserId(uid);*/
                                Map<String,Object>mobMap = new HashMap<>();
                                mobMap.put("title",cancelTitle);
                                mobMap.put("content",cancelContent);
                                mobMap.put("url",urlHead+orders+"1");
                                MobPushUtil.MobPush(2,cancelContent,new String[]{order.getCreateUser()},"1",JSON.toJSONString(mobMap));
                                InfromMsg infromMsg = new InfromMsg(null,userId.longValue(),"0","4", "8", order.getOrderId().longValue(), new Date(),
                                        new Date(),userId.toString(),order.getCreateUser(),cancelTitle,cancelContent,urlHead+orders+"1",reserveCaddieOrder);
                                infromMsgService.insert(infromMsg);

                                MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                                SmsUtil.sendSMS(mineVo.getPhone(),templateIdCaddieCancel);

                                return IfunResult.ok();
                            }
                        } else {
                            return IfunResult.build(1, "2小时内不可取消!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return IfunResult.build(1, "推送消息/发送短信失败");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new SystemException(Constant.ERR_SYSTEM);
            }
        }
        return null;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

  /*  public void setUserType(String userType) {
        this.userType = userType;
    }*/

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

  /*  public void setuid(String uid) {
        this.uid = uid;
    }*/

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

/*    public void setCoachWorkType(String coachWorkType) {
        this.coachWorkType = coachWorkType;
    }*/
}
