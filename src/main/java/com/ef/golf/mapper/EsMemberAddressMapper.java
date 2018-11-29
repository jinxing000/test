package com.ef.golf.mapper;

import com.ef.golf.pojo.EsMemberAddress;

import java.util.List;
import java.util.Map;

public interface EsMemberAddressMapper {
    int deleteByPrimaryKey(Integer addrId);

    int insert(EsMemberAddress record);

    int insertSelective(EsMemberAddress record);

    EsMemberAddress selectByPrimaryKey(Integer addrId);

    int updateByPrimaryKeySelective(EsMemberAddress record);

    int updateByPrimaryKey(EsMemberAddress record);
    List<EsMemberAddress> getMemberAddress(Integer user_id);//会员地址
    int MemberAddressCount(Integer user_id);//一个会员一共多少个地址

    EsMemberAddress getUserAddressByUserId(Integer userId);

    EsMemberAddress getAddress(Map map);

    EsMemberAddress specilAddress(Integer userId);

    //设为默认地址时 更新其他地址的状态
    int updateOtherDefAddr(EsMemberAddress esMemberAddress);

//获取默认收货地址
    EsMemberAddress getDefaultAddress(Integer userId);
}