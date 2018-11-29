package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.SiteService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 * 导入solr索引库
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SolrSiteAction extends AbstractService {

    @Resource
    private SiteService siteService;

    public Object doService() {
        IfunResult ifunResult = siteService.fingSiteDatabaseToSolrIndex();
        return null;
    }

}
