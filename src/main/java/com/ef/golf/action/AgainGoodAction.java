package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.GoodsCart;
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
import java.util.List;
import java.util.Map;

/**
 * 再次购买
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AgainGoodAction extends AbstractService {
    @Autowired
    EsGoodsCartService esGoodsCartService;
    //@NotNull(message = "goods_id不能为空")
    private Integer goods_id;
    //@NotNull(message = "sid不能为空")
    private String sid;
    //@NotNull(message = "uid不能为空")
    private String uid;
    //@NotNull(message = "num不能为空")
    private Integer num;
    //@NotNull(message = "product_id不能为空")
    private Integer product_id;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisBaseDao redisBaseDao;

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public void setSid(String sid) {
        this.sid = sid;
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
        String UID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(UID) || !UID.equals(uid)) {
            throw new LoginException(Constant.ERR_USER);
        }
        //获取用户id
        Integer userId = userService.getUid(UID).intValue();
        List<GoodsCart> againList = esGoodsCartService.getAgainList(userId);
        if (againList.size() > 0 && againList != null) {
            esGoodsCartService.delectUserCart(userId);
        }
        Product product = esGoodsCartService.selcetProduct(product_id);
        Map map2 = esGoodsCartService.addCart(product, num, sid, userId);


        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();


        return map2;
    }
}
