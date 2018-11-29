package com.ef.golf.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TravelLineDetailModule {
    private Integer lineModuleId;
    private Date createTime;
    private Date modifyTime;
    private String createUser;
    private String modifyUser;

    private Integer lineId;
    private Integer sort;

    private String name; //详情小模块名

    private String modulePicUrl; //详情小模块图片(前端不用)

    private String[] modulePicUrls;//详情小模块图片数组(前端)

    private String ratio;

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public Integer getLineModuleId() {
        return lineModuleId;
    }

    public void setLineModuleId(Integer lineModuleId) {
        this.lineModuleId = lineModuleId;
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

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getModulePicUrl() {
        return modulePicUrl;
    }

    public void setModulePicUrl(String modulePicUrl) {
        this.modulePicUrl = modulePicUrl == null ? null : modulePicUrl.trim();
    }


    public String[] getModulePicUrls() {
        if(StringUtils.isNotBlank(modulePicUrl)){
            modulePicUrls = modulePicUrl.split(",");
        }
        return modulePicUrls;
    }

    public void setModulePicUrls(String[] modulePicUrls) {
        this.modulePicUrls = modulePicUrls;
    }
}