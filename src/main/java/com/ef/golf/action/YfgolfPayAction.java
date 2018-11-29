package com.ef.golf.action;

import com.alibaba.fastjson.JSON;
import com.ef.golf.aspectj.Syslog;
import com.ef.golf.common.Constant;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.DemandException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.*;
import com.ef.golf.vo.MineVo;
import com.pingplusplus.model.BalanceTransfer;
import com.pingplusplus.model.Recharge;
import com.pingplusplus.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2018年5月3日
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class YfgolfPayAction extends AbstractService {

    //
    //(1球童打赏 2教练结算 3商家 4愿望 8课程单次费用(由0账户发起转账到教练账户) 9教练陪打费（由0账户发起转账到教练账户)10红包(看交易汇总表)
    private String channel;//支付方式 = "balance"
    private String uid;
    private String sid;
    private String recipient;//收款者
    private String type;//类型（1球童 2教练结算(结算) 3商家 4愿望 5课程 6红包(该接口不会用到) 7教练预约(预约) 8赠送(该接口不会用到)
    private String money;//分
    private String description;//"2" 充值转账
    private String hopeId;//愿望id
    private String ticketId;//优惠券id (商家结算需要)
    private String oldMoney="0";//原始金额(商家结算需要)(分)

    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private UserService userService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private AccountService accountService;
    @Resource
    private SystemPayService systemPayService;
    @Resource
    private DebitRecordService debitRecordService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private HopeRecordService hopeRecordService;
    @Resource
    private HopeService hopeService;
    @Resource
    private UserTicketService userTicketService;
    @Resource
    private MerchantOrderService merchantOrderService;
    @Resource
    private TicketService ticketService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private InfromMsgService infromMsgService;
    @Autowired
    private RedisBaseDao redisBaseDao;
    @Autowired
    private CaddieRewardUtil caddieRewardUtil;

    @Value("${urlHead}")
    private String urlHead;
    @Value("${wishDetails}")
    private String wishDetails;
    @Value("${hopeTitle}")
    private String hopeTitle;
    @Value("${hopeContent}")
    private String hopeContent;
    @Value("${wish}")
    private String wish;
    @Value("${hopeComeTrue}")
    private String hopeComeTrue;

    String userId = "";
    /**
     * 教练预约 课程预约 支付成功改状态? 区分balance、wx、alipay? 余额还是流水插入orderId? 交易记录更改?
     *
     * 退款（余额退款） 余额退款需要(orderId(获取实付金额),支付人id,企业付退款,本地余额退回原有账户(sr,cz,zs))
     *
     *     (充值退款)  调ping++充值退款原路退回 本地cz余额减少 orderId存哪儿？
     * */
    @Override
    public Object doService() throws Exception {

        Map<String,Object>map = new HashMap<>();
        Integer id = redisLoginVerifyUtil.longinVerifty(sid,uid);
        userId = id+"";


        if("1".equals(type)){
            if(!caddieRewardUtil.caddieReward(userId,recipient)){
                throw new SystemException(Constant.ERR_CADDIE_REWARD_MAX);
            }
            caddieRewardUtil.setCaddieRewardMap(userId,recipient);
        }


        /** 获取下用户信息 */
        MineVo mineVo = userService.getInfo(Integer.valueOf(userId));
        /** 查询当前用户余额 */
        User user = pingUtil.userBalacnce(userId+"");
        long userBalance = user.getAvailableBalance();

        /** 判断支付方式 是余额 */
        if("balance".equals(channel)){
            /** 余额足够 调转账接口*/
            if(userBalance >= Integer.valueOf(money)){
                String balanceOrderNo = orderNoUtil.serialNoGenerate("06",mineVo.getPhone());
                Account userAccount = accountService.getUserBalance(Integer.valueOf(userId));
                if((userAccount.getSrBalance()+userAccount.getCzBalance())<(Double.valueOf(AmountUtils.changeF2Y(money)))){
                    map.put("status",1);
                    map.put("message","当前余额仅可用于订场、商城购物!");
                   throw new SystemException(Constant.ERR_BONUSES_BANLACE);
                }
                /** 价格校验 */
                if("3".equals(type)) {

                    if ("0".equals(oldMoney)) {
                        oldMoney = money;
                    }
                    Integer realPrice = Integer.valueOf(oldMoney);
                    if (StringUtils.isNotEmpty(ticketId) && !"0".equals(ticketId)) {
                        UserTicket userTicket = userTicketService.selectByPrimaryKey(ticketId);
                        Tickets tickets = ticketService.selectByPrimaryKey(userTicket.getTicketId());//优惠券详情*/
                            if (userTicket == null || tickets == null)
                                throw new QueryException(Constant.ERR_QUERY - 4);
                            if (userTicket.getState() != 2&&userTicket.getState()!=3)
                                throw new DemandException(Constant.ERR_DEMAND - 2);
                            //判断优惠券的归属  1  2
                            if (userTicket.getLocation() != 1 && userTicket.getLocation() != 7)
                                throw new DemandException(Constant.ERR_DEMAND - 3);
                            if (tickets.getType() == 1) {/** 折扣 */
                                Double yuan = Double.valueOf(AmountUtils.changeF2Y(oldMoney)) * (tickets.getDiscount() / 100.0);

                            if (tickets.getDiscountMoney()!=null){
                                    if((Double.valueOf(AmountUtils.changeF2Y(oldMoney))-yuan)>=tickets.getDiscountMoney()){
                                        yuan = (Double.valueOf(AmountUtils.changeF2Y(oldMoney)))-tickets.getDiscountMoney();
                                    }
                            }
                            if(yuan<=0.01){
                                yuan=0.01;
                            }
                            realPrice = Integer.valueOf(AmountUtils.changeY2F(yuan + ""));
                        }
                            if (tickets.getType() == 2) { /** 满减 */
                                Double yuan = Double.valueOf(AmountUtils.changeF2Y(oldMoney)) - tickets.getSpecialMoney();
                                if(yuan<=0.01){
                                    yuan=0.01;
                                }

                                realPrice = Integer.valueOf(AmountUtils.changeY2F(yuan + ""));
                            }
                    }
                        if (!money.equals(realPrice + "")) {
                            throw new SystemException(Constant.ERR_PRICE);
                        }

                }
                if(!"4".equals(type)) {
                    try{
                        /** 转账 */
                        BalanceTransfer balanceTransfer =
                                pingUtil.balanceTransfer(Integer.valueOf(money), "余额支付", userId, recipient, balanceOrderNo);
                        if ("succeeded".equals(balanceTransfer.getStatus())) {
                            /** 账户转出记录 余额更新 */
                            insertDebitRecord(balanceTransfer);
                            /** 转账记录 */
                            insertSystem_pay(balanceTransfer);
                            map.put("status", 0);
                            map.put("message", "支付完成");
                        } else {
                            map.put("status", 1);
                            map.put("message", "支付失败");
                        }
                    }catch (Exception e){
                        throw new SystemException(Constant.ERR_UNKNOW);
                    }
                }
                /**
                 * 以上为余额除愿望之外支付
                 * 以上为余额愿望支付
                 * */
                if(StringUtils.isNotBlank(hopeId)&&"4".equals(type)){ //愿望余额支付
                    /** 查询愿望信息 */
                    Hope hope = hopeService.selectByPrimaryKey(Integer.valueOf(hopeId));
                    System.out.println("hope"+hope);
                    /** 转账 */

                    try{

                        BalanceTransfer balanceTransfer = pingUtil.balanceTransfer(Integer.valueOf(money), "愿望转账支付", userId,"0", orderNoUtil.serialNoGenerate("06", uid));

                        System.out.println("sdsdssdsdsdsdsdsds======="+balanceTransfer.getStatus());
                        if("succeeded".equals(balanceTransfer.getStatus())){
                            int i = hopeRecordService.insertSelective(
                                    new Hope_record(null, Integer.valueOf(userId), Integer.valueOf(hopeId)
                                            , Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount() + "")), "2", new Date(), null, null, null));

                            System.out.println("《《================容联云愿望推送消息开始================》》");
                            String receiver[] = {balanceTransfer.getRecipient()};
                            Map<String,Object>info = new HashMap<>();
                                info.put("type","1");
                                info.put("title","我支持了你的愿望");
                                info.put("subtitle","为你构筑愿望"+Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+""))+"元");
                                info.put("wishId",hopeId);
                            ServicePushUtils.servicePush(balanceTransfer.getUser(),"101",receiver,info.toString(),null);
                            System.out.println("《《================容联云愿望推送消息结束================》》");

                            /** 更新愿望已实现金额 */
                            hope.setHopeRealMoney(hope.getHopeRealMoney()+Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")));
                            if(hope.getHopeRealMoney() >= hope.getHopeMoney()){
                                    hope.setHopeState("2");
                                    hope.setGetStauts("0");
                                    hope.setRealizeTime(new Date());

                                /** 愿望实现发短信、推送 */
                                Map<String,Object>mobMap = new HashMap<>();
                                mobMap.put("title",hopeTitle);
                                mobMap.put("content",hopeContent);
                                mobMap.put("url",urlHead+wishDetails+hopeId);
                                MobPushUtil.MobPush(2,hopeContent,new String[]{hope.getUserId()+""},"1",JSON.toJSONString(mobMap));
                                InfromMsg infromMsg = new InfromMsg(null,Long.valueOf(hope.getUserId()),"0","12", "0", Long.valueOf(hopeId), new Date(),
                                        new Date(),"sys",hope.getUserId()+"",hopeTitle,hopeContent,urlHead+wishDetails+hopeId,wish);
                                infromMsgService.insert(infromMsg);
                                MineVo hopeUser = userService.getInfo(hope.getUserId());
                                SmsUtil.sendSMS(hopeUser.getPhone(),hopeComeTrue);


                                List<Integer> userIds = userRoleService.selectUserIdByRoleId("12");
                                for (Integer goEasy: userIds
                                        ) {
                                    GoEasyUtil.pushMessage(goEasy+"","有用户愿望已实现,请及时处理!");
                                }
                            }
                            hopeService.updateByPrimaryKeySelective(hope);

                            /** 账户转出记录 余额更新 */
                            insertDebitRecord(balanceTransfer);
                            /** 转账记录 */
                            insertSystem_pay(balanceTransfer);
                            map.put("status",0);
                            map.put("message","支付完成");
                        }else{
                            map.put("status",1);
                            map.put("message","支付失败");
                        }
                    }catch (Exception e){
                        throw new SystemException(Constant.ERR_BONUSES_BANLACE);
                    }
                }
            }else{
                map.put("status",1);
                map.put("message","余额不足");
                throw new SystemException(Constant.ERR_NoBalance);
            }

        }
        /** 判断支付方式 不是余额 充值到当前用户余额 */
        if("alipay".equals(channel)||"wx".equals(channel)||"upacp".equals(channel)){
            String clientIp= HttpGetIpUtil.getIpAddress(request);
            String orderNo = orderNoUtil.orderNoGenerate("01",uid);
            System.out.println("=======充值开始生产本地订单========="+orderNo);
            String subject="";
            long merchantOrderId = 0;
            // type;//类型（1球童 2教练结算(结算) 3商家 4愿望 5课程 6红包(该接口不会用到) 7教练预约(预约) 8赠送(该接口不会用到)

            switch (type) {
                case "1":
                    subject="球童打赏";
                    break;
                case "2":
                    subject = "教练结算";
                    break;
                case "3":
                    subject = "商家买单";
                    break;
                case "4":
                    subject = "支持愿望";
                    break;
            }

            if("3".equals(type)){

                if("0".equals(oldMoney)){
                    oldMoney=money;
                }
                Integer realPrice=Integer.valueOf(oldMoney);
                if (StringUtils.isNotEmpty(ticketId)&&!"0".equals(ticketId)) {

                    UserTicket userTicket = userTicketService.selectByPrimaryKey(ticketId);

                    Tickets tickets = ticketService.selectByPrimaryKey(userTicket.getTicketId());//优惠券详情*/
                    if(userTicket==null||tickets == null)
                        throw new QueryException(Constant.ERR_QUERY - 4);
                    if (userTicket.getState() != 2&&userTicket.getState()!=3)
                        throw new DemandException(Constant.ERR_DEMAND - 2);
                    //判断优惠券的归属  1  2
                    if(userTicket.getLocation()!=1&&userTicket.getLocation()!=7)
                        throw new DemandException(Constant.ERR_DEMAND - 3);
                    if(tickets.getType()==1){/** 折扣 */
                    Double yuan = Double.valueOf(AmountUtils.changeF2Y(oldMoney))*(tickets.getDiscount()/100.0);
                        if(yuan<=0.01){
                            yuan=0.01;
                        }
                        realPrice = Integer.valueOf(AmountUtils.changeY2F(yuan+""));
                       /* realPrice = Integer.valueOf(AmountUtils.changeF2Y(Integer.valueOf(oldMoney)+"")) * (tickets.getDiscount()/100.0);*/
                    }
                    if(tickets.getType()==2){/** 满减 */
                    Double yuan = Double.valueOf(AmountUtils.changeF2Y(oldMoney))-tickets.getSpecialMoney();
                        if(yuan<=0.01){
                            yuan=0.01;
                        }
                        realPrice = Integer.valueOf(AmountUtils.changeY2F(yuan+""));
                        /*realPrice = Integer.valueOf(oldMoney) - tickets.getSpecialMoney();*/
                    }
                }

                //确定价格
                /*DecimalFormat df = new DecimalFormat("#.00");
                String m1 = df.format(realPrice);
                String m2 = df.format(money);*/
                if(!money.equals(realPrice+"")){
                    throw new SystemException(Constant.ERR_PRICE);
                }
                /** 此处商家结算调用ping++ Balance Settlements 余额结算 结算更好*/
                MerchantOrder merchantOrder = new MerchantOrder();
                    merchantOrder.setCreateUser(Long.valueOf(userId));
                    merchantOrder.setPayMoney(Double.valueOf(AmountUtils.changeF2Y(realPrice.toString())));
                    if(null!=ticketId&&!"".equals(ticketId)&&!"0".equals(ticketId)){
                        merchantOrder.setTicketId(Long.valueOf(ticketId));
                        userTicketService.updateUserTicketById(Integer.valueOf(userId), Integer.valueOf(ticketId));
                    }
                    MineVo mineVo2 = userService.getInfo(Integer.valueOf(userId));
                    merchantOrder.setModifyTime(new Date());
                    merchantOrder.setCreateTime(new Date());
                    merchantOrder.setContactsPhone(mineVo2.getPhone());
                    merchantOrder.setMerchantId(Long.valueOf(recipient));
                    merchantOrder.setOldMoney(Double.valueOf(AmountUtils.changeF2Y(oldMoney)));

                    merchantOrder.setDiscountsMoney(Double.valueOf(AmountUtils.changeF2Y(oldMoney))-Double.valueOf(AmountUtils.changeF2Y(money)));
                    merchantOrder.setMerchantName(mineVo2.getNickName());
                    merchantOrder.setModifyUser(userId);
                    merchantOrder.setOrderNo(orderNo);
                merchantOrderService.insertSelective(merchantOrder);
                merchantOrderId = merchantOrder.getMerchantOrderId();

            }
            /** 业务需要在webhooks通知完成 借用ping++充值接口body参数 */
            Map<String,Object>bodyMap = new HashMap<>();
            if("2".equals(description)){
                if(!"".equals(hopeId)&&null!=hopeId){
                    bodyMap.put("recipient",recipient);
                    bodyMap.put("uid",uid);
                    bodyMap.put("hopeId",hopeId);
                }else{
                    bodyMap.put("recipient",recipient);
                    bodyMap.put("uid",uid);
                }
            }
            bodyMap.put("type",type);
            if("3".equals(type)){  //商家买单 支付成功再写入支付方式  如果支付方式为空未支付
                bodyMap.put("oldMoney",Double.valueOf(AmountUtils.changeF2Y(oldMoney)));
                bodyMap.put("merchantOrderId",merchantOrderId);
            }
            String body = JSON.toJSONString(bodyMap);
            Recharge recharge = pingUtil.recharge(userId,description,Integer.valueOf(money),channel,orderNo,clientIp,subject,body);

                return recharge;
        }
        return map;
    }
    /** 转账记录 */
     protected void insertSystem_pay(BalanceTransfer balanceTransfer) throws Exception {

         Flow flow = new Flow();
             flow.setBalance(0.0);
             flow.setCreateTime(new Date());
             flow.setFlowType(type);
             flow.setSequenceNumber(balanceTransfer.getOrderNo());
             flow.setMoney(Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")));
             flow.setFlowId(null);
         flow.setUserId(Integer.valueOf(balanceTransfer.getUser()));
         /** 转账记录 */
         System_pay systemPay = new System_pay();
             systemPay.setUserId(Integer.valueOf(balanceTransfer.getUser()));
             systemPay.setTakeId(Integer.valueOf(balanceTransfer.getRecipient()));
             /** 分转元 */
             String moneyF2Y = AmountUtils.changeF2Y((long)balanceTransfer.getAmount());
             systemPay.setMoney(Double.valueOf(moneyF2Y));
             systemPay.setCreateTime(new Date());
             systemPay.setModifyTime(new Date());
             systemPay.setType(type);
             systemPay.setState(balanceTransfer.getStatus().equals("succeeded")?"1":"2");
             systemPay.setOrderNo(balanceTransfer.getOrderNo());
             systemPay.setChannel(channel);
         systemPayService.insertSelective(systemPay,balanceTransfer.getOrderNo(),Double.valueOf(AmountUtils.changeF2Y(oldMoney)));
         System.out.println("========================================================"+systemPay.getSysPayId());
         if("3".equals(type)) {
             MerchantOrder merchantOrder = new MerchantOrder();
                 merchantOrder.setCreateUser(Long.valueOf(balanceTransfer.getUser()));
                 merchantOrder.setPayMoney(Double.valueOf(AmountUtils.changeF2Y(money + "")));
                 if (null != ticketId && !"".equals(ticketId)) {
                     merchantOrder.setTicketId(Long.valueOf(ticketId));
                     userTicketService.updateUserTicketById(Integer.valueOf(userId), Integer.valueOf(ticketId));
                 }
                 MineVo mineVo = userService.getInfo(Integer.valueOf(balanceTransfer.getUser()));
                 merchantOrder.setModifyTime(new Date());
                 merchantOrder.setCreateTime(new Date());
                 merchantOrder.setContactsPhone(mineVo.getPhone());
                 merchantOrder.setMerchantId(Long.valueOf(balanceTransfer.getRecipient()));
                 merchantOrder.setOldMoney(Double.valueOf(AmountUtils.changeF2Y(oldMoney + "")));
                 merchantOrder.setDiscountsMoney(Double.valueOf(AmountUtils.changeF2Y(oldMoney + "")) - Double.valueOf(AmountUtils.changeF2Y(money + "")));
                 merchantOrder.setMerchantName(mineVo.getNickName());
                 merchantOrder.setModifyUser(balanceTransfer.getUser());
                 merchantOrder.setOrderNo(balanceTransfer.getOrderNo());
                 merchantOrder.setChannel(channel);
                 merchantOrderService.insertSelective(merchantOrder);
             GoEasyUtil.pushMessage(merchantOrder.getMerchantId().toString(),"账户收入"+Double.valueOf(AmountUtils.changeF2Y(oldMoney + ""))+"元");
         }
     }
    /** 账户转出记录 余额更新 */
    protected void insertDebitRecord(BalanceTransfer balanceTransfer) throws Exception {
        /** 转出方账户详细 */
        Account zcAccount = accountService.getUserBalance(Integer.valueOf(userId));
        /** 接收方账户详细 */
        Account jsAccount = accountService.getUserBalance(Integer.valueOf(recipient));
        /** 转款金额 */

        Double F2YPayMoney = Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+""));
        DebitRecord debitRecord = new DebitRecord();
        if(zcAccount.getCzBalance()>=F2YPayMoney){//充值余额
                debitRecord.setCzBalance(F2YPayMoney);
            /** 更新转出方预存余额... */
            accountService.updateUserCzBalance(Integer.valueOf(userId),zcAccount.getCzBalance()-F2YPayMoney);
        }else if (zcAccount.getCzBalance()<F2YPayMoney&&zcAccount.getCzBalance()+zcAccount.getSrBalance()>=F2YPayMoney){//充值余额-->充值余额+收入余额

                debitRecord.setCzBalance(zcAccount.getCzBalance());
                debitRecord.setSrBalance(F2YPayMoney-zcAccount.getCzBalance().intValue());
            /** 更新转出方预存、收入余额 */
            accountService.updateUserCzBalance(Integer.valueOf(userId),0.00);
            accountService.updateUserSrBalance(Integer.valueOf(userId),zcAccount.getCzBalance()+zcAccount.getSrBalance()-F2YPayMoney);
        }else if (zcAccount.getCzBalance()<F2YPayMoney&&zcAccount.getCzBalance()+zcAccount.getSrBalance()<F2YPayMoney&&
                zcAccount.getCzBalance()+zcAccount.getSrBalance()+zcAccount.getZsBalance()>=F2YPayMoney){//充值余额-->充值余额+收入余额-->充值余额+收入余额+赠送余额

                debitRecord.setCzBalance(zcAccount.getCzBalance());
                debitRecord.setSrBalance(zcAccount.getSrBalance());
                debitRecord.setZsBalance(F2YPayMoney-zcAccount.getCzBalance().intValue()-zcAccount.getSrBalance().intValue());
            /** 更新转出方预存、收入、赠送余额 */
            accountService.updateUserCzBalance(Integer.valueOf(userId),0.0);
            accountService.updateUserSrBalance(Integer.valueOf(userId),0.0);
            accountService.updateUserZsBalance(Integer.valueOf(userId),zcAccount.getCzBalance()+zcAccount.getSrBalance()+zcAccount.getZsBalance()-F2YPayMoney);
        }
        /** 更新接收方收入余额 */
        if("3".equals(type)){
            accountService.updateUserSrBalance(Integer.valueOf(recipient),jsAccount.getSrBalance()+Double.valueOf(AmountUtils.changeF2Y(oldMoney)));
        }else if(!"4".equals(type)){
            accountService.updateUserSrBalance(Integer.valueOf(recipient),jsAccount.getSrBalance()+F2YPayMoney);
        }
            debitRecord.setAccountId(zcAccount.getAccountId());
            debitRecord.setUserId(Integer.valueOf(userId));
            debitRecord.setPayForm("1");
            debitRecord.setCreateTime(new Date());
            debitRecord.setPingId(balanceTransfer.getOrderNo());
            debitRecord.setChannel(channel);
        /** 保存此次消费记录 */
        debitRecordService.insertSelective(debitRecord);
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHopeId(String hopeId) {
        this.hopeId = hopeId;
    }
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setOldMoney(String oldMoney) {
        this.oldMoney = oldMoney;
    }

}
