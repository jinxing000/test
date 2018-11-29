package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.IntegralRecord;
import com.ef.golf.pojo.UserSign;
import com.ef.golf.service.IntegralRecordService;
import com.ef.golf.service.IntegralService;
import com.ef.golf.service.UserSignService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserSignAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private IntegralService integralService;
    @Resource
    private RedisBaseDao redisBaseDao;
    @Resource
    private IntegralRecordService integralRecordService;
    @Resource
    private UserSignService userSignService;

    //@NotNull(message = "不可为空")
    private String uid;
    //@NotNull(message = "不可为空")
    private String sid;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> resMap = new HashMap<>();
        Integer userIdScore = redisLoginVerifyUtil.longinVerifty(sid, uid);
        if (userIdScore != null) {
            boolean bl = StringUtils.isNotEmpty(redisBaseDao.get(userIdScore.toString()));
            if (bl == true) {
                resMap.put("message", "重复签到");
                return resMap;
            } else {
                Calendar midnight = Calendar.getInstance();
                midnight.setTime(new Date());
                midnight.add(midnight.DAY_OF_MONTH, 1);
                midnight.set(midnight.HOUR_OF_DAY, 0);
                midnight.set(midnight.MINUTE, 0);
                midnight.set(midnight.SECOND, 0);
                midnight.set(midnight.MILLISECOND, 0);
                Integer seconds = (int) ((midnight.getTime().getTime() - new Date().getTime()) / 1000);
                redisBaseDao.saveEx(userIdScore.toString(), userIdScore.toString(), seconds);
            }
        }

        /** 根据用户id查询总积分 */
        Integer totalScore = integralService.getUserTotalScore(userIdScore);
        /** 生成随机分 */
        int score = (int) (Math.random() * 12) + 2;
        /** 更新签到次数 */
        UserSign userSign = userSignService.getUserSignRecord(userIdScore);
        UserSign userSign1 = new UserSign();
        userSign1.setId(userSign.getId());
        userSign1.setUserId(userIdScore);
        userSign1.setSigncount(userSign.getSigncount() + 1);
        userSign1.setLastModifyTime(new Date());
        userSignService.updateByPrimaryKeySelective(userSign1);
        /** 更新用户总积分 */
        Map<String, Object> map = new HashMap<>();
        if (totalScore != null) {
            map.put("scoreTotal", totalScore + score);
        }
        map.put("userId", userIdScore);
        map.put("modifyTime", new Date());
        integralService.updateUserTotalScore(map);
        /** 签到积分记录 */
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setAlterationNote("签到送积分");
        integralRecord.setUserId(Long.valueOf(userIdScore));
        integralRecord.setScore("+" + score);
        integralRecord.setCreateTime(new Date());
        integralRecordService.insertSelective(integralRecord);
        resMap.put("score", score);//当前签到随机给的分
        resMap.put("totalScore",totalScore + score);//总积分
        resMap.put("signCount",userSign.getSigncount() + 1);//签到次数
        return resMap;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
