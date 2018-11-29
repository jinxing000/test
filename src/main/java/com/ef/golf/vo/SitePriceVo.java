package com.ef.golf.vo;


/**
 * create by xzw
 * 2018年2月7日16:13:43
 * 查询球场价格
 */
public class SitePriceVo {

    private Integer siteId;//球场id
    private String beginDate;//开始日期
    private String endDate;//结束日期

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
