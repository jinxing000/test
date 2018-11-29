package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Hope;
import com.ef.golf.pojo.TuiHuoAddress;
import com.ef.golf.service.BackOrderService;
import com.ef.golf.service.HopeService;
import com.ef.golf.service.TuiHuoAddressService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by xzw
 * 2018年1月3日15:17:30
 * 退货回寄
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TuiHuoHuiJiAction extends AbstractService {



    //主订单id
    private Integer efOrderId;//用于状态更新

    //商城订单id
    private Integer  shopOrderId; //用于绑定物流公司和单号

    //物流公司
    private String wuLiuCompany;

    //快递单号
    private String huiTuiShipNo;


    @Autowired
    BackOrderService backOrderService;

    @Override
    public Object doService() throws Exception {
        Map<String,Object> map=new HashMap<>();
        try {
          int count=backOrderService.updateSellBack("退货物流公司:"+wuLiuCompany+"-"+"快递单号:"+huiTuiShipNo,shopOrderId,efOrderId);
            map.put("code",200);
            map.put("msg","提交成功!");
        } catch (Exception e) {
            map.put("code",-200);
            map.put("msg","提交失败!");
            throw new SystemException(Constant.ERR_UPDATE);
        }
        return  map;
    }

    public void setEfOrderId(Integer efOrderId) {
        this.efOrderId = efOrderId;
    }

    public void setShopOrderId(Integer shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public void setWuLiuCompany(String wuLiuCompany) {
        this.wuLiuCompany = wuLiuCompany;
    }

    public void setHuiTuiShipNo(String huiTuiShipNo) {
        this.huiTuiShipNo = huiTuiShipNo;
    }
}
