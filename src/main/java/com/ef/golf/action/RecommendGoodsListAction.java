package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.GoodsService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.GoodsHotSpecVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 商品推荐列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RecommendGoodsListAction extends AbstractService {
    private Integer pageNo = 1;
    private Integer pageSize = 8;

    @Resource
    private GoodsService esGoodsService;

    @Override
    public Object doService() throws Exception {

        PageBean pageBean = esGoodsService.getGoodsRecommendList(pageNo,pageSize);

        return pageBean;
    }


    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
