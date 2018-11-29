package com.ef.golf.service;

import com.ef.golf.pojo.SystemTransfer;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/5/12 9:44
 */
public interface SystemTransferService {

    int deleteByPrimaryKey(Long id);

    int insert(SystemTransfer record);

    int insertSelective(SystemTransfer record);

    SystemTransfer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemTransfer record);

    int updateByPrimaryKey(SystemTransfer record);
}
