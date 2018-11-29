package com.ef.golf.controller;

import com.ef.golf.pojo.Banner;
import com.ef.golf.pojo.IfunResult;
import com.ef.golf.service.BannerService;
import com.ef.golf.service.SubjectTravelLineService;
import com.ef.golf.service.TravelLineConsultService;
import com.ef.golf.service.TravelService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.ServiceReplyVo;
import com.ef.golf.vo.TravelLineVo;
import com.ef.golf.vo.TravelSubjectLineVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ef.golf.controller
 * Administrator
 * 2018/11/14 9:40
 */
@Controller
@RequestMapping(value = "/travel")
public class TravelController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private TravelService travelService;
    @Autowired
    private SubjectTravelLineService subjectTravelLineService;
    @Autowired
    private TravelLineConsultService travelLineConsultService;

    /***
     * 首页
     */
    @ResponseBody
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public Object travelIndex() {

        Map<String,Object>resultMap = new HashMap<>();
            //banner
            List<Banner>banners = bannerService.bannerByGrouping("5");
            //recommend
            PageBean pageBean = travelService.getTravelLineByType("0",1,3);
            //subject  专题内部线路排序
            List<TravelSubjectLineVo>travelSubjectLineVos = subjectTravelLineService.getSubjectAndSubjectTravel();
                for (TravelSubjectLineVo t : travelSubjectLineVos){
                    if(4<t.getTravelLineVo().size()){
                        List<TravelLineVo>newLineVos = t.getTravelLineVo().subList(0,4);
                        t.setTravelLineVo(newLineVos);
                    }
                    t.getTravelLineVo().sort((t1, t2) -> t1.getSort().compareTo(t2.getSort()));
                }
            resultMap.put("banners",banners);
            resultMap.put("recommendTravelLine",pageBean.getResultList());
            resultMap.put("TravelSubject",travelSubjectLineVos);

        return IfunResult.build(200,"ok",resultMap);
    }
    /**
     * 线路详情
     * @param lineId
     * */
    @ResponseBody
    @RequestMapping(value = "/details",method = RequestMethod.POST)
    public Object TravelDetails(Integer lineId) {
        TravelLineVo travelLineVo = travelService.getTravelDetailsByLineId(lineId);
        if(null == travelLineVo){
            return IfunResult.build(-999,"数据异常",null);
        }
        return IfunResult.build(200,"ok",travelLineVo);
    }
    /**
     * 旅游线路列表
     * @param pageNo
     * @param pageSize
     * @param type 0 推荐 1 国内 2 国外
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Object recommendList
            (String type,@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        PageBean pageBean = travelService.getTravelLineByType(type,pageNo,pageSize);
        return IfunResult.build(200,"ok",pageBean);
    }

    /**
     * 专题列表
     * @param pageNo 默认1
     * @param pageSize 默认5
     * @param subjectId
     * */
    @ResponseBody
    @RequestMapping(value = "/subjectList",method = RequestMethod.POST)
    public Object recommendList(Integer subjectId,@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                                @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){

        PageBean pageBean = subjectTravelLineService.getTravelBuSubjectId(subjectId,pageNo,pageSize);

        return IfunResult.build(200,"ok",pageBean);
    }
    /**
     * 非工作时间客服咨询页
     * */
    @ResponseBody
    @RequestMapping(value = "/searchCue",method = RequestMethod.POST)
    public Object searchCue(){
        ServiceReplyVo serviceReplyVo = travelService.getSearchCueMsg();
        return IfunResult.build(200,"ok",serviceReplyVo);
    }
    /**
     * 用户意向记录
     * */
    @ResponseBody
    @RequestMapping(value = "/saveSearchRecord",method = RequestMethod.POST)
    public Object saveSearchRecord(String userId,String lineId){
        if(StringUtils.isBlank(userId)&&StringUtils.isBlank(lineId)){
            return IfunResult.build(-999,"请求参数不全",null);
        }
        int count = travelLineConsultService.insertTravelLineConsult(userId,lineId);
        return IfunResult.build(200,"ok",count);
    }
}
