package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsRegionsMapper;
import com.ef.golf.pojo.EsRegions;
import com.ef.golf.service.EsRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EsRegionServiceImpl implements EsRegionService {
    @Autowired
    EsRegionsMapper regionsMapper;
    @Override
    public List<EsRegions> getRegionList(Integer p_region_id) {

        return regionsMapper.getTreeNodes(p_region_id);
    }
}
