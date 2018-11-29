package com.ef.golf.service.impl;

import com.ef.golf.common.Constant;
import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.exception.SystemException;
import com.ef.golf.mapper.*;
import com.ef.golf.pojo.*;
import com.ef.golf.service.RedPackageService;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.OrderNoUtil;
import com.ef.golf.vo.MineVo;
import com.ef.golf.vo.QianRedPackageListVo;
import com.ef.golf.vo.RedPackageDetailsVo;
import com.ef.golf.vo.RedPackageRefundVo;
import com.pingplusplus.model.BalanceTransfer;
import com.pingplusplus.model.Transfer;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/5/8 9:58
 */
@Repository
public class RedPackageServiceImpl implements RedPackageService {

    @Resource
    private RedPackageMapper redPackageMapper;
    @Resource
    private SmallRedPackageMapper smallRedPackageMapper;
    @Resource
    private System_payMapper systemPayMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private DebitRecordMapper debitRecordMapper;
    @Resource
    private JiaoYiHuizongMapper jiaoYiHuizongMapper;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private OrderNoUtil orderNoUtil;


    @Override//发红包  写入大红包记录   更改发送红包者的余额   小红包记录需要加入吗？暂不处理
    public Map<String,Object> sendRedPackage(RedPackage redPackage, List<SmallRedPackage> smallRedPackageList, String uid) throws SystemException {
        Map<String,Object>map = new HashMap<>();
        BalanceTransfer balanceTransfer;
        try{
            /** 大红包记录 */
            redPackageMapper.insertSelective(redPackage);
            /** 转账到公司账户 */
           balanceTransfer = pingUtil.balanceTransfer(redPackage.getMoneyAmount(),"红包金额",redPackage.getUserId()+"","0",orderNoUtil.serialNoGenerate("06",uid));
           if(balanceTransfer.getStatus().equals("succeeded")){
                /** 转账记录 */
                insertSystem_pay(balanceTransfer);
                /** 账户转出记录、余额更新 */
                insertDebitRecord(balanceTransfer,redPackage);
                map.put("balanceTransfer",balanceTransfer);
                map.put("code",0);
                map.put("message","付款成功");
           }else{
               map.put("balanceTransfer",balanceTransfer);
               map.put("code",1);
               map.put("message","付款异常");
           }
            map.put("code",0);
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException(Constant.ERR_BalanceTransfer);
        }
        return map;
    }

    @Override//抢红包  小红包写入数据库  写入该记录到交易中  更新抢得红包的人的充值余额账户
    //(1-订单支付 2-预存充值 3-退款 4-提现 5-收入 6-教练结算 7-打赏球童 8-买单 9-支持愿望 10- 红包 11-赠送 12预约课程 13预约教练 14单次课程费)
    public Map<String,Object> qiangRedPackage(SmallRedPackage smallRedPackage) throws SystemException {
        Map<String,Object>map = new HashMap<>();
        /*BalanceTransfer balanceTransfer = null;*/
        Transfer transfer = null;
        try{
            /** 小红包记录 */
            smallRedPackageMapper.insertSelective(smallRedPackage);
            /** 转账给抢到红包的人 */
            MineVo mineVo = userMapper.getInfo(smallRedPackage.getReceiverId());

           transfer = pingUtil.transferAccounts
                    (smallRedPackage.getSmallMoneyAmount(),"balance",orderNoUtil.orderNoGenerate("h",mineVo.getPhone()),smallRedPackage.getReceiverId()+"","10");//description=10 红包
            if("pending".equals(transfer.getStatus())){
                map.put("status",2);//企业付处理中
                map.put("message","处理中");
            }else if("paid".equals(transfer.getStatus())){
                Account account = accountMapper.getUserBalance(smallRedPackage.getReceiverId());

                /** 转账记录 */
                System_pay systemPay = new System_pay();
                systemPay.setUserId(0);
                systemPay.setTakeId(Integer.valueOf(transfer.getRecipient()));
                /** 分转元 */
                String mm = AmountUtils.changeF2Y(transfer.getAmount()+"");
                /*systemPay.setMoney((double)(transfer.getAmount()/100));*/
                systemPay.setMoney(Double.valueOf(mm));
                systemPay.setCreateTime(new Date());
                systemPay.setModifyTime(new Date());
                systemPay.setType("10");
                systemPay.setState(transfer.getStatus().equals("paid")?"1":"2");
                systemPay.setOrderNo(transfer.getOrderNo());
                systemPayMapper.insertSelective(systemPay);
                /** 抢到红包进入预存余额 */

                /** 再webhooks修改 */

                /*Map<String,Object>sqlMap = new HashMap<>();
                sqlMap.put("userId",account.getUserId());
                sqlMap.put("czBalance",account.getCzBalance()+Double.valueOf(mm));
                accountMapper.updateUserCzBalance(sqlMap);*/
                jiaoYiHuizongMapper.insertSelective(
                        new JiaoYiHuizong(null,Integer.valueOf(transfer.getRecipient()),"10",new Date(),1,
                        Double.valueOf(AmountUtils.changeF2Y(transfer.getAmount()+"")),transfer.getOrderNo(),null,"(balance)qyf"));
                map.put("transfer",transfer);
                map.put("status",0);
            }else if("failed".equals(transfer)){
                map.put("status",3);//付款失败
            }else if("scheduled".equals(transfer)){
                map.put("status",4);//待发送
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException(Constant.ERR_BalanceTransfer);
        }
        return map;
    }
    /** 红包详细 */
    @Override
    public Map<String, Object> selectRedPackageDetails(Integer userId,String redPackageId) throws Exception {
        Map<String,Object>map = new HashMap<>();
        /** 上层详细 */
        RedPackageDetailsVo redPackageDetailsVo = redPackageMapper.selectShangRedPackageDetails(userId,redPackageId);
        int money = (int)redPackageDetailsVo.getSmallMoneyAmount().doubleValue();
        redPackageDetailsVo.setSmallMoneyAmount(Double.valueOf(AmountUtils.changeF2Y(money+"")));

        System.out.println("SmallMoneyAmount====="+redPackageDetailsVo.getSmallMoneyAmount());
        /** 下层已抢列表 */
        List<QianRedPackageListVo> qianRedPackageListVo = redPackageMapper.selectQiangRedPackageList(redPackageId);

        for (QianRedPackageListVo qrpv:qianRedPackageListVo
             ) {
            int m = (int)qrpv.getSmallMoneyAmount().doubleValue();
            qrpv.setSmallMoneyAmount(Double.valueOf(AmountUtils.changeF2Y(m+"")));
            System.out.println("qrpv====="+qrpv.getSmallMoneyAmount());
        }
        map.put("redPackageDetailsVo",redPackageDetailsVo);
        map.put("qianRedPackageListVo",qianRedPackageListVo);
        map.put("smallRedPackageCount",qianRedPackageListVo.size());
        return map;
    }

    /** 小红包列表 */
    @Override
    public List<SmallRedPackage> selectByPrimaryKey(String bigRedPackageId) {
        return smallRedPackageMapper.selectByPrimaryKey(bigRedPackageId);
    }

    /** @retun  Integer redPcount
     * 小红包个数
     * */
    @Override
    public Integer smallRedPackageCount(String redPackageId) {
        return smallRedPackageMapper.smallRedPackageCount(redPackageId);
    }

    @Override
    public List<QianRedPackageListVo> selectQiangRedPackageList(String redPackageId) throws Exception {
        List<QianRedPackageListVo> qianRedPackageListVo = redPackageMapper.selectQiangRedPackageList(redPackageId);
        /*for (QianRedPackageListVo qrpv:qianRedPackageListVo
                ) {
            int m = (int)qrpv.getSmallMoneyAmount().doubleValue();
            qrpv.setSmallMoneyAmount(Double.valueOf(AmountUtils.changeF2Y(m+"")));
            System.out.println("qrpv====="+qrpv.getSmallMoneyAmount());
        }*/
        return qianRedPackageListVo;
    }

    @Override
    public RedPackage selectBigByPrimaryKey(String id) {
        return redPackageMapper.selectByPrimaryKey(id);
    }

    @Override //红包退款
    public List<RedPackageRefundVo> redPackageRefundMoney() {
        return redPackageMapper.redPackageRefundMoney();
    }

    @Override
    public int updateByPrimaryKeySelective(RedPackage record) {
        return redPackageMapper.updateByPrimaryKeySelective(record);
    }


    /** 转账记录 */
    protected void insertSystem_pay(BalanceTransfer balanceTransfer) throws Exception {
        /** 转账记录 */
        System_pay systemPay = new System_pay();
        systemPay.setUserId(Integer.valueOf(balanceTransfer.getUser()));
        systemPay.setTakeId(Integer.valueOf(balanceTransfer.getRecipient()));
        /** 分转元 */
        systemPay.setMoney(Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")));
        systemPay.setCreateTime(new Date());
        systemPay.setModifyTime(new Date());
        systemPay.setType("10");
        systemPay.setState(balanceTransfer.getStatus().equals("succeeded")?"1":"2");
        systemPay.setOrderNo(balanceTransfer.getOrderNo());
        JiaoYiHuizong jiaoYiHuizong = new JiaoYiHuizong(null,Integer.valueOf(balanceTransfer.getUser()),"10",new Date(),2,
                Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+"")),balanceTransfer.getOrderNo(),null,"balance");
        jiaoYiHuizongMapper.insertSelective(jiaoYiHuizong);
        systemPayMapper.insertSelective(systemPay);
    }


    /** 账户转出记录 余额更新 */
    protected void insertDebitRecord(BalanceTransfer balanceTransfer,RedPackage redPackage) throws Exception {
        /** 转出方账户详细 */
        Account zcAccount = accountMapper.getUserBalance(redPackage.getUserId());
        /** 转款金额 */
        /*int zkMoney = balanceTransfer.getAmount()/100;*/
        Double zkMoney = Double.valueOf(AmountUtils.changeF2Y(balanceTransfer.getAmount()+""));
        DebitRecord debitRecord = new DebitRecord();
        if(zcAccount.getCzBalance()>=zkMoney){//预存余额
            debitRecord.setCzBalance(zkMoney);
            /** 更新转出方预存余额... */
            Map<String,Object>map1 = new HashMap<>();
            map1.put("userId",redPackage.getUserId());
            map1.put("czBalance",zcAccount.getCzBalance()-zkMoney);
            accountMapper.updateUserCzBalance(map1);
        }else if (zcAccount.getCzBalance()<zkMoney&&zcAccount.getCzBalance()+zcAccount.getSrBalance()>zkMoney){//充值余额-->充值余额+收入余额
            debitRecord.setCzBalance(zcAccount.getSrBalance());
            debitRecord.setSrBalance(zkMoney-zcAccount.getCzBalance().intValue());
            /** 更新转出方预存、收入余额 */
            Map<String,Object>map2 = new HashMap<>();
            map2.put("userId",redPackage.getUserId());
            map2.put("czBalance",0.00);
            accountMapper.updateUserCzBalance(map2);

            Map<String,Object>map3 = new HashMap<>();
            map3.put("userId",redPackage.getUserId());
            map3.put("srBalance",zcAccount.getCzBalance()+zcAccount.getSrBalance()-zkMoney);
            accountMapper.updateUserSrBalance(map3);
        }else if (zcAccount.getCzBalance()<zkMoney&&zcAccount.getCzBalance()+zcAccount.getSrBalance()<zkMoney&&
                zcAccount.getCzBalance()+zcAccount.getSrBalance()+zcAccount.getZsBalance()>zkMoney){//充值余额-->充值余额+收入余额-->充值余额+收入余额+赠送余额
            debitRecord.setCzBalance(zcAccount.getCzBalance());
            debitRecord.setSrBalance(zcAccount.getSrBalance());
            debitRecord.setZsBalance(zkMoney-zcAccount.getCzBalance().intValue()-zcAccount.getSrBalance().intValue());
            /** 更新转出方预存、收入、赠送余额 */
            Map<String,Object>map4 = new HashMap<>();
            map4.put("userId",redPackage.getUserId());
            /*map4.put("czBalance",zcAccount.getCzBalance());*/
            map4.put("czBalance",0.0);
            accountMapper.updateUserCzBalance(map4);

            Map<String,Object>map5 = new HashMap<>();
            map5.put("userId",redPackage.getUserId());
            /*map5.put("srBalance",zcAccount.getSrBalance());*/
            map5.put("srBalance",0.0);
            accountMapper.updateUserSrBalance(map5);

            Map<String,Object>map6 = new HashMap<>();
            map6.put("userId",redPackage.getUserId());
            map6.put("zsBalance",zcAccount.getCzBalance()+zcAccount.getSrBalance()+zcAccount.getZsBalance()-zkMoney);
            accountMapper.updateUserZsBalance(map6);
        }
        /** 保存此次消费记录 */
        /*debitRecordMapper.insertSelective(debitRecord);*/
        debitRecord.setCreateTime(new Date());
        debitRecord.setPayForm("0");
        debitRecord.setUserId(Integer.valueOf(balanceTransfer.getUser()));
        debitRecord.setAccountId(zcAccount.getAccountId());
        debitRecord.setPingId(balanceTransfer.getOrderNo());
        debitRecordMapper.insert(debitRecord);
    }
}
