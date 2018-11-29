package com.ef.golf.action;

import com.ef.golf.callable.RedPackageCheckTask;
import com.ef.golf.callable.RedPackageGetTask;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.RedPackageService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2018/5/5.
 * 抢红包
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RedPackageCheckAction extends AbstractService {
    @Resource
    private ThreadPoolTaskExecutor executor;

    @Resource
    private RedPackageCheckTask redPackageCheckTask;

    //红包id
    private String redPackageId;

    private String uid;
    private String sid;

    @Override
    public Object doService() throws Exception {
        redPackageCheckTask.setRedPackageId(redPackageId);
        redPackageCheckTask.setUid(uid);
        redPackageCheckTask.setSid(sid);
        Future<Map<String, Object>> future = executor.submit(redPackageCheckTask);
        try {

            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setRedPackageId(String redPackageId) {
        this.redPackageId = redPackageId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
