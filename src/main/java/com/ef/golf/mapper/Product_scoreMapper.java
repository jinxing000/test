package com.ef.golf.mapper;

import com.ef.golf.pojo.Product_score;
import com.ef.golf.vo.QuanziScoreVo;
import com.ef.golf.vo.SiteScoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface Product_scoreMapper {
    int deleteByPrimaryKey(Integer productScoreId);

    int insert(Product_score record);

    int insertSelective(Product_score record);

    Product_score selectByPrimaryKey(Integer productScoreId);

    int updateByPrimaryKeySelective(Product_score record);

    int updateByPrimaryKey(Product_score record);

    List<SiteScoreVo> selectScoreById(Map map);

    Double getScoreAvg(Integer productId);

    /**
     * @param product_id  产品ID
     * @param score 产品每项平均分
     * @return
     * 更新产品每项的平均分
     */
    int updateScore(@Param("product_id") Integer product_id, @Param("score") String score);

    /**
     * @param product_type  产品类型
     * @param product_id 产品ID
     * @param attr_id 产品项目ID
     * @param score 每个项目平均分
     * @return
     * 插入一条新的产品平均分
     */
    int insertScore(@Param("product_id") Integer product_id,@Param("attr_id") Integer attr_id,@Param("productType") String product_type,@Param("score") String score);

    /**
     * @param product_id 产品id
     * @param attr_id 产品项目id
     * @return
     * 查询产品某一项的平均分
     */
    int selectScoreByIds(@Param("product_id")Integer product_id,@Param("attr_id")Integer attr_id);

    /**
     * @param product_id
     * @param attr_id
     * @return
     * 计算平均分
     */
    int selectAverageScore(@Param("product_id") Integer product_id, @Param("attr_id") Integer attr_id);

    /**
     * 获取用户综合评分
     * @param productId
     * @return
     */
    List<QuanziScoreVo> getScore(Integer productId);



}