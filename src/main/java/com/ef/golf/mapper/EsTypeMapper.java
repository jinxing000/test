package com.ef.golf.mapper;

import com.ef.golf.pojo.EsType;
import com.ef.golf.pojo.EsTypeWithBLOBs;

public interface EsTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(EsTypeWithBLOBs record);

    int insertSelective(EsTypeWithBLOBs record);

    EsTypeWithBLOBs selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(EsTypeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(EsTypeWithBLOBs record);

    int updateByPrimaryKey(EsType record);
}