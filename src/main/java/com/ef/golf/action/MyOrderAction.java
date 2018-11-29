package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.pojo.Order;
import com.ef.golf.service.AccountService;
import com.ef.golf.service.OrderService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.OrderVo;
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
public class MyOrderAction extends AbstractService {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Resource
    private RedisBaseDao redisBaseDao;

    @Resource
    private AccountService accountService;

    //@NotNull(message = "您当前尚未登录或登录失效，请登录。。。")
    private String sid;

    private String uid;

    private String orderState;

    private int showCount = 10;
    private int pageNum = 1;


    @Override
    public Object doService() throws Exception {
        // TODO 用注解方式去验证参数
        Map<String, Object> orderMap = new HashMap<String, Object>();

        // TODO 若sid失效则会出现空指针异常
        String UID = redisBaseDao.get(sid);
        if (!UID.equals(uid) || StringUtils.isEmpty(UID)) {
            throw new LoginException(Constant.ERR_PARAMETER);
        }
        Long userId = userService.getUid(UID);
        int accountId = accountService.selectAccountId(userId);
        Order order = new Order();
        order.setOrderState(this.orderState);
        order.setAccountId(accountId);
        order.setShowCount(this.showCount);
        order.setCurrentPage(this.pageNum);

        List<OrderVo> myOrderList = orderService.getMyAllOrder(order);
        if (myOrderList != null && myOrderList.size() > 0) {
            orderMap.put("myOrder", myOrderList);
            orderMap.put("sid", sid);
            orderMap.put("uid", uid);
            orderMap.put("totalPage", order.getTotalPage());
            orderMap.put("pageNum", order.getCurrentPage());
            redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
        } else {
            throw new QueryException(Constant.ERR_QUERY - 3);
        }
        return orderMap;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}