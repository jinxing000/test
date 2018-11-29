package com.ef.golf.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

/**
 *个人信息
 */
public class MineVo {

    private String userId;//用户id

    private String headPortraitUrl;//头像

    private String gender;//性别

    private String birthday;//生日

    private Integer age;//年龄

    private String region;//上次登陆城市

    private Integer ballAge;//球龄

    private String phone;//联系电话

    private String description;//个性签名
    /*wxc 2018.3.22 start*/
    private String vip;//vip等级
    /*wxc 2018.3.22 end*/
    private String qiuHuiName;//所属球会
    private String chaDian;//差点
    private String shibadong;//教练 专属  的十八洞成绩
    private Integer scoreTotal;//总
    private Integer totalScore;
    private Double usePay;//教练下场费
    private String grade;//教练等级
    private Boolean isSetting;//支付密码设置的标记

   /* private String realname;*/
   private  String nickName;//昵称

   private  String userType;//用户类型
   private String exclusiveNo;//专属码
    private String recExclusiveNo;//绑定人专属码
    private Integer signCount;//签到次数
    private Double balance;

    private String memberLevel;

    private Integer tickets;//拥有的优惠券数量

    private String approveStatus;
    private String realname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Boolean getIsSetting() {
        return isSetting;
    }

    public void setIsSetting(Boolean isSetting) {
        this.isSetting = isSetting;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public String getExclusiveNo() {
        return exclusiveNo;
    }

    public void setExclusiveNo(String exclusiveNo) {
        this.exclusiveNo = exclusiveNo;
    }

    public Double getUsePay() {
        return usePay;
    }

    public void setUsePay(Double usePay) {
        this.usePay = usePay;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getChaDian() {
        return chaDian;
    }

    public void setChaDian(String chaDian) {
        this.chaDian = chaDian;
    }

    public String getShibadong() {
        return shibadong;
    }

    public void setShibadong(String shibadong) {
        this.shibadong = shibadong;
    }

    public Boolean getSetting() {
        return isSetting;
    }

    public void setSetting(Boolean setting) {
        isSetting = setting;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String city) {
        this.region = city;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getBallAge() {
        return ballAge;
    }

    public void setBallAge(Integer ballAge) {
        this.ballAge = ballAge;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQiuHuiName() {
        return qiuHuiName;
    }

    public void setQiuHuiName(String qiuHuiName) {
        this.qiuHuiName = qiuHuiName;
    }

    public String getRecExclusiveNo() {
        return recExclusiveNo;
    }

    public void setRecExclusiveNo(String recExclusiveNo) {
        this.recExclusiveNo = recExclusiveNo;
    }

    /*  public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }*/

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public MineVo(){}

    public MineVo(String userId, String headPortraitUrl, String gender, String birthday, Integer age, String region, Integer ballAge, String phone, String description, String vip, String qiuHuiName, String chaDian, String shibadong, Integer scoreTotal, Integer totalScore, Double usePay, String grade, Boolean isSetting, String nickName, String userType, String exclusiveNo, String recExclusiveNo, Integer signCount, Double balance, String memberLevel, Integer tickets, String approveStatus) {
        this.userId = userId;
        this.headPortraitUrl = headPortraitUrl;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.region = region;
        this.ballAge = ballAge;
        this.phone = phone;
        this.description = description;
        this.vip = vip;
        this.qiuHuiName = qiuHuiName;
        this.chaDian = chaDian;
        this.shibadong = shibadong;
        this.scoreTotal = scoreTotal;
        this.totalScore = totalScore;
        this.usePay = usePay;
        this.grade = grade;
        this.isSetting = isSetting;
        this.nickName = nickName;
        this.userType = userType;
        this.exclusiveNo = exclusiveNo;
        this.recExclusiveNo = recExclusiveNo;
        this.signCount = signCount;
        this.balance = balance;
        this.memberLevel = memberLevel;
        this.tickets = tickets;
        this.approveStatus = approveStatus;
    }
}
