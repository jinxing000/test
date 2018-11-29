package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CommentService;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.vo.CommentVo;
import com.ef.golf.vo.SiteScoreVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 各个模块的产品评论列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MoreCommentsListAction extends AbstractService {
    @Resource
    private CommentService commentService;

    @Resource
    private ProductScoreService productScoreService;

    //@NotNull(message = "productId不能为空")
    private Integer productId;//被评论对象的id，比如订场模块是场地的id，商城里是商品的id
    //@NotNull(message = "pageNo不能为空")
    private Integer pageNo;
    //@NotNull(message = "pageSize不能为空")
    private Integer pageSize;
    //@NotNull(message = "commentType不能为空")
    private String commentType;//评论类型（1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程）

    private String userId;



    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        pageNo = (pageNo - 1) * pageSize;
        maps.put("productId", productId);
        maps.put("commentType", commentType);
        maps.put("userId", userId);
        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);

       // int commentCount = goodsService.commentCount(goods_id);//某一商品评论总数
       /* List<CommentVo> commentList = goodsService.commentList(maps);//商品评论列表*/

        int commentCount =commentService.getCommentCountByProductIdAndCommentType(maps);
        //获取评论信息

        List<CommentVo> siteCommentVoList=commentService.selectCommentsByProductIdAndCommentType(maps);




        //获取评分  所有模块评分都共用 SiteScoreVo  map里的key可以根据具体模块自行取名
        //数据库表中ef_product_score 中的productType  与 ef_score_attr，ef_comments的类型码要保持一致
       // 产品类型(1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程)
        Map<String,Object> map1 =new HashMap<>();
        map1.put("productId",productId);
        map1.put("productType",commentType);
        map1.put("attrAscription",commentType);
        List<SiteScoreVo> siteScoreVoList=productScoreService.selectScoreById(map1);

            map.put("getScore",siteScoreVoList);

       /* List<CommentPrictureVo> commentPrictureVo = goodsService.pictureList(goods_id);//商品评论图片列表*/

        map.put("totalCount", commentCount);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        map.put("totalPage", commentCount%pageSize==0?commentCount/pageSize:(commentCount/pageSize+1));
        map.put("resultList", siteCommentVoList);

        return map;
    }




    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }
}
