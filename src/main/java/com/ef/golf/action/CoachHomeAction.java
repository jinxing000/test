package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Area_dic;
import com.ef.golf.pojo.Banner;
import com.ef.golf.pojo.Coach_course;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.AreaDicService;
import com.ef.golf.service.BannerService;
import com.ef.golf.service.CourseService;
import com.ef.golf.service.UserService;
import com.ef.golf.vo.CoachCourseVo;
import com.ef.golf.vo.QuanziUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CoachHomeAction extends AbstractService {

    @Resource
    private UserService userService;
    @Resource
    private BannerService bannerService;
    @Resource
    private CourseService courseService;

    private String grouping = "2";
    private String city;

    private int pageNum = 1;
    private int showCount = 5;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            /** banner */
            List<Banner> bannerList = bannerService.bannerByGrouping(grouping);
            System.out.print(bannerList.size());
            map.put("bannerList", bannerList);
            /** 教练*/
            QuanziUserVo quanziUserVo = new QuanziUserVo();
            quanziUserVo.setRegion(city.trim());
            quanziUserVo.setCurrentPage(pageNum);
            quanziUserVo.setShowCount(showCount);
            List<QuanziUserVo> coachListPage = userService.getCoachListPage(quanziUserVo);
            map.put("coachListPage", coachListPage);
            /*map.put("pageNo",page.getCurrentPage());
            map.put("pageSize",page.getShowCount());
            map.put("totalPage",page.getTotalPage());
            map.put("count",page.getTotalResult());*/
            /** 课程 */
            CoachCourseVo coach_course = new CoachCourseVo();
            coach_course.setCurrentPage(pageNum);
            coach_course.setShowCount(showCount);
            coach_course.setRecommend("1");
            List<CoachCourseVo> coach_courses = courseService.queryAllCourseListPage(coach_course);
            map.put("coach_courses", coach_courses);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new QueryException(Constant.ERR_QUERY);
        }
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }
}
