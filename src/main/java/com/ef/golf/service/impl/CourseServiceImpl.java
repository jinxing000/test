package com.ef.golf.service.impl;

import com.ef.golf.mapper.Coach_courseMapper;
import com.ef.golf.mapper.Course_orderMapper;
import com.ef.golf.mapper.UserMapper;
import com.ef.golf.pojo.Coach_course;
import com.ef.golf.pojo.Course_order;
import com.ef.golf.pojo.User;
import com.ef.golf.service.CourseService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class CourseServiceImpl implements CourseService {


    @Resource
    private Coach_courseMapper coach_courseMapper;
    @Resource
    private Course_orderMapper course_orderMapper;
    @Resource
    private UserMapper userMapper;



    @Override
    public List<CoachCourseVo> queryAllCourseListPage(CoachCourseVo coach_course) {
        List<CoachCourseVo> coach_courses = coach_courseMapper.queryAllCourseListPage(coach_course);
        return coach_courses;
    }

    @Override
    public List<CoachCourseVo> getCoachCourseList(String name) {
        List<CoachCourseVo> coachCourseList = coach_courseMapper.getCoachCourseList(name);
        return coachCourseList;
    }
    public CourseDetailsVo getCourseDetails(Integer id){
        return coach_courseMapper.getCourseDetails(id);
    }

    @Override
    public int insertSelective(Coach_course coach_course) {
        return coach_courseMapper.insertSelective(coach_course);
    }

    @Override
    public void courseDel(Integer id) {
        coach_courseMapper.courseDel(id);
    }

    @Override
    public PageBean<CourseWorkOrderVo> courseWorkOrderList(Integer coachId, String orderStat, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("coachId",coachId);
        map.put("orderState",orderStat);
        Integer count = course_orderMapper.courseWorkOrderCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<CourseWorkOrderVo> list = course_orderMapper.courseWorkOrderList(map);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public PageBean getCoachCourseLists(Integer coachId, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("coachId",coachId);
        Integer count = coach_courseMapper.getCoachCourseListCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Coach_course> list = coach_courseMapper.getCoachCourseLists(map);
        pageBean.setResultList(list);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public Course_order getCourseSignMsg(Integer orderId) {
        return course_orderMapper.getCourseSignMsg(orderId);
    }

    /** 用户预约课程查询 */
    @Override
    public PageBean getUserReserveCourse(String createUser, Integer pageNo, Integer pageSize) throws ParseException {
        MineVo mineVo = userMapper.getInfo(Integer.valueOf(createUser));
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        /*if("2".equals(mineVo.getUserType())){
            map.put("coachId",createUser);
        }else{

        }*/
        /** 2018.10.26 教练被预约订单不在此接口展示 */
        map.put("createUser",createUser);

        Integer count = course_orderMapper.getUserReserveCourseCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<UserReserveCourseVo>list = course_orderMapper.getUserReserveCourse(map);

        List<UserReserveCourseVo>urc = new ArrayList<>();
        for (UserReserveCourseVo urcv:list) {
            if("23".equals(urcv.getOrderState())){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date courseDate = simpleDateFormat.parse(urcv.getCourseDate()+" "+urcv.getCourseTime());
                Calendar cal = Calendar.getInstance();
                cal.setTime(courseDate);
                cal.add(Calendar.HOUR, -24);// 24小时制
                courseDate = cal.getTime();
                if(new Date().getTime()>courseDate.getTime()){
                    urc.add(urcv);
                }
            }
        }

        pageBean.setResultList(urc);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        return pageBean;
    }
    @Override
    public PageBean getCourseOrderList(String createUser, String orderState, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("createUser",createUser);
        map.put("orderState",orderState);
        Integer count = course_orderMapper.getCourseOrderListCount(map);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<CourseOrderListVo> list = course_orderMapper.getCourseOrderList(map);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalCount(count);
        pageBean.setResultList(list);
        return pageBean;
    }

    @Override
    public CourseOrderListVo getCourseOrderByOrderId(Integer orderId) {
        return course_orderMapper.getCourseOrderByOrderId(orderId);
    }

    @Override
    public Course_order getCourseOrderDetails(Integer orderId) {
        return course_orderMapper.getCourseOrderDetails(orderId);
    }

    @Override
    public CourseOrderListVo getCourseOrderByOrderIdOrderCenter(Integer orderId) {
        return course_orderMapper.getCourseOrderByOrderIdOrderCenter(orderId);
    }
}
