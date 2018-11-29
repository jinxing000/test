package com.ef.golf.mapper;

import com.ef.golf.pojo.CustomService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomService record);

    int insertSelective(CustomService record);

    CustomService selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomService record);

    int updateByPrimaryKey(CustomService record);

    List<CustomService> findCustomServiceAll(@Param("csType") Integer csType);
}