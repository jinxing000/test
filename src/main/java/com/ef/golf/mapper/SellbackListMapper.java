package com.ef.golf.mapper;

import com.ef.golf.pojo.SellbackList;

public interface SellbackListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellbackList record);

    int insertSelective(SellbackList record);

    SellbackList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellbackList record);

    int updateByPrimaryKey(SellbackList record);
    SellbackList ByPrimaryKey(Integer order_id);

}