package com.hep.facades.facadelayer;

import de.hybris.platform.commercefacades.user.data.CustomerData;

import java.util.List;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2018/1/24  18:22
 */
public interface CustomerFacade {

    void modifyListCustomer(List<CustomerData> customerDataList) throws Exception;
}
