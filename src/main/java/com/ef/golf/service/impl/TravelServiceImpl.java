package com.ef.golf.service.impl;

import com.ef.golf.mapper.PlatformWorketimeMapper;
import com.ef.golf.mapper.TravelLineDetailModuleMapper;
import com.ef.golf.mapper.TravelLineMapper;
import com.ef.golf.mapper.TravelSubjectMapper;
import com.ef.golf.pojo.PlatformWorktime;
import com.ef.golf.pojo.TravelLineDetailModule;
import com.ef.golf.service.TravelService;
import com.ef.golf.util.PageBean;
import com.ef.golf.vo.ServiceReplyVo;
import com.ef.golf.vo.TravelLineVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/11/14 10:37
 */
@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelLineMapper travelLineMapper;
    @Autowired
    private TravelLineDetailModuleMapper travelLineDetailModuleMapper;
    @Autowired
    private PlatformWorketimeMapper platformWorketimeMapper;
    @Autowired
    private TravelSubjectMapper travelSubjectMapper;

    /***
     * type = 1 推荐
     * type = 2 国内
     * type = 3 国外
     */
    @Override
    public PageBean getTravelLineByType(String type,Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        Map<String,Object>map = new HashMap<>();
        map.put("type",type);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<TravelLineVo> travelLineVos = travelLineMapper.getTravelLineByType(map);
            for (TravelLineVo travelLineVo : travelLineVos){
                List<String> pictures = travelLineMapper.getPictureByTravelLineId(travelLineVo.getLineId());
                if(null!=pictures&&!pictures.isEmpty()){
                    travelLineVo.setPicture(pictures.get(0));
                }
            }
        Integer count = travelLineMapper.getTravelLineByTypeCount(type);
        pageBean.setResultList(travelLineVos);
        pageBean.setPageSize(pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public TravelLineVo getTravelDetailsByLineId(Integer lineId) {
        TravelLineVo travelLineVo = new TravelLineVo();
        Map<String,Object>map = new HashMap<>();//封装详情模块和图片
        if(StringUtils.isNotBlank(lineId+"")){
            //查询详情
            travelLineVo = travelLineMapper.getTravelLineDetailsById(lineId);
            List<TravelLineDetailModule> travelLineDetailModules = travelLineDetailModuleMapper.getModulePicUrlByLineId(lineId);

           /* for (TravelLineDetailModule tldm : travelLineDetailModules){
                try {
                    InputStream murl = new URL(tldm.getModulePicUrls()[0]).openStream();
                    BufferedImage sourceImg = ImageIO.read(murl);
                    //System.out.println(String.format("%.1f",tldm.getModulePicUrls()[0]).length()/1024.0);// 源图大小
                    double scale = sourceImg.getWidth()*1.0/sourceImg.getHeight();
                    tldm.setScale(String .format("%.2f",scale));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/


            PlatformWorktime platformWorktime = platformWorketimeMapper.getPlatformWorktime("2");
            travelLineVo.setLineDetailModules(travelLineDetailModules);
            //获取图片列表
            List<String> pictures = travelLineMapper.getPictureByTravelLineId(lineId);
            travelLineVo.setPictures(pictures);
            travelLineVo.setsTime(platformWorktime.getsTime());
            travelLineVo.setxTime(platformWorktime.getxTime());
            travelLineVo.setPhone(platformWorktime.getPhone());
            //获取详情内小模块
        }
        return travelLineVo;
    }

    /**
     * 需要携带工作时间
     * */
    @Override
    public ServiceReplyVo getSearchCueMsg() {

        ServiceReplyVo serviceReplyVo = travelLineMapper.getSearchCueMsg();

        PlatformWorktime platformWorktime = platformWorketimeMapper.getPlatformWorktime("2");
        if(null!=platformWorktime) {
            serviceReplyVo.setsTime(platformWorktime.getsTime());
            serviceReplyVo.setxTime(platformWorktime.getxTime());
        }
        return serviceReplyVo;
    }
}
