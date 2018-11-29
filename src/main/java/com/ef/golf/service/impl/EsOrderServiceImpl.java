package com.ef.golf.service.impl;

import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.exception.MallException;
import com.ef.golf.mapper.*;
import com.ef.golf.pojo.*;
import com.ef.golf.service.EsOrderService;
import com.ef.golf.util.*;
import com.ef.golf.vo.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class EsOrderServiceImpl implements EsOrderService {
    @Autowired
    EsOrderMapper  esOrderMapper;
    @Autowired
    DlyTypeMapper dlyTypeMapper;
    @Autowired
    EsTypeAreaMapper esTypeAreaMapper;

    @Autowired
    OrderMapper mainOrderMapper;

    @Autowired
    private PingUtil pingUtil;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    Es_SettingsMapper es_settingsMapper;
    @Autowired
    DepotMapper depotMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    GoodsSnapshotMapper snapshotMapper;
    @Autowired
    GoodsSnGaMapper snGaMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ProductSnapshotMapper productSnapshotMapper;
    @Autowired
    GoodsGlleryMapper goodsGlleryMapper;
    @Autowired
    SnapshotGalleryMapper snapshotGalleryMapper;
    @Autowired
   Order_logMapper logMapper;
    @Autowired
    OrderItemMapper orderItemsMapper;
    @Autowired
    Product_scoreMapper product_scoreMapper;
    @Autowired
    EsProductStoreMapper esProductStoreMapper;
    @Autowired
    StoreLogMapper storeLogMapper;
    @Autowired
    ActivityDetailMapper activityDetailMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    EsOrderMapper  orderMapper;

    @Autowired
    GoodsCartMapper goodsCartMapper;
    @Autowired
    EsPaymentLogsMapper esPaymentLogsMapper;
    @Autowired
    UserTicketMapper userTicketMapper;

    @Autowired
    private TicketsMapper ticketsMapper;
    @Value("${shopticketId}")
    private String shopticketId;

    @Override
    public void onBeforeOrderCreate(EsOrder order, List<CartItem> itemList) {
        for (CartItem cartItem : itemList) {
            //查询快照表中是否有快照商品
          /*  String sql = "select * from es_goods_snapshot where goods_id=?";
            List list = daoSupport.queryForList(sql, cartItem.getGoods_id());*/
            List <GoodsSnapshot> list = snapshotMapper.getList(cartItem.getGoods_id());
            //查询商品表中的商品
            //sql = "select * from es_goods where goods_id=?";
            Goods goodsSnapshot = goodsMapper.selectByPrimaryKey(cartItem.getGoods_id()); //daoSupport.queryForObject(sql, GoodsSnapshot.class, cartItem.getGoods_id());
            //判断快照表中是否存在当前商品的快照，不存在则创建快照
            if(list.size()==0){
                this.addSnapshotData(goodsSnapshot, cartItem);
            }else{
                //如果存在，相同商品可能存在多个快照表，获取最新的快照表
                GoodsSnapshot map_snapshot =  list.get(list.size()-1);
                //判断商品最后更新时间是否相同，如果不相同需要重新生成快照
                if(!map_snapshot.getLastModify().equals(goodsSnapshot.getLastModify())){
                    this.addSnapshotData(goodsSnapshot, cartItem);
                }else{
                    Integer snapshot_id = map_snapshot.getSnapshotId();
                    cartItem.setSnapshot_id(snapshot_id);
                }
            }
        }



    }

    @Override
    public void addSnapshotData(Goods snapshot, CartItem cartItem) {
        GoodsSnapshot goodsSnapshot =  new GoodsSnapshot();
        goodsSnapshot.setEditTime(System.currentTimeMillis()/1000);
        goodsSnapshot.setGoodsId(snapshot.getGoodsId());
        goodsSnapshot.setAdjuncts(snapshot.getAdjuncts());
        goodsSnapshot.setBig(snapshot.getBig());
        goodsSnapshot.setBrandId(snapshot.getBrandId());
        goodsSnapshot.setCarriage(snapshot.getCarriage());
        goodsSnapshot.setBrief(snapshot.getBrief());
        goodsSnapshot.setBuyCount(snapshot.getBuyCount());
        goodsSnapshot.setCatId(snapshot.getCatId());
        goodsSnapshot.setName(snapshot.getName());
        goodsSnapshot.setCost(snapshot.getCost());
        goodsSnapshot.setCreateTime(snapshot.getCreateTime());
        goodsSnapshot.setGoodsComment(snapshot.getGoodsComment());
        goodsSnapshot.setBrandId(snapshot.getBrandId());
        goodsSnapshot.setMetaDescription(snapshot.getMetaDescription());
        goodsSnapshot.setWeight(snapshot.getWeight());
        goodsSnapshot.setViewCount(snapshot.getViewCount());
        goodsSnapshot.setUnit(snapshot.getUnit());
        goodsSnapshot.setTypeId(snapshot.getTypeId());
        goodsSnapshot.setThumbnail(snapshot.getThumbnail());
        goodsSnapshot.setStore(snapshot.getStore());
        goodsSnapshot.setSpecs(snapshot.getSpecs());
        goodsSnapshot.setSord(snapshot.getSord());
        goodsSnapshot.setEnableStore(snapshot.getEnableStore());
        goodsSnapshot.setSn(snapshot.getSn());
        goodsSnapshot.setSmall(snapshot.getSmall());
        goodsSnapshot.setPrice(snapshot.getPrice());
        goodsSnapshot.setPoint(snapshot.getPoint());
        goodsSnapshot.setParams(snapshot.getParams());
        goodsSnapshot.setPageTitle(snapshot.getPageTitle());
        goodsSnapshot.setMktprice(snapshot.getMktprice());
        goodsSnapshot.setP20(snapshot.getP20());
        goodsSnapshot.setP19(snapshot.getP19());
        goodsSnapshot.setP18(snapshot.getP18());
        goodsSnapshot.setP17(snapshot.getP17());
        goodsSnapshot.setP16(snapshot.getP16());
        goodsSnapshot.setP15(snapshot.getP15());
        goodsSnapshot.setP14(snapshot.getP14());
        goodsSnapshot.setP13(snapshot.getP13());
        goodsSnapshot.setP12(snapshot.getP12());
        goodsSnapshot.setP11(snapshot.getP11());
        goodsSnapshot.setP10(snapshot.getP10());
        goodsSnapshot.setP9(snapshot.getP9());
        goodsSnapshot.setP8(snapshot.getP8());
        goodsSnapshot.setP7(snapshot.getP7());
        goodsSnapshot.setP6(snapshot.getP6());
        goodsSnapshot.setP5(snapshot.getP5());
        goodsSnapshot.setP4(snapshot.getP4());
        goodsSnapshot.setP3(snapshot.getP3());
        goodsSnapshot.setP2(snapshot.getP2());
        goodsSnapshot.setP1(snapshot.getP1());
        goodsSnapshot.setOriginal(snapshot.getOriginal());
        goodsSnapshot.setMetaKeywords(snapshot.getMetaKeywords());
        goodsSnapshot.setLastModify(snapshot.getLastModify());
        goodsSnapshot.setIsPack(snapshot.getIsPack());
        goodsSnapshot.setIntro(snapshot.getIntro());
        goodsSnapshot.setHaveSpec(snapshot.getHaveSpec());
        goodsSnapshot.setHaveField(snapshot.getHaveField());
        goodsSnapshot.setGoodsType(snapshot.getGoodsType());
        goodsSnapshot.setGrade(snapshot.getGrade());
        goodsSnapshot.setDisabled(snapshot.getDisabled());
        snapshotMapper.insertSelective(goodsSnapshot);
        //将快照数据添加到数据库
       // orderSnapshotManager.addSnapshot(goodsSnapshot);
       // String sql = "select max(snapshot_id) from es_goods_snapshot ";
        Integer snapshot_id = snapshotMapper.getMax();
        cartItem.setSnapshot_id(snapshot_id);
        //将货品快照数据添加到数据库
      /*  sql = "select * from es_product where goods_id=? ";
        List<ProductSnapshot> products = daoSupport.queryForList(sql,ProductSnapshot.class,cartItem.getGoods_id());
        for (ProductSnapshot productSnapshot : products) {
            productSnapshot.setSnapshot_id(snapshot_id);
            productSnapshotManager.add(productSnapshot);
        }*/

       List<Product>products =  productMapper.getList(cartItem.getGoods_id());
       for (Product product:products){
           ProductSnapshot productSnapshot = new ProductSnapshot();
           productSnapshot.setSnapshotId(snapshot_id);
           productSnapshot.setWeight(product.getWeight());
           productSnapshot.setStore(product.getStore());
           productSnapshot.setSpecs(product.getSpecs());
           productSnapshot.setProductId(product.getProductId());
           productSnapshot.setPrice(product.getPrice());
           productSnapshot.setName(product.getName());
           productSnapshot.setIsPack(product.getIsPack());
           productSnapshot.setGoodsId(product.getGoodsId());
           productSnapshot.setEnableStore(product.getEnableStore());
           productSnapshot.setCost(product.getCost());
           productSnapshot.setSn(product.getSn());
           productSnapshotMapper.insertSelective(productSnapshot);
       }







        //查询商品图片信息表，将图片信息存入快照图片表
     /*   sql = "select * from es_goods_gallery where goods_id=?";
        List<GoodsGallery> queryForList = daoSupport.queryForList(sql, GoodsGallery.class, cartItem.getGoods_id());
        for (GoodsGallery goodsGallery : queryForList) {
            GoodsSnapshotGallery goodsSnapshotGallery = new GoodsSnapshotGallery();
            BeanUtils.copyProperties(goodsGallery, goodsSnapshotGallery);
            goodsSnapshotGallery.setSnapshot_id(snapshot_id);
            daoSupport.insert("es_goods_snapshot_gallery", goodsSnapshotGallery);
        }*/
        List<GoodsGllery> queryForList =goodsGlleryMapper.getList(cartItem.getGoods_id());
        for (GoodsGllery goodsGallery : queryForList) {
            SnapshotGallery goodsSnapshotGallery = new SnapshotGallery();
            BeanUtils.copyProperties(goodsGallery, goodsSnapshotGallery);
            goodsSnapshotGallery.setSnapshotId(snapshot_id);
            goodsSnapshotGallery.setTiny(goodsGallery.getTiny());
            goodsSnapshotGallery.setThumbnail(goodsGallery.getThumbnail());
            goodsSnapshotGallery.setSort(goodsGallery.getSort());
            goodsSnapshotGallery.setSmall(goodsGallery.getSmall());
            goodsSnapshotGallery.setBig(goodsGallery.getBig());
            goodsSnapshotGallery.setImgId(goodsGallery.getImgId());
            goodsSnapshotGallery.setOriginal(goodsGallery.getOriginal());
            goodsSnapshotGallery.setIsdefault(goodsGallery.getIsdefault());
            snapshotGalleryMapper.insertSelective(goodsSnapshotGallery);
        }




    }









    @Override
    public Double countPrice(List<EsTypeArea> areaList, Double weight, Double orderPrice, String regoinId) {
        Double price = null;
        for (EsTypeArea typeArea : areaList) {
            String idGroup = typeArea.getAreaIdGroup();

            if (idGroup == null || idGroup.equals("")) {
                continue;
            }

            idGroup = idGroup == null ? "" : idGroup;
            String[] idArray = idGroup.split(",");

            // 检测所属地区是否在配送范围
            if (StringUtil.isInArray(regoinId, idArray)) {
                Double thePrice = this.countExp(typeArea.getExpressions(),
                        weight, orderPrice);
                if (price != null)
                    price = thePrice.compareTo(price) > 0 ? thePrice : price;
                else
                    price = thePrice;
            }

        }

        return price;
    }

    @Override
    public Double countExp(String exp, Double weight, Double orderprice) {
        Context cx = Context.enter();
        try {
            Scriptable scope = cx.initStandardObjects();
            String str = "var w=" + weight + ";";
            str += "p=" + orderprice + ";";
            str += "function tint(value){return value<0?0:value;}";
            str += exp;
            Object result = cx.evaluateString(scope, str, null, 1, null);
            Double res = Context.toNumber(result);

            return res;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Context.exit();
        }
        return -1D;
    }

    @Override
    public Double[] countPrice(Integer typeId, Double weight, Double orderPrice, String regionId) {

        Double dlyPrice = null;
        //商品如果免运费就认为重量为0  20180724   尹金星
        if(weight==0.0){
            dlyPrice=0.0;
            Double[] priceArray = { dlyPrice, 0.0 };
            return priceArray;
        }
        DlyType dlyType = dlyTypeMapper.selectByPrimaryKey(typeId);

        if (dlyType.getIsSame().compareTo(1) == 0) { // 统一的费用配置
            dlyPrice = this.countExp(dlyType.getExpressions(), weight,
                    orderPrice);
        } else {
            List<EsTypeArea>  dlyTypeAreas =esTypeAreaMapper.getList(typeId);

            dlyPrice = this.countPrice(dlyTypeAreas, weight,
                    orderPrice, regionId);
        }
        Double protectPrice = null;
        // b2b2c 在不影响 原来程序上加默认配送费用
        //修改原有逻辑的bug edit by jianghongyan
        if (dlyPrice == null) {
            dlyPrice = this.countExp(dlyType.getExpressions(), weight,
                    orderPrice);
        }
		/*if("b2b2c".equals(EopSetting.PRODUCT)&&dlyPrice<0){
			dlyPrice = this.countExp(dlyType.getExpressions(), weight,
					orderPrice);
		}*/
        //edit end

        // 精度到小数点后两位 2015-08-31 by kingapex
        dlyPrice = CurrencyUtil.round(dlyPrice, 2);

        Double[] priceArray = { dlyPrice, protectPrice };
        return priceArray;

    }

    @Override
    public String createSn() {
        boolean isHave = true;  //数据库中是否存在该订单
        String sn = "";			//订单号

        //如果存在当前订单
        while(isHave) {
            StringBuffer  snSb = new StringBuffer(System.currentTimeMillis()/1000+"") ;
            snSb.append(new Random().nextInt(100-10)+10);
            int count = esOrderMapper.orderCount(snSb.toString());
            if(count == 0) {
                sn = snSb.toString();
                isHave = false;
            }
        }
        return sn;
    }

    @Override
    public void saveGoodsItem(List<CartItem> itemList, EsOrder order) {
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        Integer order_id = order.getOrderId();
        for (int i = 0; i < itemList.size(); i++) {

            OrderItem orderItem = new OrderItem();

            CartItem cartItem = itemList.get(i);
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setName(cartItem.getName());
            orderItem.setNum(cartItem.getNum());

            orderItem.setGoodsId(cartItem.getGoods_id());
            orderItem.setShipNum(0);
            orderItem.setProductId(cartItem.getProduct_id());
            orderItem.setOrderId(order_id);
            orderItem.setGainedpoint(cartItem.getPoint());
            orderItem.setAddon(cartItem.getAddon());

            //3.0新增的三个字段
            orderItem.setSn(cartItem.getSn());
            orderItem.setImage(cartItem.getThumbnail());
            orderItem.setCatId(cartItem.getCatid());
            orderItem.setGoodsType(cartItem.getItemtype().shortValue());
            orderItem.setUnit(cartItem.getUnit());

            orderItem.setSnapshotId(cartItem.getSnapshot_id());//添加快照

            /**
             * add by jianghongyan 为适配积分商城添加
             */
           // this.orderPluginBundle.beforeItemSave(orderItem,cartItem);
            //this.daoSupport.insert("es_order_items", orderItem);
            orderItemsMapper.insertSelective(orderItem);
            System.out.println(orderItem.getItemId());
            int itemid = orderItem.getItemId();//this.logMapper.lastOne();
            orderItem.setItemId(itemid);
            orderItemList.add(orderItem);
            this.onItemSave(order,orderItem);
        }

        String itemsJson  = JSONArray.fromObject(orderItemList).toString();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("items_json",itemsJson);
        map.put("order_id",order_id);
       // this.daoSupport.execute("update es_order set items_json=? where order_id=?", itemsJson,order_id);
        esOrderMapper.updateItemsJson(map);

    }

    @Override
    public void decreaseEnable(int goodsid, int productid, int depotid, int num) {
        Map<String,Object>maps = new HashMap<String,Object>();
        maps.put("num",num);
        maps.put("productid",productid);
        maps.put("depotid",depotid);
        Map<String,Object>map2 = new HashMap<String,Object>();
        Map<String,Object>map3 = new HashMap<String,Object>();
        //this.daoSupport.execute("update es_product_store set enable_store=enable_store-? where depotid=? and productid=?", num,depotid,productid);
          esProductStoreMapper.updateEsProductStore(maps);
        //this.daoSupport.execute("update es_product set enable_store=enable_store-? where product_id=?", num,productid);
        //this.daoSupport.execute("update es_goods set enable_store=enable_store-? where goods_id=?", num,goodsid);
        map2.put("product_id",productid);
        map2.put("num",num);
        productMapper.updateProduct(map2);
        map3.put("goods_id",goodsid);
        map3.put("num",num);
        goodsMapper.updateGoods(map3);
        int enable_store = goodsMapper.getGoods(goodsid).getEnableStore();//this.daoSupport.queryForInt("select enable_store from es_goods where goods_id=?", goodsid);
        if(enable_store==0){
            //this.daoSupport.execute("update es_goods set market_enable=? where goods_id=?", 0,goodsid);
            goodsMapper.updateGoodsStore(map3);
        }




    }

    @Override
    public void onItemSave(EsOrder order, OrderItem item) {
        Integer order_id = order.getOrderId();
        String ordersn = order.getSn();
        int depotid = order.getDepotid();
        //更新商品可用库存
        //this.productStoreManager.decreaseEnable(item.getGoods_id(), item.getProduct_id(), depotid, item.getNum());
         this.decreaseEnable(item.getGoodsId(),item.getProductId(),depotid,item.getNum());
        //记录库存日志
        Long mills = System.currentTimeMillis()/1000;
        StoreLog storeLog = new StoreLog();
        storeLog.setDateline(mills);
        storeLog.setDepotType(0);
        storeLog.setDepotid(depotid);
        storeLog.setGoodsid(item.getGoodsId());
        storeLog.setGoodsname( item.getName() );
        storeLog.setNum(0);
        storeLog.setEnableStore(0-item.getNum());
        storeLog.setOpType(StoreLogType.create_order.getType());  //记录为创建订单减少可用库存
        storeLog.setProductid(item.getProductId());
        storeLog.setUserid(0);
        storeLog.setRemark("创建订单["+ordersn+"]，减少可用库存");
        storeLog.setUsername("系统");
        storeLogMapper.insertSelective(storeLog);

    }

    @Override
    public List<OrderItem> getItemList(Integer order_id) {
        return orderItemMapper.getItemList(order_id);
    }

    @Override
    public EsOrder add(EsOrder order, List<CartItem> itemList,Order efOrder,String jiesuanType) throws MallException {
        String opname = "游客";

        if (order == null)
            throw new RuntimeException("error: order is null");

        /************************** 用户信息 ****************************/
     /*   Member member = UserConext.getCurrentMember();

        // 非匿名购买
        if (member != null) {
            order.setMember_id(member.getMember_id());
            opname = member.getUname();
        }*/
        User user = userMapper.selectByPrimaryKey(order.getMemberId().longValue());
        opname=user.getUserName();
        // 配送方式名称
        DlyType dlyType = new DlyType();
        if (dlyType != null && order.getShippingId()!=0){
            dlyType = dlyTypeMapper.selectByPrimaryKey(order.getShippingId());
        }else{
            dlyType.setName("");
        }
        order.setShippingType(dlyType.getName());


        /************ 支付方式价格及名称 ************************/
        //修改为提交订单后，选择支付方式付款，修改人：xulipeng  2016年06月03日
        //如果支付方式为 0，则为在线支付，反之则为其它支付方式，如货到付款等。
     /*   if(order.getPayment_id().intValue()==0){
            order.setPayment_name("在线支付");
            order.setPayment_type("onlinePay");

        }else{
            PayCfg payCfg = this.paymentManager.get(order.getPayment_id());
            //此方法实现体为空注释掉
            //order.setPaymoney(this.paymentManager.countPayPrice(order.getOrder_id()));
            order.setPayment_name(payCfg.getName());
            order.setPayment_type(payCfg.getType());
        }*/

        order.setPaymentName("-------");
        order.setPaymentType("onlinePay");

        /************ 创建订单 ************************/
        order.setCreateTime(System.currentTimeMillis()/1000);

        //获取系统设置购买商品金额与积分的比例
        Map map = es_settingsMapper.getSettingPoint();
        String cfg_value = (String) map.get("cfg_value");
        JSONObject json = JSONObject.fromObject(cfg_value);
        int buygoods_num_mp = json.getInt("buygoods_num_mp");
        int need_pay_money = (int) Math.rint((order.getNeedPayMoney().doubleValue()));
        order.setGainedpoint(CurrencyUtil.mul(need_pay_money, buygoods_num_mp).intValue());

        //判断订单号是否为空
        if(StringUtil.isEmpty(order.getSn())){
            order.setSn(this.createSn());
        }
        order.setStatus(OrderStatus.ORDER_CONFIRM);
        order.setDisabled("0");
        order.setPayStatus(OrderStatus.PAY_NO);
        order.setShipStatus(OrderStatus.SHIP_NO);
        order.setOrderStatus("订单已生效");

        //给订单添加仓库 ------仓库为默认仓库
        Integer depotId= depotMapper.getCount();//this.daoSupport.queryForInt("select id from es_depot where choose=1");
        order.setDepotid(depotId);
        /************ 写入订单货物列表 ************************/


        /**检测商品库存  Start**/

        boolean result = true;	//用于判断购买量是否超出库存
        for(CartItem item : itemList){
            int productId = item.getProduct_id();
            Product product = productMapper.selectByPrimaryKey(productId);
            int enableStore = product.getEnableStore();
            int itemNum = item.getNum();
            if(itemNum >enableStore){
                result = false;
                break;
            }
        }
        if(!result){
            throw new MallException(Constant.SHANGCHENG_STORE_LACK);
        }
        /**检测商品库存  End**/
        //this.orderPluginBundle.onBeforeCreate(order,itemList, sessionid);
        this.onBeforeOrderCreate(order,itemList);
        //this.daoSupport.insert("es_order", order);
        this.esOrderMapper.insertSelective(order);

//添加主订单中的信息
        String ip = HttpGetIpUtil.getIpAddress(request);
       // User user = userMapper.selectByPrimaryKey(Long.parseLong(efOrder.getCreateUser()));
        com.pingplusplus.model.Order obj = pingUtil.createOrder(efOrder.getCreateUser(), order.getSn(), "7", "", "亿方商城订单",
                "shippingDescribe", Integer.parseInt(AmountUtils.changeY2F(order.getOrderAmount().toString())), ip);
        efOrder.setPingId(obj.getId());
        efOrder.setOrderNo(order.getSn());
        mainOrderMapper.insertSelective(efOrder);

//		if (itemList.isEmpty() )
//			throw new RuntimeException("创建订单失败，购物车为空");

       /* Integer orderId = this.daoSupport.getLastId("es_order");

        order.setOrder_id(orderId);*/
       System.out.println(order.getOrderId());
        Integer order_id =order.getOrderId(); //logMapper.lastOne();
        this.saveGoodsItem(itemList, order);


        /************ 写入订单日志 ************************/
        Order_log orderLog = new Order_log();
        orderLog.setMessage("订单创建");
        orderLog.setOpId(0);
        orderLog.setOpName(opname);
        orderLog.setOpTime(System.currentTimeMillis()/1000);
        orderLog.setOrderId(order_id);
        logMapper.insertSelective(orderLog);
       // this.orderPluginBundle.onAfterCreate(order,itemList, sessionid);
        //下单则自动改为已确认
      //  if(!order.getIsCod()){
            this.confirmOrder(order.getOrderId());
      //  }

       /* //只有b2c产品清空session
        if(EopSetting.PRODUCT.equals("b2c")){
            cartManager.clean(sessionid);
        }*/
        //增加应收

       //清理购物车中的被选择的物品
        if("1".equals(jiesuanType)){//1 代表购物车处理
            int count= goodsCartMapper.deleteChooseGoodsCart(user.getId());
        }

        /** 干掉用户优惠券 */
        if (StringUtils.isNotEmpty(efOrder.getTicketId()+"")&&!"0".equals(efOrder.getTicketId())) {
            Map<String,Object> mapTicket = new HashMap<>();
            mapTicket.put("userId",user.getId());
            mapTicket.put("ticketId",efOrder.getTicketId());
            userTicketMapper.updateUserTicketById(mapTicket);
        }
        return order;
    }
 //自动确认订单

    public void confirmOrder(Integer orderId) {
        if(orderId== null ) throw new  IllegalArgumentException("param orderId is NULL");
        EsOrder order = esOrderMapper.selectByPrimaryKey(orderId);
        User member = this.userMapper.selectByPrimaryKey(Long.parseLong(order.getMemberId()+""));



        /************ 写入订单日志 ************************/
        /*Order_log orderLog = new Order_log();
        orderLog.setMessage("订单ID为${orderId}，确认订单");
        orderLog.setOpId(0);
        orderLog.setOpName(opname);
        orderLog.setOpTime(System.currentTimeMillis()/1000);
        orderLog.setOrderId(order_id);
        logMapper.insertSelective(orderLog);*/

        //添加一条应收记录 20131110新增
        this.addPaymentIn(member, order);
        //esOrderMapper.(orderId, "订单确认");
        /*if(order.getNeedPayMoney()==0){
            this.payConfirm(order.getOrder_id());
        }*/
    }

    /**
     * 添加应收记录
     * @param member
     * @param order
     */
    private void addPaymentIn(User member,EsOrder order){

        PaymentLog paymentLog =  new PaymentLog();

        if(member!=null){
            paymentLog.setMember_id(member.getId().intValue());
            paymentLog.setPay_user(member.getRealname());
        }else{
            paymentLog.setPay_user("匿名购买者");
        }
        paymentLog.setMoney(order.getNeedPayMoney());
        paymentLog.setOrder_sn(order.getSn());
        paymentLog.setSn("");
        paymentLog.setPay_method(order.getPaymentName());
        paymentLog.setOrder_id(order.getOrderId());
        paymentLog.setType(PaymentLogType.receivable.getValue()); //应收
        paymentLog.setStatus(0);
        paymentLog.setCreate_time(DateUtil.getDateline());


            paymentLog.setAdmin_user(member.getRealname());
        esPaymentLogsMapper.insertSelective(paymentLog);

    }
    @Override
    public ActivityDetail selectByKey(Integer activity_id) {

        return  activityDetailMapper.selectByKey(activity_id);

    }

    @Override
    public List<OrderItemVo> orderList(Map map) {

        List<OrderItemVo> OrderItemVos=new ArrayList<>();

        OrderItemVos=esOrderMapper.orderList(map);



        //此处是该笔订单赠券信息
        List<ShopSendTicketVo> shopSendTicketVos = new ArrayList<>();

        for (OrderItemVo orderItemVo:OrderItemVos){
            ShopSendTicketVo shopSendTicketVo=new ShopSendTicketVo();
            shopSendTicketVo.setGoodsId(orderItemVo.getGoods_id());//商品id
            shopSendTicketVo.setPrice(orderItemVo.getPrice());//售价
            shopSendTicketVo.setReduction(orderItemVo.getReduction());
            shopSendTicketVo.setCredit(orderItemVo.getCredit());
            shopSendTicketVo.setCount(orderItemVo.getNum());
            shopSendTicketVos.add(shopSendTicketVo);
        }

        //查询ticket模板id
        List<Tickets> ticketsList = ticketsMapper.getTicketListIsShopSendTicket(Arrays.asList(shopticketId.split(",")));
        //调用赠券计算工具
        Map<String,Object> sendMap = SystemSendTicketUtils.shopSendTicket(ticketsList,shopSendTicketVos);

        List<CreditVo> creditVoList= (List<CreditVo>) sendMap.get("creditVoList");

        int i=0;//购物车里的商品有id一直  货品不一样的情况  通过项次对应

        for (OrderItemVo orderItemVo:OrderItemVos) {
            for( CreditVo creditVo:creditVoList){
                if(orderItemVo.getGoods_id()==creditVo.getGoodsId()){
                    orderItemVo.setCredit(creditVo.getCredit());
                    break;
                }
            }
            /*orderItemVo.setCredit(creditVoList.get(i).getCredit());
            i++;*/
        }
        return OrderItemVos;


    }

    @Override
    public List<EsOrderVo> userOrderList(Map map) {
        return esOrderMapper.userOrderList(map);
    }

    @Override
    public EsOrder createOrder(Integer add_id, Integer type_id, Integer ticket_id, Integer userId) {


        return null;
    }

    @Override
    public OrderPrice countPrice(List<CartItem> cartItemList, Integer shippingid, String regionid, Integer ticket_id) {
        OrderPrice orderPrice = new OrderPrice();

        //计算商品重量
        Double weight=0.0;

        //订单总价格
        Double  orderTotal = 0d;

        //配送费用
        Double dlyPrice = 0d;

        //优惠后的订单价格,默认为商品原始价格
        Double goodsPrice =0.0;

        if(cartItemList!=null && !cartItemList.isEmpty()){
            //计算商品重量及商品价格
            for (CartItem cartItem : cartItemList) {

                // 计算商品重量
                if("0".equals(cartItem.getCarriage())){//不免运费  就计算重量   免运费时按重量为0算  20180724  尹金星   考虑了免运费的情况
                    weight = CurrencyUtil.add(weight, CurrencyUtil.mul((cartItem.getWeight().doubleValue()), cartItem.getNum()));

                }



                //计算商品优惠后的价格小计
                Double itemTotal = CurrencyUtil.mul((cartItem.getPrice().doubleValue()), cartItem.getNum());
                goodsPrice=CurrencyUtil.add(goodsPrice, itemTotal);

            }
        }else{//如果购物车为空的情况下相当于立即提交，这时把山品单价价格和重量从外界传递过来
            // 计算商品重量
          //  weight = CurrencyUtil.add(weight, CurrencyUtil.mul((cartItem.getWeight().doubleValue()), cartItem.getNum()));


            //计算商品优惠后的价格小计
         //   Double itemTotal = CurrencyUtil.mul((cartItem.getPrice().doubleValue()), cartItem.getNum());
          //  goodsPrice=CurrencyUtil.add(goodsPrice, itemTotal);
        }


        //如果传递了配送信息，计算配送费用

        if(regionid!=null &&shippingid!=null ){
            if(shippingid!=0){
                //计算原始配置送费用
                weight=Math.ceil(weight/1000.0)*1000;
                Double[] priceArray = this.countPrice(shippingid, weight, goodsPrice, regionid);

                //费送费用
                dlyPrice = priceArray[0];
            }

        }


        //商品金额
        orderPrice.setGoodsPrice(goodsPrice);

        //配送费用
        orderPrice.setShippingPrice(dlyPrice);

        //订单总金额:商品金额+运费
        orderTotal = CurrencyUtil.add(goodsPrice, dlyPrice);

        //订单总金额
        orderPrice.setOrderPrice(orderTotal);


        //应付金额为订单总金额
        orderPrice.setNeedPayMoney(orderTotal);

        //订单总的优惠金额
        //orderPrice.setDiscountPrice( CurrencyUtil.add(0d,BonusSession.getUseMoney()));

        orderPrice.setNeedPayMoney(CurrencyUtil.add(orderTotal,-orderPrice.getDiscountPrice()));

        //如果优惠金额后订单价格小于0
        if(orderPrice.getNeedPayMoney()<=0){
            orderPrice.setNeedPayMoney(0d);
        }

        //订单可获得积分
        orderPrice.setPoint(0);

        //商品总重量
        orderPrice.setWeight(weight);

        //订单可获得的赠品ID add by DMRain 2016-1-15
        orderPrice.setGift_id(0);

        //订单可获得的优惠券ID add by DMRain 2016-1-19
        if(ticket_id!=null){
            orderPrice.setBonus_id(ticket_id);
        }


        return orderPrice;
    }

    @Override
    public SpeItemVo selectSpeItemVo(Integer goods_id) {
        return orderMapper.selectSpeItemVo(goods_id);
    }


    @Override
    public EsOrder selectByPrimaryKey(Integer orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateOrderStatus(Map map) {
        return orderMapper.updateOrderStatus(map);
    }

    @Override
    public List<Map<String, Object>> getList(Integer userId, Map map) {

        return null;
    }

    @Override
    public int orderCount(Map map) {
        return esOrderMapper.orderCounts(map);
    }

    @Override
    public int totalCount(Map map) {
        return esOrderMapper.totalCount(map);
    }

    @Override
    public EsOrderVo getEsOrderBySn(String orderNo) {
        return esOrderMapper.getEsOrderBySn(orderNo);
    }
}
