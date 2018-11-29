package com.ef.golf.action;

import com.alibaba.fastjson.JSON;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.pojo.EsMemberAddress;
import com.ef.golf.service.EsMemberAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 商城会员地址增加，删除，更新
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EsMemberAddAction extends AbstractService {
    @Autowired
    EsMemberAddressService esMemberAddressService;
    /*    private Integer type_id;
        private String  name;
        private Integer moblie;
        private String areaId;
        private String address;

        private  Integer addr_id;//地址id
        private Integer def_addr;//是否默认收货地址 1默认*/
    //@NotNull(message = "userId不能为空")
    private Integer userId;
    //@NotNull(message = "from不能为空")
    private String from;//1 增加 2 修改 3 删除
    /*private String province;//市,县
    private  String city;//区*/

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    /*
      private  String region;//详细地区
      private EsMemberAddress memberAddress;
        public void setMemberAddress(EsMemberAddress memberAddress) {
            this.memberAddress = memberAddress;
        }*/
    //@NotNull(message = "memberAddress不能为空")
    private String memberAddress;

    @Override
    public Object doService() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> maps = new HashMap<String, Object>();

        maps = (Map) JSON.parse(memberAddress);

        EsMemberAddress esMemberAddress = null;
        if (from.equals("1")) {

            esMemberAddress = new EsMemberAddress();
         /*if ("0".equals(((String)maps.get("def_addr")))){*/
            if ("0".equals((String) maps.get("def_addr")) ){
                esMemberAddress.setDefAddr(0);//0 不是默认
            } else {
                esMemberAddress.setDefAddr(1);//1 默认

               /* //其他地址变为非默认状态
                int count=esMemberAddressService.MemberAddressCount(userId);
                if(count>0){
                    int i = esMemberAddressService.updateByPrimaryKeySelective(esMemberAddress);
                }*/

            }

            esMemberAddress.setMobile((String) maps.get("mobile"));
            esMemberAddress.setName((String) maps.get("name"));
            esMemberAddress.setAddr((String) maps.get("addr"));
            esMemberAddress.setProvinceId(Integer.parseInt((String) maps.get("province_id")));
            esMemberAddress.setCityId(Integer.parseInt((String) maps.get("city_id")));
            esMemberAddress.setRegion((String) maps.get("region"));
            esMemberAddress.setProvince((String) maps.get("province"));
            esMemberAddress.setCity((String) maps.get("city"));
            esMemberAddress.setRegionId(Integer.parseInt((String) maps.get("region_id")));
            esMemberAddress.setMemberId(userId);
            int i = esMemberAddressService.insertSelective(esMemberAddress);

            if (i > 0) {
                map.put("mes", "保存成功");
            } else {
                map.put("mes", "保存失败");
            }
        } else if (from.equals("2")) {
            if (null != (String) maps.get("addr_id")) {
                esMemberAddress = esMemberAddressService.selectByPrimaryKey(Integer.parseInt((String) maps.get("addr_id")));
                esMemberAddress.setMobile((String) maps.get("mobile"));
                esMemberAddress.setName((String) maps.get("name"));
                esMemberAddress.setAddr((String) maps.get("addr"));
                esMemberAddress.setProvinceId(Integer.parseInt((String) maps.get("province_id")));
                esMemberAddress.setCityId(Integer.parseInt((String) maps.get("city_id")));
                esMemberAddress.setRegion((String) maps.get("region"));
                esMemberAddress.setProvince((String) maps.get("province"));
                esMemberAddress.setCity((String) maps.get("city"));
                esMemberAddress.setDefAddr(Integer.parseInt((String) maps.get("def_addr")));
                esMemberAddress.setRegionId(Integer.parseInt((String) maps.get("region_id")));
                esMemberAddress.setAddrId(Integer.parseInt((String) maps.get("addr_id")));
                esMemberAddress.setMemberId(userId);
                int i = esMemberAddressService.updateByPrimaryKeySelective(esMemberAddress);
                if (i > 0) {
                    map.put("mes", "更新成功");
                } else {
                    map.put("mes", "更新失败");
                }
            } else {
                map.put("mes", "提交数据不全");
            }

        } else if (from.equals("3")) {
            if (null != ((String) maps.get("addr_id"))) {
                int i = esMemberAddressService.deleteByPrimaryKey(Integer.parseInt((String)maps.get("addr_id")));
                if (i > 0) {
                    map.put("mes", "删除成功");
                } else {
                    map.put("mes", "删除失败");
                }
            } else {
                map.put("mes", "提交数据不全");
            }
        }
        return map;
    }



    /*public void setName(String name) {
        this.name = name;
    }

    public void setMoblie(Integer moblie) {
        this.moblie = moblie;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setAddr_id(Integer addr_id) {
        this.addr_id = addr_id;
    }

    public void setDef_addr(Integer def_addr) {
        this.def_addr = def_addr;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }
*/

}
