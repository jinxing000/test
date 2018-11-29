package com.ef.golf.pojo;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/4/24 10:19
 */
public class GetHopeMsg {

    private Integer id;/** id */
    private Integer hopeId;/** 愿望id */
    private String consignee;/** 收货人 */
    private String phone;/** 手机号 */
    private String address;/** 地址 */
    private String waybillNo;/** 运单号 */
    private String company;/** 快递公司 */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHopeId() {
        return hopeId;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
