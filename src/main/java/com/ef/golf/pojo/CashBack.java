package com.ef.golf.pojo;

/**
 * com.ef.golf.pojo
 * Administrator
 * 2018/4/28 11:14
 */
public class CashBack {

    private Integer id;
    private String maxnum;//最大值
    private String minnum;//最小值
    private String createUser;//创建人姓名
    private String createTime;//创建时间
    private String createUserid;//创建人id
    private String modifyUser;//修改人姓名
    private String modifyUserid;//修改人id
    private String modifyTime;//修改时间
    private int proportion;//  返现比例
    private String remark;//备注


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(String maxnum) {
        this.maxnum = maxnum;
    }

    public String getMinnum() {
        return minnum;
    }

    public void setMinnum(String minnum) {
        this.minnum = minnum;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyUserid() {
        return modifyUserid;
    }

    public void setModifyUserid(String modifyUserid) {
        this.modifyUserid = modifyUserid;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
