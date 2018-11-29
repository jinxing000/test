package com.ef.golf.action;

import com.ef.golf.common.Constant;
import com.ef.golf.core.service.AbstractService;
import com.ef.golf.exception.QueryException;
import com.ef.golf.pojo.Banner;
import com.ef.golf.service.BannerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xzw on 2017/9/22.
 */


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BannerAction extends AbstractService {

    @Autowired
    private BannerService bs;

    private final Log log = LogFactory.getLog(BannerAction.class);
    //通过分组传入banner所属分组
    //@NotNull
    private String grouping;

    public Object doService() throws QueryException {

        List<Banner> bannerList = bs.bannerByGrouping(grouping);
        System.out.print(bannerList.size());
        if (bannerList == null || bannerList.size() <= 0) {
            throw new QueryException(Constant.ERR_NODATES - 10);
        }
        return bannerList;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }
}
