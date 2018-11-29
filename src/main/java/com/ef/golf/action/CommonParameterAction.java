package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.QueryException;
import com.ef.golf.pojo.UserSign;
import com.ef.golf.service.*;
import com.ef.golf.vo.TicketSeVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wxc on 2018.6.21
 */


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommonParameterAction extends AbstractService {

    private Integer userId;

    @Resource
    private InfromMsgService infromMsgService;
    @Resource
    private WorkTypeService workTypeService;
    @Resource
    private TicketSecondService ticketSecondService;
    @Resource
    private IntegralService integralService;
    @Resource
    private UserSignService userSignService;

    public Object doService() throws QueryException {
        Map<String,Object> map = new HashMap<>();
        Integer informMsgCount = infromMsgService.getInfromMsgCount(userId+"");
        Integer workTypeCount = workTypeService.getWorkTypeMsgCount(userId);
        List<TicketSeVo> list = ticketSecondService.getTicketList(userId);
        Integer totalScore = integralService.getUserTotalScore(userId);
        UserSign userSign = userSignService.getUserSignRecord(userId);
        map.put("msgCount",informMsgCount);//未读消息数
        map.put("quanziCount",workTypeCount);//圈子未读消息数
        map.put("youhuiquanCount",list.size()<0?0:list.size());//优惠券数量
        if(null!=userSign&&null!=userSign.getSigncount()){
            map.put("signCount",userSign.getSigncount());//签到次数
        }
        map.put("totalScore",totalScore<0?0:totalScore);//总积分
        return map;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
