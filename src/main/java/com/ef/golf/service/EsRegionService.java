package com.ef.golf.service;

import com.ef.golf.pojo.EsRegions;

import java.util.List;

public interface EsRegionService {
    public List<EsRegions> getRegionList(Integer p_region_id);
}
