package com.ef.golf.service.impl;

import com.ef.golf.mapper.CustomServiceMapper;
import com.ef.golf.mapper.PlatformWorketimeMapper;
import com.ef.golf.mapper.TravelLineConsultMapper;
import com.ef.golf.mapper.TravelLineMapper;
import com.ef.golf.pojo.CustomService;
import com.ef.golf.pojo.PlatformWorktime;
import com.ef.golf.pojo.TravelLine;
import com.ef.golf.pojo.TravelLineConsult;
import com.ef.golf.service.TravelLineConsultService;
import com.ef.golf.util.DateUtil;
import com.ef.golf.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/11/15 14:42
 */
@Service
public class TravelLineConsultServiceImpl implements TravelLineConsultService {

    @Autowired
    private TravelLineConsultMapper travelLineConsultMapper;

    @Autowired
    private PlatformWorketimeMapper platformWorketimeMapper;
    @Autowired
    private CustomServiceMapper customServiceMapper;
    @Autowired
    private TravelLineMapper travelLineMapper;

    @Override
    public int insertTravelLineConsult(String userId, String lineId) {
        int count = 0;
        //未处理记录
        TravelLineConsult travelLineConsult = travelLineConsultMapper.getSearchRecord(userId,lineId);
        PlatformWorktime platformWorktime = platformWorketimeMapper.getPlatformWorktime("2");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        if(null == travelLineConsult){
            travelLineConsult = new TravelLineConsult();
            travelLineConsult.setCreateTime(date);
            travelLineConsult.setCreateUser("system");
            travelLineConsult.setModifyTime(date);
            travelLineConsult.setModifyUser("system");
            travelLineConsult.setDealed(0);
            travelLineConsult.setLineId(Integer.valueOf(lineId));
            travelLineConsult.setUserId(Integer.valueOf(userId));
            travelLineConsult.setLookNum(1);
            travelLineConsult.setWorking(1);
            try {
                if(DateUtil.belongCalendar(sdf.parse(sdf.format(date)),sdf.parse(platformWorktime.getsTime()),sdf.parse(platformWorktime.getxTime()))){
                    travelLineConsult.setWorking(0);
                }else{
                    List<CustomService> customServiceAll = customServiceMapper.findCustomServiceAll(8);
                    TravelLine travelLine = travelLineMapper.selectByPrimaryKey(Integer.valueOf(lineId));
                    String [] sms = {travelLine.getLineName()};
                    for (CustomService cs : customServiceAll){
                        if("8".equals(cs.getType().toString())){
                            SmsUtil.sendSMS(cs.getPhone(),cs.getTemplateid(),sms);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            count = travelLineConsultMapper.insertSelective(travelLineConsult);
        }else{
            travelLineConsult.setLookNum(travelLineConsult.getLookNum()+1);
            travelLineConsult.setModifyTime(date);
            count = travelLineConsultMapper.updateByPrimaryKeySelective(travelLineConsult);
        }
        return count;
    }
}
