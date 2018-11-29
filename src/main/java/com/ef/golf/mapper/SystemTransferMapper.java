package com.ef.golf.mapper;

import com.ef.golf.pojo.SystemTransfer;

public interface SystemTransferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemTransfer record);

    int insertSelective(SystemTransfer record);

    SystemTransfer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemTransfer record);

    int updateByPrimaryKey(SystemTransfer record);
}