package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.CommentService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PersionAction extends AbstractService {


    private String uid;

    private String sid;


    @Resource
    private RedisBaseDao redisBaseDao;

    @Resource
    private UserService userService;


    @Resource
    private CommentService commentService;

    public Object doService() throws SystemException, LoginException, QueryException, DemandException {
        // String rUID = redisBaseDao.get(sid);
        // if(StringUtils.isEmpty(rUID)||!rUID.equals(uid))
        //     throw new LoginException(Constant.ERR_USER);
        //
        //
        // redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));


        return new UserVo(uid, sid);
    }


    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
