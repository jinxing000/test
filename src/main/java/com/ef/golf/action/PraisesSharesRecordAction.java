package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.DynamicPraiseService;
import com.ef.golf.service.ShareService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PraisesSharesRecordAction extends AbstractService {

    @Resource
    private DynamicPraiseService dynamicPraiseService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private ShareService shareService;
    //@NotNull
    private Integer type;//  1点赞 2分享

    private int pageNo = 1;

    private int pageSize = 5;
    //@NotNull
    private int dynamicId;

    private String uid;
    private String sid;

    @Override
    public Object doService() throws Exception {
        Integer userId = 0;
        if(StringUtils.isNotBlank(uid)&&StringUtils.isNotBlank(sid)) {
            userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        }
        PageBean pageBean = null;
        if (type == 1) {
            //获取点赞记录
            pageBean = dynamicPraiseService.getPraises(dynamicId, pageNo, pageSize,userId);
        } else {
            //获取转发记录
            pageBean = shareService.getShares(dynamicId, pageNo, pageSize,userId);
        }
        return pageBean;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setDynamicId(int dynamicId) {
        this.dynamicId = dynamicId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
