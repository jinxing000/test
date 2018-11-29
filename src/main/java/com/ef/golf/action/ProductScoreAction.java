package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.vo.SiteScoreVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各个模块的产品评分项
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProductScoreAction extends AbstractService {
    @Resource
    private ProductScoreService productScoreService;


    //@NotNull(message = "productId不能为空")
    private Integer productId;//被评论对象的id，比如订场模块是场地的id，商城里是商品的id
    //@NotNull(message = "productType不能为空")
    private String productType;//评论类型（1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程）




    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        //获取评分  所有模块评分都共用 SiteScoreVo  map里的key可以根据具体模块自行取名
        //数据库表中ef_product_score 中的productType  与 ef_score_attr，ef_comments的类型码要保持一致
       // 产品类型(1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程)
        Map<String,Object> map1 =new HashMap<>();
        map1.put("productId",productId);
        map1.put("productType",productType);
        map1.put("attrAscription",productType);
        List<SiteScoreVo> siteScoreVoList=productScoreService.selectScoreById(map1);
            map.put("getScore",siteScoreVoList);


        return map;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
