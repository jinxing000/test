package com.ef.golf.mapper;

import com.ef.golf.pojo.Coach_course;
import com.ef.golf.vo.CoachCourseVo;
import com.ef.golf.vo.CourseDetailsVo;

import java.util.List;
import java.util.Map;

public interface  Coach_courseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coach_course record);

    int insertSelective(Coach_course record);

    Coach_course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coach_course record);

    int updateByPrimaryKey(Coach_course record);
    //查询所有课程
    List<CoachCourseVo> queryAllCourseListPage(CoachCourseVo coach_course);
    Integer queryAllCourseListPageCount();
    /*Integer queryAllCourseListPageCount();*/
    //搜索课程
    List<CoachCourseVo>getCoachCourseList(String courseName);
    //查询课程详情
    CourseDetailsVo getCourseDetails(Integer id);
    //根据课程id逻辑删除课程
    void courseDel(Integer id);
    List<Coach_course> getCoachCourseLists(Map map);
    Integer getCoachCourseListCount(Map map);
}