package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.pojo.Activity;
import com.ef.golf.service.EsGoodsCartService;
import com.ef.golf.service.EsGoodsCatService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.CartItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by zhang
 * 2018年1月3日15:17:30
 * 商城的购物车列表
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsGoodsCartListAction extends AbstractService {

    @Autowired
    EsGoodsCartService esGoodsCartService;
    @Autowired
    EsGoodsCatService esGoodsCatService;
    //@NotNull(message = "sid不能为空")
    private String sid;
    //@NotNull(message = "uid不能为空")
    private String uid;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisBaseDao redisBaseDao;


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
        List<CartItem> itemList = esGoodsCartService.getItemList(user_id,"0");


        for (CartItem cart : itemList) {

            //把列表项中的商品分类 替换成父分类
            //根据商品小类查找对应的所属大类
            Integer parentId=esGoodsCatService.getBigCatId(cart.getCatid());
            cart.setCatid(parentId);

            Integer act_id = cart.getActivity_id();
            if (act_id != null && act_id != 0) {
                Activity activity = esGoodsCartService.getActivity(act_id);
                if (activity.getDisabled() == 1 || activity.getEndTime() < System.currentTimeMillis() / 1000) {
                    // this.daoSupport.execute("update es_cart set activity_id = null where cart_id = ?", cart.getCart_id());
                    int i = esGoodsCartService.updateCartActivity(cart.getCart_id());
                }
            }
        }

        map.put("itemList", itemList);
        return map;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
