package com.ef.golf.service.impl;

import com.ef.golf.mapper.*;
import com.ef.golf.pojo.*;
import com.ef.golf.service.GiftService;
import com.ef.golf.service.Gift_giveService;
import com.ef.golf.util.PageBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/6/1 15:38
 */
@Repository
public class GiftServiceImpl  implements GiftService{
    @Resource
    private GiftMapper giftMapper;
    @Resource
    private Gift_giveMapper giftGiveMapper;
    @Resource
    private IntegralMapper integralMapper;
    @Resource
    private IntegralRecordMapper integralRecordMapper;
    @Resource
    private DynamicMapper dynamicMapper;
    @Resource
    private WorkTypeMapper workTypeMapper;
    @Override
    public List<Gift> findByPage() {
        List<Gift> list = giftMapper.findByPage();
        return list;
    }

    @Override//个人到个人 积分增减 积分记录 送礼物-score 收礼物+score
    public int insertSelective(Gift_give record) {
        giftGiveMapper.insertSelective(record);
        int flag=1;
        try{
            Gift gift = giftMapper.selectByPrimaryKey(record.getGiftId());
            if(gift!=null){
                /** 更新动态礼物数 */
                if(null!=record.getDynamicId()){
                    /** 查询动态 */
                    Dynamic dynamic = dynamicMapper.selectByPrimaryKey(record.getDynamicId());
                    dynamic.setGiftNum(dynamic.getGiftNum()+1);
                    dynamicMapper.updateByPrimaryKey(dynamic);

                    WorkType workType = new WorkType
                            (null,Long.valueOf(record.getUserId()),"5",Long.valueOf(record.getDynamicId()),"0",new Date(),"1",null);
                    workTypeMapper.insertSelective(workType);

                }
                /** 根据用户id查询总积分 送礼人*/
                Integer sonScore = integralMapper.getUserTotalScore(record.getUserId());
                /** 根据用户id查询总积分 收礼人*/
                Integer shouScore = integralMapper.getUserTotalScore(record.getRecieverId());
                /** 更新送礼用户积分 */
                if(sonScore!=null&&shouScore!=null){
                    Map<String,Object>map1 = new HashMap<>();
                    map1.put("scoreTotal",sonScore - Integer.valueOf(gift.getGiftIntegral()));
                    map1.put("userId",record.getUserId());
                    map1.put("modifyTime",new Date());
                    integralMapper.updateUserTotalScore(map1);
                    Map<String,Object>map2 = new HashMap<>();
                    map2.put("scoreTotal",shouScore + Integer.valueOf(gift.getGiftIntegral()));
                    map2.put("userId",record.getRecieverId());
                    map2.put("modifyTime",new Date());
                    integralMapper.updateUserTotalScore(map2);
                }
                /** 送礼积分记录 */
                IntegralRecord integralRecord1 = new IntegralRecord(null,Long.valueOf(record.getUserId()),
                        "-"+gift.getGiftIntegral(),null,"送礼物减积分",new Date());
                int i = integralRecordMapper.insertSelective(integralRecord1);
                /** 收礼积分记录 */
                IntegralRecord integralRecord2 = new IntegralRecord(null,Long.valueOf(record.getRecieverId()),
                        "+"+gift.getGiftIntegral(),null,"收礼物加积分",new Date());
                int j = integralRecordMapper.insertSelective(integralRecord2);
            }
        }catch (Exception e){
            flag=0;
            e.printStackTrace();
        }

        return flag;
    }

    @Override
    public int updateByPrimaryKeySelective(Gift_give record) {
        try{
            Gift gift = giftMapper.selectByPrimaryKey(record.getGiftId());
            if(gift!=null){
                /** 根据用户id查询总积分 送礼人*/
                Integer sonScore = integralMapper.getUserTotalScore(record.getUserId());
                /** 根据用户id查询总积分 收礼人*/
                Integer shouScore = integralMapper.getUserTotalScore(record.getRecieverId());
                /** 更新送礼用户积分 */
                if(sonScore!=null&&shouScore!=null){
                    Map<String,Object>map1 = new HashMap<>();
                    map1.put("scoreTotal",sonScore - Integer.valueOf(gift.getGiftIntegral()));
                    map1.put("userId",record.getUserId());
                    map1.put("modifyTime",new Date());
                    integralMapper.updateUserTotalScore(map1);
                    Map<String,Object>map2 = new HashMap<>();
                    map2.put("scoreTotal",shouScore + Integer.valueOf(gift.getGiftIntegral()));
                    map2.put("userId",record.getRecieverId());
                    map2.put("modifyTime",new Date());
                    integralMapper.updateUserTotalScore(map2);
                }
                /** 送礼积分记录 */
                IntegralRecord integralRecord1 = new IntegralRecord(null,Long.valueOf(record.getUserId()),
                        "-"+gift.getGiftIntegral(),null,"送礼物减积分",new Date());
                int i = integralRecordMapper.insertSelective(integralRecord1);
                /** 收礼积分记录 */
                IntegralRecord integralRecord2 = new IntegralRecord(null,Long.valueOf(record.getRecieverId()),
                        "+"+gift.getGiftIntegral(),null,"收礼物加积分",new Date());
                int j = integralRecordMapper.insertSelective(integralRecord2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return giftGiveMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Gift_give selectByPrimaryKey(Integer giftRecordId) {
        return giftGiveMapper.selectByPrimaryKey(giftRecordId);
    }

    @Override
    public Gift selectByPrimaryKeyGift(Integer giftId) {
        return giftMapper.selectByPrimaryKey(giftId);
    }
}
