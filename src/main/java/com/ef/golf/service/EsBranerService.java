package com.ef.golf.service;

import com.ef.golf.pojo.Adv;
import com.ef.golf.pojo.Brand;
import com.ef.golf.vo.AdvVo;
import com.ef.golf.vo.BrandVo;
import com.sun.org.apache.xerces.internal.impl.dv.xs.AnySimpleDV;

import java.util.List;
import java.util.Map;

public interface EsBranerService {
    List<AdvVo> getAdvList ();

    List<Brand> getBrandList();

    List<BrandVo>getBrandListes(Map params);
}
