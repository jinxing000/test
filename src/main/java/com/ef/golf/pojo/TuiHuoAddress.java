package com.ef.golf.pojo;

/**
 * Created by Administrator on 2018/6/14.
 * 退货回寄的地址 也就是让用户寄回到哪里去
 */
public class TuiHuoAddress {
    private Integer id;//    id
    private String  name;//	用户退货的收货人
    private String  mobile;//用户退货的收货人电话
    private String  province;// 收货省
    private String  city;//	收货市
    private String  region;// 	收货区
    private String  addr;//包括省市区以及具体的地址的地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
