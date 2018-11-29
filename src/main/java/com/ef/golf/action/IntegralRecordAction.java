package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.IntegralRecordService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IntegralRecordAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private IntegralRecordService integralRecordService;

    //@NotNull(message = "不可为空")
    private String uid;
    //@NotNull(message = "不可为空")
    private String sid;
    private Integer pageNo = 1;
    private Integer pageSize = 5;

    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        PageBean pageBean = integralRecordService.getIntegralRecord(userId, pageNo, pageSize);
        return pageBean;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
