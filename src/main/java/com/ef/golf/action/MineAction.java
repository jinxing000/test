package com.ef.golf.action;


import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.LoginException;
import com.ef.golf.exception.QueryException;
import com.ef.golf.pojo.EsMemberAddress;
import com.ef.golf.pojo.EsTypeArea;
import com.ef.golf.pojo.User;
import com.ef.golf.service.EsMemberAddressService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.AddressVo;
import com.ef.golf.vo.MineVo;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MineAction extends AbstractService {

    @Resource
    private RedisBaseDao redisBaseDao;
    @Resource
    private UserService userService;
    @Resource
    private EsMemberAddressService esMemberAddressService;

    //@NotNull(message = "您当前尚未登录或登录失效，请登录。。。")
    private String sid;
    private String uid;


    @Override
    public Object doService() throws Exception {
        Map<String, Object> mineMap = new HashMap<String, Object>();
        // TODO 用注解方式去验证参数
        if (StringUtils.isNotEmpty(sid)) {
            String UID = redisBaseDao.get(sid);
            if (StringUtils.isEmpty(UID) || !UID.equals(uid)) {
                throw new LoginException(Constant.ERR_USER);
            }
            //获取用户id
            Long userId = userService.getUid(UID);
            MineVo mineVo = userService.getInfo(userId.intValue());
            mineVo.setUserId(userId.toString());
            if (mineVo != null) {
                mineMap.put("mineVo", mineVo);
            } else {
                throw new QueryException(Constant.ERR_QUERY - 4);
            }

            User user = new User();
            user.setId(userId);
            /*EsMemberAddress esMemberAddress = esMemberAddressService.getUserAddressByUserId(userId.intValue());
            if(esMemberAddress!=null){
                mineMap.put("esMemberAddress",esMemberAddress);
            }*/
          //  List<EsMemberAddress> esMemberAddressList = esMemberAddressService.getMemberAddress(userId.intValue());
            //该处是为了获取用户默认的收货地址，直接去取默认的就行了，不需要取所有列表
            EsMemberAddress address=esMemberAddressService.getUserAddressByUserId(userId.intValue());
            //List<EsTypeArea> list = esMemberAddressService.getListOne();
            if(address!=null){


                List<EsTypeArea> list = esMemberAddressService.getList(6);//6代表顺丰陆运，目前写死
                Gson gson = new Gson();
                boolean flag;
                //for (EsMemberAddress address : esMemberAddressList) {
                    flag = false;
                    for (EsTypeArea area : list) {
                        String[] area_id = area.getAreaIdGroup().split(",");
                        List<String> areaIdList=Arrays.asList(area_id);
                        // for(String str : area_id) {
                            if (areaIdList.contains(address.getCityId().toString())) {
                               // EsTypeArea typeArea = esMemberAddressService.getList(Integer.valueOf(area.getTypeId())).get(0);
                                Map<String, Object> map2 = gson.fromJson(area.getConfig(), Map.class);
                                Double firstunit = (Double) map2.get("firstunit");//首重
                                Double continueunit = (Double) map2.get("continueunit");//续重
                                Double firstprice = (Double) map2.get("firstprice");//首费 15+tint(w-1000)/500*5
                                Double continueprice = (Double) map2.get("continueprice");//续费
                                AddressVo addressVo = new AddressVo();
                                addressVo.setAddr_id(address.getAddrId()+"");
                                addressVo.setAddr(address.getAddr());
                                addressVo.setMobile(address.getMobile());
                                addressVo.setName(address.getName());
                                addressVo.setDef_addr(address.getDefAddr()+"");
                                addressVo.setFirstunit(firstunit / 1000);
                                addressVo.setFirstprice(firstprice);
                                addressVo.setContinueprice(continueprice);
                                addressVo.setContinueunit(continueunit / 1000);
                                addressVo.setType_id(area.getTypeId()+"");
                                addressVo.setCity(address.getCity());
                                addressVo.setProvince(address.getProvince());
                                addressVo.setRegion(address.getRegion());
                                addressVo.setCity_id(address.getCityId()+"");
                                addressVo.setProvince_id(address.getProvinceId()+"");
                                addressVo.setRegion_id(address.getRegionId()+"");
                                mineMap.put("addressVo", addressVo);
                                flag = true;
                                break;
                            }
                       // }
                    }
            }else{//如果没有设置默认收货地址
                mineMap.put("addressVo", null);
            }

            //}
            //重置session时间
            // redisBaseDao.expire(sid, Integer.valueOf(Consts.REDIS_OUT_TIME));
        }
        return mineMap;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
