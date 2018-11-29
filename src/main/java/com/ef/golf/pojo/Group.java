package com.ef.golf.pojo;

import java.util.Date;

/**
 * Created by Administrator on 2018/5/10.
 * 群实体类
 *
 */
public class Group {
    //群id
private Integer id;
//对应的容联云groupId
    private  String rongLianYunGroupId;
    //所属球会id
    private Integer  qiuHuiId;

    //群名称
private String qunName;
    //群管理员
private Integer userId;

    //群申请加入模式
    // 0：默认直接加入
//1需要身份验证
//2私有群组    缺省0

    private String permission;

    //群公告
    private String declared;

    //群成立时间
    private Date groupCreateTime;
    //群修改时间
    private Date groupModifyTime;

    //状态  是否删除
    private int del;
    //创建人id
    private  Integer  createUser;
    //修改人id
    private Integer modifyUser;

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Group() {
    }

    public Group(String rongLianYunGroupId, Integer  qiuHuiId, String qunName, Integer userId, String permission, String declared, Date groupCreateTime, Date groupModifyTime, int del, Integer createUser, Integer modifyUser) {
        this.rongLianYunGroupId=rongLianYunGroupId;
        this.qiuHuiId=qiuHuiId;
        this.qunName = qunName;
        this.userId = userId;
        this.permission = permission;
        this.declared = declared;
        this.groupCreateTime = groupCreateTime;
        this.groupModifyTime=groupModifyTime;
        this.del = del;
        this.createUser = createUser;
        this.modifyUser = modifyUser;
    }

    public Group(Integer id,String rongLianYunGroupId,Integer  qiuHuiId,String qunName, Integer userId, String permission, String declared, Date groupCreateTime,Date groupModifyTime, int del, Integer createUser, Integer modifyUser) {
        this.id = id;
        this.rongLianYunGroupId=rongLianYunGroupId;
        this.qiuHuiId=qiuHuiId;
        this.qunName = qunName;
        this.userId = userId;
        this.permission = permission;
        this.declared = declared;
        this.groupCreateTime = groupCreateTime;
        this.groupModifyTime=groupModifyTime;
        this.del = del;
        this.createUser = createUser;
        this.modifyUser = modifyUser;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRongLianYunGroupId() {
        return rongLianYunGroupId;
    }

    public Integer getQiuHuiId() {
        return qiuHuiId;
    }

    public void setQiuHuiId(Integer qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public void setRongLianYunGroupId(String rongLianYunGroupId) {
        this.rongLianYunGroupId = rongLianYunGroupId;
    }

    public String getQunName() {
        return qunName;
    }

    public void setQunName(String qunName) {
        this.qunName = qunName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDeclared() {
        return declared;
    }

    public void setDeclared(String declared) {
        this.declared = declared;
    }

    public Date getGroupCreateTime() {
        return groupCreateTime;
    }

    public void setGroupCreateTime(Date groupCreateTime) {
        this.groupCreateTime = groupCreateTime;
    }

    public Date getGroupModifyTime() {
        return groupModifyTime;
    }

    public void setGroupModifyTime(Date groupModifyTime) {
        this.groupModifyTime = groupModifyTime;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Integer modifyUser) {
        this.modifyUser = modifyUser;
    }
}
