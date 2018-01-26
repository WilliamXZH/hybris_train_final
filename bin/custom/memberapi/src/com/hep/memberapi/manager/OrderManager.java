package com.hep.memberapi.manager;

/**
 * @Title
 * @Description: 订单管理接口
 * @Author Bin.Zhou
 * @Email bin.zhou02@hand-china.com
 * @Date 2018/01/24 15:31
 */
public interface OrderManager {

    /**
     * 推送新的订单数据至会员系统
     */
    void pushOrders();
}
