package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.SearchResult;
import com.ef.golf.service.SiteService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.DistanceUtil;
import com.ef.golf.util.SearchCaddieResult;
import com.ef.golf.vo.SolrCaddieVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 * 查询solr索引库
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SolrCaddieListAction extends AbstractService {

    @Resource
    private UserService userServicel;

    //排序类型
    private String sortType = "1";//1.亿方推荐(默认) 2.好评优先

    //服务球龄
    private String serviceAge;//1.1-3年 2.3-5年 3.5-8年 4.8-10年 5.大于等于10年

    private String qiuHuiName;
    private String realname;//球童真实名字
    private String exclusive_no;//球童专属码
    private Integer page = 1;
    private Integer rows = 5;


    public Object doService() {
        SearchCaddieResult searchCaddieResult = userServicel.getCaddieListSolrIndex(sortType, serviceAge, qiuHuiName, realname, exclusive_no, page, rows);
        return searchCaddieResult;
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

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setExclusive_no(String exclusive_no) {
        this.exclusive_no = exclusive_no;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
