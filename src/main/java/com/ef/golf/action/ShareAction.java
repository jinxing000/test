package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Share;
import com.ef.golf.service.ShareService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShareAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private ShareService shareService;

    private String uid;

    private String sid;

    private String shareType;//1.QQ 2.微信 3.微博

    //@NotNull
    private String shareDynamicId;//分享的动态id


    @Override
    public Object doService() throws Exception {

        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);

        Share share = new Share();
        share.setCreateTime(new Date());
        share.setShareDynamicId(Integer.parseInt(this.shareDynamicId));
        share.setShareUserId(userId);
        share.setShareType(this.shareType);
        try {
            shareService.insertSelective(share);
        } catch (Exception e) {
            throw new SystemException(Constant.ERR_UPDATE);
        }
        return new UserVo(sid, uid);
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }


    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public void setShareDynamicId(String shareDynamicId) {
        this.shareDynamicId = shareDynamicId;
    }
}
