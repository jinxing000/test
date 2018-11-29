package com.ef.golf.pojo;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Photo_album {
    private Integer photoId;

    private Integer userId;

    private String photoType;

    private String photoUrl;

    private String createUser;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

   /* private String[] photoUrls;*/

    /*public String[] getPhotoUrls() {
        if (StringUtils.isNotBlank(photoUrl)){
            photoUrls = photoUrl.split(",");
        }
        return photoUrls;
    }
*/
    /*public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }*/

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType == null ? null : photoType.trim();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl == null ? null : photoUrl.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}