package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 购物车点击立即购买请求此接口查询
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BuyGoodsAction extends AbstractService {
}
