package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.CourseSignService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * create by wxc
 * 2018年2月23日11:41:56
 * 查询教练课程
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserReserveCourseAction extends AbstractService {

    private Integer userId;
    private Integer pageNo = 1;
    private Integer pageSize = 5;

    @Resource
    private CourseService courseService;
    @Resource
    private UserService userService;

    @Override
    public Object doService() throws Exception {

            PageBean pageBean = courseService.getUserReserveCourse(userId.toString(), pageNo, pageSize);
            return pageBean;

    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
