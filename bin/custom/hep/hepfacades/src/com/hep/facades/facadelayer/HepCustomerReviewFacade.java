package com.hep.facades.facadelayer;

import com.hep.facades.review.data.HepCustomerReviewData;

import java.util.List;

/**
 * @author 杨黎
 * @Title
 * @description
 * @DATE 2018/1/26  10:30
 */
public interface HepCustomerReviewFacade {

    void modifyHepCustomerReviewList(List<HepCustomerReviewData> hepCustomerReviewDataList) throws Exception;
}
