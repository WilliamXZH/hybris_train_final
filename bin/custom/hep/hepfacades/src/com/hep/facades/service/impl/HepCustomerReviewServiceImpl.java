package com.hep.facades.service.impl;

import com.hep.core.model.HepCustomerReviewModel;
import com.hep.facades.service.HepCustomerReviewService;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.customerreview.model.CustomerReviewModel;
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
 * @DATE 2018/1/26  9:52
 */
@Component("hepCustomerReviewService")
public class HepCustomerReviewServiceImpl implements HepCustomerReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HepCustomerReviewServiceImpl.class);

    @Autowired
    FlexibleSearchService flexibleSearchService;

    @Override
    public CustomerModel getCustomerByUid(String uid){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select {pk} from {customer}");
        stringBuilder.append(" where {uid}=?uid");
        FlexibleSearchQuery query = new FlexibleSearchQuery(stringBuilder);
        query.addQueryParameter("uid",uid);
        LOGGER.info("begin to search========"+query);
        CustomerModel customerModel = null;
        try{
            customerModel = flexibleSearchService.searchUnique(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return customerModel;
        }
    }

    @Override
    public ProductModel getProductByCode(String code){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select {pk} from {product! as p} ");
        stringBuilder.append("where {p.catalogversion}=");
        stringBuilder.append("({{select {cvn.pk} ");
        stringBuilder.append("from {catalogversion as cvn left join catalog as cg on {cvn.catalog}={cg.pk}} ");
        stringBuilder.append("where {cg.id}='electronicsProductCatalog' and {cvn.version}='Staged'}})");
        stringBuilder.append("and {p.code}=?code");
        FlexibleSearchQuery query = new FlexibleSearchQuery(stringBuilder);
        query.addQueryParameter("code",code);
        LOGGER.info("begin to search========"+query);
        ProductModel productModel = null;
        try{
            productModel = flexibleSearchService.searchUnique(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return productModel;
        }
    }

    @Override
    public OrderEntryModel getOrderEntryByOrder(String code){
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select {pk} from ");
        stringBuilder.append("{orderentry as on join order as o on {on.order}={o.pk}} ");
        stringBuilder.append("where {o.code}=?code");
        FlexibleSearchQuery query = new FlexibleSearchQuery(stringBuilder);
        query.addQueryParameter("code",code);
        LOGGER.info("begin to search========"+query);
        OrderEntryModel orderEntryModel = null;
        try{
            orderEntryModel = flexibleSearchService.searchUnique(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return orderEntryModel;
        }
    }

    @Override
    public List<HepCustomerReviewModel> findHepCustomerReviewByCode(HepCustomerReviewModel hepCustomerReviewModel) {
        //查找有CustomerModel已经存在该uid，存在就修改该数据。
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select {pk} from {hepCustomerReview}");
        stringBuilder.append("where {code}=?code");
        FlexibleSearchQuery query = new FlexibleSearchQuery(stringBuilder);
        query.addQueryParameter("code",hepCustomerReviewModel.getCode());
        LOGGER.info("begin to search========"+query);
        return flexibleSearchService.<HepCustomerReviewModel>search(query).getResult();
    }


}
