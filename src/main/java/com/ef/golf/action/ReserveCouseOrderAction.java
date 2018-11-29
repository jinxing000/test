package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.CourseDetailsVo;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xzw on 2017/9/23.
 * 预定课程
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReserveCouseOrderAction extends AbstractService {

    private String reserveCourse = "课程预约";//商品标题

    private String describe = "预约描述";//商品描述

    private Double payMoney;//价格

    private String channel;//支付渠道

    //教练id
    private Integer coachId;
    //教练姓名
    private String coachName;
    //课程id
    private Integer courseId;
    //课程次数
    private Integer courseNum;
    //课程名字
    private String courseName;
    //上课日期
    private String courseDate;
    //上课时间
    private String courseTime;
    //球场id
    private Integer siteId;
    //球场名字
    private String siteName;
    //预约人姓名
    private String reserveName;
    //联系方式
    private String contactsPhone;
    //备注
    private String remark;
    //优惠券id
    private String ticketid = "0";

    private String uid;

    private String sid;

    @Resource
    private TicketService ticketService;
    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;
    @Resource
    private CourseService courseService;
    @Resource
    private RedisBaseDao redisBaseDao;
    @Resource
    private HttpServletRequest request;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    /*  @Resource
      private CreateOrderController createOrderController;*/
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private UserTicketService userTicketService;


    public Object doService() throws LoginException, QueryException, DemandException, SystemException {



        String rUID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(rUID) || !rUID.equals(uid))
            throw new LoginException(Constant.ERR_USER);
        CourseDetailsVo courseDetailsVo = courseService.getCourseDetails(courseId);

        double totalPrice = 0;//应付总价格
        if (courseDetailsVo.getPrice() != null) {
            totalPrice = courseDetailsVo.getPrice();
        }
        Double realPrice = 0.0;//优惠后的总价格
        realPrice = totalPrice;

        if (StringUtils.isNotEmpty(ticketid) && !"0".equals(ticketid)) {
            UserTicket userTicket = userTicketService.selectByPrimaryKey(ticketid);
            Tickets tickets = ticketService.selectByPrimaryKey(userTicket.getTicketId());//优惠券详情*/
            if (tickets == null)
                throw new QueryException(Constant.ERR_QUERY - 4);
            if (userTicket.getState() != 2&&userTicket.getState()!=3)
                throw new DemandException(Constant.ERR_DEMAND - 2);
            //判断优惠券的归属

            if (userTicket.getLocation() != 1 && userTicket.getLocation() != 0)
                throw new DemandException(Constant.ERR_DEMAND - 3);
            if (tickets.getType() == 1) {/** 折扣 */
                realPrice = totalPrice * tickets.getDiscount() / 100.0;
                if(realPrice<=0.01){
                    realPrice=0.01;
                }
                if(tickets.getDiscountMoney()!=null){
                    if((totalPrice-realPrice)>=tickets.getDiscountMoney()){
                        realPrice = (totalPrice)-tickets.getDiscountMoney();
                    }
                }
            }
            if (tickets.getType() == 2) {/** 满减 */
                realPrice = totalPrice - tickets.getSpecialMoney();
                if(realPrice<=0.01){
                    realPrice=0.01;
                }
            }
        }
        //确定价格
        DecimalFormat df = new DecimalFormat("#.00");
        String money = df.format(realPrice);

        String qiangMoney = df.format(payMoney);
        if(0.01==payMoney){
            qiangMoney = payMoney+"";
        }
        if(realPrice==0.01){
            money = realPrice+"";
        }
        //确定价格
       /* String money = realPrice + "";*/
        if (!money.equals(qiangMoney)) {
            throw new SystemException(Constant.ERR_PARAMETERLESS);
        }
        Integer uId = redisLoginVerifyUtil.longinVerifty(sid, uid);

        String phoneNumber = userService.getInfo(uId).getPhone();

        String orderNo = orderNoUtil.orderNoGenerate("09", phoneNumber);

        String ip = HttpGetIpUtil.getIpAddress(request);
        String userId = uId.toString();
        //String orderNo=order.getOrderNo();
        String orderType = "9";
        String subject = courseName;
        String body = describe;
        Double amount = realPrice;

        /** 优惠金额 */
        double couponAmount = totalPrice - amount;
        Date date = new Date();
        Date date1 = new Date(date.getTime()+36000000);
        //ping++执行创建订单
        com.pingplusplus.model.Order obj = pingUtil.createOrder(userId, orderNo, orderType, phoneNumber, subject, body, Integer.valueOf(AmountUtils.changeY2F(amount+"")), ip);

        String pingOrderNo = obj.getMerchantOrderNo();
        //获取ping++生成的订单编号
        String orderId = obj.getId();
        /*=======封装courseOrder对象 start==========*/
        Course_order course_order = new Course_order();
            /*course_order.setOrderId(Long.valueOf(orderId));*/
        course_order.setCoachId(coachId);
        course_order.setCoachName(coachName);
        course_order.setCourseId(courseId);
        course_order.setCourseNum(courseNum);
        course_order.setCourseName(courseName);
        course_order.setContactsPhone(contactsPhone);
        if(null!=siteId&&!"".equals(siteId)) {
            course_order.setSiteId(siteId);
        }
        if(null!=siteName&&!"".equals(siteName)) {
            course_order.setSiteName(siteName);
        }
        course_order.setReserveName(reserveName);
        course_order.setRemark(remark);
        if (StringUtils.isNotBlank(ticketid) && !"0".equals(ticketid)) {
            course_order.setTickId(Integer.valueOf(ticketid));
        }
        course_order.setCourseTime(courseTime.toString());
        course_order.setCourseDate(courseDate.toString());
        course_order.setCreateTime(date);
        course_order.setModifyTime(date);
        course_order.setCreateUser(userId);
        course_order.setModifyUser(userId);
         /*=======封装coachOrder对象 end==========*/
        /** 干掉用户优惠券 */
        if (StringUtils.isNotBlank(ticketid) && !"0".equals(ticketid)) {
            userTicketService.updateUserTicketById(Integer.valueOf(userId), Integer.valueOf(ticketid));
        }
        //本地生成一条订单记录
        Order order = orderService.saveCourseOrder(course_order, pingOrderNo, orderId, amount, totalPrice, couponAmount, ticketid);
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("orderId", order.getOrderId());
        orderMap.put("user", new UserVo(uid, sid));
        orderMap.put("obj", obj.toString());
        return orderMap;
    }

    public void setReserveCourse(String reserveCourse) {
        this.reserveCourse = reserveCourse;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
