package com.ef.golf.service.impl;

import com.ef.golf.mapper.PoundageMapper;
import com.ef.golf.pojo.Poundage;
import com.ef.golf.service.PoundageService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/6/15 17:08
 */
@Repository
public class PoundageServiceImpl implements PoundageService{

    @Resource
    private PoundageMapper poundageMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return poundageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Poundage record) {
        return poundageMapper.insert(record);
    }

    @Override
    public int insertSelective(Poundage record) {
        return poundageMapper.insertSelective(record);
    }

    @Override
    public Poundage selectByPrimaryKey(Integer id) {
        return poundageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Poundage record) {
        return poundageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Poundage record) {
        return poundageMapper.updateByPrimaryKey(record);
    }

    @Override
    public Poundage getAllPoundage(String userType) {
        return poundageMapper.getAllPoundage(userType);
    }
}
