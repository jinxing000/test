package com.ef.golf.action;

import com.ef.golf.callable.GetGiftTask;
import com.ef.golf.core.service.AbstractService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 赠送礼物抢礼物
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GiftQiangAction extends AbstractService {

    //接受者id
    private String userId;
    //群id
    /*private String groupId;*/

    //送礼物记录id  必传
    //@NotNull(message = "发礼物返回的giftGiveId,必须")
    private String giftGiveId;

    @Resource
    private ThreadPoolTaskExecutor executor;
    /*@Resource
    private GetGiftTask qiangGiftTask;*/
    @Resource
    private GetGiftTask getGiftTask;

    @Override
    public Object doService() throws Exception {
        getGiftTask.setGiftGiveId(giftGiveId);
        getGiftTask.setUserId(userId);
        Future<Map<String, Object>> future = executor.submit(getGiftTask);
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /* public void setGroupId(String groupId) {
        this.groupId = groupId;
    }*/

    public void setGiftGiveId(String giftGiveId) {
        this.giftGiveId = giftGiveId;
    }
}
