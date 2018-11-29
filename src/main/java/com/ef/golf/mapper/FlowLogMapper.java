package com.ef.golf.mapper;

import com.ef.golf.pojo.FlowLog;

public interface FlowLogMapper {
    int deleteByPrimaryKey(Integer flowId);

    int insert(FlowLog record);

    int insertSelective(FlowLog record);

    FlowLog selectByPrimaryKey(Integer flowId);

    int updateByPrimaryKeySelective(FlowLog record);

    int updateByPrimaryKey(FlowLog record);
}