package com.xyz.store.service.impl;

import com.xyz.store.mapper.ProductMapper;
import com.xyz.store.pojo.Product;
import com.xyz.store.service.IProductService;
import com.xyz.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    //调用dao层寻找排名前四的商品
    public List<Product> findHotList() {

        List<Product> hotList = productMapper.findHotList();

        return hotList;
    }
    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    @Override
    public Product findById(Integer id) {
        // 根据参数id调用私有方法执行查询，获取商品数据
        Product product = productMapper.findById(id);
        // 判断查询结果是否为null
        if (product == null) {
            // 是：抛出ProductNotFoundException
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 返回查询结果
        return product;
    }

    @Override
    public List<Product> findGoodList() {
        List<Product> goodList = productMapper.findGoodList();
        if (goodList==null){
            throw new ProductNotFoundException("没有新到好货!");
        }
        return goodList;
    }

}
