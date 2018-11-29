package com.ef.golf.common.pxx.util;


import com.ef.golf.common.Consts;
import com.ef.golf.common.pxx.model.AuthenticationBank;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by wxc
 * 2018年3月14日10:12:36
 * ping++聚合支付工具类
 */
@Repository
public class PingUtil {

    static {
        Pingpp.apiKey= Consts.PXX_API_KEY;
        System.setProperty("https.protocols", "TLSv1.2");
        //Pingpp.overrideApiBase("https://sapi.pingxx.com"); // 你需要添加的代码
        Pingpp.appId=Consts.PXX_APP_ID;
        Pingpp.privateKey=getPKCS8PrivateKey();
    }

    /** 银行卡校验 */
    public Identification authenticationBank(AuthenticationBank authenticationBank){
        Map<String,Object>params = new HashMap<>();
            params.put("type","bank_card");
            params.put("app",Pingpp.appId);
            Map<String,Object>data = new HashMap<>();
            data.put("id_name",authenticationBank.getId_name());
            data.put("id_number",authenticationBank.getId_number());
            data.put("card_number",authenticationBank.getCard_number());
            data.put("phone_number",authenticationBank.getPhone_number());
            params.put("data",data);
        Identification identification = null;
        try{
            identification = Identification.identify(params);
        }catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return identification;
    }
    //提现
    public Withdrawal withdrawal(int amount,String userId,String orderNo,int userFee,
                                 String description,String channel,String account,String name/*,String open_bank_code,String prov,String city*/){
        Map<String,Object>map = new HashMap<>();
        map.put("amount",amount);
        map.put("user",userId);
        map.put("order_no",orderNo);
        map.put("channel",channel);
        map.put("user_fee",userFee);
        map.put("description",description);
        Map<String,Object>extra = new HashMap<>();
            extra.put("account","");
            extra.put("name","");
            extra.put("open_bank_code","");
            extra.put("open_bank","");
            extra.put("prov","");
            extra.put("city","");
            extra.put("type","");
            extra.put("sub_bank","");
            map.put("extra",extra);
        Withdrawal wwithdrawal = null;
        try{
            wwithdrawal = Withdrawal.create(map);
        }catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return wwithdrawal;

    }
    //转账
    public BalanceTransfer balanceTransfer(int amount,String description,String user,String recipient,String order_no){

        Map<String,Object> params=new HashMap<>();
        params.put("amount",amount);
        params.put("description",description);
        params.put("user",user);
        params.put("recipient",recipient);
        params.put("order_no",order_no);
        BalanceTransfer cbalanceTransfer = null;
        try{
            cbalanceTransfer =  BalanceTransfer.create(params);
        }catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return cbalanceTransfer;
    }
    //赠送
    public BalanceBonus balanceBonus(int amount,String user,String order_no,String description){
        Map<String,Object> params=new HashMap<>();
        /*params.put("app",Pingpp.appId);*/
        params.put("amount",amount);
        params.put("order_no",order_no);
        params.put("user",user);
        params.put("description",description);
        BalanceBonus balanceBonus = null;
        try{
            balanceBonus =  BalanceBonus.create(params);
        }catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return balanceBonus;
    }


    //查询账户余额
    public User userBalacnce(String  userId){
        Map<String,Object> params=new HashMap<>();
        params.put("app",Pingpp.appId);
        params.put("uid",userId);
        User user = null;
        try{
            user = User.retrieve(userId);
        }catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
           e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
            return user;
    }
    //查询账户余额
    public Boolean payStatus(String  pingId){

        Boolean flag = false;

        try{
            if(24 == pingId.length()){ // recharge订单
                Recharge recharge =  Recharge.retrieve(pingId);
                if(true == recharge.getSucceeded()){
                    flag = true;
                }
            }else if(19 == pingId.length()){ // order订单
                Order order = Order.retrieve(pingId);
                if("paid".equals(order.getStatus())&&true == order.getPaid()){
                    flag = true;
                }
            }
        }catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
           e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return flag;
    }
    /**
     * 创建订单
     * @param userId
     * @param orderType
     * @param phoneNumber
     * @param subject
     * @param body
     * @param amount
     * @param clientIp
     * @return Order对象
     */
    public  Order createOrder(String userId,String orderNo,String orderType,String phoneNumber,String subject,String body,int amount,String clientIp){
        Map<String,Object> params=new HashMap<>();
        params.put("uid",userId);
        params.put("app",Pingpp.appId);
        params.put("merchant_order_no",orderNo);
        params.put("subject",subject);
        params.put("body",body);
        params.put("amount",amount);
        params.put("currency","cny");
        params.put("client_ip",clientIp);
        Order obj=null;
        try {
            obj= Order.create(params);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 支付订单
     * @param channel
     * @param chargeAmount
     * @param orderNo
     * @return Order
     */
    public  Order pay(String channel,int chargeAmount,String orderNo) {
        Map<String,Object>  params=new HashMap<>();
        params.put("channel",channel);
        params.put("charge_amount",chargeAmount);
        /*Map<String,Object> extra=new HashMap<>();
        extra.put("success_url",Consts.SUCCESS_URL);
        if("upacp".equals(channel)){
            params.put("extra",extra);
        }*/
        Order obj=null;
        try {
            obj=Order.pay(orderNo,params);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     * 查询order
     * */
    public  Order queryOrder(String orderId){
        Order obj=null;
        try {
            obj = Order.retrieve(orderId);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return obj;
    }
    /** 取消order */
    /**
     * 根据用户id查询订单列表
     * @param userId 用户id
     * @param isPaid 是否已经支付
     * @param isRefunded 是否存在退款信息
     */
    public  String orderList(String userId,boolean isPaid,boolean isRefunded){
        Map<String, Object> params=new HashMap<String, Object>();
        params.put("uid", userId);
        params.put("page", 1);//页码
        params.put("per_page", 10);//页面大小
        params.put("paid", isPaid);
        params.put("app", Pingpp.appId);
        params.put("refunded", isRefunded);
        OrderCollection orderList=null;
        try {
            orderList = Order.list(params);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (APIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ChannelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RateLimitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderList.toString();
    }


    /**
     *充值
     * @param userId 用户id
     * @param description 描述
     * @param amount 充值金额（单位：分）
     * @param channel 支付方式/渠道
     * @param orderNo 订单编号
     * @param clientIp 客户端ip
     * @param subject 充值标题
     * @param body 充值描述
     * @return 26376.87  42186.81
     */
    public Recharge recharge(String userId,String description,int amount,String channel,String orderNo,String clientIp,String subject,String body){
        Map<String,Object> params=new HashMap<>();
        params.put("user",userId);
        params.put("user_fee",0);
        params.put("from_user",userId);
        params.put("description",description);
        Map<String,Object> charge=new HashMap<>();
        charge.put("amount",amount);
        charge.put("channel",channel);
        charge.put("order_no",orderNo);
        charge.put("client_ip",clientIp);
        charge.put("subject",subject);
        charge.put("body",body);
        Map<String,Object> extra=new HashMap<>();
        //extra.put("cancel_url",Consts.CANCEL_URL);
        charge.put("extra",extra);
        params.put("charge",charge);
        Recharge recharge=null;
        try {
            recharge=Recharge.create(params);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return recharge;
    }

    /**
     * 充值赠送余额
     * @param userId 用户id
     * @param money 赠送金额，单位：分
     * @param orderNo 订单编号
     * @param description 描述
     */
    public BalanceBonus presentMoney(String userId,int money,String orderNo,String description){
        Map<String,Object> params=new HashMap<>();
        params.put("user",userId);
        params.put("amount",money*100);
        params.put("order_no",orderNo);
        params.put("description",description);
        BalanceBonus obj=null;
        try {
            obj=BalanceBonus.create(params);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 企业付:转账，打赏等功能
     * @param amount 金额（分）
     * @param orderNo 编号
     * @param recipient 收款者账号
     * @param channel 支付方式
     * @return Transfer 对象
     */
    public Transfer transferAccounts(int amount,String channel,String orderNo,String recipient,String description){
        Map<String,Object> transfer=new HashMap<String,Object>();
        transfer.put("amount",amount);
        transfer.put("currency","cny");
        transfer.put("order_no",orderNo);
        transfer.put("channel",channel);
        transfer.put("type","b2c");
        transfer.put("recipient",recipient);
        transfer.put("description",description);
        Map<String,String> app=new HashMap<>();
        app.put("id",Pingpp.appId);
        transfer.put("app",app);
        Transfer transfer1=null;
        try {
            transfer1=Transfer.create(transfer);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return transfer1;
    }
    /** 创建ping++账户 */
    public User createUser(String userId){

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", userId);
        userMap.put("address", "");
        userMap.put("avatar", "");
        userMap.put("email","");
        userMap.put("gender", "");
        userMap.put("metadata","");
        userMap.put("mobile", "");
        userMap.put("mobile","");
        User user = null;
        try {
            user = User.create(userMap);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 获取ping++私钥，
     * @return
     */
    private static String getPKCS8PrivateKey(){

        return "-----BEGIN PRIVATE KEY-----\n"+
                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANLIKN5k9jPl1NPt\n"+
                "PZ7UzJgVr+wZoXtahvO8ipt8iKdeEhrYypfGV0OxSAkJuWC+9MV4bAZbYTcGnC7L\n"+
                "bRZEj2pp2ybaEPcWf9izpiJ26ZWUYtrZXbKlcGpSnU6f8YaGte8J0ePLhE2BPKQr\n"+
                "sujoKX6k4m6EODE5g82tHJJyywA9AgMBAAECgYBx248/+peHlymsxKUZi+dsa6KY\n"+
                "zYuN/2uQc5MHxHBowAbVm69r5saymSvJPgh1+xBrLs5jbK8Jszdgi6BEfApfPFiw\n"+
                "nN/u+br1Ep2nweE9OD3dgn92qamINDiVntEqZaE35i8oJJRz34c95SVgz2dTbzfS\n"+
                "dXW20rcjw5akjyOaAQJBAPq0ZGFEf9EgTar/p7E/zP0naFXWCMIjYVIY+CeQxs4y\n"+
                "G7LHcQkQJphzp6o+jfWIjSZkz78mRfHFiTWjBRdfIL0CQQDXO+W+IxA7kzpDAowo\n"+
                "7AIgtzNdxIAh95UP7byw3TyLRUjGjJNYqSe2TxGL3aUv75CNhAlsl5cPUuf20xzH\n"+
                "nBWBAkAfjznbrL3SW5irYAeCHp/1dlKCC7GKjt2fchk7EdSU/GXUOZsXIvtY0aZX\n"+
                "ngxMzmXGYYZtb6n6Z32ATQdFC+qZAkAEf9ILqXv7YtaC9Bit+lEJEgtW2Ha9Pwm0\n"+
                "rYmWQOm1F5uv2txxrAeSfhCl1dpQKDfjXXE8/eZAgcErnboqSg8BAkEA9bmWtpsU\n"+
                "xncQk8q9iasIhvzCawAaJU2jVctVQGz7EGs3bp48zu0yDM8KmhDF8YYCRdVPWWUU\n"+
                "D0wxgK4DrZ19UA==\n"+
                "-----END PRIVATE KEY-----\n";

    }

    /**
     * 生成订单号
     * type：订单类型
     * @return 订单编号
     */
    public synchronized String generateOrderNo(String type,String phoneNumber){
        Long date= new Date().getTime();
        String numTime=date.toString();
        String orderNo= type+
                numTime.substring(numTime.length()-10,numTime.length())+
                phoneNumber.substring(phoneNumber.length()-4,phoneNumber.length());
        return orderNo;
    }

}
