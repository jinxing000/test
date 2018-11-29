package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.User;
import com.ef.golf.service.*;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.DynamicVo;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 * 关注接口
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDaynamiAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private DynamicService dynamicService;

    @Resource
    private DynamicPraiseService dynamicPraiseService;

    @Resource
    private ShareService shareService;

    @Resource
    private DynamicCommentService dynamicCommentService;

    @Resource
    private Gift_giveService giftGiveService;

    private Integer userID;

    private String lat = "40.0497810000";

    private String lng = "116.3424590000";

    private Integer pageNum = 1;

    private Integer showCount = 5;

    public Object doService() throws SystemException, LoginException {


        //4.获取用户的动态
        User user = new User();
        user.setId(Long.valueOf(this.userID));
        user.setLat(this.lat);
        user.setLng(this.lng);
        user.setCurrentPage(this.pageNum);
        user.setShowCount(this.showCount);
        List<DynamicVo> getDynamicList = dynamicService.getDynamicByUserIdListPage(user);
        if (getDynamicList.size() > 0) {
            //分割图片
            for (int i = 0; i < getDynamicList.size(); i++) {
                String imgUrl = getDynamicList.get(i).getImgUrl();
                if (StringUtils.isNotEmpty(imgUrl)) {
                    String str[] = imgUrl.split(";");
                    getDynamicList.get(i).setDynamicImgs(str);
                }
                //获取动态id
                int dynamicId = getDynamicList.get(i).getDynamicId();
                //获取点赞记录
                getDynamicList.get(i).setDynamicPraises(dynamicPraiseService.getPraises(dynamicId, 1, 8,null).getResultList());
                //获取评论记录
                getDynamicList.get(i).setDynamicComments(dynamicCommentService.getComments(dynamicId));
                //获取转发记录
                getDynamicList.get(i).setDynamicShares(shareService.getShares(dynamicId, 1, 8,userID).getResultList());
                //获取礼物记录
                getDynamicList.get(i).setDynamicGift(giftGiveService.getGifts(dynamicId));
            }
        }

        Page pageResult = new Page();
        pageResult.setResultList(getDynamicList);
        return pageResult;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
