package com.ef.golf.pojo;

public class Product_score {
    private Integer productScoreId;

    private Integer productId;

    private String productType;

    private Integer attrId;

    private String score;

    private String commentsId;

    public Integer getProductScoreId() {
        return productScoreId;
    }

    public void setProductScoreId(Integer productScoreId) {
        this.productScoreId = productScoreId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId;
    }
}