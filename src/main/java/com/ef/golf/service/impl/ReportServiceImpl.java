package com.ef.golf.service.impl;

import com.ef.golf.mapper.ReportMapper;
import com.ef.golf.pojo.Report;
import com.ef.golf.service.ReportService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by xzw
 * 2017年12月28日14:08:20
 * 举报表
 */
@Repository
public class ReportServiceImpl implements ReportService {

    @Resource
    private ReportMapper reportMapper;


    @Override
    public int insertSelective(Report record) {
        return reportMapper.insertSelective(record);
    }

    @Override
    public List<Integer> getByReportList(Integer userId) {
        return reportMapper.getByReportList(userId);
    }
}
