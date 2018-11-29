package com.ef.golf.pojo;

import com.ef.golf.vo.OrderCommonVo;

import java.util.Date;

public class Coach_order extends OrderCommonVo {
    private Integer id;

    private Integer coachId;

   private Integer qiuhuiId;

    /*private Integer siteId;*/

    private Long orderId;

    private String playDate;

    private String playTime;

    private String coachName;

    private String playName;

    private String qiuhuiName;

    /*private String siteName;*/

    private String contactsPhone;

    private String remark;

    private Date createTime;

    private Date modifyTime;

    private String createUser;

    private String modifyUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

   /* public Integer getQiuhuiId() {
        return qiuhuiId;
    }

    public void setQiuhuiId(Integer qiuhuiId) {
        this.qiuhuiId = qiuhuiId;
    }*/

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate == null ? null : playDate.trim();
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime == null ? null : playTime.trim();
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName == null ? null : coachName.trim();
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName == null ? null : playName.trim();
    }

   /* public String getQiuhuiName() {
        return qiuhuiName;
    }

    public void setQiuhuiName(String qiuhuiName) {
        this.qiuhuiName = qiuhuiName == null ? null : qiuhuiName.trim();
    }*/

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getQiuhuiId() {
        return qiuhuiId;
    }

    public void setQiuhuiId(Integer qiuhuiId) {
        this.qiuhuiId = qiuhuiId;
    }

    public String getQiuhuiName() {
        return qiuhuiName;
    }

    public void setQiuhuiName(String qiuhuiName) {
        this.qiuhuiName = qiuhuiName;
    }
}