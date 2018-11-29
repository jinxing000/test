package com.ef.golf.vo;

/**
 * com.ef.golf.vo
 * Administrator
 * 2018/4/27 11:15
 * 球童订单列表
 */
public class QiuTongOrderListVo {

    private String headPortraitUrl;//球童头像url
    private Integer orderId;//订单id
    private String orderNo;//订单编号
    private String createTime;//下单时间
    private String orderState;//订单状态
    private String modifyUser;//修改人
    private String modifyTime;//修改时间
    private String qiuTongName;//球童名字
    private Integer qiutongId;//球童ID
    private String playDate;//打球日期
    private String playTime;//打球时间
    private String qiuHuiId;//球会id
    private String qiuHuiName;//球会名称
    private String playName;//打球人姓名
    private String contactsPhone;//打球人联系方式
    private String ticketName;
    private String remark;//备注信息

    public Integer getQiutongId() {
        return qiutongId;
    }

    public void setQiutongId(Integer qiutongId) {
        this.qiutongId = qiutongId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getQiuTongName() {
        return qiuTongName;
    }

    public void setQiuTongName(String qiuTongName) {
        this.qiuTongName = qiuTongName;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getQiuHuiId() {
        return qiuHuiId;
    }

    public void setQiuHuiId(String qiuHuiId) {
        this.qiuHuiId = qiuHuiId;
    }

    public String getQiuHuiName() {
        return qiuHuiName;
    }

    public void setQiuHuiName(String qiuHuiName) {
        this.qiuHuiName = qiuHuiName;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}


