package com.ef.golf.service.impl;

import com.ef.golf.mapper.DynamicMapper;
import com.ef.golf.mapper.HopeMapper;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.User;
import com.ef.golf.service.DynamicService;
import com.ef.golf.vo.DynamicHopeVo;
import com.ef.golf.vo.DynamicVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Repository
public class DynamicServiceImpl implements DynamicService {

    @Resource
    private DynamicMapper dynamicMapper;
    @Resource
    private HopeMapper hopeMapper;


    @Override
    public List<DynamicVo> getDynamicListPage(Dynamic dynamic) {
        return dynamicMapper.getDynamicListPage(dynamic);
    }

    @Override
    public int getDynamicListPageCount(Dynamic dynamic) {
        return dynamicMapper.getDynamicCount(dynamic);
    }

    @Override
    public List<DynamicVo> getCollectDynamicListPage(Dynamic dynamic) {
        return dynamicMapper.getCollectDynamicListPage(dynamic);
    }

    @Override
    public Dynamic selectByPrimaryKey(Integer dynamicId) {
        return dynamicMapper.selectByPrimaryKey(dynamicId);
    }

    @Override
    public List<DynamicHopeVo> getHopes(Integer userId) {
        return hopeMapper.getHopes(userId);
        /*return hopeMapper.getHopeByUserId();*/
    }

    @Override
    public List<DynamicHopeVo> getHopeByUserId(Integer userId) {
        return hopeMapper.getHopeByUserId(userId);
    }

    @Override
    public int updateDynamicNum(Integer dynamicId) {
        return dynamicMapper.updateDynamicNum(dynamicId);
    }

    @Override
    public int insertSelective(Dynamic record) {
        return dynamicMapper.insertSelective(record);
    }

    @Override
    public List<DynamicVo> getDynamicByUserIdListPage(User user) {
        return dynamicMapper.getDynamicByUserIdListPage(user);
    }

    @Override
    public List<DynamicVo> getDyanmicByDynamicId(Dynamic dynamic) {
        return dynamicMapper.getDyanmicByDynamicId(dynamic);
    }

    @Override
    public int updateDynamicForDel(Integer dynamicId) {
        return dynamicMapper.updateDynamicForDel(dynamicId);
    }
}
