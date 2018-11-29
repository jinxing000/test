package com.ef.golf.service.impl;

import com.ef.golf.mapper.Area_dicMapper;
import com.ef.golf.pojo.Area_dic;
import com.ef.golf.service.AreaDicService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by xzw
 * 2018年2月2日16:43:00
 * 查询国内城市或国外城市
 */
@Repository
public class AreaDicServiceImpl implements AreaDicService {

    @Resource
    private Area_dicMapper areaDicMapper;


    @Override
    public List<Area_dic> selectAllCityInChina() {
        return areaDicMapper.selectAllCityInChina();
    }

    @Override
    public List<Area_dic> selectAllCityInForeign() {
        return areaDicMapper.selectAllCityInForeign();
    }
}
