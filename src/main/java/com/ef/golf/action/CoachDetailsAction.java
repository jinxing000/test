package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CommentService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.vo.CommentVo;
import com.ef.golf.vo.CourseDetailsVo;
import com.ef.golf.vo.SiteScoreVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by xzw
 * 2018年2月23日11:41:56
 * 查询课程详情
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CoachDetailsAction extends AbstractService {

    @Resource
    private CourseService CourseService;
    @Resource
    private ProductScoreService productScoreService;
    @Resource
    private CommentService commentService;

   //@NotNull(message = "CourseId Not Null!")
    private Integer id;

    //@NotNull(message = "pageNo不能为空")
    private Integer pageNo;
    //@NotNull(message = "pageSize不能为空")
    private Integer pageSize;

    @Override
    public Object doService() throws Exception {
        //基本信息
        Map<String,Object> courseDetails = new HashMap<>();
        CourseDetailsVo courseDetailsVo = CourseService.getCourseDetails(id);
        courseDetails.put("courseDetailsVo",courseDetailsVo);
        courseDetails.put("url",null!=courseDetailsVo.getImgs() ? courseDetailsVo.getImgs():"");


        Map<String, Object> maps = new HashMap<String, Object>();
        pageNo = (pageNo - 1) * pageSize;
        maps.put("productId", courseDetailsVo.getId());
        maps.put("commentType", 9);
        maps.put("userId", courseDetailsVo.getCoachId().toString());
        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);

        //评论
       /* commentService.*/
        //获取球场评论信息
       // List<SiteCommentVo> siteCommentVoList=commentService.selectBySiteId(id,uid);
        //先给前端一个数据格式，后期完善具体的获取课程评论

        List<CommentVo> courseCommentVoList=commentService.selectCommentsByProductIdAndCommentType(maps);
        if(courseCommentVoList!=null&&courseCommentVoList.size()>0){
            courseDetails.put("courseComments",courseCommentVoList);
        }

        //获取球场评分
        //获取评分  所有模块评分都共用 SiteScoreVo  map里的key可以根据具体模块自行取名
        //数据库表中ef_product_score 中的productType  与 ef_score_attr，ef_comments的类型码要保持一致
        // 产品类型(1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程)
        Map<String,Object> map1 =new HashMap<>();
        map1.put("productId",courseDetailsVo.getId());
        map1.put("productType",9);
        map1.put("attrAscription",9);
        List<SiteScoreVo> siteScoreVoList=productScoreService.selectScoreById(map1);
        if(siteScoreVoList!=null&&siteScoreVoList.size()>0){
            courseDetails.put("courseScore",siteScoreVoList);
        }


        return courseDetails;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCourseService(com.ef.golf.service.CourseService courseService) {
        CourseService = courseService;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
