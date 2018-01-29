package com.hep.facades.populators;

import com.hep.core.model.HepCustomerReviewModel;
import com.hep.facades.review.data.HepCustomerReviewData;
import com.hep.facades.service.HepCustomerReviewService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2018/1/25  20:11
 */
public class HepCustomerReviewPopulator implements Populator<HepCustomerReviewData,HepCustomerReviewModel>{

    private static final Logger LOGGER = LoggerFactory.getLogger(HepCustomerReviewPopulator.class);

    @Autowired
    @Qualifier("hepCustomerReviewService")
    private HepCustomerReviewService hepCustomerReviewService;


    @Override
    public void populate(HepCustomerReviewData hepCustomerReviewData, HepCustomerReviewModel hepCustomerReviewModel){
        if(hepCustomerReviewData.getCode() != null){
            hepCustomerReviewModel.setCode(hepCustomerReviewData.getCode());
        }
        if(hepCustomerReviewData.getContent() != null){
            hepCustomerReviewModel.setContent(hepCustomerReviewData.getContent());
        }
        if(hepCustomerReviewData.getRating() != null){
            hepCustomerReviewModel.setRating(Double.valueOf(hepCustomerReviewData.getRating()));
        }
        if(hepCustomerReviewData.getCustomer() != null){
            hepCustomerReviewModel.setCustomer(hepCustomerReviewService.getCustomerByUid(hepCustomerReviewData.getCustomer()));
        }
        if(hepCustomerReviewData.getProduct() != null){
            hepCustomerReviewModel.setProduct(hepCustomerReviewService.getProductByCode(hepCustomerReviewData.getProduct()));
        }
        if(hepCustomerReviewData.getOrderEntry() != null){
            hepCustomerReviewModel.setOrderEntry(hepCustomerReviewService.getOrderEntryByOrder(hepCustomerReviewData.getOrderEntry()));
        }
    }
}
