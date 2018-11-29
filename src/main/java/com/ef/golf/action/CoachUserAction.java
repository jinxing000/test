package com.ef.golf.action;

import com.alibaba.fastjson.JSONObject;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
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
 * 查询教练的推荐列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CoachUserAction extends AbstractService {

    @Resource
    private UserService userService;

    //城市
    private String city;

    private int pageNum = 1;
    private int showCount = 5;

    @Override
    public Object doService() throws Exception {
        QuanziUserVo quanziUserVo = new QuanziUserVo();
        quanziUserVo.setRegion(city);
        quanziUserVo.setCurrentPage(pageNum);
        quanziUserVo.setShowCount(showCount);
        List<QuanziUserVo> coachListPage = userService.getCoachListPage(quanziUserVo);
        Page page = new Page();
        page.setResultList(coachListPage);
        JSONObject j = new JSONObject();
        String json = j.toJSONString(coachListPage);
        System.out.println("==============" + json);
        return page;
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
