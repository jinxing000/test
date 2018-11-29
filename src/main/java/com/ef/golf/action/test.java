package com.ef.golf.action;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.EncryptUtil;
import com.ef.golf.util.MD5;
import com.ef.golf.util.SmsUtil;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.CardInfo;
import com.sun.xml.internal.ws.resources.SenderMessages;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class test extends PingppTestBase {

    public static void main(String[] args) {
        String ss = "sss";
        Map<String,Object>map = new HashMap<>();
        map.put("test1","test1");
        Map<String,Object>pmap = new HashMap<>();
        pmap.put("ss",ss);
        map.put("test2",pmap);

        System.out.println(map);

    }
    /**
     * 通过卡号查询卡信息。
     */
    @Test
    public void testCardInfoQuery() throws RateLimitException, APIException, ChannelException, InvalidRequestException, APIConnectionException, AuthenticationException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("app", PingppTestData.getAppID());
        params.put("bank_account", "6217000060008923375");

        CardInfo obj = CardInfo.query(params);

        assertEquals("622228", obj.getCardBin());
        assertEquals("0310", obj.getOpenBankCode());
        assertEquals("中国建设银行", obj.getOpenBank());
        assertEquals(2, obj.getCardType().intValue());
    }
   @Test
    public void sss() throws ParseException {


       System.out.println();
       /* Date date = new Date();
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String sss = simpleDateFormat.format(date);
       System.out.println(simpleDateFormat.parse(sss).getTime()==date.getTime());
       System.out.println(date.getTime()==date.getTime());*/
       /*String ss = "13:00";
       String data = sss+" "+ss;
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(simpleDateFormat.parse(data));
       calendar.add(Calendar.HOUR_OF_DAY, -48);
       System.out.println(data);
       System.out.println(simpleDateFormat.parse(data));
       System.out.println(calendar.getTime());*/

       /* for (int i=0;i<20;i++){
            int score = (int) (Math.random() * 12) + 2;
            System.out.println(score);
        }*/
       /* SmsUtil.sendSMS("15620532538","256862");*/
       /*
       String sss = simpleDateFormat.format(ss);
       String i = simpleDateFormat.format(date);
        System.out.println(i);
       System.out.println(sss);*/
        /*Double d= 1.00;
       DecimalFormat df = new DecimalFormat("#.00");
       String money = df.format(d);
       if(d==0.01){
           money = d+"";
       }
       System.out.println(money);*/

       /*Map<String,Object>map = new HashMap<>();
       map.put("sss",111);
       map.put("ssss",222);
       for (Map.Entry<String,Object>ertry:map.entrySet()
            ) {
           System.out.println(ertry.getKey()+"=="+ertry.getValue());
       }*/
    }


}