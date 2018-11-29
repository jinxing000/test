package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Site;
import com.ef.golf.service.MemberApproveService;
import com.ef.golf.service.SiteService;
import com.ef.golf.service.TicketService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.SitePreOrderVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 根据球场ID，查询球场订单信息
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SiteGetAction extends AbstractService {

    @Resource
    private TicketService ticketService;


    @Resource
    private SiteService siteService;

    @Resource
    private UserService userService;

    @Resource
    private MemberApproveService memberApproveService;

    @Resource
    private RedisBaseDao redisBaseDao;

    //@NotNull(message = "球场编号不能为空")
    private Integer siteId;

    private String playDate;//打球日期  yyyy-MM-dd

    private String sid;

    private String uid;//手机号用户名

    private String clubId;//球场所属球会id


    public Object doService() throws ParseException {

        //存放查询的球场详细信息
        Map<String, Object> siteGet = new HashMap<String, Object>();

        //预下单
        SitePreOrderVo sitePreOrderVo = siteService.getPreOrderInfo(uid, siteId, playDate);

        siteGet.put("sitePreOrderVo", sitePreOrderVo);

        //是否为球会会员
        Long userId = userService.getUid(uid);
        String approveStatus = memberApproveService.memberApproveEnd(userId.intValue(), clubId);
        if (approveStatus == null) {
            approveStatus = "0";
        }
        /*DateUtil.stringToDate()*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date playDates = simpleDateFormat.parse(playDate);
        siteGet.put("approveStatus", approveStatus);
        Double price =  siteService.getNowPrice(Integer.valueOf(siteId),playDates);
        siteGet.put("siteNowPrice",price);
        //价格包含    18洞 果童车柜  前端每个部分都有样式，不能拼接成一个

        Site site = siteService.selectByPrimaryKey(siteId);


   /*     if(site != null){
            siteGet.put("siteId",site.getSiteId());
            siteGet.put("reserve4",site.getReserve4());
            siteGet.put("siteAddress",site.getSiteAddress());
            siteGet.put("reserveExplain",site.getReserveExplain());
            siteGet.put("cancelExplain",site.getCancelExplain());
            siteGet.put("sitePrice",site.getpr);
        }*/
        List<String> services = new ArrayList<>();
        String service1 = site.getService1();
        String service2 = site.getService2();
        String service3 = site.getService3();
        String service4 = site.getService4();
        String service5 = site.getService5();
        String service6 = site.getService6();
        String service7 = site.getService7();
        String service8 = site.getService8();
        String service9 = site.getService9();
        if (StringUtils.isNotEmpty(service1)) {
            services.add(service1);
        }
        if (StringUtils.isNotEmpty(service2)) {
            services.add(service2);
        }
        if (StringUtils.isNotEmpty(service3)) {
            services.add(service3);
        }
        if (StringUtils.isNotEmpty(service4)) {
            services.add(service4);
        }
        if (StringUtils.isNotEmpty(service5)) {
            services.add(service5);
        }
        if (StringUtils.isNotEmpty(service6)) {
            services.add(service6);
        }
        if (StringUtils.isNotEmpty(service7)) {
            services.add(service7);
        }
        if (StringUtils.isNotEmpty(service8)) {
            services.add(service8);
        }
        if (StringUtils.isNotEmpty(service9)) {
            services.add(service9);
        }


        //获取用户是否登录
        boolean is_Collect = false;
        Long user_id = null;
        if (StringUtils.isNotEmpty(sid)) {
            if (StringUtils.isNotEmpty(redisBaseDao.get(this.sid))) {
                user_id = userService.getUid(this.uid);
            }
        }
        //获取用户优惠券
       /* List<TicketVo> ticksList = null;
        if (user_id != null) {
            if (StringUtils.isNotEmpty(user_id.toString())) {
                User user = new User();
                user.setId(user_id);
                ticksList = ticketService.getUserTickets(user);
            }
        }
        siteGet.put("ticks", ticksList);*/
        siteGet.put("services", services);
        return siteGet;
    }


    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public SiteService getSiteService() {
        return siteService;
    }

    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public MemberApproveService getMemberApproveService() {
        return memberApproveService;
    }

    public void setMemberApproveService(MemberApproveService memberApproveService) {
        this.memberApproveService = memberApproveService;
    }

    public RedisBaseDao getRedisBaseDao() {
        return redisBaseDao;
    }

    public void setRedisBaseDao(RedisBaseDao redisBaseDao) {
        this.redisBaseDao = redisBaseDao;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
}
