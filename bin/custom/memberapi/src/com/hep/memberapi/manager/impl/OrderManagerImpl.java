package com.hep.memberapi.manager.impl;

import com.google.gson.Gson;
import com.hep.facades.product.dto.memberapi.OrderDTO;
import com.hep.memberapi.common.HttpUtils;
import com.hep.memberapi.common.MemberApiResultEntity;
import com.hep.memberapi.common.PropertiesUtils;
import com.hep.memberapi.common.RequestEntity;
import com.hep.memberapi.facade.MemberApiOrderFacade;
import com.hep.memberapi.manager.OrderManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 15:37
 */
@Component
public class OrderManagerImpl implements OrderManager {
    private static final Log LOGGER = LogFactory.getLog(OrderManagerImpl.class);

    public static final String ORDER_URL_KEY = "hep.post.orders.url";
    public static final String LAST_PUSH_TIME_KEY = "memberapi.post.order.lasttime";

    public static final String API_URL_PROPERTIES = "memberapi.properties";
    public static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

    @Autowired
    private MemberApiOrderFacade orderFacade;

    @Override
    public void pushOrders() {
        Properties properties = PropertiesUtils.getProperties(API_URL_PROPERTIES);
        if (properties == null) {
            return;
        }
        String url = properties.getProperty(ORDER_URL_KEY);
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
                LOGGER.error("Last push time format error.will return all orders.");
                e.printStackTrace();
            }
        }

        Collection<OrderDTO> orderDTOS = orderFacade.searchBetweenDate(lastDate, nowDate);
        Gson gson = new Gson();
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setLasttime(new SimpleDateFormat(RequestEntity.TIME_FORMAT_PATTERN).format(nowDate));
        requestEntity.setKey("123");
        requestEntity.setSize(orderDTOS.size() + "");
        requestEntity.setData(gson.toJson(orderDTOS).replace("\"","'"));

        try {
            String resultString = HttpUtils.doPost(gson.toJson(requestEntity), url);
            MemberApiResultEntity resultEntity = gson.fromJson(resultString, MemberApiResultEntity.class);
            if (resultEntity.isSuccess()) {
                LOGGER.info("Push the order to the Member System successful.Return message:" + resultEntity.getMessage());
                LOGGER.info("Last push time:" + sdf.format(nowDate));
                properties.setProperty(LAST_PUSH_TIME_KEY, nowDate.getTime()+"");
                PropertiesUtils.store(properties, API_URL_PROPERTIES);
            } else {
                throw new Exception(resultEntity.getMessage());
            }
        } catch (Exception e) {
            LOGGER.error("Push the order to the Member System failed.Return message:" + e.getMessage());
            e.printStackTrace();
        }

    }
}
