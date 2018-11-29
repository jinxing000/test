package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Calendar;
import com.ef.golf.pojo.GetHopeMsg;
import com.ef.golf.pojo.Gift;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.GiftService;
import com.ef.golf.service.HopeService;
import com.ef.golf.service.IntegralService;
import com.ef.golf.util.PageBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 礼物列表
 * Administrator
 * 2018/4/23 16:56
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetGiftAction extends AbstractService {

    @Resource
    private GiftService giftService;
    @Resource
    private IntegralService integralService;

    private Integer pageNo= 1;
    private Integer pageSize = 5;
    private Integer userId;

    public Object doService() {
        Map<String,Object>resultMap = new HashMap<>();

        List<Gift> list = giftService.findByPage();
        /** 根据用户id查询总积分 */
        Integer totalScore = integralService.getUserTotalScore(userId);
        resultMap.put("gift",list);
        resultMap.put("totalScore",totalScore<0?0:totalScore);
        return resultMap;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {

        this.pageNo = pageNo;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
