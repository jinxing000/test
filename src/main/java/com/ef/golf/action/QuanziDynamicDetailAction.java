package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.*;
import com.ef.golf.vo.DynamicVo;
import com.ef.golf.vo.QuanziCommentVo;
import com.ef.golf.vo.QuanziGiftVo;
import com.ef.golf.vo.QuanziUserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by xzw
 * 2018年1月9日13:57:19
 * 根据动态id查询动态详情
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziDynamicDetailAction extends AbstractService {

    @Resource
    private DynamicService dynamicService;
    @Resource
    private HopeService hopeService;

    @Resource
    private Gift_giveService giftGiveService;

    @Resource
    private DynamicPraiseService dynamicPraiseService;

    @Resource
    private ShareService shareService;

    @Resource
    private DynamicCommentService dynamicCommentService;

    //@NotNull
    private Integer dynamicId;

    private Integer userId;

    private String lat;

    private String lng;

    private String lon;




    @Override
    public Object doService() throws Exception {

        if(null==lng&&null!=lon){
            lng = lon;
        }

        Map<String, Object> dynamicDeatil = new HashMap<>();
        Dynamic dynamic = new Dynamic();
        dynamic.setLat(lat);
        dynamic.setLng(lng);
        dynamic.setDynamicId(dynamicId);

        //1.获取动态详情
        List<DynamicVo> dynamicPojo = dynamicService.getDyanmicByDynamicId(dynamic);
        List<Hope> hopeList = null;
        //2.获取未实现的愿望
        for (DynamicVo dv:dynamicPojo
             ) {
            hopeList = hopeService.getUnrealizedHopes(dv.getUserId());
        }

        //3.获取礼物
        List<QuanziGiftVo> Gifts = giftGiveService.getGifts(dynamicId);
        //4.获取点赞人的信息记录
        List<QuanziUserVo> getPraises = dynamicPraiseService.getPraises(dynamicId, 1, 8,0).getResultList();
        //5.获取转发人的信息记录
        List<QuanziUserVo> getShares = shareService.getShares(dynamicId, 1, 8,userId).getResultList();
        //6.获取动态的评论记录
        List<QuanziCommentVo> getComments = dynamicCommentService.getComments(dynamicId);
        //处理图片字段，并存入数组
        if (dynamicPojo.size() > 0) {
            for (int i = 0; i < dynamicPojo.size(); i++) {
                String str = dynamicPojo.get(i).getImgUrl();
                if (StringUtils.isNotEmpty(str)) {
                    String[] imgStrs = new String[str.split(";").length];
                    imgStrs = str.split(";");
                    dynamicPojo.get(i).setDynamicImgs(imgStrs);
                }
                dynamicPojo.get(i).setDynamicGift(Gifts);
                dynamicPojo.get(i).setDynamicShares(getShares);
                dynamicPojo.get(i).setDynamicComments(getComments);
                dynamicPojo.get(i).setDynamicPraises(getPraises);
            }
        }

        dynamicDeatil.put("dynamicPojo", dynamicPojo);
        dynamicDeatil.put("hopeList", hopeList);

        return dynamicDeatil;
    }


    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
