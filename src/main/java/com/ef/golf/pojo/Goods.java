package com.ef.golf.pojo;


import com.ef.golf.core.structure.pageModel.Page;

public class Goods extends Page{
    private Integer goodsId;

    private String name;

    private String sn;

    private Integer brandId;

    private Integer catId;

    private Integer typeId;

    private Short goodsType;

    private String unit;

    private Integer weight;

    private Integer marketEnable;//是否上下架0代表已下架，1代表已上架

    private String brief;

    private Integer price;

    private Integer cost;

    private Integer mktprice;

    private Integer haveSpec;

    private Long createTime;

    private Long lastModify;

    private Integer viewCount;

    private Integer buyCount;

    private Integer disabled;

    private Integer store;

    private Integer enableStore;

    private Integer point;

    private String pageTitle;

    private String metaKeywords;

    private String metaDescription;

    private String p20;

    private String p19;

    private String p18;

    private String p17;

    private String p16;

    private String p15;

    private String p14;

    private String p13;

    private String p12;

    private String p11;

    private String p10;

    private String p9;

    private String p8;

    private String p7;

    private String p6;

    private String p5;

    private String p4;

    private String p3;

    private String p2;

    private String p1;

    private Integer sord;//排序 默认是0

    private Integer haveField;

    private Integer grade;

    private Integer isPack;

    private String thumbnail;

    private String big;

    private String small;

    private String original;

    private String  carriage;//是否免运费 1 是 0 否

    private String intro;

    private String params;

    private String specs;

    private String adjuncts;

    private String goodsComment;

    //给购买商品时赠优惠券需求专门添加两个字段
    private Integer reduction;//优惠标记   0. 没有优惠 1.返优惠券  2.返现金
    private Double credit;//优惠额度

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getAdjuncts() {
        return adjuncts;
    }

    public void setAdjuncts(String adjuncts) {
        this.adjuncts = adjuncts;
    }

    public String getGoodsComment() {
        return goodsComment;
    }

    public void setGoodsComment(String goodsComment) {
        this.goodsComment = goodsComment;
    }



    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Short getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Short goodsType) {
        this.goodsType = goodsType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getMarketEnable() {
        return marketEnable;
    }

    public void setMarketEnable(Integer marketEnable) {
        this.marketEnable = marketEnable;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getMktprice() {
        return mktprice;
    }

    public void setMktprice(Integer mktprice) {
        this.mktprice = mktprice;
    }

    public Integer getHaveSpec() {
        return haveSpec;
    }

    public void setHaveSpec(Integer haveSpec) {
        this.haveSpec = haveSpec;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModify() {
        return lastModify;
    }

    public void setLastModify(Long lastModify) {
        this.lastModify = lastModify;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getEnableStore() {
        return enableStore;
    }

    public void setEnableStore(Integer enableStore) {
        this.enableStore = enableStore;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle == null ? null : pageTitle.trim();
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords == null ? null : metaKeywords.trim();
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription == null ? null : metaDescription.trim();
    }

    public String getP20() {
        return p20;
    }

    public void setP20(String p20) {
        this.p20 = p20 == null ? null : p20.trim();
    }

    public String getP19() {
        return p19;
    }

    public void setP19(String p19) {
        this.p19 = p19 == null ? null : p19.trim();
    }

    public String getP18() {
        return p18;
    }

    public void setP18(String p18) {
        this.p18 = p18 == null ? null : p18.trim();
    }

    public String getP17() {
        return p17;
    }

    public void setP17(String p17) {
        this.p17 = p17 == null ? null : p17.trim();
    }

    public String getP16() {
        return p16;
    }

    public void setP16(String p16) {
        this.p16 = p16 == null ? null : p16.trim();
    }

    public String getP15() {
        return p15;
    }

    public void setP15(String p15) {
        this.p15 = p15 == null ? null : p15.trim();
    }

    public String getP14() {
        return p14;
    }

    public void setP14(String p14) {
        this.p14 = p14 == null ? null : p14.trim();
    }

    public String getP13() {
        return p13;
    }

    public void setP13(String p13) {
        this.p13 = p13 == null ? null : p13.trim();
    }

    public String getP12() {
        return p12;
    }

    public void setP12(String p12) {
        this.p12 = p12 == null ? null : p12.trim();
    }

    public String getP11() {
        return p11;
    }

    public void setP11(String p11) {
        this.p11 = p11 == null ? null : p11.trim();
    }

    public String getP10() {
        return p10;
    }

    public void setP10(String p10) {
        this.p10 = p10 == null ? null : p10.trim();
    }

    public String getP9() {
        return p9;
    }

    public void setP9(String p9) {
        this.p9 = p9 == null ? null : p9.trim();
    }

    public String getP8() {
        return p8;
    }

    public void setP8(String p8) {
        this.p8 = p8 == null ? null : p8.trim();
    }

    public String getP7() {
        return p7;
    }

    public void setP7(String p7) {
        this.p7 = p7 == null ? null : p7.trim();
    }

    public String getP6() {
        return p6;
    }

    public void setP6(String p6) {
        this.p6 = p6 == null ? null : p6.trim();
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5 == null ? null : p5.trim();
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4 == null ? null : p4.trim();
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3 == null ? null : p3.trim();
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2 == null ? null : p2.trim();
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1 == null ? null : p1.trim();
    }

    public Integer getSord() {
        return sord;
    }

    public void setSord(Integer sord) {
        this.sord = sord;
    }

    public Integer getHaveField() {
        return haveField;
    }

    public void setHaveField(Integer haveField) {
        this.haveField = haveField;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getIsPack() {
        return isPack;
    }

    public void setIsPack(Integer isPack) {
        this.isPack = isPack;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big == null ? null : big.trim();
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small == null ? null : small.trim();
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original == null ? null : original.trim();
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }


    public Integer getReduction() {
        return reduction;
    }

    public void setReduction(Integer reduction) {
        this.reduction = reduction;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
}