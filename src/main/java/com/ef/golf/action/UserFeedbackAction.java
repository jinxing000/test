package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.UserFeedback;
import com.ef.golf.service.UserRoleService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.GoEasyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wxc
 * 2018年1月3日15:17:30
 * 用户反馈信息
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserFeedbackAction extends AbstractService {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    private Integer userId;
    private String feedback;



    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();

        if (feedback.equals("")) {
            map.put("code", 1);
            map.put("message", "请填写反馈内容!");
            return map;
        }
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setUserId(userId);
        userFeedback.setUserFeedback(feedback);
        userFeedback.setCreateTime(new Date());
        userService.insertSelectiveFeedback(userFeedback);
        List<Integer> userIds = userRoleService.selectUserIdByRoleId("17");
        for (Integer goEasy: userIds
                ) {
            GoEasyUtil.pushMessage(goEasy+"","有新的反馈信息,请及时处理!");
        }
        map.put("message", "已提交");
        return map;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
