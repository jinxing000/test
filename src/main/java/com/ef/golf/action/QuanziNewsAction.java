package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.service.QuanZiNewsService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.QuanZiNewsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziNewsAction extends AbstractService {


    @Resource
    private QuanZiNewsService quanZiNewsService;

    @Resource
    private RedisBaseDao redisBaseDao;

    @Resource
    private UserService userService;


    //@NotNull(message = "未登录，无法查询消息")
    private String uid;

    //@NotNull(message = "未登录，无法查询消息")
    private String sid;

    /**
     * 分页用
     */
    private int pageNum = 1;

    private int showCount = 10;


    @Override
    public Object doService() throws Exception {
        List<QuanZiNewsVo> QuanziNewsList = null;

        if (StringUtils.isNotEmpty(sid)) {
            String uuid = redisBaseDao.get(sid);
            if (StringUtils.isEmpty(uuid)) {
                throw new LoginException(Constant.ERR_PARAMETER);
            }
            if (uuid.equals(uid)) {
                Long userId = userService.getUid(uid);
                QuanZiNewsVo qzv = new QuanZiNewsVo();
                qzv.setCurrentPage(this.pageNum);
                qzv.setShowCount(this.showCount);
                qzv.setUserId(userId.intValue());
                QuanziNewsList = quanZiNewsService.getNewsByUserId(qzv);
            }
        }
        if (QuanziNewsList.size() <= 0 || QuanziNewsList == null) {
            throw new QueryException(Constant.ERR_QUERY);
        } else {
            return QuanziNewsList;
        }
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

}
