package com.ef.golf.pojo;

public class Score_attr {
    private Integer attrId;//评分项id

    private String attrNam;//评分项名称

    private String attrAscription;//评分归属（1场地 2.教练 3.球童 4.球队 5.赛事 6.商家 7.商城 8.旅游 9.课程）

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getAttrNam() {
        return attrNam;
    }

    public void setAttrNam(String attrNam) {
        this.attrNam = attrNam == null ? null : attrNam.trim();
    }

    public String getAttrAscription() {
        return attrAscription;
    }

    public void setAttrAscription(String attrAscription) {
        this.attrAscription = attrAscription == null ? null : attrAscription.trim();
    }
}