package com.ef.golf.callable;

import com.ef.golf.pojo.RedPackage;
import com.ef.golf.pojo.SmallRedPackage;
import com.ef.golf.service.RedPackageService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.AmountUtils;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
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
public class RedPackageCheckTask implements Callable<Map<String,Object>>, Serializable {

    @Resource
    RedisBaseDao redisBaseDao;
    @Resource
    private RedPackageService redPackageService;
    @Resource
    private UserService userService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;
    //红包id
    private String redPackageId;
    private String uid;
    private String sid;

    @Override
    public Map<String, Object> call() throws Exception {
        Map<String, Object> map = new HashMap<>();
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
        try {
            //红包过期判断
            /*Boolean bl = redisBaseDao.exist("bigRedPackageList");*/
            Boolean bl = redisBaseDao.exist(redPackageId);

                if (bl) {
                    //根据红包id  查询红包是否已经抢完?
                    List<Integer>list = redisBaseDao.getList(redPackageId);
                    if(null!=list&&list.contains(userId)){
                        map.put("status",3);
                        map.put("message","已经抢过");

                    }else{
                        Integer smallRedPakcge = redPackageService.smallRedPackageCount(redPackageId);
                        //获得未抢列表
                        List<SmallRedPackage> smallRedPackageList = redisBaseDao.getList("smallRedPackageList"+redPackageId);
                        redisBaseDao.setList("smallRedPackageList"+redPackageId, smallRedPackageList);

                        if(/*redisBaseDao.getList("smallRedPackageList").size()==smallRedPakcge||*/smallRedPackageList.size() == 0){
                            System.out.println("=================红包已抢完======================");
                            map.put("status",2);
                            map.put("message","红包已抢完");
                        }else{
                            map.put("status",0);
                        }
                    }
                    map.put("qiangRedPackageList",redPackageService.selectQiangRedPackageList(redPackageId));
                    RedPackage redPackage = redPackageService.selectBigByPrimaryKey(redPackageId);
                    /*int money = (int)redPackage.getMoneyAmount().doubleValue();
                    redPackage.setMoneyAmount(Double.valueOf(AmountUtils.changeF2Y(money+"")));*/
                    map.put("bigRedDetails",redPackage);
                    map.put("mineinfo",userService.getInfo(redPackage.getUserId()));
                }else{
                    map.put("status",1);
                    map.put("message","红包过期");
                }
            }catch(Exception e){
            e.printStackTrace();
        }

        return map;
    }

    public String getRedPackageId() {
        return redPackageId;
    }

    public void setRedPackageId(String redPackageId) {
        this.redPackageId = redPackageId;
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
}
