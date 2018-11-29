package com.ef.golf.common;

/**
 * for efgolf
 * Created by Bart on 2017/1/13.
 * Date: 2017/1/13 20:24
 */
public class Constant {

    /*  会员默认管理员后缀  */
    public static final String MEMBER_USER = "_admin";



    /*  参数验证类错误码基础值  */
    public static final int ERR_VALIDATOR = -100;

    /*  登陆注册相关错误码  */
    public static final int ERR_USER = -1000;
    public static final int ERR_PARAMETER=-1013;
    public static final int ERR_PARAMETERLESS=-1014;
    public static final int ERR_DATENULL=-1015;
    public static final int ERR_LOGIN=-1016;

    /*  查询错误码  */
    public static final int ERR_QUERY = -10000;

    public static final int ERR_QUERY_PRICE = -14000;

    public static final int ERR_SYSTEM = -11000;

    public static final int ERR_DEMAND = -12000;

    /*更新、新增失败错误*/
    public static final int ERR_UPDATE=-13000;
    //优惠券
    public static final int ERR_TICKET=-12003;
    public static final int ERR_TICKET_MEMER=-12007;


    /*  未知异常  */
    public static final int ERR_UNKNOW = -9999;

    /*查询无结果*/
    public static final int ERR_NODATES=-700;

    public static final int SUCCESS_RETCODE= 0;
    /** 充值 */
    public static final int ERR_Recharge=-111222;
    /** 赠送异常 */
    public static final int ERR_GIVEMONEY=-110017;
    /** 转账 */
    public static final int ERR_BalanceTransfer=-111333;
    /** 余额不足 */
    public static final int ERR_NoBalance=-111444;
    public static final int ERR_Balance=-333333;
    /** 重复点赞 */
    public static final int ERR_YETPraise=-222111;
    /** 密码错误 */
    public static final int ERR_ERRPWD=-111555;

    /** 群处理 */
    public static final int ERR_QUERYGROUP=-111666;
    /** 群处理失败 */
    public static final int ERR_GROUP=-111777;
    /** 礼物操作异常 */
    public static final int ERR_GIFT=-111888;


    /** 愿望领取状态 */
    public static final int ERR_HOPESHIXIAO=-222222;
    public static final int ERR_HOPEYILINGGUO=-222333;
    public static final int ERR_HOPE=-222444;
    /** 球场价格比对异常 */
    public static final int ERR_PRICE=-222555;

    /**商城异常*/
    public static final int SHANGCHENG_ERR_PRICE=-300000;
    public static final int SHANGCHENG_STORE_LACK=-300001;
    public static final int GOODSCART_CANNOT_EMPTY=-300002;
    public static final int GOODSORDER_NOT_EXIST=-300003;
    public static final int GOODSORDER_CANNOT_CANCLE=-300004;
    public static final int GOODSORDER_ERR_HOT=-300005;
    public static final int ERR_COMMENT=-110011;

    public static final int ERR_ORDER_EXPRIY=-110012;

    public static final int ERR_TIME_TUIDIN=-110013;
    public static final int ERR_TIME_RESERVE=-110014;
    /** 赠送余额只能用于订场，商城 */
    public static final int ERR_BONUSES_BANLACE=-110015;
    /** 赠送余额只能用于订场，商城 */
    public static final int ERR_BONUSES_NOREDPACKAGE=-110018;
    /** 打赏次数过多 */
    public static final int ERR_CADDIE_REWARD_MAX=-110016;
    /** 打赏次数过多 */
    public static final int ERR_UPDATE_PAYPWD_MESSAGE=-110019;

    /*                                  正则                                  */

    public static final String REGEXP_DATE = "^([0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30))))|(\\\\s+)$";

}
