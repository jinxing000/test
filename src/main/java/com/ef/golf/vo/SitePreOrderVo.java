package com.ef.golf.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/16.
 *
 * 订场预下单接口Vo
 *
 * 球场id   siteId
 * 球场名称  reserve4
 *  预定说明 reserveExplain
 *  取消说明 cancelExplain
 *  球场会员价
 *  球场非会员当日价
 *  球场数据    site_param
 *
 *   es.site_id siteId,
 *   es.reserve4 siteName,
 *   es.weekdays_site_member_price memberPrice,
 *   esp.price sitePrice,
 *   reserve_explain reserveExplain,
 *   cancel_explain cancleExplain,
 *   site_param siteParam
 *
 * 球场预下单
 */
public class SitePreOrderVo  implements Serializable {

    private Integer siteId;//球场id
    private String siteName;//球场名字
    private Double memberPrice;//球会会员价
    private Double sitePrice;//普通价
    private String reserveExplain;//预定说明
    private String cancleExplain;//取消说明
    private String siteParam;//球场数据

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Double getSitePrice() {
        return sitePrice;
    }

    public void setSitePrice(Double sitePrice) {
        this.sitePrice = sitePrice;
    }

    public String getReserveExplain() {
        return reserveExplain;
    }

    public void setReserveExplain(String reserveExplain) {
        this.reserveExplain = reserveExplain;
    }

    public String getCancleExplain() {
        return cancleExplain;
    }

    public void setCancleExplain(String cancleExplain) {
        this.cancleExplain = cancleExplain;
    }

    public String getSiteParam() {
        return siteParam;
    }

    public void setSiteParam(String siteParam) {
        this.siteParam = siteParam;
    }
}
