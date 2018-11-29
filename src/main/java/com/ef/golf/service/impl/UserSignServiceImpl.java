package com.ef.golf.service.impl;

import com.ef.golf.mapper.UserSignMapper;
import com.ef.golf.pojo.UserSign;
import com.ef.golf.service.UserSignService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserSignServiceImpl implements UserSignService {

    @Resource
    private UserSignMapper userSignMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userSignMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserSign record) {
        return userSignMapper.insert(record);
    }

    @Override
    public int insertSelective(UserSign record) {
        return userSignMapper.insertSelective(record);
    }

    @Override
    public UserSign selectByPrimaryKey(Long id) {
        return userSignMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserSign record) {
        return userSignMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserSign record) {
        return userSignMapper.updateByPrimaryKey(record);
    }

    @Override
    public UserSign getUserSignRecord(Integer userId) {
        return userSignMapper.getUserSignRecord(userId);
    }
}
