package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.service.SiteOrderService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.SiteOrderDatailVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询场地订单详情
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SiteOrderDetailAction extends AbstractService {


    @Resource
    private SiteOrderService siteOrderService;

    @Resource
    private RedisBaseDao redisBaseDao;
    @Resource
    private UserService userService;

    // TODO 参数建议使用基础类型接收
    private Integer orderId;

    private String uid;
    //@NotNull(message = "您当前尚未登录或登录失效，请登录。。。")
    private String sid;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> SiteOrderMap = new HashMap<String, Object>();

        /* TODO 用注解方式去验证参数
         * 未验证参数
         * uid / sid 出现空参数 redis直接报错
         */

        if (StringUtils.isNotEmpty(sid)) {
            String UID = redisBaseDao.get(sid);
            if (StringUtils.isEmpty(UID) || !UID.equals(uid)) {
                throw new LoginException(Constant.ERR_USER);
            }
            Long userId = userService.getUid(UID);
            if (userId != null) {
                SiteOrderDatailVo siteOrderDatailVo = siteOrderService.selectSiteOrderDetail(orderId);
                if (siteOrderDatailVo != null) {
                    SiteOrderMap.put("sid", sid);
                    SiteOrderMap.put("uid", uid);
                    SiteOrderMap.put("siteOrderMap", siteOrderDatailVo);
                } else {
                    throw new QueryException(Constant.ERR_QUERY - 2);
                }
            } else {
                throw new LoginException(Constant.ERR_USER);
            }
        }

        // redisBaseDao.expire(sid,Integer.valueOf(Consts.REDIS_OUT_TIME) );
        return SiteOrderMap;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
