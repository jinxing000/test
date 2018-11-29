package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Banner;
import com.ef.golf.pojo.Hotcity;
import com.ef.golf.pojo.Site;
import com.ef.golf.service.BannerService;
import com.ef.golf.service.SiteService;
import com.ef.golf.vo.SiteVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/22.
 * Date: 2017/9/22 17:52
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HotCitysAction extends AbstractService {


    @Resource
    private SiteService siteService;


    public Object doService() throws SystemException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Hotcity> hotcities = siteService.getHotCitys();
        map.put("hotcities", hotcities);
        return map;
    }

}
