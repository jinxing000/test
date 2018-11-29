package com.ef.golf.mapper;

import com.ef.golf.pojo.EsSubject;

import java.util.List;

public interface EsSubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EsSubject record);

    int insertSelective(EsSubject record);

    EsSubject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EsSubject record);

    int updateByPrimaryKeyWithBLOBs(EsSubject record);

    int updateByPrimaryKey(EsSubject record);

    List<EsSubject> findSpecialList();
    EsSubject findSpecialListBySubjectId(Integer subjectId);
}