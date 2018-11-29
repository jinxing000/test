package com.ef.golf.mapper;

import com.ef.golf.pojo.Gift;
import com.ef.golf.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GiftMapper {
    int deleteByPrimaryKey(Integer giftId);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(Integer giftId);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);

    List<Gift> findByPage();
    int findByPageCount();
}