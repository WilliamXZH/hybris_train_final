package com.hep.facades.facadelayer.impl;

import com.hep.core.model.HepCustomerReviewModel;
import com.hep.facades.facadelayer.HepCustomerReviewFacade;
import com.hep.facades.review.data.HepCustomerReviewData;
import com.hep.facades.service.HepCustomerReviewService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
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
 * @DATE 2018/1/26  10:32
 */
@Component("hepCustomerReviewFacade")
public class HepCustomerReviewFacadeImpl implements HepCustomerReviewFacade{

    private static final Logger LOGGER = LoggerFactory.getLogger(HepCustomerReviewFacadeImpl.class);

    private HepCustomerReviewModel hepCustomerReviewModel;

    @Autowired
    @Qualifier("hepCustomerReviewConvert")
    private Converter<HepCustomerReviewData,HepCustomerReviewModel> customerReviewDataConverter;

    @Autowired
    private ModelService modelService;

    @Autowired
    private FlexibleSearchService flexibleSearchService;

    @Autowired
    private HepCustomerReviewService hepCustomerReviewService;

    @Override
    public void modifyHepCustomerReviewList(List<HepCustomerReviewData> hepCustomerReviewDataList) throws Exception {
        for (HepCustomerReviewData hepCustomerReviewData:
                hepCustomerReviewDataList) {
            hepCustomerReviewModel = new HepCustomerReviewModel();
            hepCustomerReviewModel.setCode( hepCustomerReviewData.getCode());

//            //查找有CustomerModel已经存在该uid，存在就修改该数据。
//            final StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("select {pk} from {hepCustomerReview}");
//            stringBuilder.append("where {code}=?code");
//            FlexibleSearchQuery query = new FlexibleSearchQuery(stringBuilder);
//            query.addQueryParameter("code",hepCustomerReviewModel.getCode());
//            LOGGER.info("begin to search========"+query);
//            List<HepCustomerReviewModel> hepCustomerReviewModelList = flexibleSearchService.<HepCustomerReviewModel>search(query).getResult();
            List<HepCustomerReviewModel> hepCustomerReviewModelList = hepCustomerReviewService.findHepCustomerReviewByCode(hepCustomerReviewModel);
            if(hepCustomerReviewModelList.size() > 0){
                LOGGER.info("this review has existed,begin to update a few data");
                hepCustomerReviewModel = customerReviewDataConverter.convert(hepCustomerReviewData,hepCustomerReviewModelList.get(0));
            }else{
                hepCustomerReviewModel = customerReviewDataConverter.convert(hepCustomerReviewData);
            }
            modelService.save(hepCustomerReviewModel);
        }
    }
}
