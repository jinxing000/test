package com.ef.golf.controller;

import com.cloopen.rest.sdk.utils.PropertiesUtil;
import com.ef.golf.common.Consts;
import com.ef.golf.common.pxx.ChargeUtil;
import com.ef.golf.common.pxx.UserUtil;
import com.ef.golf.common.pxx.model.User;
import com.ef.golf.pojo.Coach_course;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.PictureUploadUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.PxxVo;
import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
教练课程添加、删除
 */
@Controller
@RequestMapping("/course")
public class CoachCourseAddController {


    private static String BucketName7;

    private static String networkDomainName7;

    private static String courseImgs;

    @Resource
    private CourseService courseService;

    @RequestMapping(value = "/courseAdd",method = RequestMethod.POST)
    @ResponseBody
    public IfunResult courseAdd(Coach_course coach_course,@RequestPart(value = "photoName") MultipartFile[] multipartFile) {
      PageBean pageBean = courseService.getCoachCourseLists(coach_course.getCoachId(),1,5);
      if(pageBean.getResultList().size()>=5){
          return IfunResult.build(1,"已存在五项有效课程");
      }
        coach_course.setStatus("1");
        coach_course.setCreateTime(new Date());
        coach_course.setModifyTime(new Date());
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < multipartFile.length; i++){
                //调用工具类上传图片
                String url = PictureUploadUtil.imgsUpload(multipartFile[i],courseImgs,BucketName7);
                sb.append(networkDomainName7+url+",");
            }
            coach_course.setImgUrl(sb.toString());
            courseService.insertSelective(coach_course);
            return IfunResult.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return IfunResult.build(1,"添加失败");
    }



    //逻辑删除
    @RequestMapping("/courseDel")
    @ResponseBody
    public IfunResult cousrDelete(Integer id){
        try {
            if(null!=id){
                courseService.courseDel(id);
                return IfunResult.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
            return IfunResult.build(0,"删除失败");
        }
        return IfunResult.build(0,"删除失败");
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
            BucketName7 = properties.getProperty("BucketName7");
            networkDomainName7 = properties.getProperty("networkDomainName7");
            courseImgs = properties.getProperty("courseImgs");
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
