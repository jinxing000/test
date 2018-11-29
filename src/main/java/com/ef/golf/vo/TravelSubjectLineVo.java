package com.ef.golf.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/11/14 12:15
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelSubjectLineVo {

    private Integer subjectId; //专题id
    private String subjectName; //专题名字
    private String imgUrl; //专题图标
    private List<TravelLineVo> travelLineVo;//专题内线路



    public TravelSubjectLineVo() {
    }

    public TravelSubjectLineVo(Integer subjectId, String subjectName, String imgUrl, List<TravelLineVo> travelLineVo) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.imgUrl = imgUrl;
        this.travelLineVo = travelLineVo;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<TravelLineVo> getTravelLineVo() {

        return travelLineVo;
    }

    public void setTravelLineVo(List<TravelLineVo> travelLineVo) {
        this.travelLineVo = travelLineVo;
    }
}
