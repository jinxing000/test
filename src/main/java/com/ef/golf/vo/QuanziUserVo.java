package com.ef.golf.vo;


import com.ef.golf.core.structure.pageModel.Page;

/**
 * create by xzw
 *2018年1月5日16:38:32
 * 保存圈子里用户主页信息
 */
public class QuanziUserVo extends Page {

    private Integer userId;//用户id
    private String nickName;//用户昵称
    private String headPortraitUrl;//头像url
    private String userType;//用户身份类型
    private String ballAge;//球龄
    private String age;//年龄   通过生日计算而来
    private String chaDian;//差点
    private String gender;//性别
    private String grade;//教练等级
    private String vip;//充值等级
    private String region;//地区
    private String shibadong;//十八洞最好成绩只有教练用
    private String score;//评分  注意与 用户表中的score 区分，用户表中score是教练的十八洞
    private String subjection;//隶属
    private String description;
    private String exclusiveNo;//专属码
    private String approveStatus;//认证状态

    private String qiuHuiName;//球会名称
    private String qiuHuiId;//球会id

    private String sortType;//用户排序方式  接收前端传递过来的参数  列表排序规则
    private String serviceAge;//服务球龄
    private String usePay;//下场费（教练专用）

    private String hasAttend="0";//是否关注，默认为0，未关注，1表示已关注

    public String getHasAttend() {
        return hasAttend;
    }

    public void setHasAttend(String hasAttend) {
        this.hasAttend = hasAttend;
    }

    public QuanziUserVo(Integer userId, String nickName, String headPortraitUrl, String userType, String ballAge, String age, String chaDian, String gender, String grade, String vip, String region, String shibadong, String score, String subjection, String description, String exclusiveNo, String approveStatus, String qiuHuiName, String qiuHuiId, String sortType, String serviceAge, String usePay, String hasAttend) {
        this.userId = userId;
        this.nickName = nickName;
        this.headPortraitUrl = headPortraitUrl;
        this.userType = userType;
        this.ballAge = ballAge;
        this.age = age;
        this.chaDian = chaDian;
        this.gender = gender;
        this.grade = grade;
        this.vip = vip;
        this.region = region;
        this.shibadong = shibadong;
        this.score = score;
        this.subjection = subjection;
        this.description = description;
        this.exclusiveNo = exclusiveNo;
        this.approveStatus = approveStatus;
        this.qiuHuiName = qiuHuiName;
        this.qiuHuiId = qiuHuiId;
        this.sortType = sortType;
        this.serviceAge = serviceAge;
        this.usePay = usePay;
        this.hasAttend = hasAttend;
    }

    public QuanziUserVo(){}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getBallAge() {
        return ballAge;
    }

    public void setBallAge(String ballAge) {
        this.ballAge = ballAge;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getChaDian() {
        return chaDian;
    }

    public void setChaDian(String chaDian) {
        this.chaDian = chaDian;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSubjection() {
        return subjection;
    }

    public void setSubjection(String subjection) {
        this.subjection = subjection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExclusiveNo() {
        return exclusiveNo;
    }

    public void setExclusiveNo(String exclusiveNo) {
        this.exclusiveNo = exclusiveNo;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getQiuHuiName() {
        return qiuHuiName;
    }

    public void setQiuHuiName(String qiuHuiName) {
        this.qiuHuiName = qiuHuiName;
    }

    public String getQiuHuiId() {
        return qiuHuiId;
    }

    public void setQiuHuiId(String qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getServiceAge() {
        return serviceAge;
    }

    public void setServiceAge(String serviceAge) {
        this.serviceAge = serviceAge;
    }

    public String getUsePay() {
        return usePay;
    }

    public void setUsePay(String usePay) {
        this.usePay = usePay;
    }

    public String getShibadong() {
        return shibadong;
    }

    public void setShibadong(String shibadong) {
        this.shibadong = shibadong;
    }
}
