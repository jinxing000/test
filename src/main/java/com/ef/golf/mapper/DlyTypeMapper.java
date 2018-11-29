package com.ef.golf.mapper;

import com.ef.golf.pojo.DlyType;
import com.ef.golf.pojo.DlyTypeWithBLOBs;

import java.util.List;

public interface DlyTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(DlyTypeWithBLOBs record);

    int insertSelective(DlyTypeWithBLOBs record);

    DlyTypeWithBLOBs selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(DlyTypeWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DlyTypeWithBLOBs record);

    int updateByPrimaryKey(DlyType record);

    List<DlyType> getList();
}