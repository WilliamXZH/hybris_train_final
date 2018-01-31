package com.hep.memberapi.service;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Collection;
import java.util.Date;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/25 09:04
 */
public interface ProductService {
    /**
     * 查询某一段时间中产生或者修改过的商品
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     * @throws Exception
     */
    Collection<ProductModel> searchBetweenDate(Date startDate, Date endDate) throws Exception;
}
