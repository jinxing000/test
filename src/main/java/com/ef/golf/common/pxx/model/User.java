package com.ef.golf.common.pxx.model;

import com.pingplusplus.exception.*;
import com.pingplusplus.net.APIResource;

import java.util.List;
import java.util.Map;

public class User extends APIResource {

    String id;
    String object;
    String app;
    String type;
    String related_app;
    String address;
    int available_coupons;
    String avatar;
    int available_balance;                  //可用余额，可用于消费。
    int withdrawable_balance;                //可提现余额，可用于消费、提现、转账等。
    int created;
    boolean disabled;
    String email;
    String gender;
    boolean identified;
    boolean livemode;
    String mobile;
    String name;
    Map<String, Object> metadata;
    List settle_accounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelated_app() {
        return related_app;
    }

    public void setRelated_app(String related_app) {
        this.related_app = related_app;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAvailable_coupons() {
        return available_coupons;
    }

    public void setAvailable_coupons(int available_coupons) {
        this.available_coupons = available_coupons;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAvailable_balance() {
        return available_balance;
    }

    public void setAvailable_balance(int available_balance) {
        this.available_balance = available_balance;
    }

    public int getWithdrawable_balance() {
        return withdrawable_balance;
    }

    public void setWithdrawable_balance(int withdrawable_balance) {
        this.withdrawable_balance = withdrawable_balance;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isIdentified() {
        return identified;
    }

    public void setIdentified(boolean identified) {
        this.identified = identified;
    }

    public boolean isLivemode() {
        return livemode;
    }

    public void setLivemode(boolean livemode) {
        this.livemode = livemode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public List getSettle_accounts() {
        return settle_accounts;
    }

    public void setSettle_accounts(List settle_accounts) {
        this.settle_accounts = settle_accounts;
    }




    public static User create(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException, RateLimitException {
        return request(RequestMethod.POST, classURL(User.class), params, User.class);
    }
}
