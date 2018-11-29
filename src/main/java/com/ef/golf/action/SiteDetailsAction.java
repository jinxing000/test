package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Collect;
import com.ef.golf.pojo.Img;
import com.ef.golf.pojo.PlatformWorktime;
import com.ef.golf.pojo.Site;
import com.ef.golf.service.*;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.CommentVo;
import com.ef.golf.vo.SiteScoreVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 根据球场ID，查询球场详细信息
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SiteDetailsAction extends AbstractService{

    @Resource
    private TicketService ticketService;

    @Resource
    private ImgService imgService;

    @Resource
    private SiteService siteService;

    @Resource
    private CommentService commentService;

    @Resource
    private ProductScoreService productScoreService;

    @Resource
    private CollectService collectService;

    @Resource
    private UserService userService;

    @Resource
    private MemberApproveService memberApproveService;

    @Resource
    private PlatformWorktimeService platformWorktimeService;

    @Resource
    private RedisBaseDao redisBaseDao;

    //@NotNull(message = "球场编号不能为空")
    private String siteId;

    private String sid;

    private String uid;

    private String imgType;



    public Object doService(){

        //存放查询的球场详细信息
        Map<String,Object> siteDetails=new HashMap<String,Object>();
        //获取球场详情
        Site site=siteService.selectByPrimaryKey(Integer.parseInt(siteId));
        List<String> services=new ArrayList<>();
        if(site!=null){
            //2018.3.16 start
            siteDetails.put("siteId",site.getSiteId());
            siteDetails.put("siteParam",site.getSiteParam());
            siteDetails.put("siteName",site.getReserve4());
            siteDetails.put("sitePhone",site.getSitePhone());
            siteDetails.put("siteIntroduction",site.getSiteIntroduction());
            siteDetails.put("siteCity",site.getSiteCity());
            siteDetails.put("siteAddress",site.getSiteAddress());
            siteDetails.put("siteModel",site.getSiteModel());
            siteDetails.put("siteCreateTime",site.getSiteCreateTime());
            siteDetails.put("siteArea",site.getSiteArea());
            siteDetails.put("fairwayGrassType",site.getFairwayGrassType());
            siteDetails.put("siteDesigner",site.getSiteDesigner());
            siteDetails.put("greenGrassType",site.getGreenGrassType());
            siteDetails.put("fairwayLength",site.getFairwayLength());
            siteDetails.put("quihuiId",site.getQiuHuiId());
            siteDetails.put("reserve1",site.getReserve1());
            siteDetails.put("reserve2",site.getReserve2());
            siteDetails.put("weekdaysEfMemberPrice",site.getWeekdaysEfMemberPrice());
            siteDetails.put("weekdaysSiteMemberPrice",site.getWeekdaysSiteMemberPrice());
            siteDetails.put("weekdaysCustomerPrice",site.getWeekdaysCustomerPrice());
            siteDetails.put("holidayEfMemberPrice",site.getHolidayEfMemberPrice());
            siteDetails.put("holidaySiteMemberPrice",site.getHolidaySiteMemberPrice());
            siteDetails.put("holidayCustomerPrice",site.getHolidayCustomerPrice());
            //2018.3.16 end
          String service1=site.getService1();
          String service2=site.getService2();
          String service3=site.getService3();
          String service4=site.getService4();
          String service5=site.getService5();
          String service6=site.getService6();
          String service7=site.getService7();
          String service8=site.getService8();
          String service9=site.getService9();
          if(StringUtils.isNotEmpty(service1)){
                services.add(service1);
            }
            if(StringUtils.isNotEmpty(service2)){
                services.add(service2);
            }
            if(StringUtils.isNotEmpty(service3)){
                services.add(service3);
            }
            if(StringUtils.isNotEmpty(service4)){
                services.add(service4);
            }
            if(StringUtils.isNotEmpty(service5)){
                services.add(service5);
            }
            if(StringUtils.isNotEmpty(service6)){
                services.add(service6);
            }
            if(StringUtils.isNotEmpty(service7)){
                services.add(service7);
            }
            if(StringUtils.isNotEmpty(service8)){
                services.add(service8);
            }
            if(StringUtils.isNotEmpty(service9)){
                services.add(service9);
            }


        }
        //获取球场当日价格
        Double price =  siteService.getNowPrice(Integer.valueOf(siteId),new Date());
        siteDetails.put("siteNowPrice",price);
        //获取球场评论信息
        Map<String, Object> maps = new HashMap<String, Object>();
        //前端没有传递该参数，暂时自行处理
        Integer pageNo=1;
        Integer pageSize=5;
        pageNo = (pageNo - 1) * pageSize;
        maps.put("productId", Integer.parseInt(siteId));
        maps.put("commentType", 1);
        maps.put("userId", userService.getUid(uid));
        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);
        List<CommentVo> siteCommentVoList=commentService.selectCommentsByProductIdAndCommentType(maps);
        if(siteCommentVoList!=null&&siteCommentVoList.size()>0){
            siteDetails.put("siteComments",siteCommentVoList);
        }

        //获取球场评分
        //获取评分  所有模块评分都共用 SiteScoreVo  map里的key可以根据具体模块自行取名
        //数据库表中ef_product_score 中的productType  与 ef_score_attr，ef_comments的类型码要保持一致
        // 产品类型(1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程)
        Map<String,Object> map1 =new HashMap<>();
        map1.put("productId",Integer.parseInt(siteId));
        map1.put("productType",1);
        map1.put("attrAscription",1);
        List<SiteScoreVo> siteScoreVoList=productScoreService.selectScoreById(map1);
        if(siteScoreVoList!=null&&siteScoreVoList.size()>0){
            siteDetails.put("siteScore",siteScoreVoList);
        }

        //获取用户是否登录
        boolean is_Collect=false;
        Long user_id=null;
        if(StringUtils.isNotEmpty(sid)){
            if (StringUtils.isNotEmpty(redisBaseDao.get(this.sid))){
                    user_id=userService.getUid(this.uid);
                    //判断是否收藏过该球场
                    Collect collect =new Collect();
                    collect.setUserId(user_id);
                    collect.setCollectType("1");
                    collect.setProductId(Integer.parseInt(siteId));
                    if(collectService.getCollectSiteByUserId(collect)>0)
                        is_Collect=true;
            }
        }

        //获取用户优惠券
        /*List<TicketVo> ticksList=null;
        if(user_id!=null){
            if(StringUtils.isNotEmpty( user_id.toString())){
                User user=new User();
                user.setId(user_id);
                ticksList=ticketService.getUserTickets(user);
            }
        }*/

        //查询该球场所属的球会id
       /* String qiuHuiId=siteService.getqiuHuiId(siteId);
        //查询用户认证状态(0未认证 1认证成功 2审核中)
       String state=memberApproveService.memberApproveEnd(user_id.intValue(),qiuHuiId);
       if(StringUtils.isEmpty(state)){
           state="0";
       }*/

       //球场图片

        List<Img> siteImgs= imgService.getImgs("1",Integer.parseInt(siteId));
        siteDetails.put("siteImgs",siteImgs);

     //平台上班时间
        PlatformWorktime platformWorktime=platformWorktimeService.getPlatformWorktime("1");
        siteDetails.put("platformWorktime",platformWorktime);

       // siteDetails.put("ticks",ticksList);
      /*  siteDetails.put("approveState",state);*/
        siteDetails.put("is_collect",is_Collect);
        siteDetails.put("services",services);
        return siteDetails;
    }


    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }
}
