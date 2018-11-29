package com.ef.golf.service;


import com.ef.golf.pojo.Report;

import java.util.List;

/**
 * create by xzw
 * 2017年12月28日14:06:18
 * 举报表接口
 */
public interface ReportService {

    //新增举报记录
    int insertSelective(Report record);

    List<Integer> getByReportList(Integer userId);
}
