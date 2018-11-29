package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.HopeRecordService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.QuanziRealizeHopeVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by xzw
 * 2018年1月20日14:57:24
 * 查询愿望支持者的信息
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuanziSupporterAction extends AbstractService {

    @Resource
    private HopeRecordService hopeRecordService;

    //@NotNull
    private Integer hopeId;
    private Integer pageNo = 1;
    private Integer pageSize = 5;

    @Override
    public Object doService() throws Exception {
        List<QuanziRealizeHopeVo> getSupportList = hopeRecordService.getSupportList(hopeId, pageNo, pageSize);
        Integer count = hopeRecordService.getSupportListCount(hopeId);
        PageBean pageBean = new PageBean();
        pageBean.setTotalCount(count);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setResultList(getSupportList);
        return pageBean;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
