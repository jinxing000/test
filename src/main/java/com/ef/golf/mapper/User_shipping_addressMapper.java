package com.ef.golf.mapper;

import com.ef.golf.pojo.User_shipping_address;

public interface User_shipping_addressMapper {
    int deleteByPrimaryKey(Integer addressId);

    int insert(User_shipping_address record);

    int insertSelective(User_shipping_address record);

    User_shipping_address selectByPrimaryKey(Integer addressId);

    int updateByPrimaryKeySelective(User_shipping_address record);

    int updateByPrimaryKey(User_shipping_address record);
}