package com.ef.golf.util;

/**
 * com.ef.golf.util
 * Administrator
 * 2018/11/3 10:31
 *
 *
 */
public enum RedisExpireTime {

    RedPackageTime(86400,"红包过期时间"),
    GiftTime(86400,"礼物过期时间"),
    CouponTime(86400,"优惠券过期时间"),
    SmsCode(300,"验证码失效时间");

    private Integer seconds;
    private String desc;

    RedisExpireTime(Integer seconds, String desc) {
        this.seconds = seconds;
        this.desc = desc;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public String getDesc() {
        return desc;
    }

}
