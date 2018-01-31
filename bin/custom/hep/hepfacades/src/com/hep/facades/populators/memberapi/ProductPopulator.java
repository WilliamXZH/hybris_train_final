package com.hep.facades.populators.memberapi;


import com.hep.facades.product.dto.memberapi.ProductDTO;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.stereotype.Component;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 19:53
 */
public class ProductPopulator implements Populator<ProductModel, ProductDTO> {
    @Override
    public void populate(ProductModel productModel, ProductDTO productDTO) throws ConversionException {
        productDTO.setApprovalStatus(productModel.getApprovalStatus().getCode());
        productDTO.setCode(productModel.getCode());
        productDTO.setCatalog(productModel.getCatalogVersion().getCatalog().getPk().toString());
        productDTO.setVersion(productModel.getCatalogVersion().getVersion());
    }

}
