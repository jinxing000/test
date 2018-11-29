package com.ef.golf.mapper;

import com.ef.golf.pojo.RedPackage;
import com.ef.golf.vo.QianRedPackageListVo;
import com.ef.golf.vo.RedPackageDetailsVo;
import com.ef.golf.vo.RedPackageRefundVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RedPackageMapper {
    int deleteByPrimaryKey(String id);

    int insert(RedPackage record);

    int insertSelective(RedPackage record);

    RedPackage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RedPackage record);

    int updateByPrimaryKey(RedPackage record);
    RedPackageDetailsVo selectShangRedPackageDetails(@Param("userId") Integer userId,@Param("redPackageId") String redPackageId);
    List<QianRedPackageListVo>selectQiangRedPackageList(String bigRedPackageId);

    // 红包退款
    List<RedPackageRefundVo> redPackageRefundMoney();

}