package com.ef.golf.mapper;


import com.ef.golf.pojo.MerchantOrder;

public interface MerchantOrderMapper {
    int deleteByPrimaryKey(Long merchantOrderId);

    int insert(MerchantOrder record);

    int insertSelective(MerchantOrder record);

    MerchantOrder selectByPrimaryKey(Long merchantOrderId);

    int updateByPrimaryKeySelective(MerchantOrder record);

    int updateByPrimaryKey(MerchantOrder record);
}