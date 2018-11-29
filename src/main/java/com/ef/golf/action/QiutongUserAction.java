package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.Banner;
import com.ef.golf.service.BannerService;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.QuanziUserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by xzw
 * 2018年2月23日11:41:56
 * 查询推荐的球童列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QiutongUserAction extends AbstractService {

    @Resource
    private UserService userService;
    @Resource
    private BannerService bannerService;

    //城市
    private String city;


    private int pageNum = 1;
    private int showCount = 10;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        QuanziUserVo quanziUserVo = new QuanziUserVo();
        quanziUserVo.setRegion(city);
        quanziUserVo.setCurrentPage(pageNum);
        quanziUserVo.setShowCount(showCount);
        List<QuanziUserVo> qiuTongList = userService.getQiuTongListPage(quanziUserVo);
        map.put("qiuTongList", qiuTongList);
        List<Banner> bannerList = bannerService.bannerByGrouping("3");
        map.put("bannerList", bannerList);
        return map;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }


}
