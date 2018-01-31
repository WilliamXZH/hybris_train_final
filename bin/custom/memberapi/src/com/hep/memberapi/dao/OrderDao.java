package com.hep.memberapi.dao;

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
public interface OrderDao {
    /**
     * 查询两个时间戳之间产生变更或者新增的订单
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    Collection<OrderModel> searchBetweenDate(Date startDate, Date endDate) throws Exception;
}
