package com.ef.golf.mapper;


import com.ef.golf.pojo.Course_order;
import com.ef.golf.vo.CourseOrderListVo;
import com.ef.golf.vo.CourseWorkOrderVo;
import com.ef.golf.vo.UserReserveCourseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Course_orderMapper {
    int deleteByPrimaryKey(Integer courseOrderId);

    int insert(Course_order record);

    int insertSelective(Course_order record);

    Course_order selectByPrimaryKey(Integer courseOrderId);

    int updateByPrimaryKeySelective(Course_order record);

    int updateByPrimaryKey(Course_order record);

    List<CourseWorkOrderVo>courseWorkOrderList(Map map);
    Integer courseWorkOrderCount(Map map);
    // 用户预约课程
    List<UserReserveCourseVo>getUserReserveCourse(Map map);
    Integer getUserReserveCourseCount(Map map);
    /** 用户预约课程列表 */
    List<CourseOrderListVo>getCourseOrderList(Map map);
    Integer getCourseOrderListCount(Map map);
    Course_order getCourseSignMsg(Integer orderId);

    CourseOrderListVo getCourseOrderByOrderId(Integer orderId);

    Course_order getCourseOrderDetails(Integer orderId);

    CourseOrderListVo getCourseOrderByOrderIdOrderCenter(Integer orderId);
}