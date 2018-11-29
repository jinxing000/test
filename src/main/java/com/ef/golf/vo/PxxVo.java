package com.ef.golf.vo;

/**
 *
 */
public class PxxVo {

    private String order_no;
    private String channel;
    private int amount;
    private String a ;
    private String b;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "PxxVo{" +
                "order_no='" + order_no + '\'' +
                ", channel='" + channel + '\'' +
                ", amount='" + amount + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }
}
