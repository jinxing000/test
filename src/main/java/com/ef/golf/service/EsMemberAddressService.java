package com.ef.golf.service;

import com.ef.golf.pojo.EsMemberAddress;
import com.ef.golf.pojo.EsTypeArea;

import java.util.List;
import java.util.Map;

public interface EsMemberAddressService {
    public int insertSelective(EsMemberAddress record);
    public  int updateByPrimaryKeySelective(EsMemberAddress record);
    public EsMemberAddress selectByPrimaryKey(Integer addrId);
    public List<EsMemberAddress> getMemberAddress(Integer user_id);//会员地址
    public int MemberAddressCount(Integer user_id);//一个会员一共多少个地址
    public int deleteByPrimaryKey(Integer addrId);
    public   List<EsTypeArea> getList(Integer type_id);
    public   List<EsTypeArea> getListOne();
    public  EsMemberAddress getAddress(Map map);

    EsMemberAddress specilAddress(Integer userId);

    //获取用户默认收货地址
    EsMemberAddress getUserAddressByUserId(Integer userId);
}
