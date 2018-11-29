package com.ef.golf.pojo;

import com.ef.golf.core.structure.pageModel.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User extends Page {
    private Long id;

    private String nickname;

    private String account;

    private String pswd;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private Long status;

    private String userName;

    private String phone;

    private String openId;

    private String qqId;

    private String weiboId;

    private String passwordLogin;

    private String passwordPay;

    private String recExclusiveNo;

    private String exclusiveNo;

    private String subjection;

    private String description;

    private Integer ballAge;

    private String realname;

    private String idCardNumber;

    private String bankCardNumber;

    private String bankName;

    private String region;

    private String gender;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String headPortraitUrl;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    private String userType;

    private String userState;

    private String isAlive;


    //数据库ef_golf(1.7）版本新增属性
    private String chaDian;

    private String recommend;

    private String grade;

    private String vip;

    private Double usePay;

    private String score;

    private String lat;

    private String lng;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd == null ? null : pswd.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId == null ? null : qqId.trim();
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId == null ? null : weiboId.trim();
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin == null ? null : passwordLogin.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getPasswordPay() {
        return passwordPay;
    }

    public void setPasswordPay(String passwordPay) {
        this.passwordPay = passwordPay == null ? null : passwordPay.trim();
    }

    public String getRecExclusiveNo() {
        return recExclusiveNo;
    }

    public void setRecExclusiveNo(String recExclusiveNo) {
        this.recExclusiveNo = recExclusiveNo == null ? null : recExclusiveNo.trim();
    }

    public String getExclusiveNo() {
        return exclusiveNo;
    }

    public void setExclusiveNo(String exclusiveNo) {
        this.exclusiveNo = exclusiveNo == null ? null : exclusiveNo.trim();
    }

    public String getSubjection() {
        return subjection;
    }

    public void setSubjection(String subjection) {
        this.subjection = subjection == null ? null : subjection.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getBallAge() {
        return ballAge;
    }

    public void setBallAge(Integer ballAge) {
        this.ballAge = ballAge;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber == null ? null : idCardNumber.trim();
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber == null ? null : bankCardNumber.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl == null ? null : headPortraitUrl.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState == null ? null : userState.trim();
    }

    public String getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(String isAlive) {
        this.isAlive = isAlive == null ? null : isAlive.trim();
    }

    public String getChaDian() {
        return chaDian;
    }

    public void setChaDian(String chaDian) {
        this.chaDian = chaDian;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }


    public Double getUsePay() {
        return usePay;
    }

    public void setUsePay(Double usePay) {
        this.usePay = usePay;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", account='" + account + '\'' +
                ", pswd='" + pswd + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                ", qqId='" + qqId + '\'' +
                ", weiboId='" + weiboId + '\'' +
                ", passwordLogin='" + passwordLogin + '\'' +
                ", passwordPay='" + passwordPay + '\'' +
                ", recExclusiveNo='" + recExclusiveNo + '\'' +
                ", exclusiveNo='" + exclusiveNo + '\'' +
                ", subjection='" + subjection + '\'' +
                ", description='" + description + '\'' +
                ", ballAge=" + ballAge +
                ", realname='" + realname + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", bankCardNumber='" + bankCardNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", modifyTime=" + modifyTime +
                ", userType='" + userType + '\'' +
                ", userState='" + userState + '\'' +
                ", isAlive='" + isAlive + '\'' +
                '}';
    }
}