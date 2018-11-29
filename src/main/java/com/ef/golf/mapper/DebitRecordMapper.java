package com.ef.golf.mapper;

import com.ef.golf.pojo.DebitRecord;

public interface DebitRecordMapper {
    int deleteByPrimaryKey(Long debitRecordId);

    int insert(DebitRecord record);

    int insertSelective(DebitRecord record);

    DebitRecord selectByPrimaryKey(Long debitRecordId);

    int updateByPrimaryKeySelective(DebitRecord record);

    int updateByPrimaryKey(DebitRecord record);

    DebitRecord getDebitRecord (String orderNo);
}