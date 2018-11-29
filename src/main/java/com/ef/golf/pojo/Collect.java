package com.ef.golf.pojo;

import java.util.Date;
import java.util.List;

public class Collect {
    private Integer collectId;

    private Long userId;

    private Integer productId;

    private String collectType;//1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.普通用户 10.会员关注球会 11.员工关注球会

    //2017年12月23日16:54:28  圈子模块，数据库1.7版本新增字段
    private String remark;

    private Date createTime;

    private Date modifyTime;

    private String createUser;

    private String modifyUser;

    private List<User> collectPersionsList;



    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType == null ? null : collectType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
    public List<User> getCollectPersionsList() {
        return collectPersionsList;
    }

    public void setCollectPersionsList(List<User> collectPersionsList) {
        this.collectPersionsList = collectPersionsList;
    }
}