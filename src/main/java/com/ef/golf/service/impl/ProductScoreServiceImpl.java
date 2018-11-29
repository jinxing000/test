package com.ef.golf.service.impl;

import com.ef.golf.mapper.Product_scoreMapper;
import com.ef.golf.mapper.Score_attrMapper;
import com.ef.golf.pojo.Product_score;
import com.ef.golf.pojo.Score_attr;
import com.ef.golf.service.ProductScoreService;
import com.ef.golf.vo.QuanziScoreVo;
import com.ef.golf.vo.SiteScoreVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductScoreServiceImpl implements ProductScoreService {

    @Resource
    private Product_scoreMapper product_scoreMapper;
    @Resource
    private Score_attrMapper scoreAttrMapper;


    public List<SiteScoreVo> selectScoreById(Map<String,Object> map) {
        List<SiteScoreVo> scoreVoList=product_scoreMapper.selectScoreById(map);
        if(scoreVoList==null||scoreVoList.size()==0){
            scoreVoList=new ArrayList<>();
            List<Score_attr> score_attrList=scoreAttrMapper.selectScoreAttr(map.get("attrAscription")+"");
            for(Score_attr score_attr:score_attrList){
                SiteScoreVo siteScoreVo=new SiteScoreVo();
                siteScoreVo.setAttr_id(score_attr.getAttrId());
                siteScoreVo.setAttr_nam(score_attr.getAttrNam());
                siteScoreVo.setScore("0");
                scoreVoList.add(siteScoreVo);
            }
        }
        return scoreVoList;
    }

    @Override
    public int insertSelective(Product_score record) {
        return product_scoreMapper.insertSelective(record);
    }

    public int updateScore(String product_type,Integer product_id,Integer[] attr_id){
        int scoreEnd=0;
        for (int i=0;i<attr_id.length;i++){
            //判断产品的项目是否有平均分的记录
           int end= product_scoreMapper.selectScoreByIds(product_id,attr_id[i]);
           if(end<=0){
               //如果没有平均分的记录则新增一条
               //1.获取并计算平均分
               int scoreAvg=product_scoreMapper.selectAverageScore(product_id,attr_id[i]);
               //2.新增
               scoreEnd+=product_scoreMapper.insertScore(product_id,attr_id[i],product_type,String.valueOf(scoreAvg));
           }else{
               //如果有则更新
               //1.获取并计算平均分
               int scoreAvg=product_scoreMapper.selectAverageScore(product_id,attr_id[i]);
               //2.更新
               scoreEnd+=product_scoreMapper.updateScore(product_id,String.valueOf(scoreAvg));
           }
        }

        return scoreEnd;
    }

    public int insertScore(String product_type, Integer product_id, Integer[] attr_id, String score) {
        int end=0;
        if (attr_id.length>0) {
            for (int i=0;i<attr_id.length;i++) {
                end+= product_scoreMapper.insertScore(product_id,attr_id[i],product_type,score);
            }
        }
        return end;
    }

    @Override
    public List<QuanziScoreVo> getScore(Integer productId) {
        return product_scoreMapper.getScore(productId);
    }

    @Override
    public Double getScoreAvg(Integer productId) {
        return product_scoreMapper.getScoreAvg(productId);
    }
}
