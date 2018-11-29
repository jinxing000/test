package com.ef.golf.mapper;

import com.ef.golf.pojo.CourseSign;

import java.util.List;
import java.util.Map;

public interface CourseSignMapper {
    int deleteByPrimaryKey(Long signId);

    int insert(CourseSign record);

    int insertSelective(CourseSign record);

    CourseSign selectByPrimaryKey(Long signId);

    int updateByPrimaryKeySelective(CourseSign record);

    int updateByPrimaryKey(CourseSign record);

    int insertCourseRecord(List<CourseSign>list);

    List<CourseSign>getCourseSignRecord(Map map);
    CourseSign selectCourseSignById(Map map);
    int updateSignStatus(Map map);
}