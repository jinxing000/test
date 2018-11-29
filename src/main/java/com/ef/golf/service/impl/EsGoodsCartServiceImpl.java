package com.ef.golf.service.impl;


import com.ef.golf.mapper.GoodsCartMapper;
import com.ef.golf.mapper.TicketsMapper;
import com.ef.golf.pojo.*;
import com.ef.golf.service.EsGoodsCartService;
import com.ef.golf.util.SystemSendTicketUtils;
import com.ef.golf.util.ThreadContextHolder;
import com.ef.golf.vo.CartItem;
import com.ef.golf.vo.CreditVo;
import com.ef.golf.vo.GoodsSpecVo;
import com.ef.golf.vo.ShopSendTicketVo;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EsGoodsCartServiceImpl implements EsGoodsCartService {
    @Autowired
    GoodsCartMapper goodsCartMapper;
    @Autowired
    private TicketsMapper ticketsMapper;
    @Value("${shopticketId}")
    private String shopticketId;

    @Override
    public Goods selectGoods(Integer goods_id) {
        return goodsCartMapper.selectGoods(goods_id);
    }

    @Override
    public Double totalPrice(Integer user_id) {
        return goodsCartMapper.totalPrice(user_id);
    }

    @Override
    public List<CartItem> getItemList(Integer user_id,String jiesuanType) {

        List<CartItem> cartItemList=new ArrayList<>();

        cartItemList=goodsCartMapper.getItemList(user_id,jiesuanType);



        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();

        for (CartItem cartItem:cartItemList){
            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(cartItem.getGoods_id());//商品id
            shopSendTicketVo.setPrice(cartItem.getPrice().doubleValue());//售价
            shopSendTicketVo.setReduction(cartItem.getReduction());
            shopSendTicketVo.setCredit(cartItem.getCredit());
            shopSendTicketVo.setCount(cartItem.getNum());
            shopSendTicketVos.add(shopSendTicketVo);
        }

        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");

        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应

        for (CartItem cartItem:cartItemList) {
            for( CreditVo creditVo:creditVoList){
                if(cartItem.getGoods_id()==creditVo.getGoodsId()){
                    cartItem.setCredit(creditVo.getCredit());
                    break;
                }
            }
            /*cartItem.setCredit(creditVoList.get(i).getCredit());
            i++;*/
        }


        return cartItemList;


    }

    @Override
    public Integer totalCount(Integer user_id) {
        return goodsCartMapper.totalCount(user_id);
    }

    @Override
    public Integer updateCartNum(Map map) {
        return goodsCartMapper.updateCartNum(map);
    }

    @Override
    public Product  selcetProduct(Integer goods_id) {
        return goodsCartMapper.selcetProduct(goods_id);
    }

    @Override
    public Activity getActivityList() {
        return goodsCartMapper.getActivityList();
    }

    @Override
    public Integer ActivityGoods(Map map) {
        return goodsCartMapper.ActivityGoods(map);
    }

    @Override
    public Integer updateCartActivity(Integer cart_id) {
        return goodsCartMapper.updateCartActivity(cart_id);
    }

    @Override
    public Integer deleteCart(Map map) {
        return goodsCartMapper.deleteCart(map);
    }

    @Override
    public Integer updateCheckbox(Map map) {
        return goodsCartMapper.updateCheckbox(map);
    }

    @Override
    public List<GoodsCart> getAgainList(Integer userId) {
        return goodsCartMapper.getAgainList(userId);
    }

    @Override
    public Integer delectUserCart(Integer userId) {
        return goodsCartMapper.delectUserCart(userId);
    }

    @Override
    public Activity getActivity(Integer activity_id) {
        return goodsCartMapper.getActivity(activity_id);
    }

    @Override
    public Integer ActivityMap(Integer activity_id) {
        return goodsCartMapper.ActivityMapOne(activity_id);
    }

    @Override
    public Integer isGoodsCart(Map map) {
        return goodsCartMapper.isGoodsCart(map);
    }

    @Override
    public Integer updateGoodsCartNum(Map map) {
        return goodsCartMapper.updateGoodsCartNum(map);
    }

    @Override
    public Integer insertSelective(GoodsCart record) {
        return goodsCartMapper.insertSelective(record);
    }

    @Override
    public GoodsCart selcetGoodsCart(Map map) {
        return goodsCartMapper.selcetGoodsCart(map);
    }

    @Override
    public Integer checkGoodsAct(Map map,Integer activity_id) {
        int num = this.ActivityGoods(map);
    //如果没有指定活动，则查看是否有全局活动
        if(num==0){
            try {
                //判定是否有全局活动
                if(this.ActivityMap(activity_id)!=null){
                    if(this.ActivityMap(activity_id)==1){
                        return 1;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }

        return num;
    }

    @Override
    public Integer add(GoodsCart cart, Integer user_id) {
        Map <String,Object> map = new HashMap<String,Object>();
          map.put("product_id",cart.getProductId());
          map.put("itemtype",cart.getItemtype());
          map.put("session_id",cart.getSessionId());
          map.put("user_id",user_id);
          //map.put("num",num);

          int count = this.isGoodsCart(map);
          if(count>0){
              map.put("num",cart.getNum());
            int i = this.updateGoodsCartNum(map);
            if(i>0){
                return 2;
            }else{
                return -2;
            }

          }else{
              int a = this.insertSelective(cart);
              if(a>0){
                  return 1;
              }else{
                  return -1;
              }

          }


    }

    @Override
    public Map<String, Object> getCartData(Integer user_id) {
        Map<String, Object> map = new HashMap<String, Object>();
       int totalCount = goodsCartMapper.totalCount(user_id);
       Double totalPrice = goodsCartMapper.totalPrice(user_id);
        map.put("totalCount",totalCount);
        map.put("totalPrice",totalPrice);
        return map;
    }

    @Override
    public Map addCart(Product product, Integer num,String session_id,Integer user_id) {
        Map<String,Object> map = new HashMap<String,Object>();
        if(product!=null){
            try{
                //判断商品是否下架 商品是否删除
                Goods good = this.selectGoods(product.getGoodsId());
                if(good.getMarketEnable()!=1){
                    //return JsonResultUtil.getErrorJson("抱歉！您所选择的货品已经下架");
                    map.put("code","1");
                    map.put("mes","抱歉！您所选择的货品已经下架");
                    return map;
                }
                /*
                int enableStore = product.getEnableStore();
                if (enableStore < num.intValue()) {
                    map.put("code","1");
                     map.put("mes","抱歉！您所选择的货品库存不足。");
                    return map;
                }*/
                //查询已经存在购物车里的商品

                //Cart tempCart = cartManager.getCartByProductId(product.getProduct_id(), sessionid);
                Map<String,Object> map1=new HashMap<String,Object>();
                map1.put("product_id",product.getProductId());
                map1.put("session_id",session_id);
                map1.put("user_id",user_id);

                GoodsCart tempCart = this.selcetGoodsCart(map1);
                GoodsCart cart = new GoodsCart();
              /*  if(tempCart != null){
                    int tempNum = tempCart.getNum();
                   *//* if (enableStore <num.intValue()+tempNum) {
                        map.put("code","1");
                        map.put("mes","抱歉！您所选择的货品库存不足。");
                        return map;
                    }*//*
                 //   num=num+tempCart.getNum();//如果购物车中存在同样的货品，而且库存够的话累加
                }*/
               /* HttpServletRequest request=ThreadContextHolder.getHttpRequest();//获取当前请求
                String exchange=request.getParameter("exchange");//获取exchange参数
                if("true".equals(exchange)){//如果是积分商品 这走下面的插件桩的方法
                    this.cartPluginBundle.onAddProductToCart(product,num);
                }*/

                cart.setGoodsId(product.getGoodsId());
                cart.setProductId(product.getProductId());
                cart.setSessionId(session_id);
                cart.setNum(num);//更新时会在sql中累加
                cart.setItemtype(0); //0为product和产品 ，当初是为了考虑有赠品什么的，可能有别的类型。
                cart.setWeight(product.getWeight());
                cart.setPrice( product.getPrice() );
                cart.setName(product.getName());
                cart.setMemberId(user_id);
                //默认商品添加购物车选中
                //add by Kanon 2016-7-12
                cart.setIsCheck(1);
                //如果商品参加了促销活动，就将促销活动ID添加至购物车表
                //add by DMRain 2016-1-15

                //如果活动为空，则获取当前活动 查看是否参与活动
                //Activity  act = null ;
                //if(activity_id==null){
                Activity act = goodsCartMapper.getActivityByGoodsId(product.getGoodsId());
                Integer activity_id=0;
                    if(act!=null){
                        activity_id=act.getActivityId();
                    }
                //}
                //是否参与活动
                Map<String,Object> map2=new HashMap<String,Object>();
                    map2.put("goods_id",product.getGoodsId());
                    map2.put("activity_id",activity_id);

                if (this.checkGoodsAct(map2,activity_id) == 1) {
                    //活动有效，则说明是当前活动，不需要查询，否则去查询指定活动
                    if(act==null){
                        act = this.getActivity(activity_id);
                    }
                    // 如果传了一个活动过来，这个商品就参与活动了 如果活动超时，也取消
                    if (act != null && act.getEndTime() > System.currentTimeMillis()/1000) {
                        cart.setActivityId(act.getActivityId());
                        // 活动结束时间写入购物车 满足活动结束，商品价格回归正常 ADD FROM Chopper
                        // 2016-10-19
                        cart.setActivityEndTime(act.getEndTime());
                    }
                }
                if(!String.valueOf(good.getHaveSpec()).equals("")&&!String.valueOf(good.getHaveSpec()).equals("null")){
                    if (good.getHaveSpec()==1){
                        List<GoodsSpecVo> specList= this.getGoodsSpecVoList(product.getProductId());
                        if(specList!=null && !specList.isEmpty())
                        {
                            String specstr = JSONArray.fromObject(specList).toString();
                            cart.setAddon(specstr);
                        }
                    }
                }
                int c = this.add(cart,user_id);
               /* //需要同时显示购物车信息
                if(showCartData==1){
                    return this.getCartData();
                }*/
               if(c==2||c==1){
                   map.put("code","0");
                   map.put("mes","添加成功");
               }else{
                   map.put("code","1");
                   map.put("mes","添加失败");
               }
                return map;
            }catch(RuntimeException e){
                e.printStackTrace();
                map.put("code","1");
                map.put("mes","添加失败");
                return map;
            }

        }else{
            map.put("code","1");
            map.put("mes","该货品不存在，未能添加到购物车");
            return map;
        }
        //return map;
    }

    @Override
    public List<GoodsSpecVo> getGoodsSpecVoList(Integer product_id) {
        return goodsCartMapper.getGoodsSpecVoList(product_id);
    }




}
