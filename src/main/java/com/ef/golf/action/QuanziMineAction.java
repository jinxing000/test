package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Coach_course;
import com.ef.golf.pojo.Hope;
import com.ef.golf.pojo.Photo_album;
import com.ef.golf.service.*;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by xzw
 * 2018年1月8日13:39:13
 * 圈子：通过userid获取个人信息
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziMineAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;


    @Resource
    private CollectService collectService;

    @Resource
    private UserService userService;

    @Resource
    private CourseService courseService;

    @Resource
    private HopeService hopeService;

    @Resource
    private PhotoAlbumService photoAlbumService;

    @Resource
    private DynamicService dynamicService;

    @Resource
    private ProductScoreService productScoreService;

    @Resource
    private DynamicPraiseService dynamicPraiseService;

    @Resource
    private ShareService shareService;

    @Resource
    private DynamicCommentService dynamicCommentService;

    @Resource
    private Gift_giveService giftGiveService;

    @Resource
    private CommentService commentService;

    @Resource
    private RedisBaseDao redisBaseDao;

    //@NotNull
    private Integer quanziUserId;

    private String lat = "40.0497810000";

    private String lng = "116.3424590000";

    private Integer pageNum = 1;

    private Integer showCount = 5;


    private String uid;

    private String sid;

    private Integer userId;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> persionInfo = new HashMap<>();
       /* String rUID = redisBaseDao.get(sid);
        if(StringUtils.isEmpty(rUID)||!rUID.equals(uid))
            throw new LoginException(Constant.ERR_USER);

        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);//登录者ID*/
        //1.获取用户信息
        QuanziUserVo quanziUser = userService.getUserInfoById(quanziUserId);
        //2.获取未实现的愿望
        List<Hope> getUnrealizedHopeList = hopeService.getUnrealizedHopes(quanziUserId);
        //3.获取相册
        List<Photo_album> photoAlbum = photoAlbumService.selectByUserId(quanziUserId);
        if(null!=userId){
        CollectPersionsVo collectPersionsVo = new CollectPersionsVo();
        collectPersionsVo.setUserId(userId);
        collectPersionsVo.setCurrentPage(1);
        collectPersionsVo.setShowCount(100);
        /*List<CollectPersionsVo> getCollectPersionList=new ArrayList<>();*/
        //查询我关注的人
        List<CollectPersionsVo> getCollectPersionList = collectService.getCollectPersionListPage(collectPersionsVo);
        List<Integer> list = new ArrayList<>();
            for (CollectPersionsVo collectPersions:getCollectPersionList
                 ) {
                list.add(collectPersions.getUserId());
            }
        if (getCollectPersionList.size() == 0) {
            quanziUser.setHasAttend("0");//未关注
        } else {
            /*for (int i = 0; i < getCollectPersionList.size(); i++) {*/
                //判断关注我的人，我是否关注了ta
                //boolean flag = getCollectPersionList.get(i).getUserId() == quanziUserId;
                boolean flag = list.contains(quanziUserId);
                if (!flag) {
                    quanziUser.setHasAttend("0");//未关注
                } else {
                    quanziUser.setHasAttend("1");//已关注
                }
        }
        }else{
            quanziUser.setHasAttend("0");
        }


       /*//4.获取用户的动态
       User user=new User();
        user.setId(Long.valueOf(this.userId));
        user.setLat(this.lat);
        user.setLng(this.lng);
        user.setCurrentPage(this.pageNum);
        user.setShowCount(this.showCount);*/
       /* List<DynamicVo> getDynamicList=dynamicService.getDynamicByUserIdListPage(user);
        if(getDynamicList.size()>0){
            //分割图片
            for(int i=0;i<getDynamicList.size();i++){
                String imgUrl=getDynamicList.get(i).getImgUrl();
                if(StringUtils.isNotEmpty(imgUrl)){
                    String str[]=imgUrl.split(";");
                    getDynamicList.get(i).setDynamicImgs(str);
                }
                //获取动态id
                int dynamicId=getDynamicList.get(i).getDynamicId();
                //获取点赞记录
                getDynamicList.get(i).setDynamicPraises(dynamicPraiseService.getPraises(dynamicId));
                //获取转发记录
                getDynamicList.get(i).setDynamicComments(dynamicCommentService.getComments(dynamicId));
                //获取评论记录
                getDynamicList.get(i).setDynamicShares(shareService.getShares(dynamicId));
                //获取礼物记录
                getDynamicList.get(i).setDynamicGift(giftGiveService.getGifts(dynamicId));
            }
        }*/

        //5.获取用户综合评分
        List<QuanziScoreVo> getScore = productScoreService.getScore(quanziUserId);
        persionInfo.put("userInfo", quanziUser);

        //if("2".equals(quanziUser.getUserType())||"3".equals(quanziUser.getUserType())) {
            //获取用户信息
            Map<String, Object> maps = new HashMap<String, Object>();
            //前端没有传递该参数，暂时自行处理
            Integer pageNo = 1;
            Integer pageSize = 5;
            pageNo = (pageNo - 1) * pageSize;
            maps.put("productId", quanziUser.getUserId());
            maps.put("commentType", quanziUser.getUserType());
            maps.put("userId", userService.getUid(uid));
            maps.put("pageNo", pageNo);
            maps.put("pageSize", pageSize);
            List<CommentVo> personComment = commentService.selectCommentsByProductIdAndCommentType(maps);
            //if (personComment != null && personComment.size() > 0) {
                persionInfo.put("comment", personComment);
            //}
            //获取球场评分
            //获取评分  所有模块评分都共用 SiteScoreVo  map里的key可以根据具体模块自行取名
            //数据库表中ef_product_score 中的productType  与 ef_score_attr，ef_comments的类型码要保持一致
            // 产品类型(1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程)
            Map<String, Object> map1 = new HashMap<>();
            map1.put("productId", quanziUser.getUserId());
            map1.put("productType", quanziUser.getUserType());
            map1.put("attrAscription", quanziUser.getUserType());
            List<SiteScoreVo> siteScoreVoList = productScoreService.selectScoreById(map1);
            //if (siteScoreVoList != null && siteScoreVoList.size() > 0) {
                persionInfo.put("score", siteScoreVoList);
                persionInfo.put("getScore", getScore);
           // }
        //}

        //6.我关注的用户数
        Integer mineGuanzhu = collectService.getCollectPersionNum(quanziUserId);
        //7.我的粉丝数
        Integer fans = collectService.getCollectMineNum(quanziUserId);
        if (quanziUser.getUserType().equals("2")) {
            CoachCourseVo coach_course = new CoachCourseVo();
            coach_course.setCurrentPage(pageNum);
            coach_course.setShowCount(5);
            coach_course.setRecommend("1");
            /** 获取当前教练课程 */
            List<CoachCourseVo> coach_courses = new ArrayList<>();
            PageBean pageBean = courseService.getCoachCourseLists(quanziUserId, 1, 5);
            List<Coach_course> list = pageBean.getResultList();
            for (Coach_course cc:list
                 ) {

                    CoachCourseVo ccv = new CoachCourseVo();
                    ccv.setId(cc.getId());
                    ccv.setCourseName(cc.getCourseName());
                    ccv.setCourseType(cc.getCourseType());
                    ccv.setCoachName(cc.getCoachName());
                    ccv.setPrice(cc.getPrice());
                    ccv.setImgUrl(cc.getImgUrl());
                    coach_courses.add(ccv);
                }

            if (coach_courses.size() > 0) {
                persionInfo.put("coach_courses", coach_courses);
            }
        }


        /*if (getUnrealizedHopeList.size() > 0) {*/
            persionInfo.put("UnrealizedHope", getUnrealizedHopeList);
       /* }*/
        /*if (photoAlbum.size() > 0) {*/
            persionInfo.put("photoAlbum", photoAlbum);
       /* }*/
      /* if(getDynamicList.size()>0){
            persionInfo.put("getDynamicList",getDynamicList);
        }*/
        /*if (getScore.size() > 0) {*/

        /*}*/

        if (mineGuanzhu == null) {
            persionInfo.put("mineGuanzhu", 0);
        } else {
            persionInfo.put("mineGuanzhu", mineGuanzhu);
        }


        if (fans == null) {
            persionInfo.put("fans", 0);
        } else {
            persionInfo.put("fans", fans);
        }
        if(null!=userId){
            List<Integer>blackUsers = userService.getByBlackPersonByUserId(userId);
            if(blackUsers.contains(quanziUserId)){
                persionInfo.put("isInBlack",true);
            }else{
                persionInfo.put("isInBlack",false);
            }
        }else{
            persionInfo.put("isInBlack",false);
        }

       /* List<Map>list = new ArrayList<>();
        list.add(persionInfo);
        Page page = new Page();
            page.setResultList(list);*/
        return persionInfo;
    }

    public void setQuanziUserId(Integer quanziUserId) {
        this.quanziUserId = quanziUserId;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
