package com.hep.hand.controllers;

import com.hep.facades.facadelayer.CustomerFacade;
import com.hep.facades.facadelayer.HepCustomerReviewFacade;
import com.hep.facades.review.data.HepCustomerReviewData;
import com.hep.hand.beans.Msg;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author 杨黎
 * @Title   同步数据
 * @description
 * @DATE 2018/1/24  17:14
 */
@Controller
@RequestMapping(value = "/sync")
public class HepSyncController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HepSyncController.class);

    private Msg msg;

    @Autowired
    @Qualifier("customerFacade1")
    private CustomerFacade customerFacade;

    @Autowired
    @Qualifier("hepCustomerReviewFacade")
    private HepCustomerReviewFacade hepCustomerReviewFacade;
    /**
     * description 用于同步customer数据
     * @param customerDataList
     * @return  Msg
     */
    @RequestMapping(value = "/customer",method = RequestMethod.POST)
    @ResponseBody
    public Msg syncCustomerData(@RequestBody final List<CustomerData> customerDataList){
        msg = new Msg();
        try {
            customerFacade.modifyListCustomer(customerDataList);
            msg.setStatus(1);
            msg.setMessage("Success!!!!!");
        } catch (Exception e) {
            msg.setStatus(0);
            msg.setMessage(e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/customer1",method = RequestMethod.POST)
    @ResponseBody
    public Msg syncCustomerData1(final HttpServletRequest request){
        msg = new Msg();
        msg.setStatus(1);
        msg.setMessage("success!");
        msg.setData(null);

        try {
            BufferedReader bufferedReader = request.getReader();
            String str,wholeStr = "";
            while((str = bufferedReader.readLine()) != null){
                wholeStr += str;
            }
            System.out.println(wholeStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }


    @RequestMapping(value = "/hepcustomerReview",method = RequestMethod.POST)
    @ResponseBody
    public Msg syncHepCustomerReviewData(@RequestBody final List<HepCustomerReviewData> hepCustomerReviewDataList) {
        msg = new Msg();
        try {
            hepCustomerReviewFacade.modifyHepCustomerReviewList(hepCustomerReviewDataList);
            msg.setStatus(1);
            msg.setMessage("Success!!!!!");
        } catch (Exception e) {
            msg.setStatus(0);
            msg.setMessage(e.getMessage());
        }
        return msg;
    }


}
