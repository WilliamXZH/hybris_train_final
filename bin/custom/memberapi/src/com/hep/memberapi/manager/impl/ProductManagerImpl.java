package com.hep.memberapi.manager.impl;

import com.google.gson.Gson;
import com.hep.facades.product.dto.memberapi.ProductDTO;
import com.hep.memberapi.common.HttpUtils;
import com.hep.memberapi.common.MemberApiResultEntity;
import com.hep.memberapi.common.PropertiesUtils;
import com.hep.memberapi.common.RequestEntity;
import com.hep.memberapi.facade.MemberApiProductFacade;
import com.hep.memberapi.manager.ProductManager;
import de.hybris.platform.commercefacades.order.data.OrderData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 15:38
 */
@Component
public class ProductManagerImpl implements ProductManager {

    private static final Log LOGGER = LogFactory.getLog(ProductManagerImpl.class);

    public static final String PRODUCT_URL_KEY = "hep.post.products.url";
    public static final String LAST_PUSH_TIME_KEY = "memberapi.post.product.lasttime";

    public static final String API_URL_PROPERTIES = "memberapi.properties";
    public static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

    @Autowired
    private MemberApiProductFacade productFacade;

    @Override
    public void pushProducts() {
        Properties properties = PropertiesUtils.getProperties(API_URL_PROPERTIES);
        if (properties == null) {
            return;
        }
        String url = properties.getProperty(PRODUCT_URL_KEY);
        if (StringUtils.isEmpty(url)) {
            LOGGER.error("No url config with member system api is not found. please make sure config is successful.");
            return;
        }

        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

        Date lastDate = null;
        String lastDateLong = properties.getProperty(LAST_PUSH_TIME_KEY);
        if (!StringUtils.isEmpty(lastDateLong)) {
            try {
                lastDate = new Date(Long.parseLong(lastDateLong));
            } catch (Exception e) {
                LOGGER.error("Last push time format error.will return all products.");
                e.printStackTrace();
            }
        }

        Collection<ProductDTO> productDTOS = productFacade.searchBetweenDate(lastDate, nowDate);

        Gson gson = new Gson();
        RequestEntity request = new RequestEntity();
        request.setLasttime(new SimpleDateFormat(RequestEntity.TIME_FORMAT_PATTERN).format(nowDate));
        request.setKey("123");
        request.setSize(productDTOS.size() + "");
        request.setData(gson.toJson(productDTOS).replace("\"","'"));
        try {
            String resultString = HttpUtils.doPost(gson.toJson(request), url);
            MemberApiResultEntity result = gson.fromJson(resultString, MemberApiResultEntity.class);
            if(result.isSuccess()){
                LOGGER.info("Push the product to the Member System successful.Return message:" + result.getMessage());
                LOGGER.info("Last push time:" + sdf.format(nowDate));
                properties.setProperty(LAST_PUSH_TIME_KEY, nowDate.getTime()+"");
                PropertiesUtils.store(properties, API_URL_PROPERTIES);
            }else {
                throw new Exception(result.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Push the product to the Member System failed.Return message:" + e.getMessage());
        }
    }
}
