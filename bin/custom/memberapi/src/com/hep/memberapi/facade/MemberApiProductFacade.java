package com.hep.memberapi.facade;

import com.hep.facades.product.dto.memberapi.ProductDTO;

import java.util.Collection;
import java.util.Date;

/**
 * @Title
 * @Description:
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 20:02
 */
public interface MemberApiProductFacade {
    /**
     * 查询两个时间戳之间产生变更或者新增的商品
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    Collection<ProductDTO> searchBetweenDate(Date startDate, Date endDate);
}
