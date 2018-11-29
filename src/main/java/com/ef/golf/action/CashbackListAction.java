package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.CashBack;
import com.ef.golf.service.CashBackService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CashbackListAction extends AbstractService {

    @Resource
    private CashBackService cashBackService;

    @Override
    public Object doService() throws Exception {
        List<CashBack> resultList = cashBackService.selectCashBackMsg();
        return resultList;
    }

}