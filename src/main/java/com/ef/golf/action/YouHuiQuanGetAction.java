package com.ef.golf.action;

import com.ef.golf.callable.GetTicketTask;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.UserTicketService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 尹金星
 * <p>
 * 2018/5/9
 * 优惠券抢接口
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class YouHuiQuanGetAction extends AbstractService {

    @Resource
    private ThreadPoolTaskExecutor executor;
    @Resource
    private GetTicketTask getTicketTask;

    //接受者id
    private String recieverId;

    //优惠券id
    private String youHuiQuanId;

    //抢券记录id
    private String ticketZzId;


    @Override
    public Object doService() throws Exception {

        System.out.println("recieverId=========="+recieverId+"====youHuiQuanId======"+youHuiQuanId);

        getTicketTask.setRecieverId(recieverId);
        getTicketTask.setYouHuiQuanId(youHuiQuanId);
        getTicketTask.setTicketZzId(ticketZzId);
        Future<Map<String, Object>> future = executor.submit(getTicketTask);
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public void setYouHuiQuanId(String youHuiQuanId) {
        this.youHuiQuanId = youHuiQuanId;
    }

    public void setTicketZzId(String ticketZzId) {
        this.ticketZzId = ticketZzId;
    }
}
