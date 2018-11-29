package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Gift_give;
import com.ef.golf.service.Gift_giveService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 新增一条送礼物记录
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GiftGiveAction extends AbstractService {

    @Resource
    private Gift_giveService giftGiveService;

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;


    //@NotNull
    private String uid;
    //@NotNull
    private String sid;

    //@NotNull
    private String dynamicId;//动态id
    //@NotNull
    private String giftId;//礼物id


    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        Gift_give giftGive = new Gift_give();
        giftGive.setCreateTime(new Date());
        giftGive.setDynamicId(Integer.parseInt(this.dynamicId));
        giftGive.setGiftId(Integer.parseInt(this.giftId));
        giftGive.setUserId(userId);
        giftGiveService.insertSelective(giftGive);
        return new UserVo(uid, sid);
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }
}
