package com.ef.golf.action;

import com.ef.golf.callable.SendGiftTask;
import com.ef.golf.callable.SendTicketTask;
import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
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
public class GiftSendAction extends AbstractService {

    //发送者id
    private String userId;

    //接受者id
    private String recieverId;//如果是群发不需要

    //礼物id
    private String giftId;

    //动态id
    private String dynamicId;
    //群id
    private String groupId="1";//不用了
    //发放类型
    private String sendType;//  1.个人到个人  2.群里发放 3.动态

    @Resource
    private ThreadPoolTaskExecutor executor;
    @Resource
    private SendGiftTask sendGiftTask;

    /**
     * msgType	int	必选	消息类型，1：文本消息，2：语音消息，3：视频消息，
     * 4：图片消息，5：位置消息，6：文件，26：指令消息(如需使用extOpts字段，则设置该类型)
     */
    @Override
    public Object doService() throws SystemException {
        sendGiftTask.setGiftId(giftId);
        sendGiftTask.setRecieverId(recieverId);
        sendGiftTask.setSendType(sendType);
        sendGiftTask.setUserId(userId);
        sendGiftTask.setGroupId(groupId);
        sendGiftTask.setDynamicId(dynamicId);
        Future<Map<String, Object>> future = executor.submit(sendGiftTask);
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }
}
