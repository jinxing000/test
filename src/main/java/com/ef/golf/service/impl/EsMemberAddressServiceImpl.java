package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsMemberAddressMapper;
import com.ef.golf.mapper.EsTypeAreaMapper;
import com.ef.golf.pojo.EsMemberAddress;
import com.ef.golf.pojo.EsTypeArea;
import com.ef.golf.service.EsMemberAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EsMemberAddressServiceImpl implements EsMemberAddressService {

    @Autowired
    EsMemberAddressMapper esMemberAddressMapper;
    @Autowired
    EsTypeAreaMapper area;

    @Override
    public EsMemberAddress getAddress(Map map) {
        return esMemberAddressMapper.getAddress(map);
    }

    @Override
    public EsMemberAddress specilAddress(Integer userId) {
        return esMemberAddressMapper.specilAddress(userId);
    }


    @Override
    public EsMemberAddress getUserAddressByUserId(Integer userId) {
        return esMemberAddressMapper.getUserAddressByUserId(userId);
    }

    @Override
    public int insertSelective(EsMemberAddress record) {
        int count=1;
        try{
            esMemberAddressMapper.insertSelective(record);
            if(1==record.getDefAddr()){//如果添加时把最新的这个地址设置为默认地址，那么其他地址就都设置为0
                record.setDefAddr(0);
                esMemberAddressMapper.updateOtherDefAddr(record);
            }

        }catch (Exception ex){
            count=0;
            ex.printStackTrace();
        }

        return count;
    }

    @Override
    public int updateByPrimaryKeySelective(EsMemberAddress record) {
        int count=1;
        //更新前先查询地址的默认情况
try{
    Integer oldDefAddr= esMemberAddressMapper.selectByPrimaryKey(record.getAddrId()).getDefAddr();
    Integer newDefAddr=record.getDefAddr();
    esMemberAddressMapper.updateByPrimaryKeySelective(record);
    if(oldDefAddr!=newDefAddr){
       if(newDefAddr==1){//新的默认，旧的是取消
            record.setDefAddr(0);
            esMemberAddressMapper.updateOtherDefAddr(record);
        }
    }
}catch (Exception ex){
    count=0;
    ex.printStackTrace();
}


        return count;
    }

    @Override
    public EsMemberAddress selectByPrimaryKey(Integer addrId) {
        return esMemberAddressMapper.selectByPrimaryKey(addrId);
    }

    @Override
    public List<EsTypeArea> getListOne() {
        return area.getListOne();
    }

    @Override
    public List<EsTypeArea> getList(Integer type_id) {
        return area.getList(type_id);
    }

    @Override
    public List<EsMemberAddress> getMemberAddress(Integer user_id) {
        return esMemberAddressMapper.getMemberAddress(user_id);
    }

    @Override
    public int deleteByPrimaryKey(Integer addrId) {
        return esMemberAddressMapper.deleteByPrimaryKey(addrId);
    }

    @Override
    public int MemberAddressCount(Integer user_id) {
        return esMemberAddressMapper.MemberAddressCount(user_id);
    }
}
