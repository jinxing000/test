package com.ef.golf.service;

import com.ef.golf.pojo.EsSubject;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/10/23 14:30
 */
public interface EsSubjectService {

    List<EsSubject> findSpecialList();

    EsSubject findSpecialListBySubjectId(Integer subjectId);
}
