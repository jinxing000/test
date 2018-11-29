package com.ef.golf.vo;

public class OrderItemVo {
    private Integer goods_id;
    private Integer product_id;
   // private Integer order_id;
   // private String name;
  //  private Integer userId;
  //  private String thumbnail;

    private int efOrderId;//主订单中的order_id  供支付接口使用
    private int shopOrderId;//商城订单order_id
    private String orderNo;//订单编号
    private String createTime;//下单时间
    private String status;//订单状态
    private Double price;   //价格
    private String spec; //规格
    private String goodsName;
    private  int num;

    private String imgUrl;

    //给购买商品时赠优惠券需求专门添加两个字段
    private Integer reduction;//优惠标记   0. 没有优惠 1.返优惠券  2.返现金
    private Double credit;//优惠额度


    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public int getEfOrderId() {
        return efOrderId;
    }

    public void setEfOrderId(int efOrderId) {
        this.efOrderId = efOrderId;
    }

    public int getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(int shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
