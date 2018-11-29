package com.ef.golf.mapper;

import com.ef.golf.pojo.DlyTypeArea;
import com.ef.golf.pojo.DlyTypeAreaWithBLOBs;

import java.util.List;

public interface DlyTypeAreaMapper {
    int insert(DlyTypeAreaWithBLOBs record);

    int insertSelective(DlyTypeAreaWithBLOBs record);

    List<DlyTypeArea> getList(Integer type_id);
}