package com.ef.golf.service;

import com.ef.golf.pojo.DebitRecord;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/5/2 18:32
 */
public interface DebitRecordService {

    int deleteByPrimaryKey(Long debitRecordId);

    int insert(DebitRecord record);

    int insertSelective(DebitRecord record);

    DebitRecord selectByPrimaryKey(Long debitRecordId);

    int updateByPrimaryKeySelective(DebitRecord record);

    int updateByPrimaryKey(DebitRecord record);

    DebitRecord getDebitRecord (String orderNo);

}
