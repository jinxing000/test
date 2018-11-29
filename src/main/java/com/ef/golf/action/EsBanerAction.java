package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.QueryException;
import com.ef.golf.pojo.Adv;
import com.ef.golf.pojo.Brand;
import com.ef.golf.service.EsBranerService;
import com.ef.golf.service.GoodsService;
import com.ef.golf.vo.AdvVo;
import com.ef.golf.vo.BrandVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 商城首页barner图
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsBanerAction extends AbstractService {
    private final Log log = LogFactory.getLog(EsBanerAction.class);
    @Autowired
    private EsBranerService esBranerService;

    public Object doService() throws QueryException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<AdvVo> advList = esBranerService.getAdvList();
        List<Brand> brandLIst = esBranerService.getBrandList();
        // List<BrandVo> voList = esBranerService.getBrandListes();
        //map.put("code","200");
        map.put("advList", advList);
        map.put("brandLIst", brandLIst);
        // map.put("voList",voList);
        return map;
    }

}
