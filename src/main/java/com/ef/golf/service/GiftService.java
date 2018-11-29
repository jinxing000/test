package com.ef.golf.service;

import com.ef.golf.pojo.Gift;
import com.ef.golf.pojo.Gift_give;
import com.ef.golf.util.PageBean;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/6/1 13:51
 */
public interface GiftService {
    List<Gift> findByPage();
    int insertSelective(Gift_give record);

    int updateByPrimaryKeySelective(Gift_give record);

    Gift_give selectByPrimaryKey(Integer giftRecordId);

    Gift selectByPrimaryKeyGift(Integer giftId);
}
