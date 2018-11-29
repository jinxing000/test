package com.ef.golf.action;

import com.alibaba.fastjson.JSON;
import com.ef.golf.common.Constant;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.CourseDetailsVo;
import com.ef.golf.vo.MineVo;
import com.pingplusplus.model.BalanceTransfer;
import com.pingplusplus.model.Recharge;
import com.pingplusplus.model.Transfer;
import com.pingplusplus.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018年4月19日
 * 课程签到处理
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CourseSignAction extends AbstractService {


    private Integer signId;
    private Integer coachId;
    private Integer stuId;
    private String type; //0 学员 1 教练
    private Integer orderId;

    @Resource
    private CourseSignService courseSignService;
    @Resource
    private OrderService orderService;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private UserService userService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private AccountService accountService;
    @Resource
    private SystemPayService systemPayService;
    @Resource
    private InfromMsgService infromMsgService;
    @Resource
    private CourseService courseService;

    @Value("${urlHead}")
    private String urlHead;
    @Value("${balanceRecord}")
    private String balanceRecord;
    @Value("${courseSignTitle}")
    private String courseSignTitle;
    @Value("${courseSignContent}")
    private String courseSignContent;
    @Value("${templateId.course.sign}")
    private String templateIdCourseSign;

    @Override
    public Object doService() throws Exception {
        Map<String,Object> map = new HashMap<>();

        Order order = orderService.selectByPrimaryKey(orderId);

        /** 查询前一次签到记录 如果为null，是第一次签到*///其实可以统一一个对象接
        CourseSign courseSign = courseSignService.selectCourseSignById(signId-1,coachId,stuId,orderId);
        /** 查询当前签到记录 */
        CourseSign cs = courseSignService.selectCourseSignById(signId,coachId,stuId,orderId);
        /** 查询后一次签到记录 */
        CourseSign cs2 = courseSignService.selectCourseSignById(signId+1,coachId,stuId,orderId);

        /** 学员签到 */
        Course_order course_order = courseService.getCourseOrderDetails(orderId);
        MineVo mineVo = userService.getInfo(course_order.getCoachId());
        if(null==course_order){
            throw new SystemException(Constant.ERR_UNKNOW);
        }
        CourseDetailsVo courseDetailsVo = courseService.getCourseDetails(course_order.getCourseId());

        if("0".equals(type)){
            if(courseSign == null){
                if("1".equals(cs.getCoachSignStauts())){
                    if(cs.getStuSignStauts().equals("0")){
                        CourseSign c1 = new CourseSign();
                        c1.setSignId(signId.longValue());
                        c1.setStuSignStauts("1");
                        c1.setStuSignDate(new Date());
                        c1.setSignNum(1);
                        /** 更新为已签到 */
                        courseSignService.updateByPrimaryKeySelective(c1);

                        if(cs2!=null){
                            courseSignService.updateSignStatus("0","0",signId+1,coachId,stuId,orderId);
                        }
                        //如果是一次 更改订单状态
                        if(1==Integer.valueOf(courseDetailsVo.getCourseNum())){
                            order.setOrderState("24");
                            orderService.updateByPrimaryKeySelective(order);
                        }

                        try{
                            Transfer transfer = transfer(Integer.valueOf(courseDetailsVo.getCourseNum()));
                            map.put("balanceTransfer",transfer.getStatus());
                            /** 转款发送短信 */
                            Map<String,Object>mobMap = new HashMap<>();
                                mobMap.put("title",courseSignTitle);
                                mobMap.put("content",courseSignContent);
                                mobMap.put("url",urlHead+balanceRecord);
                            MobPushUtil.MobPush(2,courseSignContent,new String[]{course_order.getCoachId().toString()},"1", JSON.toJSONString(mobMap));
                            InfromMsg infromMsg = new InfromMsg(null,stuId.longValue(),"0","6", "19", orderId.longValue(), new Date(),
                                    new Date(),stuId.toString(),course_order.getCoachId().toString(),courseSignTitle,courseSignContent,urlHead+balanceRecord,null);
                            infromMsgService.insert(infromMsg);

                                    SmsUtil.sendSMS(mineVo.getPhone(),templateIdCourseSign);
                        }catch (Exception e){
                            e.printStackTrace();
                            throw new SystemException(Constant.ERR_BalanceTransfer);
                        }
                        map.put("status",0);
                        map.put("message","签到成功");
                        map.put("courSign",c1);
                        return map;
                    }
                }else{
                    map.put("status",3);
                    map.put("message","教练签到后可签!");
                    return map;
                }

            /** 不为null 判断上一次是否是已签到状态 如果是，更新本次*/
            }else{
                if(courseSign.getStuSignStauts().equals("1")){
                    if(cs.getCoachSignStauts().equals("1")){
                        if(cs.getStuSignStauts().equals("0")){
                            CourseSign c2 = new CourseSign();
                                c2.setSignId(signId.longValue());
                                c2.setStuSignStauts("1");
                                c2.setStuSignDate(new Date());
                                /** 前一次的记录加1 */
                                c2.setSignNum(courseSign.getSignNum()+1);
                            /** 更新为已签到 */
                            courseSignService.updateByPrimaryKeySelective(c2);
                            if(cs2!=null){
                                courseSignService.updateSignStatus("0","0",signId+1,coachId,stuId,orderId);
                            }
                            //如果是一次 更改订单状态
                            if(courseSign.getSignNum()+1==Integer.valueOf(courseDetailsVo.getCourseNum())){
                                order.setOrderState("24");
                                orderService.updateByPrimaryKeySelective(order);
                            }
                            /** 如果教练也签到了 给教练转账 */
                            if(cs.getCoachSignStauts().equals("1")){
                                try{
                                    Transfer transfer = transfer(Integer.valueOf(courseDetailsVo.getCourseNum()));
                                    map.put("balanceTransfer",transfer.getStatus());
                                    /** 转款发送短信 */
                                    SmsUtil.sendSMS(mineVo.getPhone(),templateIdCourseSign);
                                    Map<String,Object>mobMap = new HashMap<>();
                                        mobMap.put("title",courseSignTitle);
                                        mobMap.put("content",courseSignContent);
                                        mobMap.put("url",urlHead+balanceRecord);
                                    MobPushUtil.MobPush(2,courseSignContent,new String[]{course_order.getCoachId().toString()},"1",JSON.toJSONString(mobMap));
                                    InfromMsg infromMsg = new InfromMsg(null,stuId.longValue(),"0","6", "19", orderId.longValue(), new Date(),
                                            new Date(),stuId.toString(),course_order.getCoachId().toString(),courseSignTitle,courseSignContent,urlHead+balanceRecord,null);
                                    infromMsgService.insert(infromMsg);
                                }catch (Exception e){
                                    e.printStackTrace();
                                    throw new SystemException(Constant.ERR_BalanceTransfer);
                                }
                            }
                            map.put("status",0);
                            map.put("courseSign",c2);
                            map.put("message","签到成功");
                            return map;
                        }
                        //0 成功 1 不可签到 2上次课未签到 3教练签到后可签 4操作失败
                    }else{
                        map.put("status",3);
                        map.put("message","教练签到后可签!");
                        return map;
                    }
                    map.put("status",1);
                    map.put("message","不可签到");
                    return map;
                }
                map.put("status",2);
                map.put("message","上次课未签到");
                return map;
            }
        }

        if(null==order)
            throw new SystemException(Constant.ERR_UNKNOW);
        /** 教练签到 */
        if("1".equals(type)){
            if(courseSign==null){
                if(cs.getCoachSignStauts().equals("0")){
                    CourseSign c3 = new CourseSign();
                        c3.setSignId(signId.longValue());
                        c3.setCoachSignStauts("1");
                        c3.setCoachSignDate(new Date());
                    /** 更新为已签到 */
                    courseSignService.updateByPrimaryKeySelective(c3);


                   /* MobPushUtil.MobPush(2,"000000",new String[]{order.getCreateUser()},"1","");
                    InfromMsg infromMsg = new InfromMsg(null,coachId.longValue(),"0","6", "18", orderId.longValue(), new Date(),
                            new Date(),coachId.toString(),order.getCreateUser(),null,null,null,null);
                    infromMsgService.insert(infromMsg);*/
                    map.put("status",0);
                    map.put("message","签到成功");
                    return map;
                }
            }else{
                if(courseSign.getCoachSignStauts().equals("1")){
                    if(cs.getCoachSignStauts().equals("0")){
                        if("0".equals(courseSign.getStuSignStauts())){
                            map.put("status",5);
                            map.put("message","上次课学员未签到!");
                            return map;
                        }
                        CourseSign c4 = new CourseSign();
                            c4.setSignId(signId.longValue());
                            c4.setCoachSignStauts("1");
                            c4.setCoachSignDate(new Date());
                        /** 更新为已签到 */
                        courseSignService.updateByPrimaryKeySelective(c4);
                        /*MobPushUtil.MobPush(2,"000000",new String[]{order.getCreateUser()},"1","");
                        InfromMsg infromMsg = new InfromMsg(null,coachId.longValue(),"0","6", "18", orderId.longValue(), new Date(),
                                new Date(),coachId.toString(),order.getCreateUser(),null,null,null,null);
                        infromMsgService.insert(infromMsg);*/
                        if(cs2!=null){
                            /*courseSignService.updateSignStatus(null,"0",signId+1,coachId,stuId,orderId);*/
                        }
                        map.put("status",0);
                        map.put("message","签到成功");
                        return map;
                    }
                    map.put("status",1);
                    map.put("message","不可签到");
                    return map;
                }
                map.put("status",2);
                map.put("message","上次课未签到");
                return map;
            }
        }
        map.put("status",4);
        map.put("message","接口异常");
        return map;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Transfer transfer(Integer signNum) throws Exception {
        Transfer transfer = null;
        Order order = orderService.selectByPrimaryKey(orderId);

        if(order!=null&&order.getAmount()!=null){
            MineVo mineVo = userService.getInfo(coachId);
            String orderNo = orderNoUtil.serialNoGenerate("06",mineVo.getPhone());
            /** 原始金额、扣除平台服务费、除课程数 */
            double serviceMoney = order.getAmount()/signNum;
            String money = AmountUtils.changeY2F(serviceMoney+"");

                transfer = pingUtil.transferAccounts(Integer.valueOf(money),"balance",orderNo,coachId.toString(),"6");//description=6 教练服务费
                insertDebitRecord(transfer);
                insertSystem_pay(transfer);
        }
                return transfer;
    }
    /** 转账记录 */
    protected void insertSystem_pay(Transfer transfer) throws Exception {
        /** 转账记录 */
        System_pay systemPay = new System_pay();
            systemPay.setUserId(0);
            systemPay.setTakeId(Integer.valueOf(transfer.getRecipient()));
            /** 分转元 */
            String moneyF2Y = AmountUtils.changeF2Y(transfer.getAmount()+"");
            systemPay.setMoney(Double.valueOf(moneyF2Y));
            systemPay.setCreateTime(new Date());
            //1球童 2教练结算 3商家 4愿望 5课程 6红包(该接口不会用到) 7教练预约
            systemPay.setModifyTime(new Date());
            systemPay.setType("8");
            systemPay.setState(transfer.getStatus().equals("paid")?"1":"2");
            systemPay.setOrderNo(transfer.getOrderNo());
            systemPay.setChannel("(balance)qyf");
        systemPayService.insertSelective(systemPay,transfer.getOrderNo(),0.0);
    }
    /** 账户转出记录 余额更新 */
    protected void insertDebitRecord(Transfer transfer) throws Exception {
        /** 接收方账户详细 */
        Account jsAccount = accountService.getUserBalance(Integer.valueOf(transfer.getRecipient()));
        Double F2YPayMoney = Double.valueOf(AmountUtils.changeF2Y(transfer.getAmount()+""));
        /** 更新接收方收入余额 */
        accountService.updateUserSrBalance(Integer.valueOf(transfer.getRecipient()),jsAccount.getSrBalance()+F2YPayMoney);
    }
}
