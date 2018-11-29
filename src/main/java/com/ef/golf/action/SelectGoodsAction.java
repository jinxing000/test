package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.GoodsService;
import com.ef.golf.vo.GoodsHotSpecVo;
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
 * 商城的搜寻
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SelectGoodsAction extends AbstractService {
    @Autowired
    GoodsService goodsService;
    //@NotNull(message = "goodsName不能为空")
    private String goodsName;
    //@NotNull(message = "pageNo不能为空")
    private Integer pageNo;
    //@NotNull(message = "pageSize不能为空")
    private Integer pageSize;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        map.put("code", "200");
        Integer oldPageNo=pageNo;
        pageNo = (pageNo - 1) * pageSize;

        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);
        maps.put("goodsName", goodsName.toLowerCase());//统一为小写查询

        Page<GoodsHotSpecVo> page = new Page<GoodsHotSpecVo>();
        page.setCurrentPage(oldPageNo);
        page.setShowCount(pageSize);

        GoodsHotSpecVo goods=new GoodsHotSpecVo();
        goods.setCurrentPage(oldPageNo);
        goods.setShowCount(pageSize);
        goods.setName(goodsName.toLowerCase());

       List<GoodsHotSpecVo> goodsList = goodsService.getSelectGoodsListPage(goods);
     //   int count = goodsService.firstCount(cat_id);

        page.setResultList(goodsList);
        page.setTotalResult(goods.getTotalResult());
        map.put("pageNo", oldPageNo);
        map.put("pageSize", page.getShowCount());
        map.put("totalPage", page.getTotalPage());
        map.put("count", page.getTotalResult());
        map.put("resultList", goodsList);


        map.put("goodsList", goodsList);


        return map;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
