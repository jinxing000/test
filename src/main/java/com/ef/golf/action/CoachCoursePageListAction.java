package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.core.structure.pageModel.Page;
import com.ef.golf.pojo.Coach_course;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.CoachCourseVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by xzw
 * 2018年2月23日11:41:56
 * 查询教练的课程推荐列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CoachCoursePageListAction extends AbstractService {

    @Resource
    private UserService userService;
    @Resource
    private CourseService courseService;

    private Integer pageNum = 1;
    private Integer showCount = 5;
    private String recommend;//是否推荐 1 是 2 否

    @Override
    public Object doService() throws Exception {
        CoachCourseVo coach_course = new CoachCourseVo();
        coach_course.setCurrentPage(pageNum);
        coach_course.setShowCount(showCount);
        if (!"".equals(recommend) && recommend != null) {
            coach_course.setRecommend(recommend);
        }
        List<CoachCourseVo> coach_courses = courseService.queryAllCourseListPage(coach_course);
        Page page = new Page();
        page.setCurrentPage(pageNum);
        page.setShowCount(showCount);
        page.setResultList(coach_courses);
        page.setTotalResult(coach_course.getTotalResult());
        return page;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
