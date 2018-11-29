package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.service.EsGoodsCartService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 商城的删除购物车选项
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsGoodsCartDelAction extends AbstractService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisBaseDao redisBaseDao;
    @Autowired
    EsGoodsCartService esGoodsCartService;
    //@NotNull(message = "sid不能为空")
    private String sid;
    //@NotNull(message = "uid不能为空")
    private String uid;
    //@NotNull(message = "cart_id不能为空")
    private String cart_id;
    //private Integer[]cart_id;
    private Integer user_id;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        // Arrays.asList(cart_id.split(","));
        String[] s = cart_id.split(",");
        List<Integer> cartList = new ArrayList<Integer>();
        for (String str : s) {
            cartList.add(Integer.valueOf(str));
        }
        String UID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(UID) || !UID.equals(uid)) {
            throw new LoginException(Constant.ERR_USER);
        }
        //获取用户id
        Integer user_id = userService.getUid(UID).intValue();
        maps.put("user_id", user_id);
        maps.put("cartList", cartList);
        int i = esGoodsCartService.deleteCart(maps);
        if (i > 0) {
            map.put("mes", "删除成功");
        } else {
            map.put("mes", "删除失败");
        }
        return map;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }


}
