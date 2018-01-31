package com.hep.memberapi.facade.impl;

import com.hep.facades.product.dto.memberapi.OrderDTO;
import com.hep.memberapi.facade.MemberApiOrderFacade;
import com.hep.memberapi.service.OrderService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 20:09
 */
@Component
public class MemberApiOrderFacadeImpl implements MemberApiOrderFacade {
    private static final Log LOGGER = LogFactory.getLog(MemberApiOrderFacadeImpl.class);
    @Autowired
    @Qualifier("memberapiOrderConverter")
    private Converter<OrderModel, OrderDTO> orderConverter;

    @Autowired
    private OrderService orderService;

    @Override
    public Collection<OrderDTO> searchBetweenDate(Date startDate, Date endDate) {
        Collection<OrderModel> orderModels = null;
        try {
            orderModels = orderService.searchBetweenDate(startDate, endDate);
        } catch (Exception e) {
            LOGGER.error("Query OrderModel error.message:", e);
            e.printStackTrace();
        }
        return orderConverter.convertAll(orderModels);
    }
}
