package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.pojo.Activity;
import com.ef.golf.pojo.Brand;
import com.ef.golf.pojo.EsSubject;
import com.ef.golf.pojo.GoodsCat;
import com.ef.golf.service.*;
import com.ef.golf.vo.AdvVo;
import com.ef.golf.vo.GoodsHotSpecVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 尹金星 on 2018/10/23.
 * 之前的商城首页接口是分开请求的，
 * 之前对应的接口有
 * esBaner   轮播图的
 * esGoodsCat  商品分类的
 * esGoodsHot  热销商品
 * 现在合成一个接口
 *   新加了一个接口    商品专区
 *   上面的三个接口一并加入
 *
 *   总共4个接口
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShopIndexAction  extends AbstractService {


    //热销商品需要的参数

    //@NotNull(message = "pageNo不能为空")
    private Integer pageNo;
    //@NotNull(message = "pageSize不能为空")
    private Integer pageSize;

    @Autowired
    GoodsService esGoodsService;


    //商品分类需要的参数

    @Autowired
    EsGoodsCatService esGoodsCatService;

   /* public String getCat_id() {
        return cat_id;
    }*/


    private String cat_id;

  //  private Integer pageNo;   //热销商品里已经存在了

  //  private Integer pageSize; //热销商品里已经存在了


    //banner需要的

    @Autowired
    private EsBranerService esBranerService;
    @Autowired
    private EsSubjectService esSubjectService;

    @Autowired
    ActivityService ActivityService;


    @Override
    public Object doService() throws Exception {
//热销商品的代码
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);

        pageNo = (pageNo - 1) * pageSize;
        maps.put("pageNo", 0);
        maps.put("pageSize", 8);
        List<GoodsHotSpecVo> hotVoList = esGoodsService.getGoodsHotVoList(maps);
        //促销活动
        Activity activity = ActivityService.getActivity();
        int totalCount = esGoodsService.getGoodsHotVoCount(maps);
        Page<GoodsHotSpecVo> page = new Page<GoodsHotSpecVo>();
        page.setCurrentPage(pageNo);
        page.setShowCount(pageSize);
        page.setTotalResult(totalCount);
        page.setResultList(hotVoList);

        map.put("totalCount", totalCount);
        map.put("totalPage", page.getTotalPage());
        map.put("hotVoList", hotVoList);
        map.put("resultList", hotVoList);
        map.put("activityVo", activity  );
        //商品分类的代码
       // Map<String, Object> map = new HashMap<String, Object>();
      //  Map<String, Object> maps = new HashMap<String, Object>();

        if (StringUtils.isEmpty(cat_id)) {
            List<GoodsCat> firstlist = esGoodsCatService.list();

            map.put("firstList", firstlist);
        } else if (StringUtils.isNotEmpty(cat_id)) {
          //  pageNo = (pageNo - 1) * pageSize;
            maps.put("cat_id", cat_id);
            maps.put("pageNo", pageNo);
            maps.put("pageSize", pageSize);
            List<GoodsCat> secondeCat = esGoodsCatService.getCatList(maps);
            map.put("secondeCatList", secondeCat);
        }

        List<EsSubject> esSubjectList = esSubjectService.findSpecialList();

        //banner和品牌的的代码
        //Map<String, Object> map = new HashMap<String, Object>();
        List<AdvVo> advList = esBranerService.getAdvList();
        List<Brand> brandList = esBranerService.getBrandList();
        map.put("advList", advList);
        map.put("brandList", brandList);
        map.put("esSubjectList",esSubjectList);

        return map;

    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }
}
