package com.ef.golf.service.impl;

import com.ef.golf.mapper.AdvMapper;
import com.ef.golf.mapper.BrandMapper;
import com.ef.golf.pojo.Adv;
import com.ef.golf.pojo.Brand;
import com.ef.golf.service.EsBranerService;
import com.ef.golf.vo.AdvVo;
import com.ef.golf.vo.BrandVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EsBranerServiceImpl  implements EsBranerService{
    @Resource
    private AdvMapper advMapper;
    @Resource
    private BrandMapper brandMapper;


    @Override
    public List<AdvVo> getAdvList() {
        return advMapper.getList();
    }

    @Override
    public List<Brand> getBrandList() {
        return brandMapper.getList();
    }

    @Override
    public List<BrandVo> getBrandListes(Map params) {
        return brandMapper.getBrandList(params);
    }
}
