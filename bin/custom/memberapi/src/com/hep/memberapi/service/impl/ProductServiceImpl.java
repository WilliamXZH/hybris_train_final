package com.hep.memberapi.service.impl;

import com.hep.memberapi.dao.ProductDao;
import com.hep.memberapi.service.ProductService;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/25 09:05
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Collection<ProductModel> searchBetweenDate(Date startDate, Date endDate) throws Exception {
        return productDao.searchBetweenDate(startDate,endDate);
    }
}
