package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.MallException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.CartItem;
import com.ef.golf.vo.GoodsHotSpecVo;
import com.ef.golf.vo.GoodsSpecVo;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by yjx on 2017/9/23.
 * 商城订单
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsOrderAction extends AbstractService {
	@Autowired
	EsOrderService orderService;
	@Autowired
	private RedisBaseDao redisBaseDao;

	@Autowired
	EsGoodsCartService goodsCartService;
	@Autowired
	EsMemberAddressService addressService;
	@Autowired
	EsGoodsCartService esGoodsCartService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	EsGoodsCartService getGoodsCartService;
	@Autowired
	TicketService ticketService;
	@Autowired
	OrderService efOrderService;
	@Autowired
	AccountService accountService;

	@Resource
	private UserTicketService userTicketService;

	@Autowired
	private UserService userService;
	//    @NotNull(message = "addrId不能为空")
	private Integer addrId;//地址id
	// @NotNull(message = "type_id不能为空")
	private Integer type_id;//配送方式id
	/*  @NotNull(message = "ticket_id不能为空")*/
	private Integer ticket_id;//优惠劵id
	//@NotNull(message = "userId不能为空")
	private Integer userId;
	private String jiesuanType;//1.购物车结算  2.立即购买

	// @NotNull(message = "userId不能为空")
	private Double realMoney;//客户端传递过来的实付金额  用于后台计算后比对
	//为立即购买单独添加的

	private Integer goods_id;

	private String sid;

	private String uid;

	private Integer num;

	private Integer product_id;

	private String remark;//备注信息 保存到es_order 的admin_remark中


	@Override
	public Object doService() throws Exception {
		GoodsHotSpecVo goodsHotSpecVo = goodsService.getGoodsHotSpecVo(goods_id);
		if(ticket_id !=null && ticket_id!=0 && goodsHotSpecVo !=null){
			throw new LoginException(Constant.GOODSORDER_ERR_HOT);
		}


		type_id = 6;//目前默认配送方式为6  顺丰陆运
		String rUID = redisBaseDao.get(sid);
		if (StringUtils.isEmpty(rUID) || !rUID.equals(uid))
			throw new LoginException(Constant.ERR_USER);
		Map<String, Object> map = new HashMap<String, Object>();

		//订单数据的生成应该放到一个事务里来完成，所以商城模块的订单相关表的添加
		//与总表的数据添加都应该在一个service中完成
		//生成商城模块的订单
		EsOrder order = this.createOrder();
		//order对象用于返回给支付使用，前端依赖于ef_order的id去支付，所以我把这个orderId去更改返回的order的orderId，造个假吧
		com.ef.golf.pojo.Order efOrder = efOrderService.getOrderByNo(order.getSn());
		order.setOrderId(efOrder.getOrderId());

		map.put("order", order);
		map.put("mes", "订单生成成功");


		return map;
	}


	public EsOrder createOrder() throws DemandException, QueryException, MallException {


 /*       HttpServletRequest request  = ThreadContextHolder.getHttpRequest();


        Integer shippingId = StringUtil.toInt(request.getParameter("typeId"),null);
        if(shippingId==null ){
            throw new RuntimeException("配送方式不能为空");
        }

        Integer paymentId = StringUtil.toInt(request.getParameter("paymentId"),0);*/
//		修改为提交订单后，选择支付方式付款，修改人：xulipeng
// 		if(paymentId==0 ){
// 			throw new RuntimeException("支付方式不能为空");
// 		}

		EsOrder order = new EsOrder();

		order.setShippingId(type_id); //配送方式
		order.setSaleCmpl(0);
		order.setPaymentId(0);//支付方式
		order.setPaymentName("------");
		order.setPaymentType("onlinePay");
		String payType = "";
		//if(paymentId.intValue()==0){
		order.setIsOnline(1);
		payType = "online";
       /* }else{
            payType = this.paymentManager.get(paymentId).getType();
        }*/

		// Integer addressId = StringUtil.toInt(request.getParameter("addressId"), false);

       /* MemberAddress address = new MemberAddress();
        address = this.createAddress();*/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("addrId", addrId);
		EsMemberAddress address = addressService.getAddress(map);
        /*if ("cod".equals(payType)) {
            // 如果用户选择的收货地区不支持货到付款(对省、市、区三级都要做判断)
            if (regionsManager.get(address.getProvince_id()).getCod() != 1) {
                throw new RuntimeException("您选择的收货地址不支持货到付款！");
            }

            if (regionsManager.get(address.getCity_id()).getCod() != 1) {
                throw new RuntimeException("您选择的收货地址不支持货到付款！");
            }

            if (regionsManager.get(address.getRegion_id()).getCod() != 1) {
                throw new RuntimeException("您选择的收货地址不支持货到付款！");
            }
        }*/
		order.setShipProvinceid(address.getProvinceId());
		order.setMemberId(userId);
		order.setShipCityid(address.getCityId());
		order.setShipRegionid(address.getRegionId());
		if (address.getTownId() != null && !address.getTownId().equals(-1)) {
			order.setShipTownid(address.getTownId());
		}
		order.setShipAddr(address.getAddr());
		order.setShipMobile(address.getMobile());
		order.setShipTel(address.getTel());
		order.setShipZip(address.getZip());
		order.setReceipt(2);//1.需要发票  2.不需要发票   默认不需要
		order.setAdminRemark(remark);
		if (address.getTownId() != null && !address.getTownId().equals(-1)) {
			order.setShippingArea(address.getProvince() + "-" + address.getCity() + "-" + address.getRegion() + "-" + address.getTown());
		} else {
			order.setShippingArea(address.getProvince() + "-" + address.getCity() + "-" + address.getRegion());
		}
		order.setShipName(address.getName());
		order.setShipDay("任意时间");
		//order.setRegionid(address.getRegion_id());

		//新的逻辑：只要选中了“保存地址”，就会新增一条收货地址，即使数据完全没有修改
       /* if ("yes".equals(request.getParameter("saveAddress"))) {
            Member member = UserConext.getCurrentMember();
            if (member != null) {
                address.setAddr_id(null);
                addressId= this.memberAddressManager.addAddress(address);
            }
        }*/
		//address.setAddrId(add_id);
		// order.setMemberAddress(address);
       /* order.setShip_day(request.getParameter("shipDay"));
        order.setShip_time(request.getParameter("shipTime"));
        order.setRemark(request.getParameter("remark"));*/
		order.setAddressId(address.getAddrId());//保存本订单的会员id

		/**发票*/

       /* Integer receipt = Integer.parseInt(request.getParameter("receipt").toString());
        order.setReceipt(receipt);*/
		/**判断是否需要发票*/
   /*     if(receipt==1){
            String receipt_title = request.getParameter("receiptTitle");
            String receipt_content = request.getParameter("receiptContent");
            String receipt_duty = request.getParameter("receiptDuty");
            Integer receipt_type = Integer.parseInt(request.getParameter("receiptType"));
            order.setReceipt_content(receipt_content);
            order.setReceipt_title(receipt_title);
            order.setReceipt_duty(receipt_duty);
            order.setReceipt_type(receipt_type);
        }*/

		//String sessionid =request.getSession().getId();

		//立即购买后的结算不走购物车处理  20180601  尹金星
		//
		// 这时需要前端传递一个处理类型过来区分
		// 如果是购物车计算 传递类型 jiesuanType  1
		// 如果是立即购买的结算  传递jiesuanType  2
		List<CartItem> itemList = null;
		OrderPrice orderPrice = null;
		if ("1".equals(jiesuanType)) {
			itemList = esGoodsCartService.getItemList(userId, jiesuanType);//与购物车获取列表分开，这里只获取选中的
			if (itemList == null || itemList.size() == 0) {
				throw new MallException(Constant.GOODSCART_CANNOT_EMPTY);
			}
			orderPrice = orderService.countPrice(itemList, type_id, String.valueOf(address.getRegionId()), ticket_id);
		} else if ("2".equals(jiesuanType)) {//没有购物车的情况下 重量 和总价自己计算
			//临时拼凑一个购物车
			itemList = new ArrayList<>();
			CartItem tempCartItem = createTempCart(goods_id, product_id, num, sid, userId);
			itemList.add(tempCartItem);
			orderPrice = orderService.countPrice(itemList, type_id, String.valueOf(address.getRegionId()), ticket_id);
		}


		//激发价格计算事件
		//orderPrice  = this.cartPluginBundle.coutPrice(orderPrice);
		order.setOrderprice(orderPrice);


		// Integer activity_id = StringUtil.toInt(request.getParameter("activity_id"),0);

		String activity_id = null;
		for (CartItem item : itemList) {
			if (StringUtils.isNotEmpty(String.valueOf(item.getActivity_id()))) {
				activity_id = String.valueOf(item.getActivity_id());
			}
		}

		if (!activity_id.equals("null") && !activity_id.equals(null)) {
			Activity act = getGoodsCartService.getActivity(Integer.valueOf(activity_id));
			if (act != null && act.getEndTime() > System.currentTimeMillis() / 1000) {
				ActivityDetail activityDetail = orderService.selectByKey(Integer.valueOf(activity_id));
				if ((activityDetail.getMinusValue().toString()) != "null") {
					order.setActDiscount(Double.parseDouble(activityDetail.getMinusValue().toString()));
					order.setNeedPayMoney(Double.parseDouble(new BigDecimal(order.getNeedPayMoney().toString()).subtract(activityDetail.getMinusValue()).toString()));
				}
				if (activityDetail.getPointValue() != null) {
					order.setActivityPoint(activityDetail.getPointValue());
				}

			}
		}
		Double couponAmount = 0.0;
		//优惠劵的使用
		if (ticket_id != null && ticket_id.intValue() != 0) {
			UserTicket userTicket = userTicketService.selectByPrimaryKey(ticket_id + "");
			Tickets tickets = ticketService.selectByPrimaryKey(userTicket.getTicketId());//优惠券详情*/
			// Tickets tickets = ticketService.selectByPrimaryKey(ticket_id);
			if (userTicket == null || tickets == null)
				throw new QueryException(Constant.ERR_QUERY - 4);
			if (userTicket.getState() != 2 && userTicket.getState() != 3)
				throw new DemandException(Constant.ERR_DEMAND - 2);
			//判断优惠券的归属  1  8
			if (userTicket.getLocation() != 1 && userTicket.getLocation() != 8)
				throw new DemandException(Constant.ERR_DEMAND - 3);
			if (tickets != null) {
				if (tickets.getType() == 2) {/** 满减 */
					order.setNeedPayMoney(Double.parseDouble(new BigDecimal(order.getNeedPayMoney().toString()).subtract(new BigDecimal(tickets.getSpecialMoney())).toString()));
					couponAmount = tickets.getSpecialMoney();
				}
				if (tickets.getType() == 1) {/** 折扣 */
					//折扣金额 与 折扣上限比较   如果折扣金额>折扣上限   那么商品金额-折扣上限

					Double discountMoney = Double.parseDouble(new BigDecimal(order.getOrderprice().getGoodsPrice().toString()).multiply(new BigDecimal(tickets.getDiscount() / 100.0)).toString());
					if (discountMoney <= 0.01) {
						discountMoney = 0.01;
						couponAmount = Double.parseDouble(new BigDecimal(order.getOrderprice().getGoodsPrice().toString()).multiply(new BigDecimal(1 - tickets.getDiscount() / 100.0)).toString());
						;
					}
					if (tickets.getDiscountMoney() != null) {//order.getOrderprice().getGoodsPrice()-discountMoney  优惠的钱数
						if ((order.getOrderprice().getGoodsPrice() - discountMoney) >= tickets.getDiscountMoney()) {
							discountMoney = order.getOrderprice().getGoodsPrice() - tickets.getDiscountMoney();
							couponAmount = tickets.getDiscountMoney();
						}
					}
					order.setNeedPayMoney(Double.parseDouble(new BigDecimal(discountMoney).add(new BigDecimal(order.getOrderprice().getShippingPrice())).toString()));

				}
			}
		}


		//如果促销活动id不等于0
   /*   if (activity_id != 0) {

            //如果当前促销活动有效
            if (this.activityManager.checkActivity(activity_id) == 0) {
                //以下为当有促销活动时，提交订单，下列信息要set进订单信息中
                //add by DMRain 2016-6-8
                String gift_id = request.getParameter("gift_id");
                String bonus_id = request.getParameter("bonus_id");
                String act_discount = request.getParameter("act_discount");
                String activity_point = request.getParameter("activity_point");
                String freeShip = request.getParameter("freeShip");

                //如果促销活动赠送的赠品id不为空
                if (gift_id != null) {
                    order.setGift_id(Integer.parseInt(gift_id));
                }

                //如果促销活动赠送的优惠券id不为空
               if (bonus_id != null) {
                    order.setBonus_id(Integer.parseInt(bonus_id));
                }

                //如果促销活动减免的现金金额不为空
                if (act_discount != null) {
                    order.setActDiscount(Double.parseDouble(act_discount));
                    order.setNeedPayMoney(order.getNeedPayMoney().doubleValue() - Double.parseDouble(act_discount));
                }

                //如果促销活动赠送的积分不为空
                if (activity_point != null) {
                    order.setActivity_point(Integer.parseInt(activity_point));
                }

                //如果促销活动拥有免邮费的优惠条件
                if (freeShip != null) {
                    order.setNeed_pay_money(order.getNeed_pay_money() - order.getShipping_amount());
                    order.setOrder_amount(order.getOrder_amount() - order.getShipping_amount());
                    order.setShipping_amount(0.00);
                }
            }
        }*/


		//生成系统总订单中的数据


		Double spePrice = (new BigDecimal(order.getActDiscount().toString()).add(new BigDecimal(order.getDiscount()))).doubleValue();

		//价格比对
		//确定价格
		DecimalFormat df = new DecimalFormat("#.00");
		String s = df.format(realMoney);
		String needPayMoney = df.format(order.getNeedPayMoney());
		System.out.println("s=" + s);
		System.out.println("needPayMoney=" + needPayMoney);
		if (!needPayMoney.equals(s)) {
			throw new MallException(Constant.SHANGCHENG_ERR_PRICE);
		}

		Account account = accountService.getAccount(userId);
		Order efOrder = new Order();
		efOrder.setAccountId(account.getAccountId());
		efOrder.setCreateUser(userId.toString());

		//订单状态orderStatus  写入对应的ef_order 以及es_order中的订单状态，支付状态和配送状态
		//主订单状态
		efOrder.setOrderState("28");
		//商城订单
		order.setStatus(1);//订单状态
		order.setPayStatus(0);//支付状态
		order.setShipStatus(0);//物流状态


		if (ticket_id != null) {
			efOrder.setTicketId(ticket_id);
		}
		efOrder.setOrderType("7");
		efOrder.setCreateTime(new Date());
		efOrder.setIsDel(false);
		efOrder.setModifyUser(userId.toString());
		efOrder.setModifyTime(new Date());
		//  efOrder.setOrderNo(order.getSn());
		efOrder.setOrderMoeny(order.getNeedPayMoney().doubleValue());
		efOrder.setAmount(order.getGoodsAmount().doubleValue());
		//efOrder.setPingId(obj.getId());//生成商城订单号以后再设值
		efOrder.setCouponAmount(couponAmount);
		efOrder.setCreateUser(userId + "");


		return orderService.add(order, itemList, efOrder, jiesuanType);
	}

	//立即购买时构造一个不写入数据库的临时购物车
	private CartItem createTempCart(Integer goods_id, Integer product_id, Integer num, String sid, Integer userId) {
		Product product = esGoodsCartService.selcetProduct(product_id);
		CartItem tempCartItem = new CartItem();
		if (product != null) {
			try {
				//判断商品是否下架 商品是否删除
				Goods good = goodsCartService.selectGoods(product.getGoodsId());
               /* if(good.getMarketEnable()!=1){
                    //return JsonResultUtirl.getErrorJson("抱歉！您所选择的货品已经下架");
                    map.put("code","1");
                    map.put("mes","抱歉！您所选择的货品已经下架");
                    return map;
                }*/
                /*int enableStore = product.getEnableStore();
                if (enableStore < num.intValue()) {
                    map.put("code","1");
                    map.put("mes","抱歉！您所选择的货品库存不足。");
                    return map;
                }*/
				//查询已经存在购物车里的商品

				//Cart tempCart = cartManager.getCartByProductId(product.getProduct_id(), sessionid);
               /* Map<String,Object> map1=new HashMap<String,Object>();
                map1.put("product_id",product.getProductId());
                map1.put("session_id",session_id);
                map1.put("user_id",user_id);

                GoodsCart tempCart = this.selcetGoodsCart(map1);*/
				GoodsCart cart = new GoodsCart();
                /*if(tempCart != null){
                    int tempNum = tempCart.getNum();
                    if (enableStore <num.intValue()+tempNum) {
                        //throw new RuntimeException("抱歉！您所选择的货品库存不足。");
                        map.put("code","1");
                        map.put("mes","抱歉！您所选择的货品库存不足。");
                        return map;
                    }
                    num=num+tempCart.getNum();//如果购物车中存在同样的货品，而且库存够的话累加
                }*/
               /* HttpServletRequest request=ThreadContextHolder.getHttpRequest();//获取当前请求
                String exchange=request.getParameter("exchange");//获取exchange参数
                if("true".equals(exchange)){//如果是积分商品 这走下面的插件桩的方法
                    this.cartPluginBundle.onAddProductToCart(product,num);
                }*/

               /* cart.setGoodsId(product.getGoodsId());
                cart.setProductId(product.getProductId());
                cart.setSessionId(sid);
                cart.setNum(num);
                cart.setItemtype(0); //0为product和产品 ，当初是为了考虑有赠品什么的，可能有别的类型。
                cart.setWeight(product.getWeight());
                cart.setPrice( product.getPrice() );
                cart.setName(product.getName());
                cart.setMemberId(userId);
                cart.setIsCheck(1);*/


				tempCartItem.setGoods_id(product.getGoodsId());
				tempCartItem.setProduct_id(product.getProductId());
				tempCartItem.setNum(num);
				tempCartItem.setItemtype(0);
				tempCartItem.setWeight(product.getWeight());
				tempCartItem.setPrice(product.getPrice());
				tempCartItem.setName(product.getName());
				tempCartItem.setIs_check(1);
				tempCartItem.setEnable_store(product.getEnableStore());
				tempCartItem.setCatid(good.getCatId());
				tempCartItem.setMktprice(good.getMktprice());
				tempCartItem.setPoint(good.getPoint());
				tempCartItem.setSn(product.getSn());
				//tempCartItem.setSnapshot_id();
				tempCartItem.setThumbnail(good.getThumbnail());
				tempCartItem.setUnit(good.getUnit());
				tempCartItem.setSpecs(good.getSpecs());
				tempCartItem.setCarriage(good.getCarriage());
				//如果商品参加了促销活动，就将促销活动ID添加至购物车表
				//add by DMRain 2016-1-15

				//如果活动为空，则获取当前活动 查看是否参与活动
				//Activity  act = null ;
				//if(activity_id==null){
				Activity act = esGoodsCartService.getActivityList();
				Integer activity_id = 0;
				if (act != null) {
					activity_id = act.getActivityId();
				}
				//}
				//是否参与活动
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("goods_id", product.getGoodsId());
				map2.put("activity_id", activity_id);

				if (esGoodsCartService.checkGoodsAct(map2, activity_id) == 1) {
					//活动有效，则说明是当前活动，不需要查询，否则去查询指定活动
					if (act == null) {
						act = esGoodsCartService.getActivity(activity_id);
					}
					// 如果传了一个活动过来，这个商品就参与活动了 如果活动超时，也取消
					if (act != null && act.getEndTime() > System.currentTimeMillis() / 1000) {
						tempCartItem.setActivity_id(act.getActivityId());
					}
				}
				if (!String.valueOf(good.getHaveSpec()).equals("") && !String.valueOf(good.getHaveSpec()).equals("null")) {
					if (good.getHaveSpec() == 1) {
						List<GoodsSpecVo> specList = esGoodsCartService.getGoodsSpecVoList(product.getProductId());
						if (specList != null && !specList.isEmpty()) {
							String specstr = JSONArray.fromObject(specList).toString();
							tempCartItem.setAddon(specstr);
						}
					}
				}
				return tempCartItem;

			} catch (RuntimeException e) {
				e.printStackTrace();
			}

		}
		return null;
	}


	public void setJiesuanType(String jiesuanType) {
		this.jiesuanType = jiesuanType;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
