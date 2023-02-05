package com.xyz.store.controller;

import com.xyz.store.pojo.Product;
import com.xyz.store.service.IProductService;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

        @Autowired
        private IProductService productService;

        @RequestMapping("hot_list")
        public JsonResult<List<Product>> getHotList() {
                List<Product> data = productService.findHotList();
                return new JsonResult<List<Product>>(200, data);
        }

        /**
         * 用于展示商品详情页
         * @param id
         * @return
         */
        @GetMapping("{id}/details")
        public JsonResult<Product> getById(@PathVariable("id") Integer id) {
                // 调用业务对象执行获取数据
                Product data = productService.findById(id);
                // 返回成功和数据
                return new JsonResult<Product>(200, data);
        }

        @RequestMapping("good_list")
        public JsonResult<List<Product>> getById() {
                // 调用业务对象执行获取数据
                List<Product> goodList = productService.findGoodList();
                // 返回成功和数据
                return new JsonResult(200, goodList);
        }

}
