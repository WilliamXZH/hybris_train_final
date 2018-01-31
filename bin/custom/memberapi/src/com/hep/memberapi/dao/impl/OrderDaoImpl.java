package com.hep.memberapi.dao.impl;

import com.hep.memberapi.dao.OrderDao;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/25 09:06
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public Collection<OrderModel> searchBetweenDate(Date startDate, Date endDate) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT {" + OrderModel.PK + "} FROM {" + OrderModel._TYPECODE + "} WHERE 1=1");
        Map<String, Object> param = new HashMap<>();
        if (endDate != null && startDate != null) {
            sqlBuilder.append(" AND {" + OrderModel.MODIFIEDTIME + "} BETWEEN ?startDate AND ?endDate ");
            param.put("startDate", startDate);
            param.put("endDate", endDate);
        } else if (startDate != null) {
            sqlBuilder.append(" AND {" + OrderModel.MODIFIEDTIME + "} >?startDate");
            param.put("startDate", startDate);
        } else if (endDate != null) {
            sqlBuilder.append(" AND {" + OrderModel.MODIFIEDTIME + "} <?endDate");
            param.put("endDate", endDate);
        }
        FlexibleSearchQuery query = new FlexibleSearchQuery(sqlBuilder);
        query.addQueryParameters(param);
        SearchResult<OrderModel> searchResult = flexibleSearchService.search(query);
        return searchResult.getResult();
    }
}
