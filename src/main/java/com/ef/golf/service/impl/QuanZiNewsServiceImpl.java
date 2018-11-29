package com.ef.golf.service.impl;

import com.ef.golf.mapper.Quanzi_newsMapper;
import com.ef.golf.service.QuanZiNewsService;
import com.ef.golf.vo.QuanZiNewsVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class QuanZiNewsServiceImpl implements QuanZiNewsService {

    @Resource
    private Quanzi_newsMapper qm;


    @Override
    public List<QuanZiNewsVo> getNewsByUserId(QuanZiNewsVo qzv) {
        return qm.getNewsByUserIdListPage(qzv);
    }

    @Override
    public int delNewsByUserId(Integer userId) {
        return qm.delNewsByUserId(userId);
    }

    @Override
    public int delNewsByNewsId(Integer nsid) {
        return qm.delNewsByNewsId(nsid);
    }
}
