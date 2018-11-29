package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.QiuhuiVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QueryQiuHuiNamesAction extends AbstractService {

    @Resource
    private UserService userService;

    //@NotNull(message = "不可为空")
    private String nickName;

    @Override
    public Object doService() throws Exception {
        List<QiuhuiVo> usersList = userService.queryQiuHuiNames(nickName);
        return usersList;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
