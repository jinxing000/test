package com.ef.golf.service.impl;

import com.ef.golf.mapper.WithdrawalMapper;
import com.ef.golf.pojo.Withdrawal;
import com.ef.golf.service.WithdrawalService;
import com.ef.golf.util.PageBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/6/15 20:11
 */
@Repository
public class WithdrawalServiceImpl implements WithdrawalService{
    @Resource
    private WithdrawalMapper withdrawalMapper;
    @Override
    public int deleteByPrimaryKey(Integer withdrawalId) {
        return withdrawalMapper.deleteByPrimaryKey(withdrawalId);
    }

    @Override
    public int insert(Withdrawal record) {
        return withdrawalMapper.insert(record);
    }

    @Override
    public int insertSelective(Withdrawal record) {
        return withdrawalMapper.insertSelective(record);
    }

    @Override
    public Withdrawal selectByPrimaryKey(Integer withdrawalId) {
        return withdrawalMapper.selectByPrimaryKey(withdrawalId);
    }

    @Override
    public int updateByPrimaryKeySelective(Withdrawal record) {
        return withdrawalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Withdrawal record) {
        return withdrawalMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageBean getWithdrawalRecordByUserId(Integer userId, String state, Integer pageNo, Integer pageSize) {
        PageBean pageBean = new PageBean();
        List<Withdrawal> list = withdrawalMapper.getWithdrawalRecordByUserId(userId,state,pageNo,pageSize);
        Integer count = withdrawalMapper.getWithdrawalRecordByUserIdCount(userId);
        if(null!=count&&count>0){
            pageBean.setTotalCount(count);
        }
        pageBean.setPageNo((pageNo-1)*pageSize);
        pageBean.setPageSize(pageSize);
        pageBean.setResultList(list);
        return pageBean;
    }
}
