package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.User;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.CommentService;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PersonalCenterCollectAction extends AbstractService {

    @Resource
    private CollectService collectService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    private String uid;
    private String sid;
    private String type;//1 球场 2 商品
    private Integer pageNo = 1;
    private Integer pageSize = 5;

    private String lon = "";//J

    private String lat = "";//W

    @Override
    public Object doService() throws Exception {



        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        if ("1".equals(type)) {
            /** 收藏球场信息 */
            PageBean pageBean = collectService.getSiteCollect(userId, pageNo, pageSize,lon,lat);
            return pageBean;
        }
        if ("2".equals(type)) {
            /** 收藏商品信息 */
            PageBean pageBean = collectService.getCollectGoodsList(userId, pageNo, pageSize);
            return pageBean;
        }
        return null;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
