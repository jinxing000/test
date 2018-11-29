package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.Cllect;
import com.ef.golf.service.FavoriteService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 收藏商品
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FavoriteAction extends AbstractService {

    @Autowired
    FavoriteService favoriteService;
    //@NotNull(message = "goods_id不能为空")
    private Integer goods_id;
    //@NotNull(message = "sid不能为空")
    private String sid;
    //@NotNull(message = "uid不能为空")
    private String uid;
    @Autowired
    private RedisBaseDao redisBaseDao;
    @Autowired
    private UserService userService;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        String UID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(UID) || !UID.equals(uid)) {
            throw new LoginException(Constant.ERR_USER);
        }
        //获取用户id
        Long userId = userService.getUid(UID);
        int i = favoriteService.countFavorite(goods_id, userId.intValue());
        if (i == 0) {
           /*Favorite favorite = new Favorite();
            favorite.setFavoriteTime(System.currentTimeMillis()/1000);
            favorite.setGoodsId(goods_id);
            favorite.setMemberId(userId.intValue());*/
            Cllect cllect = new Cllect();
            cllect.setUserId(userId);
            cllect.setCollectType("7");
            cllect.setCreateTime(new Date());
            cllect.setCreateUser(userId.toString());
            cllect.setProductId(goods_id);
            cllect.setModifyTime(new Date());
            cllect.setModifyUser(userId.toString());
            int s = favoriteService.insertSelective(cllect);

            if (s > 0) {
                map.put("mes", "添加收藏成功");
            } else {
                map.put("mes", "添加收藏失败");
            }
        } else {
            map.put("mes", "已收藏该商品");
        }
        return map;
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
}
