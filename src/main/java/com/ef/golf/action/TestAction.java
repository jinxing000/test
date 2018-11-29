package com.ef.golf.action;

import com.alibaba.fastjson.JSON;
import com.ef.golf.common.Constant;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.mapper.EfActivityMapper;
import com.ef.golf.mapper.TicketsMapper;
import com.ef.golf.mapper.UserTicketMapper;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.GoEasyUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.SmsUtil;
import com.ef.golf.vo.ActivityMessageVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

/**
 * for efgolf
 * Created by Bart on 2017/5/31.
 * Date: 2017/5/31 10:14
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestAction extends AbstractService {

    @Resource
   private UserTicketMapper userTicketMapper;
    @Resource
    private EfActivityMapper efActivityMapper;

    @Resource
    private TicketsMapper ticketsMapper;

    @Override
    public Object doService() throws Exception {

        List<ActivityMessageVo>list = efActivityMapper.getActivityMessage("0");
        //当天0点0分0秒
        //改为转天 与10月8号 对比 那个大用那个
        Calendar beginTime = Calendar.getInstance();
        //beginTime.setTime(new Date());
        beginTime.add(Calendar.DAY_OF_MONTH,1);
        beginTime.set(Calendar.HOUR_OF_DAY, 0);
        beginTime.set(Calendar.MINUTE, 0);
        beginTime.set(Calendar.SECOND, 0);
        Date beginDate = beginTime.getTime();

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            Date beginMaxDate = simpleDateFormat.parse("2018-10-08 00:00:00");
            if(beginDate.compareTo(beginMaxDate) <=0){
                beginDate = beginMaxDate;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<UserTicket>ticketsList = new ArrayList<>();
        Date date = new Date();
        for (ActivityMessageVo amv: list) {//注册送劵活动
            if("0".equals(amv.getTypeTwo())){

                Tickets tickets = ticketsMapper.selectByPrimaryKey(Integer.valueOf(amv.getTemplateId()));

                if(null!=tickets){

                    int couponNum = Integer.valueOf(amv.getCouponNum());//这里是各模板送劵数量

                    for (int i = 0;i<couponNum;i++){//循环数量发劵

                        UserTicket userticket = new UserTicket();

                        userticket.setUserId(Long.valueOf(47));
                        userticket.setCreateTime(date);
                        userticket.setModifyTime(date);
                        userticket.setCreateUser("0");
                        userticket.setModifyUser("0");
                        userticket.setState(3);

                        userticket.setEffectiveTime(beginDate);
                        userticket.setExpiryTime(tickets.getExpiryTime());
                        userticket.setTicketId(Integer.valueOf(amv.getTemplateId()));
                        userticket.setLocation(tickets.getAttribution());
                        userticket.setProductId(Long.valueOf(tickets.getProductId()));
                        ticketsList.add(userticket);
                    }
                }
            }
        }
        if(ticketsList.size() >0  ) {
            int i = userTicketMapper.insertUserTickets(ticketsList);
        }
            return 0;

    }

}
