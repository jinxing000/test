package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.User;
import com.ef.golf.service.TicketService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.TicketVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyTicketsAction extends AbstractService {

    @Resource
    private RedisBaseDao redisBaseDao;

    @Resource
    private UserService userService;

    @Resource
    private TicketService ticketService;

    private String uid;
    //@NotNull(message = "您当前尚未登录或登录失效，请登录。。。")
    private String sid;
    private int showCount = 10;
    private int pageNum = 1;


    @Override
    public Object doService() throws Exception {

        if (StringUtils.isNotEmpty(sid)) {
            String UID = redisBaseDao.get(sid);
            if (!UID.equals(uid) || StringUtils.isEmpty(UID)) {
                throw new LoginException(Constant.ERR_USER);
            }
            Long userId = userService.getUid(UID);
            User user = new User();
            user.setId(userId);
            user.setShowCount(showCount);
            user.setCurrentPage(pageNum);
            List<TicketVo> ticketsList = ticketService.getUserTickets(user);
            redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
            return ticketsList;
        } else {
            throw new LoginException(Constant.ERR_USER);
        }

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
