package com.hep.facades.populators.memberapi;

import com.hep.facades.product.dto.memberapi.OrderDTO;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.text.SimpleDateFormat;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 19:51
 */
public class OrderPopulator implements Populator<OrderModel, OrderDTO> {
    @Override
    public void populate(OrderModel orderModel, OrderDTO orderDTO) throws ConversionException {
        orderDTO.setCode(orderModel.getCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orderDTO.setDate(sdf.format(orderModel.getDate()));
        orderDTO.setIscode(orderModel.getCurrency().getIsocode());
        orderDTO.setUid(orderModel.getUser().getUid());
    }
}
