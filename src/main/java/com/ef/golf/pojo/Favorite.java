package com.ef.golf.pojo;

public class Favorite {
    private Integer favoriteId;

    private Integer memberId;

    private Integer goodsId;

    private Long favoriteTime;

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Long getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(Long favoriteTime) {
        this.favoriteTime = favoriteTime;
    }
}