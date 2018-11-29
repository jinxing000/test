package com.ef.golf.service;

import com.ef.golf.pojo.Gift_give;
import com.ef.golf.vo.QuanziGiftVo;

import java.util.List;

public interface Gift_giveService {

    //新增一条送礼物记录
    int insertSelective(Gift_give record);

    //根据动态id获取礼物记录
    List<QuanziGiftVo> getGifts(Integer dynamicId);

    int updateByPrimaryKeySelective(Gift_give record);
}
