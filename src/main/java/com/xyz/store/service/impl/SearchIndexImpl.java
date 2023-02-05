package com.xyz.store.service.impl;

import com.xyz.store.mapper.SearchMapper;
import com.xyz.store.pojo.Product;
import com.xyz.store.service.ISearchService;
import com.xyz.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchIndexImpl implements ISearchService {
    @Autowired
    private SearchMapper searchMapper;
    @Override
    public List<Product> searchIndex(String title) {

        List<Product> products = searchMapper.searchIndex(title);
        if (products==null){
            throw new ProductNotFoundException("查找的商品不存在!");

        }
        return products;
    }
}
