package com.ef.golf.service.impl;

import com.ef.golf.mapper.MerchantOrderMapper;
import com.ef.golf.mapper.User_roleMapper;
import com.ef.golf.pojo.MerchantOrder;
import com.ef.golf.service.MerchantOrderService;
import com.ef.golf.util.GoEasyUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/4/26 13:58
 */
@Repository
public class MerchantOrderServiceImpl implements MerchantOrderService {

    @Resource
    private MerchantOrderMapper merchantOrderMapper;
    @Resource
    private User_roleMapper user_roleMapper;

    @Override
    public int deleteByPrimaryKey(Long merchantOrderId) {
        return merchantOrderMapper.deleteByPrimaryKey(merchantOrderId);
    }

    @Override
    public int insert(MerchantOrder record) {
        return merchantOrderMapper.insert(record);
    }

    @Override
    public int insertSelective(MerchantOrder record) {
        return merchantOrderMapper.insertSelective(record);
    }

    @Override
    public MerchantOrder selectByPrimaryKey(Long merchantOrderId) {
        return merchantOrderMapper.selectByPrimaryKey(merchantOrderId);
    }

    @Override
    public int updateByPrimaryKeySelective(MerchantOrder record) {
        return merchantOrderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MerchantOrder record) {
        return merchantOrderMapper.updateByPrimaryKey(record);
    }
}
