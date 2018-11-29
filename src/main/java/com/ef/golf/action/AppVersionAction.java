package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * create by wxc
 * app更新检查
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AppVersionAction extends AbstractService {

    @Autowired
    private AppVersionService appVersionService;

    private String platform;//Android iOS
    private String versionName;//版本号

    private String  plateform;//

    @Override
    public Object doService() throws Exception {
        Map<String,Object> map =new HashMap<>();
        System.out.println("plateform====="+plateform);
        System.out.println("versionName====="+versionName);
        if("iOS".equals(plateform)){
            map = appVersionService.findAppVersionByPlatform(plateform,versionName);
        }else{
            map = appVersionService.findAppVersionByPlatform(platform,versionName);
        }


        return  map;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }
}
