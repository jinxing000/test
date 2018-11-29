package com.ef.golf.service.impl;

import com.ef.golf.mapper.EsSubjectMapper;
import com.ef.golf.mapper.GoodsMapper;
import com.ef.golf.pojo.EsSubject;
import com.ef.golf.service.EsSubjectService;
import com.ef.golf.util.StringUtil;
import com.ef.golf.vo.GoodsHotSpecVo;
import com.ef.golf.vo.GoodsSpecVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * com.ef.golf.service.impl
 * Administrator
 * 2018/10/23 14:31
 */
@Service
public class EsSubjectServiceImpl implements EsSubjectService {

    @Autowired
    private EsSubjectMapper esSubjectMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override/** 查询专题 取到专题下商品 封装数据 */
    public List<EsSubject> findSpecialList() {
        List<EsSubject> eslist = esSubjectMapper.findSpecialList();

        if(null!=eslist&&0<eslist.size()){
            for (EsSubject es:eslist){/** 遍历专题 */
                /** 封装goods信息 */
                List<GoodsHotSpecVo> gsv = new ArrayList<>();
                /** 处理json串取goodsId */
                List list = StringUtil.strRepeat(es.getGoodsIds());
                /** 拿到goodsid获取商品信息 */
                for (Object goodsId:list){
                    if (StringUtils.isNotBlank(goodsId.toString())){
                        GoodsHotSpecVo goods = goodsMapper.selectGoodsDetail(Integer.valueOf(goodsId.toString()));
                        gsv.add(goods);
                    }
                }
                /** 拿到商品信息封装到subject 取四个*/
                es.setGoodsHotSpecVo(gsv.subList(0,4));
            }
        }
        return eslist;
    }

    @Override
    public EsSubject findSpecialListBySubjectId(Integer subjectId) {
       EsSubject subject = esSubjectMapper.findSpecialListBySubjectId(subjectId);
        /** 处理json串取goodsId */
        List list = StringUtil.strRepeat(subject.getGoodsIds());

        /** 封装goods信息 */
        List<GoodsHotSpecVo> gsv = new ArrayList<>();

        /** 拿到goodsid获取商品信息 */
        for (Object goodsId:list){
            if(StringUtils.isNotBlank(goodsId.toString())){
                GoodsHotSpecVo goods = goodsMapper.selectGoodsDetail(Integer.valueOf(goodsId.toString()));
                gsv.add(goods);
            }
        }
        subject.setGoodsHotSpecVo(gsv);

        return subject;
    }
}
