package com.xyz.store.mapper;

import com.xyz.store.pojo.Product;

import java.util.List;

public interface ProductMapper {
    //寻找热销排行的四个商品
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);

    /**
     * 查询新到好货
     * @return 产品李彪
     */
    List<Product> findGoodList();

}
