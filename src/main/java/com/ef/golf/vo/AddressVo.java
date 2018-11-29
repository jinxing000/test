package com.ef.golf.vo;

public class AddressVo {
    private  String addr_id;//地址id
    private String def_addr;//是否默认收货地址
    private String addr;//收货地址
    private String name;//收货人姓名
    private String mobile;//收货人电话
    private Double firstunit ;//首重
    private Double continueunit ;//续重
    private Double firstprice ;//首费
    private Double continueprice ;//续费
    private String type_id;//配送方式id
    private String province;//市,县
    private  String city;//区
    private  String region;//详细地区
    private  String province_id;
    private String city_id;
    private String region_id;

    //private String type_name;//配送方式名字


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(String addr_id) {
        this.addr_id = addr_id;
    }

    public String getDef_addr() {
        return def_addr;
    }

    public void setDef_addr(String def_addr) {
        this.def_addr = def_addr;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getFirstunit() {
        return firstunit;
    }

    public void setFirstunit(Double firstunit) {
        this.firstunit = firstunit;
    }

    public Double getContinueunit() {
        return continueunit;
    }

    public void setContinueunit(Double continueunit) {
        this.continueunit = continueunit;
    }

    public Double getFirstprice() {
        return firstprice;
    }

    public void setFirstprice(Double firstprice) {
        this.firstprice = firstprice;
    }

    public Double getContinueprice() {
        return continueprice;
    }

    public void setContinueprice(Double continueprice) {
        this.continueprice = continueprice;
    }
}
