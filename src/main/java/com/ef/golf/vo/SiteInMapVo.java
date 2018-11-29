package com.ef.golf.vo;


/**
 * 用作球场信息展示在地图中
 */
public class SiteInMapVo {

    private String SiteName;//球场名称
    private String lng;//经度
    private String lat;//纬度
    private String lngAndLat;

    public String getLngAndLat() {
        return lngAndLat;
    }

    public void setLngAndLat(String lngAndLat) {
        this.lngAndLat = lngAndLat;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
