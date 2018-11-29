package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.HopeService;
import com.ef.golf.service.MemberApproveService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MemberApproveMsgAction extends AbstractService {

    @Resource
    private MemberApproveService memberApproveService;

    private String userId;
    private String clubId;

    @Override
    public Object doService() throws Exception {
        return memberApproveService.memberApproveMsg(Integer.valueOf(userId),clubId);
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }
}
