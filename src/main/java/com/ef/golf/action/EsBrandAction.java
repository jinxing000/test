package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.GoodsService;
import com.ef.golf.vo.CatVo;
import com.ef.golf.vo.GoodsCatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 品牌下面的商品
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsBrandAction extends AbstractService {
    @Autowired
    private GoodsService goodsService;

    private Integer cat_id;
    //@NotNull(message = "cost不能为空")
    private Integer cost;
    //@NotNull(message = "pageNo不能为空")
    private Integer pageNo;

    //@NotNull(message = "pageSize不能为空")
    private Integer pageSize;
    /*@NotNull(message = "brand_id不能为空")*/
    private Integer brand_id;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);

        pageNo = (pageNo - 1) * pageSize;


        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);
        maps.put("cost", cost);
        //获取一级分类
        List<CatVo> brandList1 = goodsService.brandFirstCat(brand_id);
        List<CatVo> brandList = new ArrayList<>();
        if(brandList1 !=null &&brandList1.size() >0){

            CatVo catVo = new CatVo();
            catVo.setName("全部");
            catVo.setCat_id(0);
            brandList.add(catVo);
            for (CatVo envent :brandList1){
                brandList.add(envent);
            }
        }else {
            brandList = brandList1;
        }

        //通过品牌获取对应商品的分类
        if (cat_id == null || cat_id == 0) {
           /* if(brandList.size()==0){
                maps.put("cat_id", 0);
            }else{
                cat_id = brandList.get(0).getCat_id();
                maps.put("cat_id", cat_id);
            }*/
            maps.put("cat_id", null);

        } else {
            maps.put("cat_id", cat_id);
        }

        maps.put("brand_id",brand_id);
        List<GoodsCatVo> goodsList = goodsService.brandGoodsList(maps);
        Map<String,Object> mapCount=new HashMap<>();
        mapCount.put("cat_id",maps.get("cat_id"));
        mapCount.put("brand_id",brand_id);
        int totalCount = goodsService.brandGoodsCount(mapCount);
        Page<GoodsCatVo> page = new Page<GoodsCatVo>();
        page.setCurrentPage(pageNo);
        page.setShowCount(pageSize);

        page.setResultList(goodsList);
        page.setTotalResult(totalCount);
        map.put("brandLIst", brandList);
        map.put("goodsList", goodsList);
        map.put("resultList", goodsList);
        map.put("totalCount", totalCount);
        map.put("totalPage", page.getTotalPage());
        return map;
    }


    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

}
