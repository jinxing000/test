package com.ef.golf.mapper;

import com.ef.golf.pojo.ProductSnapshot;

public interface ProductSnapshotMapper {
    int insert(ProductSnapshot record);

    int insertSelective(ProductSnapshot record);
}