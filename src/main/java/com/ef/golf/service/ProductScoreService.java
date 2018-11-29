package com.ef.golf.service;

import com.ef.golf.pojo.Product_score;
import com.ef.golf.vo.QuanziScoreVo;
import com.ef.golf.vo.SiteScoreVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductScoreService {

    List<SiteScoreVo> selectScoreById(Map<String,Object> map);
    int insertSelective(Product_score record);

    /**
     *@param product_type 产品类型
     * @param product_id  产品ID
     * @param attr_id 产品服务项ID
     * @return
     * 更新产品每项的平均分
     */
    int updateScore(String product_type,Integer product_id,Integer[] attr_id);

    /**
     * @param product_type  产品类型
     * @param product_id 产品ID
     * @param attr_id 产品项目ID
     * @param score 每个项目平均分
     * @return
     * 插入一条新的产品平均分
     */
    int insertScore(String product_type,Integer product_id, Integer[] attr_id,String score);

    /**
     * 获取用户综合评分
     * @param productId
     * @return
     */
    List<QuanziScoreVo> getScore(Integer productId);
    Double getScoreAvg(Integer productId);

}
