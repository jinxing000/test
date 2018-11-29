package com.ef.golf.service;

import com.ef.golf.pojo.IntegralRecord;
import com.ef.golf.util.PageBean;

public interface IntegralRecordService {

    PageBean getIntegralRecord(Integer userId, Integer pageNo, Integer pageSize);
    Integer insertSelective(IntegralRecord integralRecord);
}
