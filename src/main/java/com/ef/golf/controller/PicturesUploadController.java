package com.ef.golf.controller;


import com.cloopen.rest.sdk.utils.PropertiesUtil;
import com.ef.golf.common.Constant;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Dynamic;
import com.ef.golf.pojo.Hope;
import com.ef.golf.pojo.Img;
import com.ef.golf.pojo.User;
import com.ef.golf.service.*;
import com.ef.golf.util.OssUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vedio.VideoInfo;
import com.ef.golf.vo.MineVo;
import com.ef.golf.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*import com.sun.tools.hat.internal.util.ArraySorter;*/

@Controller
@RequestMapping("/upload")
public class PicturesUploadController {

    @Resource
    private RedisBaseDao redisBaseDao;

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private UserService userService;

    @Resource
    private GoodsService goodsService;


    @Resource
    private DynamicService dynamicService;
    @Resource
    private HopeService hopeService;
    @Resource
    private ImgService imgService;
    @Resource
    private SiteService siteService;

    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> editUser(HttpServletRequest request,User user,String sid, String uid) throws LoginException, SystemException {
        Map<String,Object> userUpdaeMap=new HashMap<>();
        int end=0;
        if(StringUtils.isNotEmpty(sid)){
            String UID=redisBaseDao.get(sid);
            if(!UID.equals(uid)||StringUtils.isEmpty(UID)){
                throw new LoginException(Constant.ERR_USER);
            }
            Long userId=userService.getUid(UID);
            user.setId(userId);
           /* if(StringUtils.isNotBlank(imgUpload)){*/
            //接收文件流
            MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
            //获取文件队列（imgUpload为前端页面的input标签name的值）
            List<MultipartFile> imgFileUrl = multipart.getFiles("imgUpload");
            if(imgFileUrl.size()>0) {
                //设置文件存储路径
                String staticPath = headerImgs;
                //request.getSession().getServletContext().getRealPath("/") + "static/imgs/userHeaderImgs/";
                File dirName=new File(staticPath);
                if (!dirName.isDirectory()){
                    dirName.mkdir();
                }
                for (int i = 0; i < imgFileUrl.size(); i++) {
                    String url = "";
                    //获取文件全名称
                    String fileName = imgFileUrl.get(i).getOriginalFilename();
                    //判断是否有上传文件
                    if(StringUtils.isNotBlank(fileName)){
                        //分割文件名称
                        String[] filestr = fileName.split("\\.");
                        //为当前文件重新命名
                        String fileUrl = System.currentTimeMillis()  + "." + filestr[1];
                        //将文件封装成File类型
                        File targetFile = new File(staticPath, fileUrl);
                        //将文件写入硬盘
                        try {
                            imgFileUrl.get(i).transferTo(targetFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        url = fileUrl;
                        //文件存入阿里云oss服务器
                        OssUtil.upload(url, targetFile, BucketName1);
                        //删除本地文件
                        new File(staticPath + url).delete();
                        //删除阿里云oss服务器上的头像
                        MineVo mo=userService.getInfo(userId.intValue());
                        if(StringUtils.isNotBlank(mo.getHeadPortraitUrl())){
                            String headerStr[]=mo.getHeadPortraitUrl().split("/");
                            String headerName=headerStr[3];
                            OssUtil.delete(headerName,BucketName1);
                        }
                        String imgUrl = networkDomainName1 + url;
                        user.setHeadPortraitUrl(imgUrl);
                        //调用更新用户信息的Service
                        /*end=userService.updateUser(user);*/
                        userService.updateByPrimaryKey(user);
                    }else{//如果没有图片上传，则只更新用户信息
                        /*end=userService.updateUser(user);*/

                        userService.updateByPrimaryKey(user);
                    }

                }
            /*}*/
        }
            //重置sessionId时间
            //redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
            userUpdaeMap.put("0","正常");
            userUpdaeMap.put("sid",sid);
            userUpdaeMap.put("uid",uid);
            /*if(end>0){
                userUpdaeMap.put("0","正常");
                return userUpdaeMap;
            }else{
                throw new SystemException(Constant.ERR_SYSTEM);
            }*/
            return userUpdaeMap;
        }else{
            throw new LoginException(Constant.ERR_USER);
        }
    }

    @RequestMapping(value = "/issueDynamic",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> issueDynamic(HttpServletRequest request,String uid, String sid, String lng,
                                           String lat, String city, String content,String beginDate,String beginTime,String mediaSize,Hope hope) throws LoginException, SystemException {
        /*Hope hope1 = JSON.parseObject(hope, Hope.class);*/

        if(null!=mediaSize) {
            String[] mediaSizes = mediaSize.split(",");
            if (Double.valueOf(mediaSizes[0]) <= 0 && Double.valueOf(mediaSizes[1]) <= 0) {
                mediaSize = "200,200";
            }
        }
        String imgType[]=new String[]{"jpg","png","pdf","gif","jpeg","webp"};
        String vedioType[]=new String[]{"avi","mp4","3gp","3gp2"};
        Map<String,Object> endMap=new HashMap<>();
       /* @RequestPart(value = "issueDynamicFile") MultipartFile[] multipartFile,
        System.out.println(multipartFile.length);*/
        /*System.out.println(request.getParameter("=========="+"city"));*/

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("issueDynamicFile");
        String filePath=quanziVedio;
        StringBuilder sb=new StringBuilder();
        String vedioUrl=null;
        String vedioSuoluetuUrl=null;
       try{
       if(files.size()>0) {
           File dirFile = new File(filePath);
           if (!dirFile.isDirectory()) {
               dirFile.mkdir();
           }

           for (int i = 0; i < files.size(); i++) {
               String url = "";
               //获取文件全名称
               String fileName = files.get(i).getOriginalFilename();
               //判断是否有上传文件
               if (StringUtils.isNotBlank(fileName)) {
                       //分割文件名称
                       String[] filestr = fileName.split("\\.");
                       //获取文件后缀
                       String fileEndName=filestr[filestr.length-1];
                       //为当前文件重新命名
                       String fileUrl = System.currentTimeMillis() + "." + filestr[filestr.length-1];
                       //将文件封装成File类型
                       File targetFile = new File(filePath, fileUrl);
                       //将文件写入硬盘
                       try {
                           files.get(i).transferTo(targetFile);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   url = fileUrl;

                   //将文件上传至oss对象存储
                   //1.如果后缀是图片类型则存入oss的图片文件夹
                   //2.如果后缀是视频类型则存入oss的视频文件夹
                   boolean endImg= Arrays.asList(imgType).contains(fileEndName.toLowerCase());
                   boolean endVedio=Arrays.asList(vedioType).contains(fileEndName.toLowerCase());
                   if(endImg){
                       OssUtil.upload(url,targetFile,BucketName4);
                       sb.append(networkDomainName4+url+";");
                   }else if(endVedio){
                       OssUtil.upload(url,targetFile,BucketName3);
                       vedioUrl=networkDomainName3+url;

                       vedioSuoluetuUrl = System.currentTimeMillis()+".jpg";
                       VideoInfo videoInfo = new VideoInfo(ffmpeg);
                       videoInfo.getThumb(vedioUrl,quanziImgs+vedioSuoluetuUrl,800, 600, 0, 0, 0);
                       File file = new File(quanziImgs+vedioSuoluetuUrl);
                        OssUtil.upload(vedioSuoluetuUrl,file,BucketName3);
                        vedioSuoluetuUrl = networkDomainName3+vedioSuoluetuUrl;
                       file.delete();
                   }
                   //删除本地文件
                   new File(filePath+"/"+fileUrl).delete();
               }
           }

        }
           if(sb.toString().endsWith(";")){
               sb.deleteCharAt(sb.length()-1);
           }
           Dynamic dynamic=getDynamic(uid,sid,sb.toString(),vedioUrl,vedioSuoluetuUrl,lng,lat,city,content,mediaSize);
            if(null!=dynamic.getImgUrl()) {
                if(dynamic.getImgUrl().contains(";")) {
                    String[] pictures = dynamic.getImgUrl().split(";");
                        if(9<pictures.length){
                            StringBuilder sb1 = new StringBuilder();
                                List<String> list = Arrays.asList(pictures);
                                for (String s:list.subList(0,9)){
                                    sb1.append(s+";");
                                }
                            dynamic.setImgUrl(sb1.toString());
                        }
                }
            }
          if(hope!=null&&hope.getProductId()!=null){
               Integer userId=redisLoginVerifyUtil.longinVerifty(sid,uid);
               try{
                   String hopeStauts = "1";
                   /*PageBean pageBean = hopeService.getHopeList(userId,hopeStauts,1,5);*/
                   List<Hope>list = hopeService.getUnrealizedHopes(userId);
                   if (list.size()>=3){
                       Map<String,Object>map = new HashMap<>();
                       map.put("code",1);
                       map.put("message","已存在三个未实现愿望");
                       return map;
                   }
                   /*if("1".equals(hope.getHopeType())){
                       List<Img> siteImgs= imgService.getImgs("1",hope.getProductId());
                       *//*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                       Date playDates = simpleDateFormat.parse(simpleDateFormat.format(beginDate));*//*
                       Double price =  siteService.getNowPrice(Integer.valueOf(hope.getProductId()),hope.getBeginDate());
                       Site site = siteService.selectSiteByPrimaryKey(hope.getProductId());
                       hope.setReserved1(siteImgs.get(0).getImgUrl());
                       hope.setReserved2(site.getReserve4());
                       //价格可能不存在
                       if(null==price){
                           throw new SystemException(Constant.ERR_UNKNOW);
                       }
                       hope.setHopeMoney(price);
                   }else{
                       List<GoodsGllery> goodsGllery = goodsService.glleryList(hope.getProductId());//详情中的商品图片轮播
                       hope.setReserved1(goodsGllery.get(0).getOriginal());
                   }*/
                   hopeService.insertSelective(this.getHope(userId,hope,beginDate,beginTime));

               }catch (Exception e){
                   throw new SystemException(Constant.ERR_UPDATE);
               }
           }
           dynamicService.insertSelective(dynamic);
           endMap.put("0",new UserVo(sid,uid));
       }catch (Exception e){
           endMap.put("-232","发布失败");
           throw new SystemException(Constant.ERR_SYSTEM);
       }
        endMap.put("code",200);
       endMap.put("message","发布成功");
        return endMap;
    }


    @RequestMapping(value = "/contentDynamic",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> contentDynamic(String uid, String sid, String lng, String lat, String city, String content,String beginDate,String beginTime,Hope hope) throws LoginException, SystemException {
                Map<String,Object> endMap=new HashMap<>();
                Dynamic dynamic=getDynamic(uid,sid,"","","",lng,lat,city,content,"");
                try{
                    if(hope!=null&&hope.getProductId()!=null){
                        Integer userId=redisLoginVerifyUtil.longinVerifty(sid,uid);
                            String hopeStauts = "1";
                            /*PageBean pageBean = hopeService.getHopeList(userId,hopeStauts,1,5);*/
                            List<Hope>list = hopeService.getUnrealizedHopes(userId);
                            if (list.size()>=3){
                                Map<String,Object>map = new HashMap<>();
                                map.put("code",1);
                                map.put("message","已存在三个未实现愿望");
                                return map;
                            }
                        /*if("1".equals(hope.getHopeType())) {
                            List<Img> siteImgs = imgService.getImgs("1", hope.getProductId());
                            hope.setReserved1(siteImgs.get(0).getImgUrl());
                        }*/
                            hopeService.insertSelective(this.getHope(userId,hope,beginDate,beginTime));
                            endMap.put("message","愿望已发布");
                    }
                    dynamicService.insertSelective(dynamic);
                    endMap.put("code",200);
                    endMap.put("message","已发布");
                }catch (Exception e) {
                    endMap.put("-232","发布失败");
                    throw new SystemException(Constant.ERR_SYSTEM);
                }
            return endMap;
    }


    public Dynamic getDynamic(String uid,String sid,String imgUrl,String vedioUrl,String videoSuoluetuUrl,String lng,String lat,String city,String content,String mediaSize) throws LoginException {
        Dynamic dynamic=new Dynamic();
        StringBuilder sb=new StringBuilder();
        if(StringUtils.isNotEmpty(lng)&&StringUtils.isNotEmpty(lat)){
            sb.append(lng+",");
            sb.append(lat);
            dynamic.setDynamicJw(sb.toString());
            dynamic.setLng(lng);
            dynamic.setLat(lat);
        }
        if(StringUtils.isNotEmpty(city)){
            dynamic.setReserved2(city);
        }
        Integer userId=redisLoginVerifyUtil.longinVerifty(sid,uid);
        dynamic.setUserId(userId);
        dynamic.setContent(content);
        dynamic.setImgUrl(imgUrl);
        dynamic.setVideoUrl(vedioUrl);
        dynamic.setCreateTime(new Date());
        dynamic.setFabulousNum(0);
        dynamic.setShareNum(0);
        dynamic.setForwardNum(0);
        dynamic.setGiftNum(0);
        dynamic.setIsDel("2");
        dynamic.setReserved1("0");
        dynamic.setReserved5(videoSuoluetuUrl);
        dynamic.setMediaSize(mediaSize);
        return dynamic;
    }




    /**
     * 上传图片
     * @param request
     * @param img
     * @param uid
     * @param sid
     * @return
     */
    @RequestMapping(value = "/imgsUpload",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> imgsUpload(HttpServletRequest request,Img img,String uid,String sid){
        //订场未涉及到图片上传，后面补写
        return null;
    }
    //获取一条愿望记录
    public Hope getHope(Integer userId,Hope hope,String beginDate,String beginTime) throws ParseException {
        if(StringUtils.isBlank(hope.getEveryHopeMoney()+"")){
            hope.setEveryHopeMoney(10.0);
        }
        Hope insertHope =new Hope();
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //愿望名字 价格在 mapper
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        if(hope.getHopeType().equals("1")){
            /**球场愿望失效时间处理*/
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String siteData = beginDate+" "+beginTime;
            calendar.setTime(simpleDateFormat1.parse(siteData));

            //往前推48小时
            calendar.add(Calendar.HOUR_OF_DAY, -48);
            Date updateDate5 = calendar.getTime();
            /*System.out.println("往前推48小时的时间="+sdf.format(updateDate5));*/
            insertHope.setUserId(userId);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            /*SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");*/
            insertHope.setBeginDate(simpleDateFormat.parse(beginDate));
            /*insertHope.setBeginTime(simpleDateFormat2.parse(beginTime));*/
            insertHope.setBeginTime(beginTime);
            insertHope.setCreateTime(new Date());
            insertHope.setEveryHopeMoney(hope.getEveryHopeMoney());
            insertHope.setHopeContent(hope.getHopeContent());
            insertHope.setHopeRealMoney(0.0);
            insertHope.setHopeState("1");
            insertHope.setHopeType(hope.getHopeType());
            insertHope.setPrductRule(null);
            insertHope.setProductId(hope.getProductId());
            insertHope.setReserved1(hope.getReserved1());
            insertHope.setReserved3(updateDate5);
        }else{
            //往后推6个月
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, 6);
            Date updateDate3 = calendar.getTime();
           /* System.out.println("往后推6个月的时间="+sdf.format(updateDate3));*/
            insertHope.setUserId(userId);
            insertHope.setCreateTime(new Date());
            insertHope.setEveryHopeMoney(hope.getEveryHopeMoney());
            insertHope.setHopeContent(hope.getHopeContent());
            insertHope.setHopeRealMoney(0.0);
            insertHope.setProductId(hope.getProductId());
            insertHope.setHopeState("1");
            insertHope.setHopeType(hope.getHopeType());
            insertHope.setPrductRule(hope.getPrductRule());
            insertHope.setReserved1(hope.getReserved1());
            insertHope.setReserved3(updateDate3);
        }
        return insertHope;
    }

    private static String BucketName1;
    private static String BucketName2;
    private static String BucketName3;
    private static String BucketName4;
    private static String networkDomainName1;
    private static String networkDomainName2;
    private static String networkDomainName3;
    private static String networkDomainName4;
    private  static String headerImgs;
    private static String quanziVedio;
    private static String quanziImgs;
    private static String ffmpeg;


    static {
        try {
            initOss();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initOss() throws IOException {
        Properties properties=new Properties();
       InputStream in=PropertiesUtil.class.getClassLoader().getResourceAsStream("oss.properties");
        try {
            properties.load(in);
            BucketName1=properties.getProperty("BucketName1");
            BucketName2=properties.getProperty("BucketName2");
            BucketName3=properties.getProperty("BucketName3");
            BucketName4=properties.getProperty("BucketName4");
            networkDomainName1=properties.getProperty("networkDomainName1");
            networkDomainName2=properties.getProperty("networkDomainName2");
            networkDomainName3=properties.getProperty("networkDomainName3");
            networkDomainName4=properties.getProperty("networkDomainName4");
            headerImgs=properties.getProperty("headerImgs");
            quanziVedio=properties.getProperty("quanziVedios");
            quanziImgs=properties.getProperty("quanziImgs");
            ffmpeg=properties.getProperty("ffmpeg");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // 时间比较
    public static int compare_date(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


}
