package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.GetHopeMsg;
import com.ef.golf.service.HopeService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * create by wxc
 * 2018年1月3日15:17:30
 * 愿望领取
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HopeGetMsgAction extends AbstractService {

    @Resource
    private HopeService hopeService;
    //@NotNull(message = "愿望Id不可为空")
    private Integer hopeId;

    @Override
    public Object doService() throws Exception {
        GetHopeMsg getHopeMsg =  hopeService.getGetHopeMsg(hopeId);
        return getHopeMsg;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }
}
