package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.EsSubject;
import com.ef.golf.service.EsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * create by wxc
 * 2018年10月23日
 * 专题商品列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShopSubjectListAction extends AbstractService {

    //@NotNull(message = "专题Id不可为空!")
    private Integer subjectId;

    @Autowired
    private EsSubjectService esSubjectService;

    @Override
    public Object doService() throws Exception {
        EsSubject es = esSubjectService.findSpecialListBySubjectId(subjectId);

        return es.getGoodsHotSpecVo();
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
