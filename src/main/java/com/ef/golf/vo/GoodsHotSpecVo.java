package com.ef.golf.vo;


import com.ef.golf.core.structure.pageModel.Page;

/**
 * 热销商品详情
 */
public class GoodsHotSpecVo extends Page{

    private Integer goods_id;//id
    private Integer activity_id;//活动id
    private String name;//商品名
    private Integer enable_store;//可用库存
    private Integer price;//商品价格
    private String thumbnail;//缩略图
    private  Long start_time;//开始时间
    private  Long end_time;//结束时间
    private Integer state;//活动状态  根据时间比对出来的 0 进行中  1 结束
    private String  carriage;//是否免运费 1 是 0 否
    private Integer weight;
    private String intro;
    private Integer mktprice;// /root/jdk/jdk1.8.0_191

    //给购买商品时赠优惠券需求专门添加两个字段
    private Integer reduction;//优惠标记   0. 没有优惠 1.返优惠券  2.返现金
    private Double credit;//优惠额度
    private Integer parentCatId=0;//适应于商城优惠券的分类使用  默认0  防止手机端闪退
    private Integer catid=0;//适应于商城优惠券的分类使用  默认0  防止手机端闪退 IOS

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getParentCatId() {
        return parentCatId;
    }

    public void setParentCatId(Integer parentCatId) {
        this.parentCatId = parentCatId;
    }


    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnable_store() {
        return enable_store;
    }

    public void setEnable_store(Integer enable_store) {
        this.enable_store = enable_store;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCarriage() {
        return carriage;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getMktprice() {
        return mktprice;
    }

    public void setMktprice(Integer mktprice) {
        this.mktprice = mktprice;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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
