package com.ef.golf.callable;

import com.ef.golf.common.Constant;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Gift;
import com.ef.golf.pojo.Gift_give;
import com.ef.golf.service.*;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisExpireTime;
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
public class SendGiftTask implements Callable<Map<String,Object>>, Serializable {

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private GiftService giftService;
    @Resource
    private DynamicService dynamicService;
    @Resource
    private Gift_giveService giftGiveService;
    @Resource
    private IntegralService integralService;
    //发送者id
    private String userId;

    //接受者id
    private  String recieverId;

    //礼物id
    private String giftId;
    //群id
    private String groupId;
    //动态id
    private String dynamicId;

    //发放类型
    private String sendType;//  1.个人到个人  2.群里发放 3.动态 4
    @Override
    public Map<String, Object> call() throws Exception {
        Map<String,Object> map=new HashMap<>();
        int totalScore = integralService.getUserTotalScore(Integer.valueOf(userId));
        Gift gift = giftService.selectByPrimaryKeyGift(Integer.valueOf(giftId));


        try{
            if(totalScore<0){
                map.put("status",1);
                map.put("message","您的积分不足!");
                return map;

               // throw new SystemException(Constant.ERR_GIFT);
            }

            //个人到个人 积分增减 积分记录 送礼物-score 收礼物+score
            if ("1".equals(sendType)){/** 个人到个人 */
                /** userId=赠送者id recieverId=礼物接受者id*/
               Gift_give giftGive = new Gift_give(null,Integer.valueOf(userId),null,
                       Integer.valueOf(giftId),Integer.valueOf(recieverId),null,new Date());
               int i = giftService.insertSelective(giftGive);
               if(i>0){
                   map.put("status",0);
                   map.put("message","送礼成功");
               }else{
                   throw new SystemException(Constant.ERR_GIFT);
               }
            }else if("3".equals(sendType)){/** 动态 */
                Dynamic dynamic = dynamicService.selectByPrimaryKey(Integer.valueOf(dynamicId));
                Gift_give giftGive = new Gift_give(null,Integer.valueOf(userId),
                        Integer.valueOf(dynamicId),Integer.valueOf(giftId),dynamic.getUserId(),null,new Date());
               int i = giftService.insertSelective(giftGive);
                if(i>0){
                    map.put("status",0);
                    map.put("message","送礼成功");
                }else{
                    throw new SystemException(Constant.ERR_GIFT);
                }
            }else{/** 抢礼物 */
                List<Gift_give>redisList = new ArrayList<>();
                Gift_give giftGive = new Gift_give(null,Integer.valueOf(userId),
                        null,Integer.valueOf(giftId),null,groupId,new Date());
                /*giftService.insertSelective(giftGive);*/

                giftGiveService.insertSelective(giftGive);
            /** 根据用户id查询总积分 送礼人*/
                Integer score = integralService.getUserTotalScore(Integer.valueOf(userId));

                Map<String,Object>map1 = new HashMap<>();
                map1.put("scoreTotal",score - Integer.valueOf(gift.getGiftIntegral()));
                map1.put("userId",Integer.valueOf(userId));
                map1.put("modifyTime",new Date());
                integralService.updateUserTotalScore(map1);

                System.out.println(giftGive.getGiftRecordId());
                redisList.add(giftGive);
                redisBaseDao.setList("G"+giftGive.getGiftRecordId()+"",redisList);
                redisBaseDao.expire(giftGive.getGiftRecordId()+"", RedisExpireTime.GiftTime.getSeconds());
                map.put("status",0);
                map.put("giftGiveId",giftGive.getGiftRecordId());
                map.put("message","礼物已送出");
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

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
