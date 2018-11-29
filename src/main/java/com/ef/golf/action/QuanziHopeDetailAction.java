package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.HopeService;
import com.ef.golf.vo.QuanziHopeDetailVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create by xzw
 * 2018年1月20日13:46:45
 * 根据愿望id获取愿望详情
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziHopeDetailAction extends AbstractService {

    @Resource
    private HopeService hopeService;

    //@NotNull
    private Integer hopeId;


    @Override
    public Object doService() throws Exception {
        QuanziHopeDetailVo quanziHopeDetailVo = hopeService.getHopeDetailById(hopeId);
        return quanziHopeDetailVo;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

}
