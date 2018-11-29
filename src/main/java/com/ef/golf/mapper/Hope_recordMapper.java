package com.ef.golf.mapper;

import com.ef.golf.pojo.Hope_record;
import com.ef.golf.vo.QuanziRealizeHopeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Hope_recordMapper {
    int deleteByPrimaryKey(Integer hopeRecordId);

    int insert(Hope_record record);

    int insertSelective(Hope_record record);

    Hope_record selectByPrimaryKey(Integer hopeRecordId);

    int updateByPrimaryKeySelective(Hope_record record);

    int updateByPrimaryKey(Hope_record record);

    //根据动态id查询支持愿望的人的信息
    List<QuanziRealizeHopeVo> getSupportList(@Param("hopeId") Integer hopeId,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    int getSupportListCount(Integer hopeId);
}