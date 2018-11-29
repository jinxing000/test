package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.pojo.User;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.CollectVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CollectSiteAction extends AbstractService {

    @Resource
    private CollectService collectService;

    @Resource
    private UserService userService;
    @Resource
    private RedisBaseDao redisBaseDao;
    //@NotNull(message = "不能为空")
    private String uid;
    //@NotNull(message = "您当前尚未登录或登录失效，请登录。。。")
    private String sid;

    private int showCount = 10;
    private int pageNum = 1;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> collectMap = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(uid)) {
            // TODO 用注解方式去验证参数
            String UID = redisBaseDao.get(sid);
            if (UID.equals(uid) && StringUtils.isNotEmpty(UID)) {
                Long userId = userService.getUid(uid);
                collectMap.put("sid", sid);
                collectMap.put("uid", uid);
                User user = new User();
                user.setId(userId);
                user.setShowCount(this.showCount);
                user.setCurrentPage(this.pageNum);
                List<CollectVo> collectList = collectService.getCollectSite(user);
                if (collectList != null && collectList.size() > 0) {
                    collectMap.put("collectList", collectList);
                    collectMap.put("totalPage", user.getTotalPage());
                    collectMap.put("pageNum", user.getCurrentPage());
                } else {
                    throw new QueryException(Constant.ERR_QUERY);
                }
            } else {
                throw new LoginException(Constant.ERR_USER);
            }
        }

        redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
        return collectMap;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
