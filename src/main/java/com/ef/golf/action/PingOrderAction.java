package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.OrderService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.UserVo;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Order;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PingOrderAction extends AbstractService {

    private Integer orderId;
    @Resource
    private OrderService orderService;

    public Object doService() throws SystemException, LoginException, RateLimitException, APIException, ChannelException, InvalidRequestException, APIConnectionException, AuthenticationException {
        String pingOrderId = orderService.getPingOrderId(orderId.toString());
        Order order = Order.retrieve(pingOrderId);
        return order;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
