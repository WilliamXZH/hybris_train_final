package com.hep.facades.service.impl;

import com.hep.core.model.HepCustomerReviewModel;
import com.hep.facades.facadelayer.impl.CustomerFacadeImpl;
import com.hep.facades.service.CustomerService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2018/1/29  9:00
 */
@Component("CustomerService1")
public class CustomerServicImpl implements CustomerService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFacadeImpl.class);

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<CustomerModel> findCustomerByCode(CustomerModel customerModel) {
        //查找有CustomerModel已经存在该uid，存在就修改该数据。
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select {pk} from {customer}");
        stringBuilder.append("where {uid}=?uid");
        FlexibleSearchQuery query = new FlexibleSearchQuery(stringBuilder);
        query.addQueryParameter("uid",customerModel.getUid());
        LOGGER.info("begin to search========"+query);
        return flexibleSearchService.<CustomerModel>search(query).getResult();
    }
}
