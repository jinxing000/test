package com.ef.golf.mapper;

import com.ef.golf.pojo.TravelSubject;
import com.ef.golf.vo.TravelLineVo;
import com.ef.golf.vo.TravelSubjectLineVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TravelSubjectMapper {
    int deleteByPrimaryKey(Integer subjectId);

    int insert(TravelSubject record);

    int insertSelective(TravelSubject record);

    TravelSubject selectByPrimaryKey(Integer subjectId);

    int updateByPrimaryKeySelective(TravelSubject record);

    int updateByPrimaryKey(TravelSubject record);

    List<TravelSubjectLineVo> getSubjectAndSubjectTravel();

    List<TravelLineVo>getTravelBuSubjectId(@Param("subjectId") Integer subjectId, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
    Integer getTravelBuSubjectIdCount(Integer subjectId);
}