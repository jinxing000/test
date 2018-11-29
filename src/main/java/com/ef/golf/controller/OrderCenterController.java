package com.ef.golf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ef.golf.aspectj.Syslog;
import com.ef.golf.common.Constant;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.service.ExecutionService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.MineVo;
import com.ef.golf.vo.SiteOrderListVo;
import com.pingplusplus.model.Order;
import com.pingplusplus.model.OrderRefund;
import com.pingplusplus.model.OrderRefundCollection;
import com.pingplusplus.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * for efgolf
 * Created by Bart on 2017/1/6.
 * Date: 2017/1/6 09:16
 */
@Controller   //  /orderCenter/noPay
@RequestMapping(value = "/orderCenter")
public class OrderCenterController extends AbstractService{

     // 本地订单失效 /orderCenter/del 参数 orderId 88
     // 取消预约 /orderCenter/noReserve 参数 Integer orderId,String orderType,String userId
     // 退订 /orderCenter/tuiDin 参数 orderId
     // 评分项 /orderCenter/scoreAttr  参数 String orderType(1场地 2.教练 3.球童 9.课程)
     // 确认预约 /orderCenter/okReserve 参数 String orderType,Integer orderId,Integer userId
     // 评价 /orderCenter/evaluate 参数 String orderType,Integer productId,Integer attrId,String score,Integer userId,Integer orderId,String content

    @Resource
    private OrderService orderService;
    @Resource
    private ScoreService scoreService;
    @Resource
    private CommentService commentService;
    @Resource
    private ProductScoreService productScoreService;
    @Resource
    private PoundageService poundageService;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private UserService userService;
    @Resource
    private SystemPayService systemPayService;
    @Autowired
    private SiteOrderService siteOrderService;
    @Resource
    private InfromMsgService infromMsgService;
    @Resource
    private Qiutong_orderService qiutongOrderService;
    @Resource
    private CoachOrderService coachOrderService;
    @Resource
    private CourseService courseService;
    @Resource
   private UserRoleService userRoleService;

    @Resource
    private ExecutionService executionService;

    @Value("${urlHead}")
    private String urlHead;
    @Value("${workOrders}")
    private String workOrders;
    @Value("${cancelTitle}")
    private String cancelTitle;
    @Value("${cancelContent}")
    private String cancelContent;
    @Value("${successYesTitle}")
    private String successYesTitle;
    @Value("${successYesContent}")
    private String successYesContent;
    @Value("${reserveCaddieOrder}")
    private String reserveCaddieOrder;
    @Value("${reserveCourseOrder}")
    private String reserveCourseOrder;
    @Value("${reserveCoachOrder}")
    private String reserveCoachOrder;
    @Value("${templateId.course.guest}")
    private String templateIdCourseGuest;
    @Value("${templateId.coach.yesYue}")
    private String templateIdCoachYesYue;
    @Value("${templateId.coach.guest}")
    private String templateIdCoachGuest;
    @Value("${templateId.caddie.cancel}")
    private String templateIdCaddieCancel;
    @Value("${site.tuidin.now}")
    private String siteTuidinNow;
    @Value("${siteTuiDinTitle}")
    private String siteTuiDinTitle;
    @Value("${siteTuiDinContent}")
    private String siteTuiDinContent;
    @Value("${orders}")
    private String orders;


    //取消订单
    @Syslog(title = "update",methods = "NoPay",description = "取消支付,取消订单")
    @ResponseBody
    @RequestMapping(value = "/noPay",method = RequestMethod.POST)
    public Object NoPay(String orderId,String orderType,String userId) throws SystemException {
        Map<String,Object>map = new HashMap<>();
        com.ef.golf.pojo.Order order = orderService.selectByPrimaryKey(Integer.valueOf(orderId));
        try{//Dispatcher

            /** 本地订单更新状态 */
            /** 1场地 2.教练 3.球童 9.课程 */
            /** 2.场地 3.教练 4.球童 8.商城 0.课程 */
            com.ef.golf.pojo.Order bdOrder =orderService.selectByPrimaryKey(Integer.parseInt(orderId));
            bdOrder.setOrderId(Integer.valueOf(orderId));
            bdOrder.setModifyUser(userId);
            bdOrder.setModifyTime(new Date());

            if("2".equals(orderType)){
                bdOrder.setOrderState("6");
                bdOrder.setModifyTime(new Date());
                bdOrder.setModifyUser(userId);
            }else if("3".equals(orderType)){
                bdOrder.setOrderState("20");
                bdOrder.setModifyTime(new Date());
                bdOrder.setModifyUser(userId);
            }else if("4".equals(orderType)){
                bdOrder.setOrderState("13");
                bdOrder.setModifyTime(new Date());
                bdOrder.setModifyUser(userId);
            }else if("0".equals(orderType)){
                bdOrder.setOrderState("27");
                bdOrder.setModifyTime(new Date());
                bdOrder.setModifyUser(userId);
            }else if("8".equals(orderType)){ //外部传递的类型为8代表商城，商城的订单取消支付的ef_order 对应的状态是34,
                bdOrder.setOrderState("34");
                bdOrder.setModifyTime(new Date());
                bdOrder.setModifyUser(userId);
            }
            int i = orderService.updateByPrimaryKeySelective(bdOrder);


            if(i>0){
                if(null!=order.getPingId()){
                    /** 取消pingOrder */
                    Order  order1= Order.cancel(order.getPingId());
                    map.put("order",order1);
                }
                map.put("status",0);
                map.put("message","已取消");
            }else{
                map.put("status",1);
                map.put("message","取消失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException(Constant.ERR_UPDATE);
        }
        return IfunResult.ok(map);
    }
    /** 本地订单失效 */
    @ResponseBody
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public Object del(Integer orderId){
        Map<String,Object>map = new HashMap<>();
            int i = orderService.updateOrderIsDel(orderId);
            if(i!=1){
                map.put("status",1);
                map.put("message","失败");
            }else{
                map.put("status",0);
                map.put("message","成功");
            }
        return IfunResult.ok(map);
    }

    /** 退订 */
    @com.ef.golf.aspectj.Syslog(title = "update",methods = "tuiDin",description = "球场退订")
    @ResponseBody
    @RequestMapping(value = "/tuiDin",method = RequestMethod.POST)
    public Object tuiDin(Integer orderId,String userId)  {
        int  i =0;
        Map<String,Object>map = new HashMap<>();

        try {
            i = orderService.siteOrderTuiDin(orderId,userId);

        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(i!=1){
            map.put("orderCancelTime",simpleDateFormat.format(date));
            return IfunResult.build(1,"网络繁忙",map);
        }else{
            List<Integer> userIds = userRoleService.selectHuserIdByPermission("场地订单");
            for (Integer goEasy: userIds
                    ) {
                GoEasyUtil.pushMessage(goEasy+"","有客人退订球场,请及时处理!");
            }
            map.put("orderCancelTime",simpleDateFormat.format(date));
            return IfunResult.build(0,"退订中",map);
        }
        } catch (SystemException e) {
            return IfunResult.build(-10013,"开球前24小时内不可退订",map);
        }

    }
    /** 取消预约 */
    @com.ef.golf.aspectj.Syslog(title = "update",methods = "quxiaoyuyue",description = "取消预约")
    @ResponseBody
    @RequestMapping(value = "/noReserve",method = RequestMethod.POST)
    public Object quxiaoyuyue(Integer orderId,String orderType,String userId) throws SystemException {
        Map<String,Object>map = new HashMap<>();
        com.ef.golf.pojo.Order bdOrder = orderService.selectByPrimaryKey(orderId);
        bdOrder.setModifyUser(userId);
        bdOrder.setModifyTime(new Date());
        if("2".equals(orderType)){//球场
            com.ef.golf.pojo.Order order = orderService.selectByPrimaryKey(orderId);
            SiteOrderListVo siteOrderListVo= siteOrderService.getSiteOrderDetails(order.getOrderId());
            if(!DateUtil.checkTime(siteOrderListVo.getPlayDate()+" "+siteOrderListVo.getPlayTime(),86400000)){
                return IfunResult.build(-10013,"开球前24小时内不可退订",map);
            }
            List<Integer> userIds = userRoleService.selectHuserIdByPermission("场地订单");
            for (Integer goEasy: userIds
                    ) {
                GoEasyUtil.pushMessage(goEasy+"","有客人退订球场,请及时处理!");
            }
            bdOrder.setOrderState("37");
        }else if("3".equals(orderType)){//教练
            Coach_order coach_order = coachOrderService.getCoachOrderDetails(orderId);
            if(!DateUtil.checkTime(coach_order.getPlayDate()+" "+coach_order.getPlayTime(),86400000)){
                throw new SystemException(Constant.ERR_TIME_RESERVE);
            }
            bdOrder.setOrderState("20");
            Map<String,Object>mobMap = new HashMap<>();
            mobMap.put("title",cancelTitle);
            mobMap.put("content",cancelContent);
            mobMap.put("url",urlHead+workOrders);

            MobPushUtil.MobPush(2,cancelContent,new String[]{coach_order.getCoachId().toString()},"1",JSON.toJSONString(mobMap));
            InfromMsg infromMsg = new InfromMsg(null,Long.valueOf(userId),"0","5", "12", orderId.longValue(), new Date(),
                    new Date(),userId.toString(),coach_order.getCoachId().toString(),cancelTitle,cancelContent,urlHead+workOrders,reserveCoachOrder);
            infromMsgService.insert(infromMsg);
            MineVo mineVo = userService.getInfo(coach_order.getCoachId());
            SmsUtil.sendSMS(mineVo.getPhone(),templateIdCoachGuest);

            /** 如果不接受或者取消(双方)全额退款 原路退回 */
            try{
                com.pingplusplus.model.Order pingOrder= com.pingplusplus.model.Order.retrieve(bdOrder.getPingId());
                Map<String,Object>params = new HashMap<>();
                params.put("description","教练订单退款");
                OrderRefundCollection orderRefund = OrderRefund.create(pingOrder.getId(),params);
                System.out.println("发起退款==================="+orderRefund);
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if("4".equals(orderType)){//球童
            bdOrder.setOrderState("13");
            Qiutong_order qiutongOrder = qiutongOrderService.getQiuTongOrderDetails(orderId);

            if(!DateUtil.checkTime(qiutongOrder.getPlayDate()+" "+qiutongOrder.getPlayTime(),86400000)){
                throw new SystemException(Constant.ERR_TIME_RESERVE);
            }

            Map<String,Object>mobMap = new HashMap<>();
            mobMap.put("title",cancelTitle);
            mobMap.put("content",cancelContent);
            mobMap.put("url",urlHead+workOrders);

            MobPushUtil.MobPush(2,cancelContent,new String[]{qiutongOrder.getQiutongId().toString()},"1",JSON.toJSONString(mobMap));
            InfromMsg infromMsg = new InfromMsg(null,Long.valueOf(userId),"0","4", "8", orderId.longValue(), new Date(),
                    new Date(),userId.toString(),qiutongOrder.getQiutongId().toString(),cancelTitle,cancelContent,urlHead+workOrders,reserveCaddieOrder);
            infromMsgService.insert(infromMsg);
            MineVo mineVo = userService.getInfo(qiutongOrder.getQiutongId());
            SmsUtil.sendSMS(mineVo.getPhone(),templateIdCaddieCancel);
        }else if("0".equals(orderType)){//课程
            bdOrder.setOrderState("27");
            Course_order course_order = courseService.getCourseOrderDetails(orderId);

            if(!DateUtil.checkTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bdOrder.getCreateTime()),86400000)){
                throw new SystemException(Constant.ERR_TIME_RESERVE);
            }

            Map<String,Object>mobMap = new HashMap<>();
            mobMap.put("title",cancelTitle);
            mobMap.put("content",cancelContent);
            mobMap.put("url",urlHead+workOrders);

            MobPushUtil.MobPush(2,cancelContent,new String[]{course_order.getCoachId().toString()},"1",JSON.toJSONString(mobMap));
            InfromMsg infromMsg = new InfromMsg(null,Long.valueOf(userId),"0","6", "17", orderId.longValue(), new Date(),
                    new Date(),userId.toString(),course_order.getCoachId().toString(),cancelTitle,cancelContent,urlHead+workOrders,reserveCourseOrder);
            infromMsgService.insert(infromMsg);
            MineVo mineVo = userService.getInfo(course_order.getCoachId());
            SmsUtil.sendSMS(mineVo.getPhone(),templateIdCourseGuest);

            /** 如果不接受或者取消(双方)全额退款 原路退回 */
            try{
                /** 获取ping order */
                com.pingplusplus.model.Order pingOrder= com.pingplusplus.model.Order.retrieve(bdOrder.getPingId());
                Map<String,Object>params = new HashMap<>();
                params.put("description","教练订单退款");
                OrderRefundCollection orderRefund = OrderRefund.create(pingOrder.getId(),params);
                System.out.println("发起退款==================="+orderRefund);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        int i = orderService.updateByPrimaryKeySelective(bdOrder);
        if(i>0){
            map.put("status",0);
            map.put("message","已取消");
        }else{
            map.put("status",1);
            map.put("message","取消失败");
        }
        return IfunResult.ok(map);
    }

    /** 评分项 */
    @ResponseBody
    @RequestMapping(value = "/scoreAttr",method = RequestMethod.POST)
    public Object scoreAttr(String orderType){
        List<Score_attr> scoreAttr = new ArrayList<>();
        switch (orderType){
            case "2"://球场
                scoreAttr = scoreService.selectScoreAttr("1");
                break;
            case "3"://教练
                scoreAttr = scoreService.selectScoreAttr("2");
                break;
            case "4"://球童
                scoreAttr = scoreService.selectScoreAttr("3");
                break;
            case "0"://课程
                scoreAttr = scoreService.selectScoreAttr("9");
                break;
            case "8"://商城
                scoreAttr = scoreService.selectScoreAttr("7");
                break;
        }
        return IfunResult.ok(scoreAttr);
    }
    /** 确认预约 */
    @com.ef.golf.aspectj.Syslog(title = "update",methods = "okReserve",description = "客人确认预约")
    @ResponseBody
    @RequestMapping(value = "/okReserve",method = RequestMethod.POST)
    public Object okReserve(String orderType,Integer orderId,Integer userId) throws SystemException {
        Map<String,Object>map = new HashMap<>();
        com.ef.golf.pojo.Order order = null;
        List<Score_attr> scoreAttr = null;
        if(orderType.equals("3")){
            order = orderService.selectByPrimaryKey(orderId);
            if(order.getOrderState().equals("16")){
                Map<String,Object>sqlMap = new HashMap<>();
                sqlMap.put("orderState","17");
                sqlMap.put("modifyUser",userId);
                sqlMap.put("modifyTime",new Date());
                sqlMap.put("orderId",orderId);
                sqlMap.put("orderNo",order.getOrderNo());
                int s = orderService.updateWorkOrderStauts(sqlMap);

                Map<String,Object>mobMap = new HashMap<>();
                mobMap.put("title",successYesTitle);
                mobMap.put("content",successYesContent);
                mobMap.put("url",urlHead+workOrders);
                Coach_order coach_order = coachOrderService.getCoachOrderDetails(orderId);
                MobPushUtil.MobPush(2,successYesContent,new String[]{coach_order.getCoachId().toString()},"1",JSON.toJSONString(mobMap));
                InfromMsg infromMsg = new InfromMsg(null,Long.valueOf(userId),"0","5", "13", orderId.longValue(), new Date(),
                        new Date(),userId.toString(),coach_order.getCoachId().toString(),successYesTitle,successYesContent,urlHead+workOrders,reserveCoachOrder);
                infromMsgService.insert(infromMsg);
                MineVo coach = userService.getInfo(coach_order.getCoachId());
                SmsUtil.sendSMS(coach.getPhone(),templateIdCoachYesYue);


                /** 确认赴约后 给教练转款 扣除服务费 */
                Poundage poundage = poundageService.getAllPoundage("2");
                Integer zz = Integer.valueOf(AmountUtils.changeY2F(order.getAmount()+""));
                MineVo mineVo = userService.getInfo(Integer.valueOf(order.getCreateUser()));
                String orderNo = orderNoUtil.serialNoGenerate("06",mineVo.getPhone());

                Transfer transfer = pingUtil.transferAccounts(zz,"balance",orderNo,coach_order.getCoachId()+"","6");
                System.out.println("发起教练陪打转款==================="+transfer);
                System_pay systemPay = new System_pay();
                systemPay.setUserId(0);
                systemPay.setTakeId(Integer.valueOf(transfer.getRecipient()));
                /** 分转元 */
                String moneyF2Y = null;
                try {
                    moneyF2Y = AmountUtils.changeF2Y(transfer.getAmount().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                systemPay.setMoney(Double.valueOf(moneyF2Y));
                systemPay.setCreateTime(new Date());
                systemPay.setModifyTime(new Date());
                systemPay.setType("9");
                systemPay.setState(transfer.getStatus().equals("succeeded")?"1":"2");
                systemPay.setOrderNo(transfer.getOrderNo());
                systemPay.setChannel("(balance)qyf");
                int i = systemPayService.insertSelective(systemPay,transfer.getOrderNo(),0.0);

                if(s>0){
                    map.put("status",0);
                    map.put("message","已提交");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
            }
        }
        return IfunResult.ok(map);
    }

    /** 评价 */
    @ResponseBody
    @RequestMapping(value = "/evaluate",method = RequestMethod.POST)
    public Object Evaluate(String orderType,Integer userId,Integer orderId,String evaluate) throws SystemException {


        com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(evaluate);
        List<Map> jsonMap = new ArrayList<>();
        jsonMap = JSONObject.parseArray(JSONObject.toJSONString(jsonArray), Map.class);
        List<Product_score> productScore = new ArrayList<>();
        String content = "";
        String goodsId = "";
        /*for (Map jm:jsonMap) {
          productScore = (List<Product_score>) jm.get("productScores");
          content = (String) jm.get("content");
          goodsId = (String) jm.get("goodsId");
        }*/

        Map<String,Object>map = new HashMap<>();
        Map<String,Object>sqlMap = new HashMap<>();
       Comments comments = new Comments();
        Product_score product_score = new Product_score();
        com.ef.golf.pojo.Order order=null;
        Integer productId = 0;
        int i=0;
        int j=0;
        int s=0;
        switch (orderType){
            case "2"://球场
                for (Map jm:jsonMap) {

                    com.alibaba.fastjson.JSONArray array = JSON.parseArray((String) jm.get("productScores"));
                    productScore = JSONObject.parseArray(JSONObject.toJSONString(array), Product_score.class);

                   /* productScore = (List<Product_score>) jm.get("productScores");*/
                    content = (String) jm.get("content");

                    System.out.println("球场====content"+content);
                    goodsId = (String) jm.get("goodsId");
                    comments.setUserId(userId.longValue());
                    comments.setOrderId(orderId);

                    comments.setCommentsType("1");
                    comments.setContent(content);
                    comments.setCreateTime(new Date());
                    comments.setIsDel(false);
                    comments.setProductId(Integer.valueOf(goodsId));
                    i = commentService.insertSelective(comments);
                    if(null!=product_score) {
                        for (Product_score ps : productScore) {
                            product_score.setAttrId(ps.getAttrId());
                            product_score.setProductId(ps.getProductId());
                            product_score.setProductType("1");
                            product_score.setScore(ps.getScore());
                            product_score.setCommentsId(comments.getCommentsId()+"");
                            productId = ps.getProductId();
                            j = productScoreService.insertSelective(product_score);
                        }

                    }else{
                        throw new SystemException(Constant.ERR_UNKNOW);
                    }

                }
                /*for (Product_score ps: productScore) {
                    product_score.setAttrId(ps.getAttrId());
                    product_score.setProductId(ps.getProductId());
                    product_score.setProductType("1");
                    product_score.setScore(ps.getScore());
                    productId = ps.getProductId();
                    j = productScoreService.insertSelective(product_score);
                }*/


                order = orderService.selectByPrimaryKey(orderId);
                if(order.getOrderState().equals("4")){
                    sqlMap.put("orderState","5");
                    sqlMap.put("modifyUser",userId);
                    sqlMap.put("modifyTime",new Date());
                    sqlMap.put("orderId",orderId);
                    sqlMap.put("orderNo",order.getOrderNo());
                    s = orderService.updateWorkOrderStauts(sqlMap);
                }
                if(i>0&&j>0&&s>0){
                    map.put("status",0);
                    map.put("message","已提交");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
                break;
            case "3"://教练
                for (Map jm:jsonMap) {

                    com.alibaba.fastjson.JSONArray array = JSON.parseArray((String) jm.get("productScores"));
                    productScore = JSONObject.parseArray(JSONObject.toJSONString(array), Product_score.class);

                    /*productScore = (List<Product_score>) jm.get("productScores");*/
                    content = (String) jm.get("content");
                    System.out.println("教练====content"+content);
                    goodsId = (String) jm.get("goodsId");
                    comments.setUserId(userId.longValue());
                    comments.setOrderId(orderId);

                    comments.setCommentsType("2");
                    comments.setContent(content);
                    comments.setCreateTime(new Date());
                    comments.setIsDel(false);
                    comments.setProductId(Integer.valueOf(goodsId));
                    i = commentService.insertSelective(comments);
                    if(null!=product_score) {
                        for (Product_score ps : productScore) {
                            product_score.setAttrId(ps.getAttrId());
                            product_score.setProductId(ps.getProductId());
                            product_score.setProductType("2");
                            product_score.setScore(ps.getScore());
                            product_score.setCommentsId(comments.getCommentsId()+"");
                            productId = ps.getProductId();
                            j = productScoreService.insertSelective(product_score);
                        }

                    }else{
                        throw new SystemException(Constant.ERR_UNKNOW);
                    }

                }
                /*for (Product_score ps: productScore) {
                    product_score.setAttrId(ps.getAttrId());
                    product_score.setProductId(ps.getProductId());
                    product_score.setProductType("2");
                    product_score.setScore(ps.getScore());
                    productId = ps.getProductId();
                    j = productScoreService.insertSelective(product_score);
                }*/

                order = orderService.selectByPrimaryKey(orderId);
                if(order.getOrderState().equals("17")){
                    sqlMap.put("orderState","18");
                    sqlMap.put("modifyUser",userId);
                    sqlMap.put("modifyTime",new Date());
                    sqlMap.put("orderId",orderId);
                    sqlMap.put("orderNo",order.getOrderNo());
                    s = orderService.updateWorkOrderStauts(sqlMap);
                }
                if(i>0&&j>0&&s>0){
                    map.put("status",0);
                    map.put("message","已提交");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
                break;
            case "4"://球童
                for (Map jm:jsonMap) {

                    com.alibaba.fastjson.JSONArray array = JSON.parseArray((String) jm.get("productScores"));
                    productScore = JSONObject.parseArray(JSONObject.toJSONString(array), Product_score.class);

                    /*productScore = (List<Product_score>) jm.get("productScores");*/
                    content = (String) jm.get("content");
                    System.out.println("球童====content"+content);
                    goodsId = (String) jm.get("goodsId");
                    comments.setUserId(userId.longValue());
                    comments.setOrderId(orderId);

                    comments.setCommentsType("3");
                    comments.setContent(content);
                    comments.setCreateTime(new Date());
                    comments.setIsDel(false);
                    comments.setProductId(Integer.valueOf(goodsId));
                    i = commentService.insertSelective(comments);
                    if(null!=product_score) {
                        for (Product_score ps : productScore) {
                            product_score.setAttrId(ps.getAttrId());
                            product_score.setProductId(ps.getProductId());
                            product_score.setProductType("3");
                            product_score.setScore(ps.getScore());
                            product_score.setCommentsId(comments.getCommentsId()+"");
                            productId = ps.getProductId();
                            j = productScoreService.insertSelective(product_score);
                        }

                    }else{
                        throw new SystemException(Constant.ERR_UNKNOW);
                    }

                }
                /*for (Product_score ps: productScore) {
                    product_score.setAttrId(ps.getAttrId());
                    product_score.setProductId(ps.getProductId());
                    product_score.setProductType("3");
                    product_score.setScore(ps.getScore());
                    productId = ps.getProductId();
                    j = productScoreService.insertSelective(product_score);
                }*/

                order = orderService.selectByPrimaryKey(orderId);
                if(order.getOrderState().equals("10")){
                    sqlMap.put("orderState","11");
                    sqlMap.put("modifyUser",userId);
                    sqlMap.put("modifyTime",new Date());
                    sqlMap.put("orderId",orderId);
                    sqlMap.put("orderNo",order.getOrderNo());
                    s = orderService.updateWorkOrderStauts(sqlMap);
                }
                if(i>0&&j>0&&s>0){
                    map.put("status",0);
                    map.put("message","已提交");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
                break;
            case "0"://课程
                for (Map jm:jsonMap) {
                    com.alibaba.fastjson.JSONArray array = JSON.parseArray((String) jm.get("productScores"));
                    productScore = JSONObject.parseArray(JSONObject.toJSONString(array), Product_score.class);
                    /*productScore = (List<Product_score>) jm.get("productScores");*/
                    content = (String) jm.get("content");
                    System.out.println("课程====content"+content);
                    goodsId = (String) jm.get("goodsId");
                    comments.setUserId(userId.longValue());
                    comments.setOrderId(orderId);

                    comments.setCommentsType("9");
                    comments.setContent(content);
                    comments.setCreateTime(new Date());
                    comments.setIsDel(false);
                    comments.setProductId(Integer.valueOf(goodsId));
                    i = commentService.insertSelective(comments);
                    if(null!=product_score) {
                        for (Product_score ps : productScore) {
                            product_score.setAttrId(ps.getAttrId());
                            product_score.setProductId(ps.getProductId());
                            product_score.setProductType("9");
                            product_score.setScore(ps.getScore());
                            productId = ps.getProductId();
                            product_score.setCommentsId(comments.getCommentsId()+"");
                            j = productScoreService.insertSelective(product_score);
                        }

                    }else{
                        throw new SystemException(Constant.ERR_UNKNOW);
                    }

                }
                /*for (Product_score ps: productScore) {
                    product_score.setAttrId(ps.getAttrId());
                    product_score.setProductId(ps.getProductId());
                    product_score.setProductType("9");
                    product_score.setScore(ps.getScore());
                    productId = ps.getProductId();
                    j = productScoreService.insertSelective(product_score);
                }*/

                order = orderService.selectByPrimaryKey(orderId);
                if(order.getOrderState().equals("24")){
                    sqlMap.put("orderState","25");
                    sqlMap.put("modifyUser",userId);
                    sqlMap.put("modifyTime",new Date());
                    sqlMap.put("orderId",orderId);
                    sqlMap.put("orderNo",order.getOrderNo());
                    s = orderService.updateWorkOrderStauts(sqlMap);
                }
                if(i>0&&j>0&&s>0){
                    map.put("status",0);
                    map.put("message","已提交");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
                break;
            case "8"://商品
                for (Map jm:jsonMap) {
                    System.out.println(jm.get("productScores"));

                    com.alibaba.fastjson.JSONArray array = JSON.parseArray((String) jm.get("productScores"));
                    productScore = JSONObject.parseArray(JSONObject.toJSONString(array), Product_score.class);

                    /*String attay = (String) jm.get("productScores");

                   productScore =  JSONObject.parseArray(attay, Product_score.class);*/

                    /*productScore = (List<Product_score>) jm.get("productScores");*/
                    content = (String) jm.get("content");
                    System.out.println("商品====content"+content);
                    goodsId = (String) jm.get("goodsId");
                    comments.setUserId(userId.longValue());
                    comments.setOrderId(orderId);
                    comments.setProductId(Integer.valueOf(goodsId));
                    comments.setCommentsType("7");
                    comments.setContent(content);
                    comments.setCreateTime(new Date());
                    comments.setIsDel(false);
                    i = commentService.insertSelective(comments);
                    if(null!=product_score) {
                        for (Product_score ps : productScore) {
                            product_score.setAttrId(ps.getAttrId());
                            product_score.setProductId(ps.getProductId());
                            product_score.setProductType("7");
                            product_score.setScore(ps.getScore());
                            product_score.setCommentsId(comments.getCommentsId()+"");
                           /*productId = ps.getProductId();*/
                            j = productScoreService.insertSelective(product_score);
                        }

                    }else{
                        throw new SystemException(Constant.ERR_UNKNOW);
                    }
                }
                /*for (Product_score ps: productScore) {
                    product_score.setAttrId(ps.getAttrId());
                    product_score.setProductId(ps.getProductId());
                    product_score.setProductType("7");
                    product_score.setScore(ps.getScore());
                    productId = ps.getProductId();
                    j = productScoreService.insertSelective(product_score);
                }*/

                order = orderService.selectByPrimaryKey(orderId);
                if(order.getOrderState().equals("31")){
                    sqlMap.put("orderState","35");//已评价 35
                    sqlMap.put("modifyUser",userId);
                    sqlMap.put("modifyTime",new Date());
                    sqlMap.put("orderId",orderId);
                    sqlMap.put("orderNo",order.getOrderNo());
                    s = orderService.updateWorkOrderStauts(sqlMap);
                }
                if(i>0&&j>0&&s>0){
                    map.put("status",0);
                    map.put("message","已提交");
                }else{
                    throw new SystemException(Constant.ERR_UPDATE);
                }
                break;
        }
        return IfunResult.ok(map);
    }
}
