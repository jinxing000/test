package com.ef.golf.action;


import com.alibaba.fastjson.JSON;
import com.ef.golf.common.Constant;
import com.ef.golf.common.mob.MobPushUtil;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.*;
import com.ef.golf.service.*;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.util.SmsUtil;
import com.ef.golf.vo.CashBackVo;
import com.pingplusplus.model.BalanceBonus;
import com.pingplusplus.model.Recharge;
import com.pingplusplus.model.Transfer;
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
 * 支付成功状态
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RechargeSuccessAction extends AbstractService {

    private String rechargeId;
    private String state;

    @Resource
    private UserService userService;
    @Resource
    private IntegralRecordService integralRecordService;
    @Resource
    private IntegralService integralService;
    @Resource
    private CashBackService cashBackService;
    @Resource
    private FlowService flowService;
    @Resource
    private AccountService accountService;
    @Resource
    private RechargeService rechargeService;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;
    @Resource
    private RewardService rewardService;
    @Resource
    private InfromMsgService infromMsgService;
    @Autowired
    private FirstRechargeService firstRechargeService;
    @Autowired
    private HttpServletRequest request;

    @Value("${systemTitle}")
    private String systemTitle;
    @Value("${systemContent}")
    private String systemContent;
    @Value("${urlHead}")
    private String urlHead;
    @Value("${balanceRecord}")
    private String balanceRecord;
    @Value("${awardMoney}")
    private String awardMoney;
    @Value("${templateId.awardMoney2}")
    private String templateIdAwardMoney2;

    /**
     * description="1" 普通充值 ="2" 充值转账
     */

    public Object doService() throws SystemException, InterruptedException {


        if(StringUtils.isBlank(state)){
            return 0;
        }

        System.out.println("===============");
        Map<String, Object> map = new HashMap<>();
        try {
            Recharge recharge = Recharge.retrieve(rechargeId);
            Integer userId = Integer.valueOf(recharge.getUser());

            System.out.println(recharge.getDescription()+"==============="+recharge.getSucceeded());
            if (!recharge.getDescription().equals("2")) {
                if (recharge.getSucceeded()) {

                    /** 充值处理 */
                    /** 查询用户信息 */
                    User user = userService.selectByPrimaryKey(Long.valueOf(recharge.getUser()));
                    String recExclusiveNo = user.getRecExclusiveNo();
                    /** 获取专属码所属用户信息   */
                    User user1 = null;
                    if (StringUtils.isNotBlank(recExclusiveNo)) {
                        user1 = userService.getUserMsgByExclusiveNo(recExclusiveNo);
                    }
                    Double amount=0.0;
                    if (userId != null) {

                        //判断正在充值金额加当天已充金额在那个区间按区间比例赠送  查询当天累计充值金额 查询当天充值赠送金额
                        /** 充值的金额 */
                        amount = Double.valueOf(AmountUtils.changeF2Y(recharge.getAmount().toString()));
                        System.out.println("amount"+"====================================="+amount);
                        /** 查询当天已充值的钱     已充值=之前的+ 本次冲的钱*/
                        Double toDayMoney = rechargeService.getTodayRechargeMoney(userId);//充值记录表
                        System.out.println("toDayMoney"+"================================"+toDayMoney);
                        /** 查询当天充值赠送的钱 */
                        Double toDayZsMoney = flowService.getTodayZenSongMoney(userId);//流水表
                        System.out.println("toDayZsMoney"+"================================"+toDayZsMoney);
                        System.out.println("amount"+"================================"+amount);
                        System.out.println("toDayMoney"+"================================"+toDayMoney);
                        /** 查询充值赠送比例 */
                        List<CashBackVo> list = cashBackService.getCashBackScaleVo();

                        /** 如果等于1  首冲 按照首冲比例赠送 */
                        int rechargeCount = rechargeService.selectRechargeCountByUserId(userId);
                        /** 首充规则 */
                        if(1==rechargeCount&&amount<list.get(0).getMaxnum()){
                            List<FirstRecharge>frlist = firstRechargeService.selectAllFirstRecharge();
                            Double giveMoney = 0.0;
                            if(null!=frlist&&0<frlist.size()) {
                                for (FirstRecharge fr : frlist) {
                                    if (fr.getMaxNum() >= amount && amount >= fr.getMinNum()) {
                                        if ("0".equals(fr.getType())) {/** 是0 按照固定值赠送 */
                                            giveMoney = fr.getRate() * 1.0;
                                        } else {
                                            giveMoney = (amount * (fr.getRate() / 100.0));
                                        }
                                        System.out.println("giveMoney======================" + giveMoney);
                                    }
                                    if (giveMoney < 1) {
                                        continue;
                                    } else {
                                        String order_no = orderNoUtil.serialNoGenerate("05", user.getPhone());
                                        BalanceBonus balanceBonus1 = pingUtil.balanceBonus
                                                (Integer.valueOf(AmountUtils.changeY2F(giveMoney + "")), userId + "", order_no, "首充");
                                        if (!balanceBonus1.getPaid()) {
                                            throw new SystemException(Constant.ERR_GIVEMONEY);
                                        } else {
                                            /** 1充值 2提现 3赠送 4 奖励  */
                                            Account account = accountService.getUserBalance(userId);
                                            //赠送流水
                                            Flow flow1 = new Flow();
                                            flow1.setUserId(userId);
                                            flow1.setMoney(giveMoney);
                                            flow1.setSequenceNumber(recharge.getCharge().getOrderNo());
                                            flow1.setFlowType("4444");
                                            flow1.setCreateTime(new Date());
                                            /** 余额 */
                                            flow1.setBalance(account.getZsBalance() + giveMoney);
                                            accountService.updateUserZsBalance(userId, account.getZsBalance() + giveMoney);
                                            flowService.insertSelective(flow1, "BalanceBonus");
                                        }
                                    }
                                }
                            }
                        }else{

                            for (CashBackVo cbv:list) {
                                //如果当前充值金额加当天充的介于minnum和maxnum之间赠送

                            /*if(cbv.getMaxnum()>(amount+toDayMoney)&&(amount+toDayMoney)>=cbv.getMinnum()){*/
                                if(cbv.getMaxnum()>=(toDayMoney)&&(toDayMoney)>=cbv.getMinnum()){
                                    System.out.println("=====赠送==================="+cbv.getMinnum()+"======="+cbv.getMaxnum()+"======"+cbv.getRate());
                                /*Double giveMoney =(amount+toDayMoney)*(cbv.getRate()/100.0);*/
                                    Double giveMoney =(toDayMoney)*(cbv.getRate()/100.0);//总的应赠送
                                    System.out.println("giveMoney"+"================================"+giveMoney);
                                    if(giveMoney<1){
                                        continue;//break会更好吧                                 ？
                                    }
                                    Double pingGiveMoney = giveMoney-toDayZsMoney;
                                    if(pingGiveMoney>=1){

                                    System.out.println("pingGiveMoney"+"================================"+pingGiveMoney);
                                    String order_no = orderNoUtil.serialNoGenerate("05", user.getPhone());

                                    BalanceBonus balanceBonus1 = pingUtil.balanceBonus
                                            (Integer.valueOf(AmountUtils.changeY2F(pingGiveMoney+"")), userId + "", order_no, "本人赠送描述");

                                    System.out.println("======赠送状态=======" + balanceBonus1.getPaid());
                                    if (!balanceBonus1.getPaid()) {
                                    /*map.put("message", "本人赠送金额异常");
                                    return map;*/
                                        throw new SystemException(Constant.ERR_GIVEMONEY);
                                    } else {
                                        /** 1充值 2提现 3赠送 4 奖励  */
                                        Account account = accountService.getUserBalance(userId);
                                        //赠送流水
                                        Flow flow1 = new Flow();
                                        flow1.setUserId(userId);
                                        flow1.setMoney(pingGiveMoney);
                                        flow1.setSequenceNumber(recharge.getCharge().getOrderNo());
                                        flow1.setFlowType("4444");
                                        flow1.setCreateTime(new Date());
                                        /** 余额 */
                                        flow1.setBalance(account.getZsBalance() + pingGiveMoney);
                                        accountService.updateUserZsBalance(userId,account.getZsBalance()+pingGiveMoney);
                                        flowService.insertSelective(flow1,"BalanceBonus");
                                    }
                                    System.out.println("=============" + balanceBonus1.getPaid());
                                }
                                }
                            }
                        }

                       /* Double zMoneyY2F = ZengSongUtil.zengSong(Integer.valueOf(AmountUtils.changeF2Y(recharge.getAmount()+"")),toDayMoney,list);*/
                                /*String zMoneyY2F = AmountUtils.changeY2F(money + "");*/
                                if (user1 != null) {/** user1为绑定专属码人 */
                                    System.out.println("=====绑定人奖励======");
                                    System.out.println();
                                    /** 绑定人奖励金额 收入金额-->转账*/
                                    /** 查询绑定人奖励比例 根据判定人用户类型计算转账金额 */
                                    List<Reward> reward = rewardService.getAllReward(user1.getUserType());
                                    System.out.println("======================"+reward.get(0).getReward());
                                    Double jMoney = (Integer.valueOf(reward.get(0).getReward())/10000.0) * amount;
                                    System.out.println("==========="+jMoney);
                                    String jMoneyY2F = AmountUtils.changeY2F(jMoney + "");
                                    System.out.println("分--元"+jMoney+"======"+jMoneyY2F+"=======");
                                    /** 查询平台余额是否充足 */
                                    com.pingplusplus.model.User user2 = pingUtil.userBalacnce("0");
                                    System.out.println("======================     =============================" + user2.getAvailableBalance());
                                    System.out.println("=================================================================");
                                    System.out.println(user1.getUserType());
                                    System.out.println(user2.getAvailableBalance() > Integer.valueOf(jMoneyY2F));
                                    System.out.println(Integer.valueOf(jMoneyY2F)>=1);
                                    if(Integer.valueOf(jMoneyY2F)>=1){

                                        if (user2.getAvailableBalance() > Integer.valueOf(jMoneyY2F)) {

                                            String jOrderNo = orderNoUtil.serialNoGenerate("06", user1.getPhone());
                                            System.out.println("==================="+jMoneyY2F);
                                            Transfer transfer = pingUtil.transferAccounts(Integer.valueOf(jMoneyY2F),"balance",jOrderNo,user1.getId().toString(),"5");
                                            /** 1充值 2提现 3赠送 4 奖励  */
                                            Account account = accountService.getUserBalance(userId);
                                            //赠送流水
                                            Flow flow1 = new Flow();

                                            flow1.setUserId(user1.getId().intValue());
                                            flow1.setMoney(jMoney);
                                            flow1.setSequenceNumber(transfer.getOrderNo());
                                            flow1.setFlowType("6666");
                                            flow1.setCreateTime(new Date());
                                            /** 余额 */
                                            flow1.setBalance(account.getSrBalance() + jMoney);
                                            flowService.insertSelective(flow1,"(balance)qyf");


                                            Map<String,Object>mobMap = new HashMap<>();
                                            mobMap.put("title",systemTitle);
                                            mobMap.put("content",systemContent);
                                            mobMap.put("url",urlHead+balanceRecord);
                                            MobPushUtil.MobPush(2,systemContent,new String[]{user1.getId().toString()},"1", JSON.toJSONString(mobMap));
                                            InfromMsg infromMsg = new InfromMsg(null,user1.getId(),"0","8", "23", user1.getId(), new Date(),
                                                    new Date(),user1.getId().toString(),user1.getId().toString(),systemTitle,systemContent,urlHead+balanceRecord,awardMoney);
                                            infromMsgService.insert(infromMsg);
                                            /** 发短信 */
                                            SmsUtil.sendSMS(user1.getPhone(),templateIdAwardMoney2);
                                            if("failed".equals(transfer.getStatus())){
                                                map.put("message", "奖励异常!");
                                            }
                                        }
                                    }
                                }
                    }
                    if (!"0".equals(userId)) {
                        System.out.println("=============" +"积分处理"+"=============================================");
                        /** 获取用户总积分 */
                        Integer totalScore = integralService.getUserTotalScore(userId);
                        /** 获取积分变动比例 */
                        IntegralRule integralRule = integralService.selectRuleByType("1");
                        /** 充值积分记录 */
                        IntegralRecord integralRecord = new IntegralRecord();
                        integralRecord.setAlterationNote("充值送积分");
                        integralRecord.setUserId(Long.valueOf(userId));
                        integralRecord.setScore("+"+(amount*(integralRule.getProportion()/100.0)));
                        System.out.println(amount*(integralRule.getProportion()/100.0));
                        integralRecord.setCreateTime(new Date());
                        integralRecordService.insertSelective(integralRecord);
                        /** 更新用户总积分 */
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("scoreTotal",((amount*(integralRule.getProportion()/100.0))+totalScore)+"");
                        map1.put("userId", userId);
                        map1.put("modifyTime", new Date());
                        int i = integralService.updateUserTotalScore(map1);
                        System.out.println("积分结束===="+i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(Constant.ERR_UNKNOW);
        }
        return map;
    }

    public void setRechargeId(String rechargeId) {
        this.rechargeId = rechargeId;
    }


    public void setState(String state) {
        this.state = state;
    }
}


