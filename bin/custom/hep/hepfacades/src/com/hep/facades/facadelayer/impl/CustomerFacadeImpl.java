package com.hep.facades.facadelayer.impl;

import com.hep.facades.facadelayer.CustomerFacade;
import com.hep.facades.service.CustomerService;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2018/1/24  18:23
 */
@Component("customerFacade1")
public class CustomerFacadeImpl implements CustomerFacade{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFacadeImpl.class);

    private CustomerModel customerModel;

    @Autowired
    @Qualifier("customerReverseConverter")
    private Converter<CustomerData,CustomerModel> customerDataCustomerModelConverter;

    @Autowired
    private ModelService modelService;

    @Autowired
    @Qualifier("CustomerService1")
    private CustomerService customerService;

    @Override
    public void modifyListCustomer(List<CustomerData> customerDataList) throws Exception{
        for (CustomerData customerData:
                customerDataList) {
            customerModel = new CustomerModel();
            customerModel.setUid(customerData.getUid());
            List<CustomerModel> customerModelList = customerService.findCustomerByCode(customerModel);
            if(customerModelList.size() > 0){
                //将customerData中与customerModel中不一致属性转换
                customerModel = customerDataCustomerModelConverter.convert(customerData,customerModelList.get(0));
            }else{
                //将customerData转换未customerModel数据
                customerModel = customerDataCustomerModelConverter.convert(customerData);
            }
            LOGGER.info("customerData convert customerModel success,and the customerModel.name is:"+customerModel.getName()+";the customerModel.uid is:"+customerModel.getUid());
            modelService.save(customerModel);
        }
    }
}
