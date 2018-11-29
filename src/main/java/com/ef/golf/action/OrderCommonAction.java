package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.GroupUtils;
import com.ef.golf.util.ServicePushUtils;
import com.ef.golf.vo.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2018/5/10.
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderCommonAction extends AbstractService {

    private Integer orderId;
    private String orderNo;
    @Resource
    private OrderService orderService;
    @Resource
    private CoachOrderService coachOrderService;
    @Resource
    private Qiutong_orderService qiutongOrderService;
    @Resource
    private CourseService courseService;
    @Resource
    private SiteOrderService siteOrderService;

    @Resource
    private EsOrderService esOrderService;

    @Resource
    private TuiHuoAddressService  tuiHuoAddressService;
    @Override
    public Object doService() throws Exception {

        Map<String,Object> map=new HashMap<>();
        Order order= orderService.getOrderByNo(orderNo);
       // Order order = orderService.selectByPrimaryKey(orderId);
        OrderCommonVo commonVo = new OrderCommonVo();
            if(null!=order){
                commonVo.setOrder(order);
                if("1".equals(order.getOrderType())){
                   /* SiteOrderDatailVo siteOrderDatailVo = siteOrderService.selectSiteOrderDetail(orderId);
                    if(null!=siteOrderDatailVo){
                        commonVo.setSiteOrder(siteOrderDatailVo);
                    }*/

                  SiteOrderListVo siteOrderListVo= siteOrderService.getSiteOrderDetails(order.getOrderId());
                  map.put("orderDetail",siteOrderListVo);
                }else if("2".equals(order.getOrderType())){
                  /*  Coach_order coach_order = coachOrderService.getCoachOrderDetails(orderId);
                    if(null!=coach_order){
                        commonVo.setCoachOrder(coach_order);
                    }*/

                CoachOrderListVo coachOrderListVo= coachOrderService.getCoachOrderDetailsByOrderId(order.getOrderId());
                    map.put("orderDetail",coachOrderListVo);
                }else if ("3".equals(order.getOrderType())){
                    /*Qiutong_order qiutongOrder = qiutongOrderService.getQiuTongOrderDetails(orderId);
                    if(null!=qiutongOrder){
                        commonVo.setQiutongOrder(qiutongOrder);
                    }*/

                   QiuTongOrderListVo qiuTongOrderListVo= qiutongOrderService.getQiuTongOrderDetailsByOrderId(order.getOrderId());
                   map.put("orderDetail",qiuTongOrderListVo);
                }else if("7".equals(order.getOrderType())){
                    EsOrderVo  esOrderVo=esOrderService.getEsOrderBySn(order.getOrderNo());
                    //查询退货地址  跟订单绑定  目前只有一条，再外边查询减少查询次数
                    TuiHuoAddress tuiHuoAddress= tuiHuoAddressService.selectTuiHuoAddress();
                    map.put("order_id", esOrderVo.getShopOrderId());
                    List<OrderItemVo> itemList = esOrderService.orderList(map);
                    esOrderVo.setOrderItemVoList(itemList);
                    esOrderVo.setTuiHuoAddress(tuiHuoAddress);
                    map.put("orderDetail",esOrderVo);
                }else if("9".equals(order.getOrderType())){
                   /* Course_order course_order = courseService.getCourseOrderDetails(orderId);
                    if(null!=course_order){
                        commonVo.setCourseOrder(course_order);
                    }*/


                    CourseOrderListVo  courseOrderListVo=   courseService.getCourseOrderByOrderIdOrderCenter(order.getOrderId());

                    map.put("orderDetail",courseOrderListVo);
                }
            }

        return map;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
