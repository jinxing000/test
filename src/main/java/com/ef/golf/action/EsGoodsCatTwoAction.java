package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.EsGoodsCatService;
import com.ef.golf.vo.GoodsCatVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsGoodsCatTwoAction extends AbstractService {
    private String both;
    //@NotNull(message = "pageNo不能为空")
    private Integer pageNo;
    //@NotNull(message = "pageSize不能为空")
    private Integer pageSize;
    //@NotNull(message = "cat_id不能为空")
    private Integer cat_id;
    @Autowired
    EsGoodsCatService esGoodsCatService;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        map.put("code", "200");
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        pageNo = (pageNo - 1) * pageSize;
        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);
        maps.put("cat_id", cat_id);
        Page<GoodsCatVo> page = new Page<GoodsCatVo>();
        page.setCurrentPage(pageNo);
        page.setShowCount(pageSize);
        if (StringUtils.isNotEmpty(both) && both.equals("all")) {
            int firstCount = esGoodsCatService.firstCount(cat_id);
            List<GoodsCatVo> GoodsCatVoList = esGoodsCatService.getGoodsCatVo(maps);//第一次请求一个一级分类下的商品
            page.setResultList(GoodsCatVoList);
            page.setTotalResult(firstCount);
            map.put("GoodsCatVoList", GoodsCatVoList);
            map.put("page", page);
        } else {
            int sercondCount = esGoodsCatService.sencondCount(cat_id);
            List<GoodsCatVo> GoodsCatVoList = esGoodsCatService.getSecondGoodsCatVo(map);
            page.setResultList(GoodsCatVoList);
            page.setTotalResult(sercondCount);
            map.put("page", page);
            map.put("GoodsCatVoList", GoodsCatVoList);
        }
        return map;
    }

    public void setBoth(String both) {
        this.both = both;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }
}
