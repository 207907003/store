package com.xyz.store.mapper;

import com.xyz.store.pojo.Order;
import com.xyz.store.pojo.OrderItem;

import java.util.List;

public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 受影响的行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品数据
     * @param orderItem 订单商品数据
     * @return 受影响的行数
     */
    Integer insertOrderItem(OrderItem orderItem);


    /**
     * 通过在order表查找所有的oid,用于展示一对多订单
     * @return 返回所有oid
     */
    List<Integer> findOidByOrder();

    List<Order> showOrder(Integer oid);

    /**
     * 通过orderitem的id查找具体信息,用于展示商品详情
     * @param id orderitem中的id
     * @return
     */
    Order findOrderInfoById(Integer id);
}
