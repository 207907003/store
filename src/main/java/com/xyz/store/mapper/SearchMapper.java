package com.xyz.store.mapper;

import com.xyz.store.pojo.Product;

import java.util.List;

public interface SearchMapper {
    /**
     * 用于主页的查询商品
     * @return
     */
    List<Product> searchIndex(String title);

}
