package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.CourseSign;
import com.ef.golf.pojo.Course_order;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.CourseSignService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.MineVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 2018年4月19日
 * 课程处理
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CourseSignRecordAction extends AbstractService {

    private Integer orderId;
    @Resource
    private CourseService courseService;
    @Resource
    private CourseSignService courseSignService;

    @Override
    public Object doService() throws Exception {
        Course_order course_order = courseService.getCourseSignMsg(orderId);
        List<CourseSign> list = courseSignService.getCourseSignRecord
                (course_order.getCourseId(), Integer.valueOf(course_order.getCreateUser()), course_order.getCoachId(), orderId);
        return list;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
