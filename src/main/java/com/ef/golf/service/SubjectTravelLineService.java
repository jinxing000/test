package com.ef.golf.service;

import com.ef.golf.util.PageBean;
import com.ef.golf.vo.TravelSubjectLineVo;

import java.util.List;

/**
 * com.ef.golf.service
 * Administrator
 * 2018/11/14 13:59
 */
public interface SubjectTravelLineService {

    List<TravelSubjectLineVo> getSubjectAndSubjectTravel();

    PageBean getTravelBuSubjectId(Integer subjectId,Integer pageNo,Integer pageSize);
}
