package com.ef.golf.service.impl;

import com.ef.golf.mapper.IntegralRecordMapper;
import com.ef.golf.pojo.IntegralRecord;
import com.ef.golf.service.IntegralRecordService;
import com.ef.golf.util.PageBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository
public class IntegralRecordServiceImpl implements IntegralRecordService {
    @Resource
    private IntegralRecordMapper integralRecordMapper;

    @Override
    public PageBean getIntegralRecord(Integer userId,Integer pageNo,Integer pageSize) {
        PageBean pageBean = new PageBean();

        Map<String,Object>map = new HashMap<>();
        map.put("userId",userId);
        Integer count = integralRecordMapper.getIntegralRecordCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<IntegralRecord>list = integralRecordMapper.getIntegralRecord(map);

        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public Integer insertSelective(IntegralRecord integralRecord) {
        return integralRecordMapper.insertSelective(integralRecord);
    }
}
