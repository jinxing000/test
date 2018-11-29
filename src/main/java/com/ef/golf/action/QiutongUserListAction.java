package com.ef.golf.action;


import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.QuanziUserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * create by xzw
 * 2018年2月26日11:14:44
 * 查询球童列表（快速搜索）
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QiutongUserListAction extends AbstractService {

    @Resource
    private UserService userService;

    //排序类型
    private String sortType = "1";//1.亿方推荐(默认) 2.好评优先

    //服务球龄
    private String serviceAge;//1.1-3年 2.3-5年 3.5-8年 4.8-10年 5.大于等于10年

    private String qiuHuiName;

    private String qiuTongName;

    private String qiuTongCode;
    private Integer pageSize = 5;
    private Integer pageNo = 1;


    @Override
    public Object doService() throws Exception {

        QuanziUserVo quanziUserVo = new QuanziUserVo();
        quanziUserVo.setCurrentPage(pageNo);
        quanziUserVo.setShowCount(pageSize);
        quanziUserVo.setSortType(sortType);
        quanziUserVo.setServiceAge(serviceAge);
        quanziUserVo.setQiuHuiName(qiuHuiName);
        quanziUserVo.setNickName(qiuTongName);
        quanziUserVo.setExclusiveNo(qiuTongCode);
        List<QuanziUserVo> userList = userService.qiuTongListPage(quanziUserVo);
        Integer count = userService.getQiuTongCount(quanziUserVo);
        Page page = new Page();
        page.setTotalResult(quanziUserVo.getTotalResult());
        page.setResultList(userList);
        page.setShowCount(pageSize);
        page.setCurrentPage(pageNo);
        page.setTotalPage(quanziUserVo.getTotalPage());
        return page;
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

    public void setQiuTongName(String qiuTongName) {
        this.qiuTongName = qiuTongName;
    }

    public void setQiuTongCode(String qiuTongCode) {
        this.qiuTongCode = qiuTongCode;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
