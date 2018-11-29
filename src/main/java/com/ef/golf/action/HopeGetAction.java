package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.GetHopeMsg;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.HopeService;
import com.ef.golf.service.UserRoleService;
import com.ef.golf.util.GoEasyUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ef.golf.action
 * Administrator
 * 2018/4/23 16:56
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HopeGetAction extends AbstractService {

    private Integer hopeId;//愿望id
    private String consignee;//收货人
    private String phone;//联系方式
    private String address;//地址id

    @Resource
    private HopeService hopeService;
    @Resource
    private UserRoleService userRoleService;

    public Object doService() throws SystemException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
        /** 返回数据map */
        Map<String, Object> map = new HashMap<>();
        Hope hope = hopeService.selectByPrimaryKey(hopeId);
        try {
            if (simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()
                    == simpleDateFormat.parse(simpleDateFormat.format(hope.getReserved3())).getTime()) {
                Hope ho = new Hope();
                ho.setHopeId(hopeId);
                ho.setGetStauts("2");
                ho.setHopeState("3");
                hopeService.updateByPrimaryKeySelective(ho);
                /*map.put("code", 1);
                map.put("message", "已失效不可领取");*/
                throw new SystemException(Constant.ERR_HOPESHIXIAO);
                /*return map;*/
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (hope.getHopeState().equals("2")) {
            if (hope.getGetStauts().equals("0")) {
                Hope ho = new Hope();
                ho.setHopeId(hopeId);
                ho.setGetStauts("1");
                ho.setReserved4(new Date());
                hopeService.updateByPrimaryKeySelective(ho);
                GetHopeMsg getHopeMsg = new GetHopeMsg();
                getHopeMsg.setHopeId(hopeId);
                getHopeMsg.setAddress(address);
                getHopeMsg.setConsignee(consignee);
                getHopeMsg.setPhone(phone);
                hopeService.insertSelective(getHopeMsg);
                List<Integer> userIds = userRoleService.selectUserIdByRoleId("12");
                for (Integer goEasy: userIds
                        ) {
                    GoEasyUtil.pushMessage(goEasy+"","有用户提交愿望领取信息,请及时处理!");
                }
                map.put("status", 0);
                map.put("message", "您的领取信息已提交，我们会尽快发货!");
                return map;
            }
        }
        if (hope.getHopeState().equals("1")) {
            /*map.put("code", 0);
            map.put("message", "已领取过");
            return map;*/
            throw new SystemException(Constant.ERR_HOPEYILINGGUO);
        }
        /*map.put("code", 0);
        map.put("message", "网络繁忙");*/
        return map;
    }

    public void setHopeId(Integer hopeId) {
        this.hopeId = hopeId;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
