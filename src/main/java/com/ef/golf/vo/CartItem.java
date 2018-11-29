package com.ef.golf.vo;

import com.ef.golf.util.NotDbField;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 购物项
 * @author kingapex
 *2010-5-5上午10:13:27
 */
public class CartItem implements Serializable{


	private Integer product_id;//p.product_id,
	private Integer goods_id;//g.goods_id,
	private String name;// c. NAME,
	private Integer mktprice;//g.mktprice,
	private Integer price;//c.price,


    private Integer cart_id;//商品大分类
	private Integer num;//c.num AS num,
	private Integer point;//   g.point,
	private Integer itemtype; //物品类型(0商品，1捆绑商品，2赠品) c.itemtype,
	private String sn; // 捆绑促销的货号

	private String addon; //配件字串c.addon,
	private String specs;//p.specs,
	private Integer catid; //商品分类id g.cat_id AS catid,
    private String thumbnail;
	private String unit;//g.unit,


    private Integer weight;       //c.weight,

    private Integer activity_id;	//促销活动ID add by DMRain 2016-1-29c.activity_id

    private Integer is_check;		//是否選中c.is_check,



    private Integer  enable_store;

    private Integer snapshot_id;	//商品快照字段
    private String carriage;//(新增字段 carriage   0:不免运费  1:免运费)

    //给购买商品时赠优惠券需求专门添加两个字段
    private Integer reduction;//优惠标记   0. 没有优惠 1.返优惠券  2.返现金
    private Double credit;//优惠额度

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

    public Integer getEnable_store() {
        return enable_store;
    }

    public void setEnable_store(Integer enable_store) {
        this.enable_store = enable_store;
    }
    @NotDbField
    public Integer getSnapshot_id() {
        return snapshot_id;
    }

    public void setSnapshot_id(Integer snapshot_id) {
        this.snapshot_id = snapshot_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMktprice() {
        return mktprice;
    }

    public void setMktprice(Integer mktprice) {
        this.mktprice = mktprice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getItemtype() {
        return itemtype;
    }

    public void setItemtype(Integer itemtype) {
        this.itemtype = itemtype;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public Integer getIs_check() {
        return is_check;
    }

    public void setIs_check(Integer is_check) {
        this.is_check = is_check;
    }


    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCarriage() {
        return carriage;
    }

    public void setCarriage(String carriage) {
        this.carriage = carriage;
    }
}
