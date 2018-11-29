package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Hope;
import com.ef.golf.service.HopeService;
import com.ef.golf.util.PageBean;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.vo.UserVo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by wxc
 * 2018年1月3日15:17:30
 * 新增一条愿望
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddHopeAction extends AbstractService {

    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private HopeService hopeService;


    //@NotNull
    private String uid;
    //@NotNull
    private String sid;
    //@NotNull
    private String hopeType;//1.球场愿望  2.商品愿望 3

    private String productId;

    private Double hopeMoney;

    private Double everyHopeMoney;

    private Date beginDate;

    private Date beginTime;

    private String productRule;

    private String hopeContent;

    private String hopeImg;

    private String hopeName;


    @Override
    public Object doService() throws Exception {
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid, uid);
        try {

            String hopeStauts = "1";
            PageBean pageBean = hopeService.getHopeList(userId, hopeStauts, 1, 5);
            if (pageBean.getResultList().size() >= 3) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("message", "已存在三个未实现愿望");
                return map;
            }

            hopeService.insertSelective(this.getHope(userId));
        } catch (Exception e) {
            throw new SystemException(Constant.ERR_UPDATE);
        }
        return new UserVo(sid, uid);
    }


    //获取一条愿望记录
    public Hope getHope(Integer userId) {
        Hope hope = new Hope();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        if (this.hopeType.equals("1")) {

            calendar.setTime(nowDate);
            //往前推48小时
            calendar.add(Calendar.HOUR_OF_DAY, -48);
            Date updateDate5 = calendar.getTime();
            /*System.out.println("往前推48小时的时间="+sdf.format(updateDate5));*/
            hope.setUserId(userId);
            /*hope.setBeginDate(this.beginDate);
            hope.setBeginTime(this.beginTime);*/
            hope.setCreateTime(new Date());
            hope.setEveryHopeMoney(this.everyHopeMoney);
            hope.setHopeContent(this.hopeContent);
            hope.setHopeMoney(this.hopeMoney);
            hope.setHopeRealMoney(0.0);
            hope.setHopeState("1");
            hope.setHopeType(this.hopeType);
            hope.setPrductRule(null);
            hope.setReserved1(this.hopeImg);
            hope.setReserved2(this.hopeName);
            hope.setReserved3(updateDate5);
        } else {
            //往后推6个月
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, 6);
            Date updateDate3 = calendar.getTime();
           /* System.out.println("往后推6个月的时间="+sdf.format(updateDate3));*/
            hope.setUserId(userId);
            hope.setCreateTime(new Date());
            hope.setEveryHopeMoney(this.everyHopeMoney);
            hope.setHopeContent(this.hopeContent);
            hope.setHopeMoney(this.hopeMoney);
            hope.setHopeRealMoney(0.0);
            hope.setHopeState("1");
            hope.setHopeType(this.hopeType);
            hope.setPrductRule(this.productRule);
            hope.setReserved1(this.hopeImg);
            hope.setReserved2(this.hopeName);
            hope.setReserved3(updateDate3);
        }
        return hope;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setHopeType(String hopeType) {
        this.hopeType = hopeType;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setHopeMoney(Double hopeMoney) {
        this.hopeMoney = hopeMoney;
    }

    public void setEveryHopeMoney(Double everyHopeMoney) {
        this.everyHopeMoney = everyHopeMoney;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public void setProductRule(String productRule) {
        this.productRule = productRule;
    }

    public void setHopeContent(String hopeContent) {
        this.hopeContent = hopeContent;
    }

    public void setHopeImg(String hopeImg) {
        this.hopeImg = hopeImg;
    }

    public void setHopeName(String hopeName) {
        this.hopeName = hopeName;
    }

}
