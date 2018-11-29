package com.ef.golf.mapper;

import com.ef.golf.pojo.EsProductStore;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EsProductStoreMapper {
    int deleteByPrimaryKey(Integer storeid);

    int insert(EsProductStore record);

    int insertSelective(EsProductStore record);

    EsProductStore selectByPrimaryKey(Integer storeid);

    int updateByPrimaryKeySelective(EsProductStore record);

    int updateByPrimaryKey(EsProductStore record);

    int updateEsProductStore (Map map);
//查看仓库中是否存某商品
    // select count(0) from es_product_store where goodsid=? and depotid=?", goodsid,depotid ;
    int selectAmountByGoodsIdAndDepotId(@Param("goodsid") int goodsid, @Param("depotid") int depotid);

    EsProductStore selectProductStore(@Param("depotid") int depotid, @Param("goodsid")int goodsid, @Param("productid")int productid);
}