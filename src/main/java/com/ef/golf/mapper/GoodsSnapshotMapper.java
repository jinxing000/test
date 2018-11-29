package com.ef.golf.mapper;

import com.ef.golf.pojo.GoodsSnapshot;
import com.ef.golf.pojo.GoodsSnapshotWithBLOBs;

import java.util.List;

public interface GoodsSnapshotMapper {
    int deleteByPrimaryKey(Integer snapshotId);

    int insert(GoodsSnapshotWithBLOBs record);

    int insertSelective(GoodsSnapshot record);

    GoodsSnapshot selectByPrimaryKey(Integer snapshotId);

    int updateByPrimaryKeySelective(GoodsSnapshot record);

    int updateByPrimaryKeyWithBLOBs(GoodsSnapshotWithBLOBs record);

    int updateByPrimaryKey(GoodsSnapshot record);

    int getMax();
    List<GoodsSnapshot> getList(Integer goods_id);
}