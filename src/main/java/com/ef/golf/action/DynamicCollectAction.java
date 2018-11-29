package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.service.DynamicCommentService;
import com.ef.golf.service.DynamicPraiseService;
import com.ef.golf.service.DynamicService;
import com.ef.golf.service.ShareService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.DynamicHopeVo;
import com.ef.golf.vo.DynamicVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * create by xzw
 * 2017年12月23日14:52:02
 * 获取我关注人的动态列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DynamicCollectAction extends AbstractService {

    @Resource
    private DynamicService dynamicService;

    @Resource
    private DynamicPraiseService dynamicPraiseService;

    @Resource
    private ShareService shareService;

    @Resource
    private DynamicCommentService dynamicCommentService;

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    //@NotNull(message = "参数不能为空")
    private String uid;
    //@NotNull(message = "参数不能为空")
    private String sid;

    private String lat = "40.0497810000";

    private String lng = "116.3424590000";

    private int pageNum = 1;

    private int showCount = 10;


    @Override
    public Object doService() throws Exception {
        //验证是否登录
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);

        Dynamic dynamic = new Dynamic();
        dynamic.setUserId(userId);//
        dynamic.setShowCount(this.showCount);
        dynamic.setCurrentPage(this.pageNum);
        dynamic.setLat(this.lat);
        dynamic.setLng(this.lng);

        //获取我关注人的动态
        List<DynamicVo> dynamicList = dynamicService.getCollectDynamicListPage(dynamic);
        if (dynamicList.size() > 0) {
            Integer[] userIds = new Integer[dynamicList.size()];
            List<DynamicHopeVo> dynamicHopeList = new ArrayList<>();
            for (int i = 0; i < dynamicList.size(); i++) {
                userIds[i] = dynamicList.get(i).getUserId();
                //获取动态id
                int dynamicId = dynamicList.get(i).getDynamicId();
                //获取点赞记录
                dynamicList.get(i).setDynamicPraises(dynamicPraiseService.getPraises(dynamicId, 1, 8,null).getResultList());
                //获取转发记录
                dynamicList.get(i).setDynamicComments(dynamicCommentService.getComments(dynamicId));
                //获取评论记录
                dynamicList.get(i).setDynamicShares(shareService.getShares(dynamicId, 1, 8,userId).getResultList());
            }
            TreeSet<Integer> hset = new TreeSet<Integer>(Arrays.asList(userIds));
            for (Integer hs:hset
                 ) {
                List<DynamicHopeVo> dynamicHopeVo = dynamicService.getHopeByUserId(hs);
                if(null!=dynamicHopeVo){
                    for (DynamicHopeVo dhv:dynamicHopeVo
                            ) {
                        dynamicHopeList.add(dhv);
                    }
                }
            }
            /*for (int i = 0; i < hset.size(); i++) {
                *//*List<DynamicHopeVo> dynamicHopeVo = dynamicService.getHopeByUserId(userIds[i]);*//*
                List<DynamicHopeVo> dynamicHopeVo = dynamicService.getHopeByUserId(i);
                if(null!=dynamicHopeVo){
                    for (DynamicHopeVo dhv:dynamicHopeVo
                            ) {
                        dynamicHopeList.add(dhv);
                    }
                }
            }*/
            if (dynamicHopeList.size() > 0) {
                for (int i = 0; i < dynamicList.size(); i++) {
                    String imgUrl = dynamicList.get(i).getImgUrl();
                    if (StringUtils.isNotEmpty(imgUrl)) {
                        //分割图片路径，并存入数组
                        dynamicList.get(i).setDynamicImgs(this.getDynamicImgUrl(imgUrl));
                    }
                    int userId1 = dynamicList.get(i).getUserId();
                    List<DynamicHopeVo> hopes = new ArrayList<DynamicHopeVo>();
                    for (int j = 0; j < dynamicHopeList.size(); j++) {
                        int userId2 = dynamicHopeList.get(j).getUserId();
                        if (userId1 == userId2) {
                            hopes.add(dynamicHopeList.get(j));
                            dynamicList.get(i).setHopes(hopes);
                        }
                    }
                }
            }
        }
        Page page = new Page();
        page.setTotalResult(dynamic.getTotalResult());
        page.setResultList(dynamicList);
        page.setShowCount(showCount);
        page.setCurrentPage(pageNum);
        return page;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    /**
     * 分割动态发表的图片地址路径
     */

    private String[] getDynamicImgUrl(String imgUrl) throws LoginException {
        String url[] = null;
        if (StringUtils.isNotEmpty(imgUrl)) {
            url = imgUrl.split(";");
            return url;
        } else {
            throw new LoginException(Constant.ERR_PARAMETER);
        }

    }
}
