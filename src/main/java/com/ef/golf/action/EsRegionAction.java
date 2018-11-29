package com.ef.golf.action;

import com.alibaba.fastjson.JSON;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.EsRegions;
import com.ef.golf.service.EsRegionService;
import com.ef.golf.util.RedisBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsRegionAction extends AbstractService {
    @Autowired
    EsRegionService regionService;
    @Autowired
    RedisBaseDao redisBaseDao;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //数据量太大，增加缓存机制，只有第一个人来时才真正才数据库加载

        List<EsRegions> regions= redisBaseDao.getList("regions");

        if(regions==null){
            regions = regionService.getRegionList(0);
         String jsonStr=   JSON.toJSONString(regions);
            redisBaseDao.setList("regions",regions);
        }

        map.put("regions", regions);
        return map;
    }
}
