package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * for efgolf
 * Created by wanxiaochao on 2018/4
 * Date: 2018/4
 * 导入solr索引库
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SolrCaddieAction extends AbstractService {

    @Resource
    private UserService userService;

    public Object doService() {
        IfunResult ifunResult = userService.findCaddieDatabaseToSolrIndex();
        return ifunResult;
    }

}
