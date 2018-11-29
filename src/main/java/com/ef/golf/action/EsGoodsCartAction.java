package com.ef.golf.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
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
 * 商城的购物车
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsGoodsCartAction extends AbstractService {


    @Autowired
    EsGoodsCartService esGoodsCartService;
  /*  @NotNull(message = "goods_id不能为空")
    private Integer goods_id;
    @NotNull(message = "sid不能为空")
    private String sid;
    @NotNull(message = "uid不能为空")
    private String uid;
    @NotNull(message = "num不能为空")
    private Integer num;
    @NotNull(message = "product_id不能为空")
    private Integer product_id;
    */
    @Autowired
    private UserService userService;
    @Autowired
    private RedisBaseDao redisBaseDao;

   /* private Integer userId;

    private Integer is_check;*/

   // private Integer cart_id;

 //   private String oper;//用于区分添加购物车和选中购物车商品

    //为了把添加购物车和再次够买使用一个逻辑，现在把传递的参数重新整理
    //客户端传递购物项的json数组，接收后转成map处理
    //把 goods_id,product_id,num,is_check
    //当添加购物车时 is_check 传空，其他正常传递
    //当选中购物项 is_check 传  1   取消选中传 0

    //@NotNull(message = "sid不能为空")
    private String sid;
    //@NotNull(message = "uid不能为空")
    private String uid;
    private Integer userId;
    private String goodsCartParam;//以json数组传递

    @Override
    public Object doService() throws Exception {

        String rUID = redisBaseDao.get(sid);
        if(StringUtils.isEmpty(rUID)||!rUID.equals(uid))
            throw new LoginException(Constant.ERR_USER);

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        String UID = redisBaseDao.get(sid);
        if (StringUtils.isEmpty(UID) || !UID.equals(uid)) {
            throw new LoginException(Constant.ERR_USER);
        }


        //fastJson解析json数组
       JSONArray jsonArray= JSON.parseArray(goodsCartParam);
       for(int i=0;i<jsonArray.size();i++){
           JSONObject jsonObject =jsonArray.getJSONObject(i);
           Integer goods_id=jsonObject.getInteger("goods_id");
           Integer product_id=jsonObject.getInteger("product_id");
           Integer num=jsonObject.getInteger("num");
           Integer is_check=jsonObject.getInteger("is_check");


        Product product = esGoodsCartService.selcetProduct(product_id);

        if(is_check.intValue()!=0&&is_check.intValue()!=1) {//当添加时提供的是1 可以添加，当修改数量时一定是前端选中状态
            map2 = esGoodsCartService.addCart(product, num, sid, userId);
        }else{//没走购物车添加或者数量的改变，肯定是更新选中的状态

            map1.put("userId", userId);
            map1.put("is_check", is_check);
            map1.put("product_id", product_id);
            try {
                esGoodsCartService.updateCheckbox(map1);
                if (is_check.intValue() == 1) {
                    map2.put("code", "1");
                    map2.put("mes", "选中成功");
                } else {
                    map2.put("code", "1");
                    map2.put("mes", "取消成功");
                }

            } catch (Exception ex) {
                map2.put("code", "0");
                map2.put("mes", "取消失败");
            }
        }
       }
        return map2;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setGoodsCartParam(String goodsCartParam) {
        this.goodsCartParam = goodsCartParam;
    }
}
