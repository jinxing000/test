package com.ef.golf.vo;

/**
 *球场日历价格数据
 */
public class SolrSiteVo {

    private int siteId;//球场id
    private String siteModel;//球场模式
    private String siteParam;//球场数据
    private String siteAddress;//球场地址
    private String siteCity;//所在城市
    private Double price;//球场价格
    private String reserve4;//球场名字
    private Double reserve1;//经度
    private Double reserve2;//纬度
    private String cYear;//年
    private String cMonth;//月
    private String cDay;//日
    private String cWeek;//周几
    private String distance;//距离


    public SolrSiteVo() {
    }

    public SolrSiteVo(int siteId, String siteModel, String siteParam, String siteAddress, String siteCity, Double price, String reserve4, Double reserve1, Double reserve2, String cYear, String cMonth, String cDay, String cWeek, String distance) {
        this.siteId = siteId;
        this.siteModel = siteModel;
        this.siteParam = siteParam;
        this.siteAddress = siteAddress;
        this.siteCity = siteCity;
        this.price = price;
        this.reserve4 = reserve4;
        this.reserve1 = reserve1;
        this.reserve2 = reserve2;
        this.cYear = cYear;
        this.cMonth = cMonth;
        this.cDay = cDay;
        this.cWeek = cWeek;
        this.distance = distance;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteModel() {
        return siteModel;
    }

    public void setSiteModel(String siteModel) {
        this.siteModel = siteModel;
    }

    public String getSiteParam() {
        return siteParam;
    }

    public void setSiteParam(String siteParam) {
        this.siteParam = siteParam;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public String getSiteCity() {
        return siteCity;
    }

    public void setSiteCity(String siteCity) {
        this.siteCity = siteCity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    public Double getReserve1() {
        return reserve1;
    }

    public void setReserve1(Double reserve1) {
        this.reserve1 = reserve1;
    }

    public Double getReserve2() {
        return reserve2;
    }

    public void setReserve2(Double reserve2) {
        this.reserve2 = reserve2;
    }

    public String getcYear() {
        return cYear;
    }

    public void setcYear(String cYear) {
        this.cYear = cYear;
    }

    public String getcMonth() {
        return cMonth;
    }

    public void setcMonth(String cMonth) {
        this.cMonth = cMonth;
    }

    public String getcDay() {
        return cDay;
    }

    public void setcDay(String cDay) {
        this.cDay = cDay;
    }

    public String getcWeek() {
        return cWeek;
    }

    public void setcWeek(String cWeek) {
        this.cWeek = cWeek;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
