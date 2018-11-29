package com.ef.golf.mapper;

import com.ef.golf.pojo.Gift_give;
import com.ef.golf.vo.QuanziGiftVo;

import java.util.List;

public interface Gift_giveMapper {
    int deleteByPrimaryKey(Integer giftRecordId);

    int insert(Gift_give record);

    int insertSelective(Gift_give record);

    Gift_give selectByPrimaryKey(Integer giftRecordId);

    int updateByPrimaryKeySelective(Gift_give record);

    int updateByPrimaryKey(Gift_give record);

    //根据动态id获取礼物记录
    List<QuanziGiftVo> getGifts(Integer dynamicId);
}