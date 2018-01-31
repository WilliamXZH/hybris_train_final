package com.hep.memberapi.facade.impl;

import com.hep.facades.product.dto.memberapi.ProductDTO;
import com.hep.memberapi.facade.MemberApiProductFacade;
import com.hep.memberapi.service.ProductService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 20:09
 */
@Component
public class MemberApiProductFacadeImpl implements MemberApiProductFacade {
    private static final Log LOGGER = LogFactory.getLog(MemberApiProductFacadeImpl.class);

    @Autowired
    @Qualifier("memberapiProductConverter")
    private Converter<ProductModel, ProductDTO> productConverter;

    @Autowired
    private ProductService productService;

    @Override
    public Collection<ProductDTO> searchBetweenDate(Date startDate, Date endDate) {
        Collection<ProductModel> productModels = null;
        try {
            productModels = productService.searchBetweenDate(startDate, endDate);
        } catch (Exception e) {
            LOGGER.error("Query ProductModel error.message:", e);
            e.printStackTrace();
        }
        return productConverter.convertAll(productModels);
    }
}
