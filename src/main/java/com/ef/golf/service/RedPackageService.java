package com.ef.golf.service;

import com.ef.golf.exception.SystemException;
import com.ef.golf.pojo.RedPackage;
import com.ef.golf.pojo.SmallRedPackage;
import com.ef.golf.vo.QianRedPackageListVo;
import com.ef.golf.vo.RedPackageRefundVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/7.
 */
public interface RedPackageService {
    //发红包  写入大红包记录   更改发送红包者的余额   小红包记录需要加入吗？

    Map<String,Object> sendRedPackage(RedPackage redPackage, List<SmallRedPackage> smallRedPackageList, String uid) throws SystemException;

    //抢红包
    Map<String,Object> qiangRedPackage(SmallRedPackage smallRedPackage) throws SystemException;
    //红包回收

    //红包查询
    Map<String,Object> selectRedPackageDetails(Integer userId,String redPackageId) throws Exception;
    //红包列表查询
    List<SmallRedPackage> selectByPrimaryKey(String bigRedPackageId);
    Integer smallRedPackageCount(String redPackageId);
    List<QianRedPackageListVo> selectQiangRedPackageList(String redPackageId) throws Exception;
    RedPackage selectBigByPrimaryKey(String id);
    // 红包退款
    List<RedPackageRefundVo> redPackageRefundMoney();
    //更新红包状态
    int updateByPrimaryKeySelective(RedPackage record);
}
