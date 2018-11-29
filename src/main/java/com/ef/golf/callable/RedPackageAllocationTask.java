package com.ef.golf.callable;

import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.pojo.Account;
import com.ef.golf.pojo.RedPackage;
import com.ef.golf.pojo.SmallRedPackage;
import com.ef.golf.service.AccountService;
import com.ef.golf.service.RedPackageService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.*;
import com.pingplusplus.model.BalanceTransfer;
import com.pingplusplus.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2018/5/10.
 */
@Component
@Scope("prototype")
public class RedPackageAllocationTask implements Callable<Map<String,Object>>, Serializable {

    private String uid;
    private String sid;

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    @Resource
    private RedPackageService redPackageService;
    @Resource
    private PingUtil pingUtil;
    @Resource
    private UserService userService;
    @Resource
    private AccountService accountService;



    //红包金额 单位是分
    private Integer moneyAmount;
    //红包个数
    private Integer count;

    //群号
    private String qunId;
    //祝福语  111
    private String content;

    @Override
    public Map<String, Object> call() throws Exception {
         List<SmallRedPackage> smallRedPackageList=new ArrayList<>();
         Map<String,Object> map=new HashMap<>();
        List<RedPackage>bigRedPackageList = new ArrayList<>();


        //要进行的并发操作
            Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
            //检查红包金额与用户可用账号余额(预存或者)是否满足，如果不满足就回写状态 通知客户端
            double yue=2000000000;
            System.out.println(yue);
            //余额查询
            User user = pingUtil.userBalacnce(userId+"");
            yue = user.getAvailableBalance();

            Account userAccount = accountService.getUserBalance(Integer.valueOf(userId));

            if(moneyAmount>yue) {
                map.put("status", 1);
                return map;
            }
            if((userAccount.getSrBalance()+userAccount.getCzBalance())<(Double.valueOf(AmountUtils.changeF2Y(moneyAmount+"")))){
                    map.put("status",2);
            }else {

                try{
                    //产生bigRedPackageId
                    String aa = AmountUtils.changeF2Y(moneyAmount+"");

                    String bigRedPackageId= RedPacketUtil.serialNoGenerate();
                    RedPackage redPackage=new RedPackage(bigRedPackageId,moneyAmount,count,Integer.parseInt(userService.getUserId(uid)),new Date(),qunId,content,0,0);
                    /** 将大红包保存到redis 24小时候失效 */
                    bigRedPackageList.add(redPackage);
                    redisBaseDao.setList(bigRedPackageId,bigRedPackageList);
                    redisBaseDao.expire(bigRedPackageId, RedisExpireTime.RedPackageTime.getSeconds());
                    //余额足够的情况下,进行预拆分
                    List<Integer> moneyList = RedPacketUtil.splitRedPackets(moneyAmount, count);
                    int best=0;//手气最佳的标记  等于1时为最佳手气
                    for (int i = 0; i < moneyList.size(); i++) {
                        if(Collections.max(moneyList)==moneyList.get(i)) {
                            best=1;
                        }
                        SmallRedPackage smallRedPackage = new SmallRedPackage(bigRedPackageId,i,0,new Date(),moneyList.get(i),0,best);
                        smallRedPackageList.add(smallRedPackage);
                    }
                    //把拆分结果放入redis
                    redisBaseDao.setList("smallRedPackageList"+bigRedPackageId, smallRedPackageList);
                    //调用业务层更新数据
                    //发送者钱减少(减少，进入公司虚拟账户)
                    //写入数据库记录:   发送者的红包记录   预拆分的小红包记录
                    Map<String,Object> resultMap = redPackageService.sendRedPackage(redPackage,smallRedPackageList,uid);
                    BalanceTransfer balanceTransfer =(BalanceTransfer) map.get("balanceTransfer");
                    Integer code = (Integer) resultMap.get("code");
                    String message =(String) resultMap.get("message");
                    //回写状态给客户端
                    map.put("status",code);
                    map.put("message",message);
                    map.put("bigRedPackageId",bigRedPackageId);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        return map;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getQunId() {
        return qunId;
    }

    public void setQunId(String qunId) {
        this.qunId = qunId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
