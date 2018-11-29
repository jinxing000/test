package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Site_price;
import com.ef.golf.service.SitePriceService;
import com.ef.golf.vo.SitePriceVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/***
 * create by xzw
 * 2018年2月7日16:41:25
 * 查询球场价格   近三个月的
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SitePriceAction extends AbstractService {

    @Resource
    private SitePriceService sitePriceService;

    private Integer siteId;


    @Override
    public Object doService() throws Exception {
        SitePriceVo sitePriceVo = new SitePriceVo();
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int beginYear = calendar.get(Calendar.YEAR);
        int beginMonth = calendar.get(Calendar.MONTH) + 1;
        int beginDay = calendar.get(Calendar.DATE);
        StringBuffer beginSb = new StringBuffer();
        beginSb.append(beginYear + "-");
        if (beginMonth < 10) {
            beginSb.append("0" + beginMonth + "-");
        } else {
            beginSb.append(beginMonth + "-");
        }

        if (beginDay < 10) {
            beginSb.append("0" + beginDay);
        } else {
            beginSb.append(beginDay);
        }
        String beginDate = beginSb.toString();
        calendar.add(Calendar.MONTH, 3);
        int endYear = calendar.get(Calendar.YEAR);
        int endMonth = calendar.get(Calendar.MONTH) + 1;
        int endDay = calendar.get(Calendar.DATE);

        StringBuffer endSb = new StringBuffer();
        endSb.append(endYear + "-");
        if (endMonth < 10) {
            endSb.append("0" + endMonth + "-");
        } else {
            endSb.append(endMonth + "-");
        }
        if (endDay < 10) {
            endSb.append("0" + endDay);
        } else {
            endSb.append(endDay);
        }
        String endDate = endSb.toString();
        sitePriceVo.setSiteId(siteId);
        sitePriceVo.setBeginDate(beginDate);
        sitePriceVo.setEndDate(endDate);
        List<Site_price> sitePriceList = sitePriceService.getSitePrice(sitePriceVo);
        return sitePriceList;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}
