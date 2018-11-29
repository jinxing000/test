package com.ef.golf.mapper;

import com.ef.golf.pojo.GetHopeMsg;

public interface GetHopeMsgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GetHopeMsg record);

    int insertSelective(GetHopeMsg record);

    GetHopeMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GetHopeMsg record);

    int updateByPrimaryKey(GetHopeMsg record);

    GetHopeMsg getGetHopeMsg(Integer hopeId);
}