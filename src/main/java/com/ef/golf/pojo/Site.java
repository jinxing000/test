package com.ef.golf.pojo;

import com.ef.golf.core.structure.pageModel.Page;

import java.io.Serializable;
import java.util.Date;

public class Site  extends Page implements Serializable {
    private Integer siteId;//球场ID

    private String siteModel;//球场模式

    private String siteCreateTime;//建立时间

    private String siteArea;//球场面积

    private String greenGrassType;//果岭草种

    private String siteParam;//球场数据

    private String siteDesigner;//设计师

    private String fairwayLength;//球道长度

    private String fairwayGrassType;//球道草种

    private String siteAddress;//球场地址

    private String siteCity;//所在城市

    private String sitePhone;//球场电话

    private String service1;//服务1

    private String service2;//服务2

    private String service3;//服务3

    private String service4;//服务4

    private String service5;//

    private String service6;//

    private String service7;//

    private String service8;//

    private String service9;//

    private Date shelvesTime;//上架时间

    private Date shelfTime;//下架时间

    private Integer salesVolume;//销量

    private String weekdaysEfMemberPrice;//平日价

    private String tehuiriPrice;//特惠日价格
    private String tehuiri;//特惠日

    private String weekdaysSiteMemberPrice;//球场会员价（不区分平日假日和特惠日，价格常年固定）

    private String weekdaysCustomerPrice;//非会员价（平日）废除

    private String holidayEfMemberPrice;//平台会员价（假日）

    private String holidaySiteMemberPrice;//球场会员价（假日）废除

    private String holidayCustomerPrice;//非会员价（假日）废除

    private String reserveExplain;//预约说明

    private String cancelExplain;//取消通知说明

    private String siteRemarks;//备注

    private String siteJw;//场地经纬

    private String reserve1;//经度

    private String reserve2;//维度

    private String reserve3;//备用字段3 上下架状态（1上架 2下架）

    private String reserve4;//备用字段4(球场名称)

    private String reserve5;//备用字段5(是否推荐 1.推荐，2.取消推荐)

    private Date createTime;//创建时间

    private Date modifyTime;//修改时间

    private String createUser;//创建人

    private String modifyUser;//修改人

    private String siteIntroduction;//球场简介

    private String qiuHuiId;//所属球会的id

    private int sortType;

    public void setTehuiriPrice(String tehuiriPrice) {
        this.tehuiriPrice = tehuiriPrice;
    }

    public void setTehuiri(String tehuiri) {
        this.tehuiri = tehuiri;
    }

    public String getQiuHuiId() {
        return qiuHuiId;
    }

    public void setQiuHuiId(String qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteModel() {
        return siteModel;
    }

    public void setSiteModel(String siteModel) {
        this.siteModel = siteModel == null ? null : siteModel.trim();
    }

    public String getSiteCreateTime() {
        return siteCreateTime;
    }

    public void setSiteCreateTime(String siteCreateTime) {
        this.siteCreateTime = siteCreateTime == null ? null : siteCreateTime.trim();
    }

    public String getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(String siteArea) {
        this.siteArea = siteArea == null ? null : siteArea.trim();
    }

    public String getGreenGrassType() {
        return greenGrassType;
    }

    public void setGreenGrassType(String greenGrassType) {
        this.greenGrassType = greenGrassType == null ? null : greenGrassType.trim();
    }

    public String getSiteParam() {
        return siteParam;
    }

    public void setSiteParam(String siteParam) {
        this.siteParam = siteParam == null ? null : siteParam.trim();
    }

    public String getSiteDesigner() {
        return siteDesigner;
    }

    public void setSiteDesigner(String siteDesigner) {
        this.siteDesigner = siteDesigner == null ? null : siteDesigner.trim();
    }

    public String getFairwayLength() {
        return fairwayLength;
    }

    public void setFairwayLength(String fairwayLength) {
        this.fairwayLength = fairwayLength == null ? null : fairwayLength.trim();
    }

    public String getFairwayGrassType() {
        return fairwayGrassType;
    }

    public void setFairwayGrassType(String fairwayGrassType) {
        this.fairwayGrassType = fairwayGrassType == null ? null : fairwayGrassType.trim();
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress == null ? null : siteAddress.trim();
    }

    public String getSiteCity() {
        return siteCity;
    }

    public void setSiteCity(String siteCity) {
        this.siteCity = siteCity == null ? null : siteCity.trim();
    }

    public String getSitePhone() {
        return sitePhone;
    }

    public void setSitePhone(String sitePhone) {
        this.sitePhone = sitePhone == null ? null : sitePhone.trim();
    }

    public String getService1() {
        return service1;
    }

    public void setService1(String service1) {
        this.service1 = service1 == null ? null : service1.trim();
    }

    public String getService2() {
        return service2;
    }

    public void setService2(String service2) {
        this.service2 = service2 == null ? null : service2.trim();
    }

    public String getService3() {
        return service3;
    }

    public void setService3(String service3) {
        this.service3 = service3 == null ? null : service3.trim();
    }

    public String getService4() {
        return service4;
    }

    public void setService4(String service4) {
        this.service4 = service4 == null ? null : service4.trim();
    }

    public String getService5() {
        return service5;
    }

    public void setService5(String service5) {
        this.service5 = service5 == null ? null : service5.trim();
    }

    public String getService6() {
        return service6;
    }

    public void setService6(String service6) {
        this.service6 = service6 == null ? null : service6.trim();
    }

    public String getService7() {
        return service7;
    }

    public void setService7(String service7) {
        this.service7 = service7 == null ? null : service7.trim();
    }

    public String getService8() {
        return service8;
    }

    public void setService8(String service8) {
        this.service8 = service8 == null ? null : service8.trim();
    }

    public String getService9() {
        return service9;
    }

    public void setService9(String service9) {
        this.service9 = service9 == null ? null : service9.trim();
    }

    public Date getShelvesTime() {
        return shelvesTime;
    }

    public void setShelvesTime(Date shelvesTime) {
        this.shelvesTime = shelvesTime;
    }

    public Date getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(Date shelfTime) {
        this.shelfTime = shelfTime;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getWeekdaysEfMemberPrice() {
        return weekdaysEfMemberPrice;
    }

    public void setWeekdaysEfMemberPrice(String weekdaysEfMemberPrice) {
        this.weekdaysEfMemberPrice = weekdaysEfMemberPrice == null ? null : weekdaysEfMemberPrice.trim();
    }

    public String getWeekdaysSiteMemberPrice() {
        return weekdaysSiteMemberPrice;
    }

    public void setWeekdaysSiteMemberPrice(String weekdaysSiteMemberPrice) {
        this.weekdaysSiteMemberPrice = weekdaysSiteMemberPrice == null ? null : weekdaysSiteMemberPrice.trim();
    }

    public String getWeekdaysCustomerPrice() {
        return weekdaysCustomerPrice;
    }

    public void setWeekdaysCustomerPrice(String weekdaysCustomerPrice) {
        this.weekdaysCustomerPrice = weekdaysCustomerPrice == null ? null : weekdaysCustomerPrice.trim();
    }

    public String getHolidayEfMemberPrice() {
        return holidayEfMemberPrice;
    }

    public void setHolidayEfMemberPrice(String holidayEfMemberPrice) {
        this.holidayEfMemberPrice = holidayEfMemberPrice == null ? null : holidayEfMemberPrice.trim();
    }

    public String getHolidaySiteMemberPrice() {
        return holidaySiteMemberPrice;
    }

    public void setHolidaySiteMemberPrice(String holidaySiteMemberPrice) {
        this.holidaySiteMemberPrice = holidaySiteMemberPrice == null ? null : holidaySiteMemberPrice.trim();
    }

    public String getHolidayCustomerPrice() {
        return holidayCustomerPrice;
    }

    public void setHolidayCustomerPrice(String holidayCustomerPrice) {
        this.holidayCustomerPrice = holidayCustomerPrice == null ? null : holidayCustomerPrice.trim();
    }

    public String getReserveExplain() {
        return reserveExplain;
    }

    public void setReserveExplain(String reserveExplain) {
        this.reserveExplain = reserveExplain == null ? null : reserveExplain.trim();
    }

    public String getCancelExplain() {
        return cancelExplain;
    }

    public void setCancelExplain(String cancelExplain) {
        this.cancelExplain = cancelExplain == null ? null : cancelExplain.trim();
    }

    public String getSiteRemarks() {
        return siteRemarks;
    }

    public void setSiteRemarks(String siteRemarks) {
        this.siteRemarks = siteRemarks == null ? null : siteRemarks.trim();
    }

    public String getSiteJw() {
        return siteJw;
    }

    public void setSiteJw(String siteJw) {
        this.siteJw = siteJw == null ? null : siteJw.trim();
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4 == null ? null : reserve4.trim();
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5 == null ? null : reserve5.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public String getSiteIntroduction() {
        return siteIntroduction;
    }


    public void setSiteIntroduction(String siteIntroduction) {
        this.siteIntroduction = siteIntroduction == null ? null : siteIntroduction.trim();
    }
}