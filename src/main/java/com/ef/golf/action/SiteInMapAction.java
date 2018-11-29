package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.QueryException;
import com.ef.golf.service.SiteService;
import com.ef.golf.vo.SiteInMapVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 查询某个城市下的所有球场信息（球场名称、球场经纬度）
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class






SiteInMapAction extends AbstractService {

    //@NotNull(message = "城市不能为空")
    private String cityName;

    @Resource
    private SiteService siteService;

    @Override
    public Object doService() throws Exception {
        List<SiteInMapVo> SiteList = siteService.getSiteByCity(cityName);
        if (SiteList.size() > 0) {
            return SiteList;
        } else {
            throw new QueryException(Constant.ERR_QUERY - 5);
        }

    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
