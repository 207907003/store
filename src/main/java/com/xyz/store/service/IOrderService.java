package com.xyz.store.service;

import com.xyz.store.pojo.Order;

import java.util.HashMap;
import java.util.List;

/** 处理订单和订单数据的业务层接口 */
public interface IOrderService {
    /**
     * 创建订单
     * @param aid 收货地址的id
     * @param cids 即将购买的商品数据在购物车表中的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 成功创建的订单数据
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);

    /**
     * 用于展示一对多订单
     * @return
     */
    HashMap<Integer,List<Order>> showOrder();

    /**
     * 用于展示order信息
     * @return
     */
    Order showOrderInfo(Integer id);
}
