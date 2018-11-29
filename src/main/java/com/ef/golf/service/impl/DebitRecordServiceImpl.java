package com.ef.golf.service.impl;

import com.ef.golf.mapper.DebitRecordMapper;
import com.ef.golf.pojo.DebitRecord;
import com.ef.golf.service.DebitRecordService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/5/2 18:33
 */
@Repository
public class DebitRecordServiceImpl implements DebitRecordService {

    @Resource
    private DebitRecordMapper debitRecordMapper;

    @Override
    public int deleteByPrimaryKey(Long debitRecordId) {
        return debitRecordMapper.deleteByPrimaryKey(debitRecordId);
    }

    @Override
    public int insert(DebitRecord record) {
        return debitRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(DebitRecord record) {


        return debitRecordMapper.insertSelective(record);
    }

    @Override
    public DebitRecord selectByPrimaryKey(Long debitRecordId) {
        return debitRecordMapper.selectByPrimaryKey(debitRecordId);
    }

    @Override
    public int updateByPrimaryKeySelective(DebitRecord record) {
        return debitRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DebitRecord record) {
        return debitRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public DebitRecord getDebitRecord(String orderNo) {
        return debitRecordMapper.getDebitRecord(orderNo);
    }
}
