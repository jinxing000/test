package com.ef.golf.service.impl;

import com.ef.golf.mapper.DlyTypeMapper;
import com.ef.golf.pojo.DlyType;
import com.ef.golf.service.EsDlyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsDlyTypeServiceImpl implements EsDlyTypeService {
    @Autowired
    DlyTypeMapper dlyTypeMapper;

    @Override
    public List<DlyType> getList() {
        return dlyTypeMapper.getList();
    }
}
