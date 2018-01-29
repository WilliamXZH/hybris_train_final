package com.hep.memberapi.service;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.Collection;
import java.util.Date;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/25 09:04
 */
public interface OrderService {
    /**
     * 查询两个时间戳之间产生变更或者新增的订单
     *
     * @param startDate 开始时间
     * @param nowDate 结束时间
     * @return
     * @throws Exception
     */
        Collection<OrderModel> searchBetweenDate(Date startDate, Date nowDate) throws Exception;
}
