package com.ef.golf.service;


import com.ef.golf.pojo.Coach_course;
import com.ef.golf.pojo.Course_order;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 16:02
 */
public interface CourseService {

   List<CoachCourseVo> queryAllCourseListPage(CoachCourseVo coach_course);

   List<CoachCourseVo> getCoachCourseList(String name);

   //课程详情
   CourseDetailsVo getCourseDetails(Integer id);
   //添加课程
   int insertSelective(Coach_course coach_course);
   //根据课程id逻辑删除课程
   void courseDel(Integer id);

   PageBean<CourseWorkOrderVo> courseWorkOrderList(Integer coachId,String orderStat,Integer pageNo,Integer pageSize);

   PageBean getCoachCourseLists(Integer coachId,Integer pageNo,Integer pageSize);
   Course_order getCourseSignMsg(Integer orderId);
   // 用户预约课程
   PageBean getUserReserveCourse(String createUser,Integer pageNo,Integer pageSize) throws ParseException;

   PageBean getCourseOrderList(String createUser,String orderState,Integer pageNo,Integer pageSize);

   CourseOrderListVo getCourseOrderByOrderId(Integer orderId);

   Course_order getCourseOrderDetails(Integer orderId);

   CourseOrderListVo getCourseOrderByOrderIdOrderCenter(Integer orderId);//适应手机端订单详情跳转
}
