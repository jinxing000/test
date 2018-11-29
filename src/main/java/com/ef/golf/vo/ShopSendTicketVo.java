package com.ef.golf.vo;

public class ShopSendTicketVo {

    private Integer goodsId; // 商品id
    private int reduction; // 是否送券
    private double credit; // 折扣
    private int count;//商品数量  //工具类返回模板数
    private Double price; // 商品价格
    private String ticketId;  //工具类返回模板id

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }


    public ShopSendTicketVo() {
    }

    public ShopSendTicketVo(Integer goodsId, int reduction, double credit, int count, Double price, String ticketId) {
        this.goodsId = goodsId;
        this.reduction = reduction;
        this.credit = credit;
        this.count = count;
        this.price = price;
        this.ticketId = ticketId;
    }
}
