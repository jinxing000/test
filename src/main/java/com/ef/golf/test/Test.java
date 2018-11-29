package com.ef.golf.test;

import com.ef.golf.util.Constants;
import org.springframework.stereotype.Component;
@Component
public class Test {


    /*static {
        MobPushConfig.appkey = Consts.mobAppkey;
        MobPushConfig.appSecret=Consts.mobAppSecret;
    }*/
    private static String resthost = Constants.RESTHost;

    public static void  main(String[] args) throws Exception {

        //2011811020002427121 221181102414881024000006 221181102472079564800010
        String s1 = "2011811020002427121";

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       /* String ss = "{\"0\":\"820,819,812,810,809,801,800,799,798,788,785\",\"1\":\"842,841,840,839,837\",\"2\":\"843,842,841,840,839,837\",\"3\":\"847,844,843,842,841,840,839,837,836,832\",\"4\":\"725,672,612,560,548,547,543,537,513,512\"}";
        //Map<String,Object> map = (Map) JSON.parse("ss");
            List list = StringUtil.strRepeat(ss);
            for (Object l:list){
                System.out.println(l.toString());
            }*/
        /*InputStream murl = new URL("http://shop-product-img.oss-cn-beijing.aliyuncs.com/1540272924069.png").openStream();
        BufferedImage sourceImg = ImageIO.read(murl);*/
        //System.out.println(DateUtil.checkTime("2018-10-20",86400));
        //System.out.println(new Date().getTime());

       /* String picture = "http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397760389.jpg;http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397760514.jpg;http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397760652.jpg;" +
                "http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397760780.jpg;http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397760912.jpg;http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397761036.jpg;" +
                "http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397761159.jpg;http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397761290.jpg;http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397761338.jpg;" +
                "http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397761472.jpg;"+"http://quanzi-imgs.oss-cn-beijing.aliyuncs.com/1539397761472.jpg";

        String [] pictures = picture.split(";");
        StringBuilder sb = new StringBuilder();
        if(pictures.length>8){
            sb.append(pictures[0]+";").append(pictures[1]+";").append(pictures[2]+";").append(pictures[3]+";")
                    .append(pictures[4]+";").append(pictures[5]+";")
                    .append(pictures[6]+";").append(pictures[7]+";").append(pictures[8]);
        }
        System.out.println(sb.toString());*/

        /*List<String>list = new ArrayList<>(Arrays.asList(pictures));*/

        /*Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String x = it.next();

            if(x.indexOf("1")>8){
                it.remove();
            }
        }

        int count = 0;
        for (String s:list) {
            count++;
            System.out.println(s);
        }
        System.out.println(count);*/
       /* double distance = DistanceUtil.getDistance(39.06214192708333, 117.2160704210069, 39.06214192708333, 117.2160704210069);

        System.out.println(distance);

        Integer userId = 1;
        Long acc = (long)2;
        String de = "阿达";
        Double db = 1.5;

        StringBuilder sb = new StringBuilder();
        sb.append("用户id:").append(userId).append("账户id:")
                .append(acc).append("description:")
                .append(acc).append("收入影响行:").append(de).append("金额:").append(db);

*/

        /*Calendar beginTime = Calendar.getInstance();

        beginTime.setTime(new Date());
        beginTime.set(Calendar.HOUR_OF_DAY,24);
        beginTime.set(Calendar.MINUTE, 0);
        beginTime.set(Calendar.SECOND, 0);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间三个月后的时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime.getTime());
        calendar.add(Calendar.MONTH,3);*/

        /*String s = "天津市";

        if(s.contains("市")){
           String ss =  s.substring(0,s.length()-1);
        }*/


        /*if("5".equals(s)||"6".equals(s)){
            System.out.println("11111111");
        }else{
            System.out.println("343242");
        }*/

        //Singleton singleton = Singleton.getInstance();

        //DecimalFormat df = new DecimalFormat("0%");
        //System.out.println(df.format((double)100/10000.0));

           /* Long i = (long)47;
        String s = AcountUtil.createPingUser(i,"");
        System.out.println(s);*/


        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId","47");
        Map<String,Object>map = SystemSendTicketUtils.getMapJson("http://localhost:8080/golf/ticket",jsonObject);
        System.out.println(map);*/
        /*System.out.println(AmountUtils.changeF2Y((long)3100));*/

       /* SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time1 = "2018-09-31 11:03:26";
        Date time = simpleDateFormat.parse(time1);
            Calendar calendar = Calendar.getInstance();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        System.out.println("当前时间：" + sdf.format(now));

        Date afterDate = new Date(now .getTime() + 300000);
        System.out.println("unix="+afterDate);
        System.out.println(sdf.format(afterDate ));

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 5);
        System.out.println(sdf.format(nowTime.getTime()));


        //System.out.println(EncryptUtil.str2Md5Str("15822059651"+"111111"));
        /*System.out.println(11500*(10/100.0));
        System.out.println(AmountUtils.changeF2Y("1000000"));*/
       /* DecimalFormat df = new DecimalFormat("0%");*/
        /*DecimalFormat df = new DecimalFormat("#.00");*/
        /*System.out.println(df.format((double) 15/10000*100));
        System.out.println(15/10000);*/
       /* System.out.println(Consts.mobAppkey);
        PushClient pushClient = new PushClient();
        PushWork pushWork = new PushWork();
        pushWork.setAppkey(Consts.mobAppkey);
        pushWork.setPlats(new Integer[]{1,2});
        pushWork.setTarget(1);
        pushWork.setContent("1111111");
        pushWork.setType(1);

        try{
           String s =  pushClient.sendPush(pushWork);
           PushWork pushWork1 = pushClient.getPushByBatchId(s);
            System.out.println(pushWork1.toString());
        }catch (Exception e){
            e.printStackTrace();
        }*/

        /*List<RedPackage>bigRedPackageList = new RedisBaseDao().getList("r201806131026570744");
        System.out.println(bigRedPackageList.toArray());*/
    /* Date d1 = new Date();
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String riqi = simpleDateFormat.format(d1);
        System.out.println(d1.getTime()+"date"+riqi+"==="+d1);
        System.out.println( System.currentTimeMillis()+"sys");
        System.out.println("纳秒"+System.nanoTime()+"毫秒"+System.currentTimeMillis());*/
    /*Set<Long>set = new HashSet<>();
    for (int i = 0;i<=50;i++){
        Long sss = System.nanoTime();
        set.add(sss);
        System.out.println(sss.toString().substring(2,4)+sss.toString().substring(sss.toString().length()-4,sss.toString().length()));
        if(!set.contains(sss)){
            System.out.println("有重复");
        }
    }*/

     /*long l1 = d1.getTime();
        System.out.println("l1===="+l1);
     Date d2 = new Date();
     long l2 =  d2.getTime();
        System.out.println("l2===="+l2);
        System.out.println(l1==l2);*/
        /*SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        //往前推48小时
        calendar.add(Calendar.HOUR_OF_DAY, -48);
        Date updateDate5 = calendar.getTime();
        System.out.println("往前推48小时的时间="+sdf.format(updateDate5));*/

       /* Page<User> userPage=new Page<>();
        List<User> userList= new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        userPage.setResultList(userList);
        Map<String,User> userMap=new HashMap<>();
        userMap.put("user1",new User());
        userMap.put("user2",new User());
        userMap.put("user3",new User());
        userMap.put("user4",new User());
        userMap.put("user5",new User());
        ExecutionResult er=new ExecutionResult("200",userMap);

        String objJson = JSON.toJSONString(er);

        System.out.println(objJson);*/
    }
}
