package com.ef.golf.mapper;

import com.ef.golf.pojo.Brand;
import com.ef.golf.pojo.BrandWithBLOBs;
import com.ef.golf.vo.BrandVo;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer brandId);

    int insert(BrandWithBLOBs record);

    int insertSelective(BrandWithBLOBs record);

    BrandWithBLOBs selectByPrimaryKey(Integer brandId);

    int updateByPrimaryKeySelective(BrandWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BrandWithBLOBs record);

    int updateByPrimaryKey(Brand record);
    List<Brand> getList();
    List<BrandVo>getBrandList(Map pamrams);
}