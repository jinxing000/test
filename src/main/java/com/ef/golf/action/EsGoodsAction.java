package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.*;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一般商品详情
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsGoodsAction extends AbstractService {
    @Resource
    private CollectService collectService;
    @Resource
    private RedisBaseDao redisBaseDao;
    @Resource
    private UserService userService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    EsGoodsCatService esGoodsCatService;
    @Autowired
    EsDlyTypeService esDlyTypeService;
    @Resource
    private CommentService commentService;

    @Resource
    private ProductScoreService productScoreService;
    //@NotNull(message = "goods_id不能为空")
    private Integer goods_id;
    //@NotNull(message = "pageNo不能为空")
    private Integer pageNo;
    //@NotNull(message = "pageSize不能为空")
    private Integer pageSize;


    private String sid;

    private String uid;
    private String userId;




    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        pageNo = (pageNo - 1) * pageSize;
        maps.put("goods_id", goods_id);
        maps.put("pageNo", pageNo);
        maps.put("pageSize", pageSize);
        maps.put("commentType", 7);
        maps.put("productId", goods_id);
        maps.put("userId", userId);
        Goods goods1 = goodsService.selectByPrimaryKey(goods_id);
        System.out.println("goods_id="+goods_id);
        GoodsHotSpecVo goods = goodsService.selectGoodsDetail(goods_id);
        //根据商品小类查找对应的所属大类
        Integer parentId=esGoodsCatService.getBigCatId(goods1.getCatId());
        goods.setParentCatId(parentId);
        goods.setCatid(parentId);
        int commentCount = goodsService.commentCount(goods_id);//某一商品评论总数
       /* List<CommentVo> commentList = goodsService.commentList(maps);//商品评论列表*/
        //获取评论信息

        List<CommentVo> siteCommentVoList=commentService.selectCommentsByProductIdAndCommentType(maps);

        map.put("commentList", siteCommentVoList);


        //获取评分  所有模块评分都共用 SiteScoreVo  map里的key可以根据具体模块自行取名

        //数据库表中ef_product_score 中的productType  与 ef_score_attr，ef_comments的类型码要保持一致
        // 产品类型(1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程)
        Map<String,Object> map1 =new HashMap<>();
        map1.put("productId",goods_id);
        map1.put("productType",7);
        map1.put("attrAscription",7);
        List<SiteScoreVo> siteScoreVoList=productScoreService.selectScoreById(map1);

            map.put("goodsScore",siteScoreVoList);


        List<GoodsGllery> goodsGllery = goodsService.glleryList(goods_id);//详情中的商品图片轮播
        List<CommentPrictureVo> commentPrictureVo = goodsService.pictureList(goods_id);//商品评论图片列表
        List<GoodsMarketVo> marketList = goodsService.marketList(goods_id);//购物车商品弹窗
        System.out.println("是否免运费Carriage="+goods.getCarriage().trim());
        System.out.println("0".equals(goods.getCarriage().trim()));
        if ("0".equals(goods.getCarriage().trim())) {
             Integer good = goods.getWeight();
            List<DlyType> list = esDlyTypeService.getList();

            DlyType type = list.get(list.size()-1);
            String config = type.getConfig();
            Double doubles = good.doubleValue();
            Gson gson = new Gson();
            Map<String, Object> map2 = gson.fromJson(config, Map.class);
            Double firstunit = (Double) map2.get("firstunit");//首重
            Double continueunit = (Double) map2.get("continueunit");//续重
            Double firstprice = (Double) map2.get("firstprice");//首费 15+tint(w-1000)/500*5
            Double continueprice = (Double) map2.get("continueprice");//续费
           /* BigDecimal sub = good.subtract(new BigDecimal(1000));
            if(sub.compareTo(new BigDecimal(0))==-1){
                sub = new BigDecimal(0);
            }*/
            Double sub = doubles - 1000 < 0 ? 0 : doubles - 1000;

           /* BigDecimal syu = (new BigDecimal(firstprice).add(sub)).divide((new BigDecimal(continueunit).subtract(new BigDecimal(continueprice))));*/
            //Double s = (sub*continueprice)/1000;
            Double syu = firstprice + (sub * continueprice) / 1000;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(1);
            nf.setRoundingMode(RoundingMode.UP);
            String s = nf.format(syu);
            System.out.println("运费为"+s);
            map.put("cost", s);
        } else {
           // map.put("cost", "免运费");
            map.put("cost", "0");
        }
        List<GoodsSpecVo> specVoList = goodsService.getGoodsSpecVo(goods_id);
   /*     if(specVoList.size()>0){
            map.put("specVoList",specVoList);
        }else{
            map.put("specVoList","");
        }

        if (marketList.size()>0){
            map.put("marketList",marketList);
        }else{
            map.put("marketList","");
        }*/
        //获取用户是否登录
        boolean is_Collect=false;
        Long user_id=null;
        if(StringUtils.isNotEmpty(sid)){
            if (StringUtils.isNotEmpty(redisBaseDao.get(this.sid))){
                user_id=userService.getUid(this.uid);
                //判断是否收藏过该商品
                Collect collect =new Collect();
                collect.setUserId(user_id);
                collect.setCollectType("7");
                collect.setProductId(goods_id);
                if(collectService.getCollectSiteByUserId(collect)>0)
                    is_Collect=true;
            }
        }
        map.put("is_collect",is_Collect);
        map.put("goods", goods);
        map.put("marketList", marketList);
        map.put("specVoList", specVoList);
        map.put("commentCount", siteCommentVoList.size());

        map.put("goodsGllery", goodsGllery);
        map.put("commentPrictureVo", commentPrictureVo);



        return map;
    }

    public double getCost(BigDecimal value) {

        return value.compareTo(new BigDecimal(0)) < 0 ? 0 : value.doubleValue();
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
