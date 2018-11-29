package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.UserTicketService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/9.
 * 检查优惠券有效性接口
 * <p>
 * 返回给客户端一个状态是否有效，
 * 客户端检查到有效就就调用优惠券发放接口
 * 无效就在客户端提示该券无效
 * <p>
 * 客户端传递参数
 * 优惠券id   youHuiQuanId
 * 当前时间   presentTime    yyyy-MM-dd hh:mm:ss   2017-12-27 23:59:00
 * <p>
 * 返回客户端的key   isEffective   等于0为有效  其他都为无效
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CheckYouHuiQuanAction extends AbstractService {


    //优惠券业务

    @Resource
    private UserTicketService userTicketService;

    //用户id
    private String userId;
    //优惠券id
    private String youHuiQuanId;

    @Override
    public Object doService() throws Exception {

        Map<String, Object> mapReturn = new HashMap<>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("youHuiQuanId", youHuiQuanId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("presentTime", simpleDateFormat.format(new Date()));
        int count = userTicketService.isExpire(map);

        mapReturn.put("isEffective", count);//等于0代表无效
        return mapReturn;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setYouHuiQuanId(String youHuiQuanId) {
        this.youHuiQuanId = youHuiQuanId;
    }
}
