package com.ef.golf.util;

import com.cloopen.rest.sdk.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PictureUploadUtil {
    /**
     * create by wxc
     * @param
     * @param
     * @param
     *  BucketName  oss图片上传位置
     */


    public static String imgsUpload(MultipartFile file,String staticPath,String BucketName) throws IOException {
        String imgUrl = null;
        //获取文件队列
       /* List<MultipartFile> fileObj = multipartHttpServletRequest.getFiles(formName);*/

        /*if (fileObj.size() > 0) {*/
        String fileName = file.getOriginalFilename();
        if(StringUtils.isNotBlank(fileName)){
            //设置文件存储路径
            /*String staticPath = memberApproveImgs;*/
            //request.getSession().getServletContext().getRealPath("/") + "static/imgs/userHeaderImgs/";
            File dirName = new File(staticPath);
            if (!dirName.isDirectory()) {
                dirName.mkdir();
            }

            /*for (int i = 0; i < fileObj.size(); i++) {*/
                String url = "";
                //获取文件全名称
                /*String fileName = fileObj.get(i).getOriginalFilename();*/
                //判断是否有上传文件
                if (fileName != null && fileName != "") {
                    //分割文件名称
                    String[] filestr = fileName.split("\\.");
                    //为当前文件重新命名
                    String fileUrl = System.currentTimeMillis() + "." + filestr[1];
                    //将文件封装成File类型
                    File targetFile = new File(staticPath, fileUrl);
                    //将文件写入硬盘
                    try {
                        file.transferTo(targetFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        url = fileUrl;
                        imgUrl = url;
                    //文件存入阿里云oss服务器
                    OssUtil.upload(url, targetFile, BucketName);
                    //删除本地文件
                    new File(staticPath + url).delete();
                }

        }
        return imgUrl;
    }



    public static String vedioUpload(MultipartFile file) throws IOException {
        String imgType[]=new String[]{"jpg","png","pdf","gif"};
        String vedioType[]=new String[]{"avi","mp4","3gp","3gp2"};

        StringBuilder sb = new StringBuilder();
        String vedioOrImg = null;
        String fileName = file.getOriginalFilename();
        if(StringUtils.isNotBlank(fileName)){
            File dirName = new File(quanziVedio);
            if (!dirName.isDirectory()) {
                dirName.mkdir();
            }
            String url = "";
            if (fileName != null && fileName != "") {
                //分割文件名称
                String[] filestr = fileName.split("\\.");
                //为当前文件重新命名
                String fileUrl = System.currentTimeMillis() + "." + filestr[1];
                //将文件封装成File类型
                File targetFile = new File(quanziVedio, fileUrl);

                String fileEndName = filestr[filestr.length-1];
                //将文件写入硬盘
                try {
                    file.transferTo(targetFile);
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
                   vedioOrImg = networkDomainName4+url+",";
                }else if(endVedio){
                    OssUtil.upload(url,targetFile,BucketName3);
                    vedioOrImg=networkDomainName3+url;
                }
                //删除本地文件
                new File(quanziVedio + url).delete();
            }

        }
        return vedioOrImg;
    }



    private static String BucketName3;
    private static String BucketName4;
    private static String networkDomainName3;
    private static String networkDomainName4;
    private  static String headerImgs;
    private static String quanziVedio;


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
            BucketName3=properties.getProperty("BucketName3");
            BucketName4=properties.getProperty("BucketName4");
            networkDomainName3=properties.getProperty("networkDomainName3");
            networkDomainName4=properties.getProperty("networkDomainName4");
            headerImgs=properties.getProperty("headerImgs");
            quanziVedio=properties.getProperty("quanziVedios");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
