package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.PlatformWorktime;
import com.ef.golf.service.PlatformWorktimeService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PlatformWorktimeAction extends AbstractService {

    @Resource
    private PlatformWorktimeService platformWorktimeService;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> platformWorktimeMap = new HashMap<>();
        PlatformWorktime platformWorktime = platformWorktimeService.getPlatformWorktime("1");
        platformWorktimeMap.put("platformWorktime", platformWorktime);
        return platformWorktimeMap;
    }
}
