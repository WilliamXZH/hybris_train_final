package com.hep.facades.service;

import com.hep.core.model.HepCustomerReviewModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2018/1/26  9:47
 */
public interface HepCustomerReviewService {

    CustomerModel getCustomerByUid(String Uid);

    ProductModel getProductByCode(String code);

    OrderEntryModel getOrderEntryByOrder(String code);

    List<HepCustomerReviewModel> findHepCustomerReviewByCode(HepCustomerReviewModel hepCustomerReviewModel);
}
