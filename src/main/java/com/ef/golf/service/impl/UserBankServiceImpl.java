package com.ef.golf.service.impl;

import com.ef.golf.mapper.UserBankMapper;
import com.ef.golf.pojo.UserBank;
import com.ef.golf.service.UserBankService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/5/31 10:47
 */
@Repository
public class UserBankServiceImpl implements UserBankService {

    @Resource
    private UserBankMapper userBankMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userBankMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserBank record) {
        return userBankMapper.insert(record);
    }

    @Override
    public int insertSelective(UserBank record) {
        return userBankMapper.insertSelective(record);
    }

    @Override
    public UserBank selectByPrimaryKey(Long id) {
        return userBankMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserBank record) {
        return userBankMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserBank record) {
        return userBankMapper.updateByPrimaryKey(record);
    }

    @Override
    public UserBank getUserBankDetails(Long userId) {
        return userBankMapper.getUserBankDetails(userId);
    }
}
