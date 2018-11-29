package com.ef.golf.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Tools {
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	public static String date3Str(Date date){
		return date2Str(date,"yyyy-MM-dd");
	}
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 验证最多6位整数、两位小数
	 * @param str
	 * @return
	 */
	public static boolean numberVerify(String str) {  
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^\\d{1,6}(\\.\\d{1,2})?$");  
        java.util.regex.Matcher match = pattern.matcher(str.trim());  
        return match.matches();  
    }  
	
	/**
	 * MD5加密
	 * @param originstr 密码原文
	 * @return          长度24的密文
	 */
	public static String ecodeByMD5(String originstr){

		String result = null;
		char hexDigits[] = {//用来将字节转换成 16 进制表示的字符
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 
		if(originstr != null){
			try {
				//返回实现指定摘要算法的 MessageDigest 对象
				MessageDigest md = MessageDigest.getInstance("MD5");
				//使用utf-8编码将originstr字符串编码并保存到source字节数组
				byte[] source = originstr.getBytes("utf-8");
				//使用指定的 byte 数组更新摘要
				md.update(source);
				//通过执行诸如填充之类的最终操作完成哈希计算，结果是一个128位的长整数
				byte[] tmp = md.digest();
				//用16进制数表示需要32位
				char[] str = new char[32];
				for(int i=0,j=0; i < 16; i++){
					//j表示转换结果中对应的字符位置
					//从第一个字节开始，对 MD5 的每一个字节
					//转换成 16 进制字符
					byte b = tmp[i];
					//取字节中高 4 位的数字转换
					//无符号右移运算符>>> ，它总是在左边补0
					//0x代表它后面的是十六进制的数字. f转换成十进制就是15
					str[j++] = hexDigits[b>>>4 & 0xf];
					// 取字节中低 4 位的数字转换
					str[j++] = hexDigits[b&0xf];
				}
				result = new String(str).substring(0, 24);//结果转换成字符串用于返回
			} catch (NoSuchAlgorithmException e) {
			//当请求特定的加密算法而它在该环境中不可用时抛出此异常
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				//不支持字符编码异常
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	
	public List<String> RemoveCopy(List<String> list) {  
		   
		 
		 List<String> listTemp= new ArrayList<String>();  
		 Iterator<String> it=list.iterator();  
		 while(it.hasNext()){  
			 String a=it.next();  
		  if(listTemp.contains(a)){  
		   it.remove();  
		  }  
		  else{  
		   listTemp.add(a);  
		  }  
		 }  
//		 for(Object i:list){  
//		  System.out.println(i);  
//		 }  
		 
		 return listTemp;
		} 
	
	public static String companyStr(String str1,String str2){
		String str3 = "";
		if(str1!=null){
			return str1;
		}else if(str2!=null){
			return str2;
		}	
		return str3;
	}
}
