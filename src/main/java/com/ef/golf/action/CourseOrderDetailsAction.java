package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.CommentService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.vo.CourseDetailsVo;
import com.ef.golf.vo.CourseOrderListVo;
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
 * create by xzw
 * 2018年2月23日11:41:56
 * 查询课程详情
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CourseOrderDetailsAction extends AbstractService {

    private Integer orderId;
    @Resource
    private CourseService courseService;

    @Override
    public Object doService() throws Exception {
        CourseOrderListVo courseOrderListVo = courseService.getCourseOrderByOrderId(orderId);
        return courseOrderListVo;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
