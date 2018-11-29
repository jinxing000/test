package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.CashBack;
import com.ef.golf.service.CashBackService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值前信息
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RechargeAgoMessageAction extends AbstractService {

    @Resource
    private CashBackService cashBackService;

    public Object doService() throws SystemException {
        Map<String,Object> map = new HashMap<>();
        List<Long>resultList = new ArrayList<>();
        resultList.add((long)1000);
        resultList.add((long)3000);
        resultList.add((long)5000);
        List<CashBack>list = cashBackService.selectCashBackMsg();
        StringBuilder sb = new StringBuilder();
        for (CashBack cb:list) {
            sb.append(cb.getRemark().trim()+"\n");
        }
        map.put("money",resultList);
        map.put("activity",sb);
        return map;
    }
}


