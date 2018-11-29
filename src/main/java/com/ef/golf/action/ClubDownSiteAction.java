package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.service.SiteService;
import com.ef.golf.vo.SiteVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * for efgolf clubBelowSite
 * Created by Bart on 2018/9
 *
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClubDownSiteAction extends AbstractService {


    @Resource
    private SiteService siteService;

   //@NotNull(message = "球会id不可为null")
   private String qiuHuiId;

   private String lon = "117.222319";
   private String lat = "39.06825";

    public Object doService() throws SystemException {
       List<SiteVo>list =  siteService.getClubDownSiteByClubId(qiuHuiId,lon,lat);
        return list;
    }

    public void setQiuHuiId(String qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
