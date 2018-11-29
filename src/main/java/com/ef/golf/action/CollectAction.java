package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.Collect;
import com.ef.golf.service.CollectService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.util.RedisLoginVerifyUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * for efgolf
 * Created by Bart on 2017/9/21.
 * Date: 2017/9/21 19:29
 * 关注接口
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CollectAction extends AbstractService {

    @Resource
    private CollectService collectService;

    @Resource
    private UserService userService;
    @Resource
    private RedisLoginVerifyUtil redisLoginVerifyUtil;

    @Resource
    private RedisBaseDao redisBaseDao;

    //@NotNull(message = "未登陆,无法关注")
    private String uid;
    //@NotNull(message = "未登陆,无法关注")
    private String sid;

    //@NotNull
    private int productId;

    //@NotNull
    private String productType;

    private String isCollect;

    public Object doService() throws SystemException, LoginException {
        Map<String, Object> map = new HashMap<>();
        //从redits读取用户信息，判断是否登录
        if (StringUtils.isEmpty(redisBaseDao.get(sid)) || !redisBaseDao.get(sid).equals(uid))
            throw new LoginException(Constant.ERR_USER);
        Long user_id = userService.getUid((uid));
        Integer userId = redisLoginVerifyUtil.longinVerifty(sid,uid);
        //如果isCollect为-1，则新增一条关注信息
        if ("-1".equals(isCollect)) {

            Collect collect = collectService.getCollectByUserIdAndProductId(userId,productId);
            if(null==collect){
                int i = collectService.saveCollect(user_id, productId, productType, uid);
                if (i > 0) {
                    map.put("status", 0);
                    map.put("message", "关注成功");
                } else {
                    map.put("status", 1);
                    map.put("message", "关注失败");
                }
            }else{
                collect.setCollectType(productType);
                collect.setModifyTime(new Date());
                collect.setCreateUser(userId.toString());
                int j = collectService.updateByPrimaryKeySelective(collect);
                if (j > 0) {
                    map.put("status", 0);
                    map.put("message", "关注成功");
                } else {
                    map.put("status", 1);
                    map.put("message", "关注失败");
                }
            }

            //否则执行取消关注
        } else {
            int i = collectService.CancelCollect(user_id.intValue(), productId, productType);
            if (i > 0) {
                map.put("status", 0);
                map.put("message", "取消关注成功");
            } else {
                map.put("status", 1);
                map.put("message", "取消关注失败");
            }
        }
        return map;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setIsCollect(String isCollect) {
        this.isCollect = isCollect;
    }

}
