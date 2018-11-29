package com.ef.golf.service.impl;

import com.ef.golf.mapper.CourseSignMapper;
import com.ef.golf.pojo.CourseSign;
import com.ef.golf.service.CourseSignService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseSignServiceImpl implements CourseSignService{

    @Resource
    private CourseSignMapper courseSignMapper;

    @Override
    public CourseSign selectByPrimaryKey(Long courseId) {
        return courseSignMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public int insertCourseRecord(List<CourseSign> list) {
        return courseSignMapper.insertCourseRecord(list);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseSign record) {
        return courseSignMapper.updateByPrimaryKeySelective(record);
    }

    /** 签到记录查询 */
    @Override
    public List<CourseSign> getCourseSignRecord(Integer courseId, Integer stuId, Integer coachId,Integer orderId) {
        Map<String,Object>map = new HashMap<>();
        map.put("courseId",courseId);
        map.put("stuId",stuId);
        map.put("coachId",coachId);
        map.put("orderId",orderId);
        return courseSignMapper.getCourseSignRecord(map);
    }

    @Override
    public CourseSign selectCourseSignById(Integer signId, Integer coachId, Integer stuId,Integer orderId) {
        Map<String,Object>map = new HashMap<>();
        map.put("signId",signId);
        map.put("coachId",coachId);
        map.put("stuId",stuId);
        map.put("orderId",orderId);
        return courseSignMapper.selectCourseSignById(map);
    }

    @Override
    public int updateSignStatus(String stuSignStauts, String coachSignStauts, Integer signId, Integer caochId, Integer stuId,Integer orderId) {
        Map<String,Object>map = new HashMap<>();
        map.put("stuSignStauts",stuSignStauts);
        map.put("coachSignStauts",coachSignStauts);
        map.put("signId",signId);
        map.put("caochId",caochId);
        map.put("stuId",stuId);
        map.put("orderId",orderId);
        return courseSignMapper.updateSignStatus(map);
    }


}

