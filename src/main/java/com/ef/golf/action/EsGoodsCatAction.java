package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.GoodsCat;
import com.ef.golf.service.EsGoodsCatService;
import com.ef.golf.vo.GoodsCatVo;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 商城首页商品一级分类,
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsGoodsCatAction extends AbstractService {

    @Autowired
    EsGoodsCatService esGoodsCatService;

   /* public String getCat_id() {
        return cat_id;
    }*/


    private String cat_id;

    private Integer pageNo;

    private Integer pageSize;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();

        // map.put("code","200");
        if (StringUtils.isEmpty(cat_id)) {
            List<GoodsCat> firstlist = esGoodsCatService.list();

            map.put("firstlist", firstlist);
        } else if (StringUtils.isNotEmpty(cat_id)) {
            pageNo = (pageNo - 1) * pageSize;
            maps.put("cat_id", cat_id);
            maps.put("pageNo", pageNo);
            maps.put("pageSize", pageSize);
            List<GoodsCat> secondeCat = esGoodsCatService.getCatList(maps);
            map.put("secondeCatList", secondeCat);
        }/*else if(StringUtils.isNotEmpty(cat_id)&&cat_id.equals("all")){
            List<GoodsCatVo> GoodsCatVoList = esGoodsCatService.getGoodsCatVo(cat_id);
            map.put("GoodsCatVoList",GoodsCatVoList);
        } else if(StringUtils.isNotEmpty(cat_id)&&!cat_id.equals("all")){
            List<GoodsCatVo> CatVoList = esGoodsCatService.getGoodsCatVo(cat_id);
            map.put("CatVoList",CatVoList);
        }*/


        return map;


    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
