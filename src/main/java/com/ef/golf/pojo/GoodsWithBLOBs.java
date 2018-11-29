package com.ef.golf.pojo;

public class GoodsWithBLOBs extends Goods {
    private String intro;

    private String params;

    private String specs;

    private String adjuncts;

    private String goodsComment;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs == null ? null : specs.trim();
    }

    public String getAdjuncts() {
        return adjuncts;
    }

    public void setAdjuncts(String adjuncts) {
        this.adjuncts = adjuncts == null ? null : adjuncts.trim();
    }

    public String getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(String goodsComment) {
        this.goodsComment = goodsComment == null ? null : goodsComment.trim();
    }
}