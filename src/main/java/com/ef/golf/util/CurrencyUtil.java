package com.ef.golf.util;

import com.ef.golf.pojo.DlyType;
import com.ef.golf.pojo.EsTypeArea;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import java.math.BigDecimal;
import java.util.List;


/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算， 这个工具类提供精确的浮点数运算，包括加减乘除和四舍五入。
 */
public final class CurrencyUtil {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 2;

	// 这个类不能实例化
	private CurrencyUtil() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static Double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args){
//		Double d1 = 1.01;
//		Double d2 = 0.42;
//		//System.out.println(d1-d2);
//		//System.out.println(Arith.sub(d1, d2));
//		//System.out.println(Arith.sub1(d1, d2));
//		long a = System.currentTimeMillis();
//		for(int i=0;i<10000;i++){
//			double r = Arith.sub1(d1, d2);
//		}
//		//System.out.println(System.currentTimeMillis() - a);
//		//System.out.println(0.05+0.01);  
//	    //System.out.println(1.0-0.42);  
//	    //System.out.println(4.015*100);  
//	    //System.out.println(123.3/100);
//	    //System.out.println(CurrencyUtil.mul(4.015, 100));
		
		//System.out.println( CurrencyUtil.sub(2D, 10D) );
	}
	private String createExpression(Double firstprice, Double continueprice,
									Integer firstunit, Integer continueunit) {
		return firstprice + "+tint(w-" + firstunit + ")/" + continueunit + "*"
				+ continueprice;
	}



	/**
	 * 检测某配送方式是否在配送地区
	 *
	 * @param areaList
	 *            配送方式的地区列表
	 * @param weight
	 *            商品重量
	 * @param orderPrice
	 *            订单金额
	 * @param regoinId
	 *            地区id
	 * @return 找到相应的配送地区的配送价格并计算配送费用，注：如果区域重合则找到最贵的配送方式并计算费用</br>
	 *         如果无匹配配送地区则返回null
	 */
	public Double countPrice(List<EsTypeArea> areaList, Double weight,
							  Double orderPrice, String regoinId) {
		Double price = null;
		for (EsTypeArea typeArea : areaList) {
			String idGroup = typeArea.getAreaIdGroup();

			if (idGroup == null || idGroup.equals("")) {
				continue;
			}

			idGroup = idGroup == null ? "" : idGroup;
			String[] idArray = idGroup.split(",");

			// 检测所属地区是否在配送范围
			if (StringUtil.isInArray(regoinId, idArray)) {
				Double thePrice = this.countExp(typeArea.getExpressions(),
						weight, orderPrice);
				if (price != null)
					price = thePrice.compareTo(price) > 0 ? thePrice : price;
				else
					price = thePrice;
			}

		}

		return price;
	}


	public Double countExp(String exp, Double weight, Double orderprice) {
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();
			String str = "var w=" + weight + ";";
			str += "p=" + orderprice + ";";
			str += "function tint(value){return value<0?0:value;}";
			str += exp;
			Object result = cx.evaluateString(scope, str, null, 1, null);
			Double res = Context.toNumber(result);

			return res;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Context.exit();
		}
		return -1D;
	}



	/*public Double[] countPrice(Integer typeId, Double weight,
							   Double orderPrice, String regionId) {

		DlyType dlyType = this.getDlyTypeById(typeId);
		Double dlyPrice = null;
		if (dlyType.getIsSame().compareTo(1) == 0) { // 统一的费用配置
			dlyPrice = this.countExp(dlyType.getExpressions(), weight,
					orderPrice);
		} else {
			dlyPrice = this.countPrice(dlyType.getTypeAreaList(), weight,
					orderPrice, regionId);
		}
		Double protectPrice = null;
		// b2b2c 在不影响 原来程序上加默认配送费用
		//修改原有逻辑的bug edit by jianghongyan
		if (dlyPrice == null) {
			dlyPrice = this.countExp(dlyType.getExpressions(), weight,
					orderPrice);
		}
		*//*if("b2b2c".equals(EopSetting.PRODUCT)&&dlyPrice<0){
			dlyPrice = this.countExp(dlyType.getExpressions(), weight,
					orderPrice);
		}*//*
		//edit end

		// 精度到小数点后两位 2015-08-31 by kingapex
		dlyPrice = CurrencyUtil.round(dlyPrice, 2);

		Double[] priceArray = { dlyPrice, protectPrice };
		return priceArray;
	}*/

















}