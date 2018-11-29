package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.HopeService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 认证失败
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MemberApproveErrAction extends AbstractService {

    @Override
    public Object doService() throws Exception {

        return null;
    }


}



