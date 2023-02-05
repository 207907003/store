package com.xyz.store.controller;

import com.xyz.store.pojo.Product;
import com.xyz.store.service.ISearchService;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private ISearchService searchService;
    @RequestMapping("search_index")
    public JsonResult<List<Product>> searchIndex(String title){
        List<Product> products = searchService.searchIndex(title);
        return new JsonResult<>(200,products);
    }
}
