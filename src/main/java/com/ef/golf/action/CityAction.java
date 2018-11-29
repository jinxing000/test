package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.common.Consts;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Area_dic;
import com.ef.golf.service.AreaDicService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CityAction extends AbstractService {

    @Resource
    private AreaDicService areaDicService;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> cityMaps = new HashMap<>();
        List<Area_dic> foreginCitys = areaDicService.selectAllCityInForeign();
        List<Area_dic> chinaCitys = areaDicService.selectAllCityInChina();
        if (foreginCitys.size() < 0 || chinaCitys.size() < 0) {
            throw new SystemException(Constant.ERR_PARAMETER);
        }
        cityMaps.put("foreginCitys", foreginCitys);
        cityMaps.put("chinaCitys", chinaCitys);
        return cityMaps;
    }
}
