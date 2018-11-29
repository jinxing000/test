package com.ef.golf.vo;

/**
 * 球童详情
 */
public class SolrCaddieVo {

    private Integer id;//球童id
    private String realname;//真实姓名
    private String subjection;//所属球会 早期的 废弃
    private String nickName;//昵称
    private String qiuhuiName;//球会名
    private Double score;//球童评分
    private String recommend;//是否推荐
    private Integer ballAge;//球龄
    private String headPortraitUrl;//头像url地址
    private String exclusiveNo;//专属码
    private String region;//上次登陆城市
    private Integer qiuhuiId;//所属球会id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQiuhuiId() {
        return qiuhuiId;
    }

    public void setQiuhuiId(Integer qiuhuiId) {
        this.qiuhuiId = qiuhuiId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSubjection() {
        return subjection;
    }

    public void setSubjection(String subjection) {
        this.subjection = subjection;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getQiuhuiName() {
        return qiuhuiName;
    }

    public void setQiuhuiName(String qiuhuiName) {
        this.qiuhuiName = qiuhuiName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getBallAge() {
        return ballAge;
    }

    public void setBallAge(Integer ballAge) {
        this.ballAge = ballAge;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getExclusiveNo() {
        return exclusiveNo;
    }

    public void setExclusiveNo(String exclusiveNo) {
        this.exclusiveNo = exclusiveNo;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public SolrCaddieVo() {
    }

    public SolrCaddieVo(Integer id,Integer qiuhuiId, String realname, String subjection, String nickName, String qiuhuiName, Double score, String recommend, Integer ballAge, String headPortraitUrl, String exclusiveNo, String region) {
        this.id = id;
        this.realname = realname;
        this.subjection = subjection;
        this.nickName = nickName;
        this.qiuhuiName = qiuhuiName;
        this.score = score;
        this.recommend = recommend;
        this.ballAge = ballAge;
        this.headPortraitUrl = headPortraitUrl;
        this.exclusiveNo = exclusiveNo;
        this.region = region;
        this.qiuhuiId = qiuhuiId;
    }
}
