package com.ef.golf.service.impl;

import com.ef.golf.mapper.Hope_recordMapper;
import com.ef.golf.pojo.Hope_record;
import com.ef.golf.service.HopeRecordService;
import com.ef.golf.vo.QuanziRealizeHopeVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository
public class HopeRecordServiceImpl implements HopeRecordService {

    @Resource
    private Hope_recordMapper hopeRecordMapper;
    @Override
    public List<QuanziRealizeHopeVo> getSupportList(Integer hopeId,Integer pageNo,Integer pageSize) {
        pageNo = (pageNo-1)*pageSize;
        return hopeRecordMapper.getSupportList(hopeId,pageNo,pageSize);
    }

    @Override
    public int getSupportListCount(Integer hopeId) {
        return hopeRecordMapper.getSupportListCount(hopeId);
    }

    @Override
    public int insertSelective(Hope_record record) {
        return hopeRecordMapper.insertSelective(record);
    }
}
