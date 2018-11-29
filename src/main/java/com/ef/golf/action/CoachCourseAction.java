package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CommentService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.CourseDetailsVo;
import com.ef.golf.vo.QuanziScoreVo;
import com.ef.golf.vo.SiteCommentVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by wxc
 * 2018年2月23日11:41:56
 * 查询教练课程
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CoachCourseAction extends AbstractService {

    private Integer coachId;
    private Integer pageNo = 1;
    private Integer pageSize = 5;

    @Resource
    private CourseService courseService;

    @Override
    public Object doService() throws Exception {

        PageBean pageBean = courseService.getCoachCourseLists(coachId, pageNo, pageSize);

        return pageBean;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
