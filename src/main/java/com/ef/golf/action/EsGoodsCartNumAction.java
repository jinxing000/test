package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.MallException;
import com.ef.golf.pojo.Product;
import com.ef.golf.service.EsGoodsCartService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 商城的购物车更新数量,购物车总金额,总数量
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsGoodsCartNumAction extends AbstractService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisBaseDao redisBaseDao;
    @Autowired
    EsGoodsCartService esGoodsCartService;
    /*  @NotNull(message = "goods_id不能为空")
      private Integer goods_id;*/
    //@NotNull(message = "sid不能为空")
    private String sid;
    //@NotNull(message = "cart_id不能为空")
    private Integer cart_id;
    //@NotNull(message = "uid不能为空")
    private String uid;
    //@NotNull(message = "num不能为空")
    private Integer num;
    //@NotNull(message = "product_id不能为空")
    private Integer product_id;

   /* public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        String UID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(UID) || !UID.equals(uid)) {
            throw new LoginException(Constant.ERR_USER);
        }
        //获取用户id
        Integer user_id = userService.getUid(UID).intValue();
        Product product = esGoodsCartService.selcetProduct(product_id);
       /* int totalCount = esGoodsCartService.totalCount(user_id);
        Double totalPrice = esGoodsCartService.totalPrice(user_id);
        map.put("totalCount",totalCount);
        map.put("totalPrice",totalPrice);*/
        Integer store = product.getEnableStore();
        if (store == null) {
            store = 0;
        }
        if (store >= num) {
            maps.put("num", num);
            maps.put("cart_id", cart_id);
            maps.put("user_id", user_id);
            int i = esGoodsCartService.updateCartNum(maps);
            if (i > 0) {
                map.put("mes", "更新成功");
            } else {
                map.put("code", "1");
                map.put("mes", "更新失败");
            }
        } else {
            map.put("code", "1");
            map.put("mes", "库存不足");
            throw new MallException(Constant.SHANGCHENG_STORE_LACK);
        }
        return map;
    }
}
