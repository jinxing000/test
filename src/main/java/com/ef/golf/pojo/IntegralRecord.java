package com.ef.golf.pojo;

import java.util.Date;

public class IntegralRecord {
    private Integer integralRecordId;

    private Long userId;

    private String score;//积分变动 例如+100 or -100

    private Integer proportion;//积分变动比例

    private String alterationNote;//更改说明 "xxx修改积分""充值送积分""签到送积分""使用积分"

    private Date createTime;

    public IntegralRecord() {
    }

    public Integer getIntegralRecordId() {
        return integralRecordId;
    }

    public void setIntegralRecordId(Integer integralRecordId) {
        this.integralRecordId = integralRecordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public String getAlterationNote() {
        return alterationNote;
    }

    public void setAlterationNote(String alterationNote) {
        this.alterationNote = alterationNote == null ? null : alterationNote.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public IntegralRecord(Integer integralRecordId, Long userId, String score, Integer proportion, String alterationNote, Date createTime) {
        this.integralRecordId = integralRecordId;
        this.userId = userId;
        this.score = score;
        this.proportion = proportion;
        this.alterationNote = alterationNote;
        this.createTime = createTime;
    }
}