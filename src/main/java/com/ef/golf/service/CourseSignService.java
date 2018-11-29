package com.ef.golf.service;

import com.ef.golf.pojo.CourseSign;

import java.util.List;

public interface CourseSignService {
    CourseSign selectByPrimaryKey(Long courseId);

    int insertCourseRecord(List<CourseSign> list);

    int updateByPrimaryKeySelective(CourseSign record);

    /** 签到记录查询 */
    List<CourseSign>getCourseSignRecord(Integer courseId,Integer stuId,Integer coachId,Integer orderId);
    CourseSign selectCourseSignById(Integer signId,Integer coachId,Integer stuId,Integer orderId);
    int updateSignStatus(String stuSignStauts,String coachSignStauts,Integer signId,Integer caochId,Integer stuId,Integer orderId);
}
