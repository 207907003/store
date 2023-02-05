package com.xyz.store.service;

import com.xyz.store.pojo.Product;

import java.util.List;

public interface IProductService  {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();
    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);

    /**
     * 查询新到好货
     * @return 产品列表
     */
    List<Product> findGoodList();

}
