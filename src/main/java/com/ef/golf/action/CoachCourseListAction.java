package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.CoachCourseVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by wxc
 * 搜索课程
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CoachCourseListAction extends AbstractService {

    @Resource
    private CourseService courseService;

    private String courseName;

    @Override
    public Object doService() throws Exception {
        List<CoachCourseVo> list = courseService.getCoachCourseList(courseName);
        return list;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
