package com.ef.golf.controller;

import com.cloopen.rest.sdk.utils.PropertiesUtil;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.Photo_album;
import com.ef.golf.service.PhotoAlbumService;
import com.ef.golf.util.OssUtil;
import com.ef.golf.util.PictureUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 个人中心相册 查询 增加 删除
 */
@Controller
@RequestMapping("/userPhoto")
public class UserPhotoController {

    @Resource
    private PhotoAlbumService photoAlbumService;
    private static String BucketName8;

    private static String networkDomainName8;

    private static String photoAlbum;
    @ResponseBody
    @RequestMapping(value = "/getPhoto",method = RequestMethod.POST)
    public IfunResult getPhoto(HttpServletRequest request,Integer ownerId){

        List<Photo_album>list = photoAlbumService.selectByUserId(ownerId);

        return IfunResult.build(0,"OK",list);
    }
    @ResponseBody
    @RequestMapping(value = "/uploadPhoto",method = RequestMethod.POST)
    public IfunResult uploadPhoto(Photo_album pa,@RequestPart(value = "photoName") MultipartFile[] multipartFile){
        try {
            for (int i = 0; i < multipartFile.length; i++){
                //调用工具类上传图片
                    String url = PictureUploadUtil.imgsUpload(multipartFile[i],photoAlbum,BucketName8);
                    pa.setPhotoUrl(networkDomainName8+url);
                    pa.setCreateTime(new Date());
                    photoAlbumService.insertPhotoAlbum(pa);
            }
            return IfunResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return IfunResult.build(1,"上传失败！");
        }
    }
    @ResponseBody
    @RequestMapping(value = "/delPhoto",method = RequestMethod.POST)
    public IfunResult delPhoto(String photoalbumId){
        try {
            String[] photoId = photoalbumId.split(",");
            for (int i=0;i<photoId.length;i++){
                String url = photoAlbumService.getPhotoAlbumUrl(Integer.valueOf(photoId[i]));
                String urlKesys = url.substring(url.length()-17,url.length());
                System.out.println("========="+urlKesys);
                List<String>keys = new ArrayList<>();
                keys.add(urlKesys);
                OssUtil.deleteObjects(keys,BucketName8);
                photoAlbumService.deleteByPrimaryKey(Integer.valueOf(photoId[i]));
            }
            return IfunResult.ok();
        }catch (Exception e){
            e.printStackTrace();
            return IfunResult.build(1,"删除失败!");
        }
    }
    static {
        try {
            initOss();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void initOss() throws IOException {
        Properties properties = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("oss.properties");
        try {
            properties.load(in);
            BucketName8 = properties.getProperty("BucketName8");
            networkDomainName8 = properties.getProperty("networkDomainName8");
            photoAlbum = properties.getProperty("photoAlbum");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
