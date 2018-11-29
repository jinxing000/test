package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.common.pxx.controller.CreateOrderController;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.UserVo;
import com.pingplusplus.model.Charge;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xzw on 2017/9/23.
 * 预定场地
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SiteOrderAction extends AbstractService {

    private String siteName="球场预定";//商品标题

    private String describe="商品描述";//商品描述

    private String payMoney;//价格

    private String channel;//支付渠道



    //打球日期
    private String playDate;

    //开球时间

    private String playTime;

    //打球人姓名

   //@Pattern(regexp = "^[\\u4e00-\\u9fa5_a-zA-Z;]+$",message = "打球人名字输入有误，不能包含数字!!")
    private String playName;

    //联系方式
    private String contactsPhone;

    //球场会员人数
    private int siteMemberNumber = 0;

    //平台会员人数
    private int efMemberNumber = 0;

    //嘉宾人数
    private int guestNumber = 0;

    //总人数
    private int customerNumber = 0;

    //备注
    private String remark;

    //优惠券id
    private String ticketid = "0";


    private String siteid;

    private String uid;

    private String sid;


    @Resource
    private SiteService siteService;
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


    public Object doService() throws ParseException, QueryException, DemandException, SystemException, LoginException {
        if (StringUtils.isNotEmpty(ticketid)&&!"0".equals(ticketid)) {
            if(siteMemberNumber >0){
                throw new DemandException(Constant.ERR_TICKET_MEMER);
            }
            int byTickId = orderService.findByTickId(ticketid);
                if(byTickId > 0){
                    throw new DemandException(Constant.ERR_TICKET);
                }
        }
        String rUID = redisBaseDao.get(sid);
        if(StringUtils.isEmpty(rUID)||!rUID.equals(uid))
            throw new LoginException(Constant.ERR_USER);

        /*boolean isHoliday=HolidayUtil.isHolidayOrFestival(TimeFormat.formatDateStr(playDate));*/
        Site site = siteService.getSite(Integer.valueOf(siteid));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date playDates = simpleDateFormat.parse(playDate);
        //获取球场当日价格
        Double price =  siteService.getNowPrice(site.getSiteId(),playDates);
        /** DecimalFormat df = new DecimalFormat("#.00"); */
        if(site == null)
            throw new QueryException(Constant.ERR_QUERY - 1);
        if(price == null)
            throw new QueryException(Constant.ERR_QUERY_PRICE);
        if("2".equals(site.getReserve3())) //场地已下架
            throw new DemandException(Constant.ERR_DEMAND - 1);
        //总人数减会员人数
        int personNum = Integer.valueOf(customerNumber)-Integer.valueOf(siteMemberNumber);

        if(!StringUtils.isNotEmpty(site.getWeekdaysSiteMemberPrice())){
            site.setWeekdaysSiteMemberPrice("0.0");
        }

        double totalPrice=price*personNum+(siteMemberNumber*Double.valueOf(site.getWeekdaysSiteMemberPrice()));//应付总价格

        Double realPrice=totalPrice;//优惠后的总价格




        //假日
        /*if(isHoliday){

            //球场会员价格
            if(siteMemberNumber>0) totalPrice+=Double.valueOf(site.getHolidaySiteMemberPrice())*siteMemberNumber;
            //亿方会员价格
            if(efMemberNumber>0) totalPrice+=Double.valueOf(site.getHolidayEfMemberPrice())*efMemberNumber;
            //非会员价格
          *//*  if(customerNumber>0)  totalPrice+=Double.valueOf(site.getHolidayCustomerPrice())*customerNumber;*//*
        }
        //非假日
        else{
            //球场会员价格
            if(siteMemberNumber>0) totalPrice+=Double.valueOf(site.getWeekdaysSiteMemberPrice())*siteMemberNumber;
            //亿方会员价格
            if(efMemberNumber>0) totalPrice+=Double.valueOf(site.getWeekdaysEfMemberPrice())*efMemberNumber;
            //非会员价格
         *//*   if(customerNumber>0) totalPrice+=Double.valueOf(site.getWeekdaysCustomerPrice())*customerNumber;*//*

        }*/
        /*realPrice = totalPrice;*/


        if (StringUtils.isNotEmpty(ticketid)&&!"0".equals(ticketid)) {
            UserTicket userTicket = userTicketService.selectByPrimaryKey(ticketid);
            Tickets tickets = ticketService.selectByPrimaryKey(userTicket.getTicketId());//优惠券详情*/
            if(userTicket==null||tickets == null)
                throw new QueryException(Constant.ERR_QUERY - 4);
            if (userTicket.getState() != 2&&userTicket.getState()!=3)
                throw new DemandException(Constant.ERR_DEMAND - 2);
            //判断优惠券的归属  1  2
            if(userTicket.getLocation()!=1&&userTicket.getLocation()!=2)
                throw new DemandException(Constant.ERR_DEMAND - 3);
            /*if(totalPrice<ticket.getConditions())
                throw new DemandException(Constant.ERR_DEMAND - 4);*/
            if(tickets.getType()==1){/** 折扣 */
                realPrice = totalPrice * (tickets.getDiscount()/100.0);
                if(realPrice<=0.01){
                    realPrice=0.01;
                }
                if(tickets.getDiscountMoney()!=null){
                    if((totalPrice-realPrice)>=tickets.getDiscountMoney()){
                        realPrice = (totalPrice)-tickets.getDiscountMoney();
                    }
                }
            }
            if(tickets.getType()==2){/** 满减 */
                realPrice = totalPrice - tickets.getSpecialMoney();
                if(realPrice<=0.01){
                    realPrice=0.01;
                }
            }
        }
        //确定价格
        DecimalFormat df = new DecimalFormat("#.00");
        String s = df.format(realPrice);
        if(realPrice==0.01){
            s = realPrice+"";
        }
        if(!payMoney.equals(s)){
            throw new SystemException(Constant.ERR_PRICE);
        }
        /*String a=realPrice+"";
        if(!a.equals(payMoney.substring(0,payMoney.lastIndexOf(".")+2))){
            throw new SystemException(Constant.ERR_PARAMETERLESS);
        }*/
        Integer uId=redisLoginVerifyUtil.longinVerifty(sid,uid);
        String phoneNumber=userService.getInfo(uId).getPhone();
        String orderNo=orderNoUtil.orderNoGenerate("01",phoneNumber);

        String ip= HttpGetIpUtil.getIpAddress(request);
        String userId=uId.toString();
        //String orderNo=order.getOrderNo();
        String orderType="1";
        String subject=siteName;
        String body=describe;
        /*payMoney = payMoney.substring(0,payMoney.lastIndexOf("."));*/

        /** 实际支付的 */
        Double amount=Double.valueOf(payMoney);
        //Double amount=Double.valueOf(realPrice);
        /** 优惠金额 */
        double couponAmount = totalPrice-amount;
        //ping++执行创建订单
        com.pingplusplus.model.Order obj=pingUtil.createOrder(userId,orderNo,orderType,phoneNumber,subject,body,Integer.valueOf(AmountUtils.changeY2F(amount+"")),ip);
        System.out.println(obj.toString());
        String pingOrderNo=obj.getMerchantOrderNo();
        //获取ping++生成的订单编号
        String orderId=obj.getId();
        //本地生成一条订单记录
        /** amount 实际支付的 couponAmount 优惠金额 */
        Order order=orderService.saveSiteOrder(getSiteOrder(site,totalPrice,amount,userId),pingOrderNo,orderId,totalPrice,couponAmount);
        /** 干掉用户优惠券 */
        if (StringUtils.isNotEmpty(ticketid)&&!"0".equals(ticketid)) {
            userTicketService.updateUserTicketById(Integer.valueOf(userId), Integer.valueOf(ticketid));
        }
        Map<String,Object> orderMap=new HashMap<>();
        orderMap.put("user",new UserVo(uid,sid));
        orderMap.put("obj",obj.toString());
        orderMap.put("orderId",order.getOrderId());
        /*return obj;*/
            return orderMap;
    }


    private Site_order getSiteOrder(Site site,double totalPrice,double realPayMoney,String userId){
        Site_order so=new Site_order();
        so.setSiteId(Integer.valueOf(siteid));
        so.setPlayDate(playDate);
        so.setPlayTime(playTime);
        so.setPlayName(playName);
        so.setContactsPhone(contactsPhone);
        so.setSiteMemberNumber(siteMemberNumber);//球场会员人数
        so.setEfMemberNumber(efMemberNumber);
        so.setCustomerNumber(customerNumber);//总人数
        so.setGuestNumber(guestNumber);
        so.setRemark(remark);
        if (StringUtils.isNotEmpty(ticketid)&&!"0".equals(ticketid)) {
            /*so.setTicketId(Integer.valueOf(StringUtils.isEmpty(ticketid) ? "0" : ticketid));*/
            so.setTicketId(Integer.valueOf(ticketid));
        }
        so.setWeekdaysSiteMemberPrice(site.getWeekdaysSiteMemberPrice());
        so.setWeekdaysEfMemberPrice(site.getWeekdaysEfMemberPrice());
        so.setWeekdaysCustomerPrice(site.getWeekdaysCustomerPrice());
        so.setHolidaySiteMemberPrice(site.getHolidaySiteMemberPrice());
        so.setHolidayEfMemberPrice(site.getHolidayEfMemberPrice());
        so.setHolidayCustomerPrice(site.getHolidayCustomerPrice());
        so.setPayMoney(totalPrice);
        so.setRealPayMoney(realPayMoney);
        so.setCreateUser(userId);
        so.setCreateTime(new Date());
        so.setModifyUser(userId);
        so.setModifyTime(new Date());
        return so;
    }


    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public void setSiteMemberNumber(int siteMemberNumber) {
        this.siteMemberNumber = siteMemberNumber;
    }

    public void setEfMemberNumber(int efMemberNumber) {
        this.efMemberNumber = efMemberNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
