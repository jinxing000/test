package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Praise;
import com.ef.golf.service.CommentService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PraiseAction extends AbstractService {


    //@NotNull(message = "评论ID不能为空")
    private int commentId;

    @Pattern(regexp = "^[1]|[-1]$", message = "点赞数错误")
    private int praiseNum;

    //@NotNull(message = "未登陆,无法点赞")
    private String uid;

    //@NotNull(message = "未登陆,无法点赞")
    private String sid;


    @Resource
    private RedisBaseDao redisBaseDao;

    @Resource
    private UserService userService;


    @Resource
    private CommentService commentService;

    public Object doService() throws SystemException, LoginException, QueryException, DemandException {
        if (StringUtils.isEmpty(redisBaseDao.get(sid)) || !redisBaseDao.get(sid).equals(uid))
            throw new LoginException(Constant.ERR_USER);
        Long userId = userService.getUid(uid);
        Praise praise = new Praise();
        praise.setCommentsId(commentId);
        praise.setUserId(userId);
        praise.setPraiseNum(praiseNum);

        commentService.addPraise(praise);

        redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
        return new UserVo(uid, sid);
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
