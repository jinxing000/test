package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.DlyType;
import com.ef.golf.pojo.EsMemberAddress;
import com.ef.golf.pojo.EsTypeArea;
import com.ef.golf.service.EsDlyTypeService;
import com.ef.golf.service.EsMemberAddressService;
import com.ef.golf.service.GoodsService;
import com.ef.golf.service.UserService;
import com.ef.golf.util.RedisBaseDao;
import com.ef.golf.vo.AddressVo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城会员地址列表
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsMemberAddressAction extends AbstractService {
    @Autowired
    EsMemberAddressService esMemberAddressService;
    /*@NotNull(message = "sid不能为空")
    private String sid;
    @NotNull(message = "uid不能为空")
    private String uid;*/
    /*@NotNull(message = "areaStr不能为空")
    private String areaStr;//省区县id字符串
    @NotNull(message = "name不能为空")
    private String name;
    @NotNull(message = "mobile不能为空")
    private String mobile;
    @NotNull(message = "addr不能为空")
    private String addr;
    @NotNull(message = "addrId不能为空")
    private Integer addrId;*/
  //  @NotNull(message = "userId不能为空")
    private String userId;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisBaseDao redisBaseDao;
    @Autowired
    GoodsService esGoodsService;
    @Autowired
    EsDlyTypeService esDlyTypeService;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();


        Integer type_id = -1;
        List<EsMemberAddress> esMemberAddressList = esMemberAddressService.getMemberAddress(Integer.valueOf(userId));
        List<EsTypeArea> list = esMemberAddressService.getListOne();
        List AddressVoList = new ArrayList();
        Gson gson = new Gson();
        String city_id = "";
        //String type_id="";
        boolean flag;
        for (EsMemberAddress address : esMemberAddressList) {
            flag = false;
            for (EsTypeArea area : list) {
                String[] area_id = area.getAreaIdGroup().split(",");
                for (String str : area_id) {
                    if ((address.getCityId().toString()).equals(str)) {
                        city_id += area.getTypeId() + ",";
                        //EsTypeArea typeArea =  esMemberAddressService.getList(Integer.valueOf(str)).get(0);
                        String config = area.getConfig();
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
                        AddressVoList.add(addressVo);
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    break;
                }

            }
            if (!flag) {
                List<DlyType> lists = esDlyTypeService.getList();
                DlyType type = lists.get(0);
                String config = type.getConfig();
                Map<String, Object> map2 = gson.fromJson(config, Map.class);
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
                addressVo.setType_id(type.getTypeId()+"");
                addressVo.setCity(address.getCity());
                addressVo.setProvince(address.getProvince());
                addressVo.setRegion(address.getRegion());
                addressVo.setCity_id(address.getCityId()+"");
                addressVo.setProvince_id(address.getProvinceId()+"");
                addressVo.setRegion_id(address.getRegionId()+"");
                //flag=true;
                AddressVoList.add(addressVo);
                // continue;
            }
        }

        return AddressVoList;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
