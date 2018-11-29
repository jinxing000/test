package com.ef.golf.service.impl;

import com.ef.golf.mapper.TravelLineMapper;
import com.ef.golf.mapper.TravelSubjectMapper;
import com.ef.golf.pojo.TravelSubject;
import com.ef.golf.service.SubjectTravelLineService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.TravelLineVo;
import com.ef.golf.vo.TravelSubjectLineVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/11/14 13:59
 */
@Service
public class SubjectTravelLineServiceImpl implements SubjectTravelLineService {

    @Autowired
    private TravelSubjectMapper travelSubjectMapper;
    @Autowired
    private TravelLineMapper travelLineMapper;

    @Override
    public List<TravelSubjectLineVo> getSubjectAndSubjectTravel() {
        List<TravelSubjectLineVo>list = travelSubjectMapper.getSubjectAndSubjectTravel();
        return list;
    }

    @Override
    public PageBean getTravelBuSubjectId(Integer subjectId, Integer pageNo, Integer pageSize) {

        PageBean pageBean = new PageBean();

        List<TravelLineVo>travelLineVos = travelSubjectMapper.getTravelBuSubjectId(subjectId,(pageNo-1)*pageSize,pageSize);
        for (TravelLineVo travelLineVo : travelLineVos){
            List<String> pictures = travelLineMapper.getPictureByTravelLineId(travelLineVo.getLineId());
            travelLineVo.setPicture(pictures.get(0));
        }
        Integer count = travelSubjectMapper.getTravelBuSubjectIdCount(subjectId);
        TravelSubject travelSubject = travelSubjectMapper.selectByPrimaryKey(subjectId);
        pageBean.setTotalCount(count);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setResultList(travelLineVos);
        pageBean.setSubjectName(travelSubject.getSubjectName());
        return pageBean;
    }
}
