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
import com.ef.golf.vo.MineVo;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　  ┃　　　　　　　┃
 * 　  ┃　　　━　　　┃
 * 　  ┃　┳┛　┗┳  ┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 */

/**
 * Created by xzw on 2017/9/23.
 * 预定教练
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReserveCoachOrderAction extends AbstractService {

    private String reserveCoach = "教练预约";//商品标题

    private String describe = "预约描述";//商品描述

    private Double payMoney;//价格

    private String channel;//支付渠道

    //教练id
    private String coachId;
    //教练姓名
    private String coachName;
    //打球时间
    private String playDate;
    //开球时间
    private String playTime;
    //球会名字
    private String qiuhuiName;
    //打球人姓名
    private String playName;
    //联系方式
    private String contactsPhone;
    //备注
    private String remark;
    //优惠券id
    private String ticketid = "0";

    private String qiuhuiId;

    private String uid;

    private String sid;

    @Resource
    private TicketService ticketService;
    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;
    @Resource
    private RedisBaseDao redisBaseDao;
    @Resource
    private HttpServletRequest request;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private UserTicketService userTicketService;


    public Object doService() throws LoginException, QueryException, DemandException, SystemException {


        String rUID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(rUID) || !rUID.equals(uid))
            throw new LoginException(Constant.ERR_USER);

        MineVo mineVo = userService.getInfo(Integer.valueOf(coachId));
        if (mineVo == null)
            throw new QueryException(Constant.ERR_QUERY - 1);

        double totalPrice = 0;//应付总价格
        totalPrice = mineVo.getUsePay();

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
            if (userTicket.getLocation() != 1 && userTicket.getLocation() != 3)
                throw new DemandException(Constant.ERR_DEMAND - 3);
            /*if(totalPrice<ticket.getConditions())
                throw new DemandException(Constant.ERR_DEMAND - 4);*/
            if (tickets.getType() == 1) {/** 折扣 */
                realPrice = totalPrice * tickets.getDiscount() / 100.0;
                if(tickets.getDiscountMoney()!=null){
                    if((totalPrice-realPrice)>=tickets.getDiscountMoney()){
                        realPrice = (totalPrice)-tickets.getDiscountMoney();
                    }
                }
                if(realPrice<=0.01){
                    realPrice=0.01;
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
        //确定价格
       /* String money = realPrice + "";*/
        /*if(!money.equals(payMoney.substring(0,payMoney.lastIndexOf(".")+2))){
            throw new SystemException(Constant.ERR_PARAMETERLESS);
        }*/
        if(0.01==realPrice){
            money = realPrice+"";
        }
        String biMoney = df.format(payMoney);
        if(0.01==payMoney){
            biMoney=payMoney+"";
        }

        if (!money.equals(biMoney)) {
            throw new SystemException(Constant.ERR_PRICE);
        }
        Integer uId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        String phoneNumber = userService.getInfo(uId).getPhone();
        String orderNo = orderNoUtil.orderNoGenerate("01", phoneNumber);

        String ip = HttpGetIpUtil.getIpAddress(request);
        String userId = uId.toString();
        //String orderNo=order.getOrderNo();
        String orderType = "2";
        String subject = coachName;
        String body = describe;
        /** 实付金额 */
        Double amount = realPrice;

        /** 实付金额amount 优惠金额couponAmount 应付金额totalPrice*/
        double couponAmount = totalPrice - amount;
        //ping++执行创建订单
        com.pingplusplus.model.Order obj = pingUtil.createOrder(userId, orderNo, orderType, phoneNumber, subject, body, Integer.valueOf(AmountUtils.changeY2F(amount + "")), ip);
        String pingOrderNo = obj.getMerchantOrderNo();
        //获取ping++生成的订单编号
        String orderId = obj.getId();
        /*=======封装coachOrder对象 start==========*/
        Coach_order coach_order = new Coach_order();
        coach_order.setOrderId(Long.valueOf(orderId));
        coach_order.setCoachId(Integer.valueOf(coachId));

        coach_order.setPlayDate(playDate);
        coach_order.setPlayTime(playTime);
        coach_order.setCoachName(coachName);
        if(null!=qiuhuiId&&!"".equals(qiuhuiId)) {
            coach_order.setQiuhuiId(Integer.valueOf(qiuhuiId));
        }
        if(null!=qiuhuiName&&!"".equals(qiuhuiName)) {
            coach_order.setQiuhuiName(qiuhuiName);
        }
        coach_order.setPlayName(playName);
        coach_order.setContactsPhone(contactsPhone);
        coach_order.setRemark(remark);
        coach_order.setCreateUser(userId);
        coach_order.setCreateTime(new Date());
        coach_order.setModifyTime(new Date());
        coach_order.setModifyUser(userId);
         /*=======封装coachOrder对象 end==========*/
        /** 干掉用户优惠券 */
        if (StringUtils.isNotEmpty(ticketid) && !"0".equals(ticketid)) {
            userTicketService.updateUserTicketById(Integer.valueOf(userId), Integer.valueOf(ticketid));
        }
        //本地生成一条订单记录
        Order order = orderService.saveCoachOrder(coach_order, ticketid, pingOrderNo, orderId, amount, totalPrice, couponAmount);
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("orderId", order.getOrderId());
        orderMap.put("user", new UserVo(uid, sid));
        orderMap.put("order", obj.toString());
        return orderMap;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public void setQiuhuiName(String qiuhuiName) {
        this.qiuhuiName = qiuhuiName;
    }

    public void setQiuhuiId(String qiuhuiId) {
        this.qiuhuiId = qiuhuiId;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
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

    public void setReserveCoach(String reserveCoach) {
        this.reserveCoach = reserveCoach;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
