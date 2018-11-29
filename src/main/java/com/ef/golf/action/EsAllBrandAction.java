package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.EsBranerService;
import com.ef.golf.service.GoodsService;
import com.ef.golf.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 商城的所有品牌
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsAllBrandAction extends AbstractService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private EsBranerService esBranerService;

    private String catId;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("catId" , catId);
        List<BrandVo> voList = esBranerService.getBrandListes(map);
        map.put("voList", voList);
        return map;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public EsBranerService getEsBranerService() {
        return esBranerService;
    }

    public void setEsBranerService(EsBranerService esBranerService) {
        this.esBranerService = esBranerService;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }
}
