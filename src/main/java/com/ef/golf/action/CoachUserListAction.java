package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.QuanziUserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 快速搜索教练列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CoachUserListAction extends AbstractService {

    @Resource
    private UserService userService;

    //排序类型
    private String sortType;//1.亿方推荐(默认) 2.好评优先

    //服务球龄
    private String serviceAge;//1.1-3年 2.3-5年 3.5-8年 4.8-10年 5.大于等于10年

    private String region;


    private String qiuHuiName;
    private String coachName;
    private String coachCode;
    private Integer pageNum = 1;
    private Integer showCount = 5;


    @Override
    public Object doService() throws Exception {
        Page page = new Page();
        QuanziUserVo quanziUserVo = new QuanziUserVo();
        quanziUserVo.setSortType(sortType);
        quanziUserVo.setServiceAge(serviceAge);
        quanziUserVo.setQiuHuiName(qiuHuiName);
        quanziUserVo.setNickName(coachName);
        quanziUserVo.setExclusiveNo(coachCode);
        quanziUserVo.setRegion(region);
        quanziUserVo.setCurrentPage(pageNum);
        quanziUserVo.setShowCount(showCount);
        List<QuanziUserVo> coachList = userService.coachListPage(quanziUserVo);
        page.setResultList(coachList);
        page.setCurrentPage(pageNum);
        page.setShowCount(showCount);
        page.setTotalResult(quanziUserVo.getTotalResult());
       // page.setTotalResult(coachList.size());
        return page;
    }


    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void setServiceAge(String serviceAge) {
        this.serviceAge = serviceAge;
    }

    public void setQiuHuiName(String qiuHuiName) {
        this.qiuHuiName = qiuHuiName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public void setCoachCode(String coachCode) {
        this.coachCode = coachCode;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
