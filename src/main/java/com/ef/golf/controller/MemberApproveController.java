package com.ef.golf.controller;

import com.cloopen.rest.sdk.utils.PropertiesUtil;
import com.ef.golf.exception.MemberProveException;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.pojo.Member_approve;
import com.ef.golf.service.MemberApproveService;
import com.ef.golf.util.OssUtil;
import org.springframework.stereotype.Controller;
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
import java.util.*;

@Controller
@RequestMapping("/save")
public class MemberApproveController {

    @Resource
    private MemberApproveService memberApproveService;

    private static String memberApproveImgs;//本地保存位置
    private static String BucketName5;//oss正面
    /*private static String BucketName6;*///oss反面
    private static String networkDomainName5;//oss
    /*private static String networkDomainName6;*///oss

    @RequestMapping(value = "/saveMemberApprove", method = RequestMethod.POST)
    @ResponseBody
    public IfunResult insertMemberApprove(HttpServletRequest request, Member_approve member_approve) throws MemberProveException {
        Integer userId = member_approve.getUserId();
        Integer clubId = member_approve.getClubId();
        String approveStatus = memberApproveService.memberApproveEnd(userId,clubId.toString());


        if (approveStatus!=null){
            if(approveStatus.equals("2")){
                Map<String,Object>map = new HashMap<>();
                map.put("approveStatus",approveStatus);
                return IfunResult.build(0,"用户认证球会审核中!",map);
            }else if(approveStatus.equals("1")){
                Map<String,Object>map = new HashMap<>();
                map.put("approveStatus",approveStatus);
                return IfunResult.build(0,"用户认证球会已认证!",map);
            }else if("3".equals(approveStatus)){
                memberApproveService.deleteFailMemberApprove(userId,clubId+"");
            }
        }

        Map<String, Object> memberProve = new HashMap<>();
        //接收文件流
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        //获取文件队列（imgUpload为前端页面的input标签name的值）
        List<MultipartFile> approve_img_up = multipart.getFiles("approve_img_up");
        /*List<MultipartFile> approve_img_down = multipart.getFiles("approve_img_down");*/
        try {
            if (approve_img_up.size() > 0) {
                //设置文件存储路径
                String staticPath = memberApproveImgs;
                //request.getSession().getServletContext().getRealPath("/") + "static/imgs/userHeaderImgs/";
                File dirName = new File(staticPath);
                if (!dirName.isDirectory()) {
                    dirName.mkdir();
                }
                //证件正面照,
                for (int i = 0; i < approve_img_up.size(); i++) {
                    String url = "";
                    //获取文件全名称
                    String fileName = approve_img_up.get(i).getOriginalFilename();
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
                            approve_img_up.get(i).transferTo(targetFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        url = fileUrl;
                        //文件存入阿里云oss服务器
                        OssUtil.upload(url, targetFile, BucketName5);
                        //删除本地文件
                        new File(staticPath + url).delete();

                        String approve_img_up_url = networkDomainName5 + url;
                        member_approve.setApproveImgUp(approve_img_up_url);
                    }
                }
                //反面
                /*for (int i = 0; i < approve_img_down.size(); i++) {
                    String url = "";
                    //获取文件全名称
                    String fileName = approve_img_down.get(i).getOriginalFilename();
                    //判断是否有上传文件
                    if (fileName != null && fileName != "") {
                        //分割文件名称
                        String[] filestr2 = fileName.split("\\.");
                        //为当前文件重新命名
                        String fileUrl2 = System.currentTimeMillis() + "." + filestr2[1];
                        //将文件封装成File类型
                        File targetFile = new File(staticPath, fileUrl2);
                        //将文件写入硬盘
                        try {
                            approve_img_down.get(i).transferTo(targetFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        url = fileUrl2;
                        //文件存入阿里云oss服务器
                        OssUtil.upload(url, targetFile, BucketName6);
                        //删除本地文件
                        new File(staticPath + url).delete();

                        String approve_img_down_url = networkDomainName6 + url;
                        member_approve.setApproveImgDown(approve_img_down_url);
                    }
                }*/
                member_approve.setApproveStatus("2");
                member_approve.setCreateTime(new Date());
                memberApproveService.insertMemberApprove(member_approve);
            }
        } catch (Exception e) {
            e.printStackTrace();
           return IfunResult.build(1,"提交失败！");
        }
       return IfunResult.ok();
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
            BucketName5 = properties.getProperty("BucketName5");
            /*BucketName6 = properties.getProperty("BucketName6");*/
            networkDomainName5 = properties.getProperty("networkDomainName5");
            /*networkDomainName6 = properties.getProperty("networkDomainName6");*/
            memberApproveImgs = properties.getProperty("memberApproveImgs");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}