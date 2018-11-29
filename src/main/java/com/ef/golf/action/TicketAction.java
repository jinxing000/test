package com.ef.golf.action;

import com.ef.golf.core.service.AbstractService;
import com.ef.golf.service.TicketSecondService;
import com.ef.golf.vo.TicketSeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TicketAction extends AbstractService {
    @Autowired
    TicketSecondService secondService;
    //@NotNull(message = "userId 不能为空")
    private Integer userId;

    @Override
    public Object doService() throws Exception {

        List<TicketSeVo> list = secondService.getTicketList(userId);

        return list;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
