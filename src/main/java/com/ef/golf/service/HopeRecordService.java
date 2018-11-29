package com.ef.golf.service;

import com.ef.golf.pojo.Hope_record;
import com.ef.golf.vo.QuanziRealizeHopeVo;

import java.util.List;

public interface HopeRecordService {

    //根据动态id查询支持愿望的人的信息
    List<QuanziRealizeHopeVo> getSupportList(Integer hopeId,Integer pageNo,Integer pageSize);
    int getSupportListCount(Integer hopeId);
    int insertSelective(Hope_record record);
}
