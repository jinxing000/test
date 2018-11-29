package com.ef.golf.callable;

import com.ef.golf.common.pxx.util.PingUtil;
import com.ef.golf.pojo.RedPackage;
import com.ef.golf.pojo.SmallRedPackage;
import com.ef.golf.service.RedPackageService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedPacketUtil;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import com.ef.golf.util.ServicePushUtils;
import com.ef.golf.vo.MineVo;
import com.pingplusplus.model.BalanceTransfer;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2018/5/10.
 */
@Component
@Scope("prototype")
public class RedPackageGetTask implements Callable<Map<String,Object>>, Serializable {

    private String uid;
    private String sid;

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private RedPackageService redPackageService;
    @Resource
    private UserService userService;
    //红包id
    private String redPackageId;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Override
    public Map<String, Object> call() throws Exception {
        Map<String,Object> map=new HashMap<>();

        try{
            Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
            //红包过期判断
            Boolean bl = redisBaseDao.exist(redPackageId);
            if(bl){
                //根据红包id  查询红包是否已经抢完?

                //获得未抢列表
                List<SmallRedPackage> smallRedPackageList=redisBaseDao.getList("smallRedPackageList"+redPackageId);
                //获得已经被抢的红包列表
                List<SmallRedPackage> smallRedPackageListQiang=redisBaseDao.getList("smallRedPackageListQiang"+redPackageId);
                if (smallRedPackageListQiang ==null){
                    smallRedPackageListQiang = new ArrayList<>();
                }

                //获得已抢用户id
                /*Set<String> userIds=redisBaseDao.sMembers("userIds");*/
                List<Integer> userIds = redisBaseDao.getList(redPackageId);


                  System.out.println("====================================="+redisBaseDao.sisMember("userIds",userId+""));
                    if (userIds!=null&&userIds.contains(userId)) {// 已经抢过了
                        map.put("status",3);
                        map.put("message", "红包已经抢过了");
                        //查询redis已抢红包列表是否还存在，如果存在就返回该列表，否则从数据库中查询，并存入redis 缓存一段时间
                        List<SmallRedPackage> SmallRedList = redPackageService.selectByPrimaryKey(redPackageId);
                        //存入map已抢的列表
                        RedPackage redPackage = redPackageService.selectBigByPrimaryKey(redPackageId);
                        /*Map<String,Object>resultMap = redPackageService.selectRedPackageDetails(redPackage.getUserId(),redPackageId);*/

                        Map<String,Object>resultMap = redPackageService.selectRedPackageDetails(userId,redPackageId);
                        map.put("resultMap",resultMap);
                        map.put("SmallRedList",SmallRedList);
                    }else{
                        //如果没抢过 获取redis中缓存的为分配的红包 更新数据库（使用数据乐观锁），数据库无发生异常 从未抢移入已抢，
                        SmallRedPackage smallRedPackage=smallRedPackageList.remove(0);
                        redisBaseDao.setList("smallRedPackageList"+redPackageId,smallRedPackageList);
                        smallRedPackage.setHasQiang(1);//修改为被抢了
                        smallRedPackage.setReceiverId(Integer.parseInt(userService.getUserId(uid)));
                        smallRedPackage.setReceiveTime(new Date());
                        System.out.println(smallRedPackage);
                        smallRedPackageListQiang.add(smallRedPackage);

                        /** 存储抢过红包的人 */
                        List<Integer>qiangList =redisBaseDao.getList(redPackageId);
                        if(null==qiangList){
                            qiangList = new ArrayList<>();
                            qiangList.add(userId);
                        }else{
                            qiangList.add(userId);
                        }
                        redisBaseDao.setList(redPackageId,qiangList);

                        /*redisBaseDao.sAdd("userIds",userId+"");*/
                        Map<String,Object>usersMap = new HashMap<>();

                        RedPackage redPackage = redPackageService.selectBigByPrimaryKey(redPackageId);
                        if(!userId.equals(redPackage.getUserId())){
                        String[]ss1 = {redPackage.getUserId()+""};
                        MineVo mineVo1 = userService.getInfo(userId);
                        ServicePushUtils.servicePush(redPackage.getQunId(),"12",ss1,mineVo1.getNickName()+"抢了你的红包", usersMap);
                        }
                        String[]ss2 = {userId+""};
                        MineVo mineVo2 = userService.getInfo(redPackage.getUserId());
                        ServicePushUtils.servicePush(redPackage.getQunId(),"12",ss2,"你领取了"+mineVo2.getNickName()+"的红包", usersMap);

                        redisBaseDao.setList("smallRedPackageListQiang"+redPackageId,smallRedPackageListQiang);
                        //调用业务逻辑  小红包写入数据库  写入该记录到交易中  更新抢得红包的人的充值余额账户
                        Map<String,Object> updateMap = redPackageService.qiangRedPackage(smallRedPackage);
                        //回写客户端抢得的红包信息
                        //个人抢得红包与给人信息关联查询
                        //总小红包数，已经抢的小红包数
                        //个人信息与红包信息的关联列表
                        Map<String,Object>resultMap = redPackageService.selectRedPackageDetails(smallRedPackage.getReceiverId(),redPackageId);
                        map.put("payStatus",updateMap.get("status"));
                        map.put("status",4);
                        map.put("resultMap",resultMap);
                        /*map.put("msgMapDetails",msgMapDetails);*/
                    }

            }else{
                map.put("status",1);
                map.put("message","红包过期");
            }
        }catch (Exception e){
            e.printStackTrace();
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

    public String getRedPackageId() {
        return redPackageId;
    }

    public void setRedPackageId(String redPackageId) {
        this.redPackageId = redPackageId;
    }
}
