package com.ef.golf.callable;

import com.ef.golf.common.Constant;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Gift_give;
import com.ef.golf.service.DynamicService;
import com.ef.golf.service.GiftService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.ServicePushUtils;
import com.ef.golf.vo.MineVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2018/5/10.
 */
@Component
@Scope("prototype")
public class GetGiftTask implements Callable<Map<String,Object>>, Serializable {

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private GiftService giftService;
    @Resource
    private UserService userService;
    //接受者
    private String userId;
    //送礼物记录id
    private String giftGiveId;

    //status=3 抢到 status=1 礼物已过期 status=2 已被领取
    //群id
    /*private String groupId;*/
    @Override
    public Map<String, Object> call() throws Exception {
        Map<String,Object> map=new HashMap<>();
        Gift_give giftGive = giftService.selectByPrimaryKey(Integer.valueOf(giftGiveId));
        if(null!=giftGive&&null!=giftGive.getRecieverId()){
            map.put("status",2);
            map.put("message","已被领取");
            return map;
        }
        try{
            if(redisBaseDao.exist("G"+giftGiveId)){/** 当key存在 补齐Gift中recieverId 存储送礼记录*/
                List<Gift_give>redisList = redisBaseDao.getList("G"+giftGiveId);
                for (Gift_give gg:redisList) {
                        gg.setRecieverId(Integer.valueOf(userId));
                        giftService.updateByPrimaryKeySelective(gg);

                    Map<String,Object>usersMap = new HashMap<>();
                    if(!userId.equals(giftGive.getUserId())){
                        String[]ss1 = {giftGive.getUserId()+""};
                        MineVo mineVo1 = userService.getInfo(Integer.valueOf(userId));
                        ServicePushUtils.servicePush(giftGive.getGroupId(),"12",ss1,mineVo1.getNickName()+"领取了你的礼物!", usersMap);
                    }
                    String[]ss2 = {userId+""};
                    MineVo mineVo2 = userService.getInfo(giftGive.getUserId());
                    ServicePushUtils.servicePush(giftGive.getGroupId(),"12",ss2,"你领取了"+mineVo2.getNickName()+"的礼物!", usersMap);


                    map.put("status",3);
                    map.put("message","抢到礼物");
                }
            }else{
                map.put("status",1);
                map.put("message","礼物已经过期");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGiftGiveId() {
        return giftGiveId;
    }

    public void setGiftGiveId(String giftGiveId) {
        this.giftGiveId = giftGiveId;
    }
}
