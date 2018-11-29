package com.ef.golf.action;

import com.ef.golf.callable.SendTicketTask;
import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.HopeService;
import com.ef.golf.service.UserService;
import com.ef.golf.service.UserTicketService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.util.ServicePushUtils;
import com.ef.golf.vo.MineVo;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 尹金星
 * <p>
 * 2018/5/9
 * 优惠券发放接口
 * <p>
 * 客户端传递  101
 * 优惠券id  youHuiQuanId
 * 发送者的  userId
 * 接受者id   recieverId       群发为空  私发需要传参
 * 群号      qunId       私聊为0，群聊具体群号
 * 优惠券发放类型  sendType  1.个人到个人  2.群里发放
 * <p>
 * 返回参数
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class YouHuiQuanSendAction extends AbstractService {

    //发送者id
    private String userId;

    //接受者id
    private String recieverId;

    //优惠券id
    private String youHuiQuanId;

    //发放类型
    private String sendType;//  1.个人到个人  2.群里发放


    @Resource
    private ThreadPoolTaskExecutor executor;
    @Resource
    private SendTicketTask sendTicketTask;

    /**
     * msgType	int	必选	消息类型，1：文本消息，2：语音消息，3：视频消息，
     * 4：图片消息，5：位置消息，6：文件，26：指令消息(如需使用extOpts字段，则设置该类型)
     */
    @Override
    public Object doService() throws Exception {

        System.out.println("sendType============"+sendType);
        System.out.println("sendType====================="+sendType);
        sendTicketTask.setRecieverId(recieverId);
        sendTicketTask.setSendType(sendType);
        sendTicketTask.setUserId(userId);
        sendTicketTask.setYouHuiQuanId(youHuiQuanId);
        Future<Map<String, Object>> future = executor.submit(sendTicketTask);
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

    public void setYouHuiQuanId(String youHuiQuanId) {
        this.youHuiQuanId = youHuiQuanId;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

}
