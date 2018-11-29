package com.ef.golf.vo;

public class RedPackageRefundVo {

    private String userId;//发红包人
    private String bigRedPackageId;//红包id
    private Long amount;//退款金额(分)

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBigRedPackageId() {
        return bigRedPackageId;
    }

    public void setBigRedPackageId(String bigRedPackageId) {
        this.bigRedPackageId = bigRedPackageId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public RedPackageRefundVo() {
    }

    public RedPackageRefundVo(String userId, String bigRedPackageId, Long amount) {
        this.userId = userId;
        this.bigRedPackageId = bigRedPackageId;
        this.amount = amount;
    }
}
