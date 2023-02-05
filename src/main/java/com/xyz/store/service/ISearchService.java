package com.xyz.store.service;

import com.xyz.store.mapper.SearchMapper;
import com.xyz.store.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ISearchService {
    /**
     * 用于主页商品搜索
     * @param title
     * @return 结果
     */
    List<Product> searchIndex(String title);
}
