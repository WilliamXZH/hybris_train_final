package com.hep.facades.service;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2018/1/29  9:00
 */
public interface CustomerService {

    List<CustomerModel> findCustomerByCode(CustomerModel customerModel);
}
