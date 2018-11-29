package com.ef.golf.pojo;

import com.ef.golf.vo.GoodsHotSpecVo;

import java.util.List;

public class EsSubject {
    private Integer     id;

    private String      title;

    private Integer     sort;

    private Integer     isDisplay;

    private String      banner;

    private String      goodsIds;

    private String      picturePath;

    private List<GoodsHotSpecVo> goodsHotSpecVo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner == null ? null : banner.trim();
    }

    public String getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public List<GoodsHotSpecVo> getGoodsHotSpecVo() {
        return goodsHotSpecVo;
    }

    public void setGoodsHotSpecVo(List<GoodsHotSpecVo> goodsHotSpecVo) {
        this.goodsHotSpecVo = goodsHotSpecVo;
    }
}