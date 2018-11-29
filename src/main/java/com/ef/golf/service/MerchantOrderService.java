package com.ef.golf.service;

import com.ef.golf.pojo.MerchantOrder;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/6/15 10:26
 */
public interface MerchantOrderService {

    int deleteByPrimaryKey(Long merchantOrderId);

    int insert(MerchantOrder record);

    int insertSelective(MerchantOrder record);

    MerchantOrder selectByPrimaryKey(Long merchantOrderId);

    int updateByPrimaryKeySelective(MerchantOrder record);

    int updateByPrimaryKey(MerchantOrder record);

}
