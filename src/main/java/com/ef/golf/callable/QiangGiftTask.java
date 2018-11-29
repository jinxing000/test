package com.ef.golf.callable;

import com.ef.golf.common.Constant;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Gift_give;
import com.ef.golf.service.DynamicService;
import com.ef.golf.service.GiftService;
import com.ef.golf.util.RedisBaseDao;
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
public class QiangGiftTask implements Callable<Map<String,Object>>, Serializable {

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private GiftService giftService;
    //接受者id
    private  String recieverId;
    //群id
    private String groupId;
    @Override
    public Map<String, Object> call() throws Exception {
        Map<String,Object> map=new HashMap<>();
        try{
            if(redisBaseDao.exist("sendGift")){/** 当key存在 补齐Gift中recieverId 存储送礼记录*/
                List<Gift_give>redisList = redisBaseDao.getList("sendGift");
                for (Gift_give gg:redisList) {
                    if(gg.getGroupId().equals(groupId)){
                        gg.setRecieverId(Integer.valueOf(recieverId));
                        giftService.insertSelective(gg);
                        redisBaseDao.delete("sendGift");
                    }else{
                        throw new SystemException(Constant.ERR_GIFT);
                    }
                }
            }else{
                map.put("status",1);
                map.put("message","礼物已被抢完啦");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
