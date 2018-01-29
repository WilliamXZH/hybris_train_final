package com.hep.memberapi.service.impl;

import com.hep.memberapi.dao.OrderDao;
import com.hep.memberapi.service.OrderService;
import de.hybris.platform.core.model.order.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/25 09:05
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public Collection<OrderModel> searchBetweenDate(Date startDate, Date endDate) throws Exception {
        return orderDao.searchBetweenDate(startDate, endDate);
    }
}
