package com.xyz.store.controller;


import com.xyz.store.pojo.District;
import com.xyz.store.service.IDistrictService;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService districtService;

    @RequestMapping ({"", "/dis"})
    //@RequestMapping(method={RequestMethod.GET})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> data = districtService.getByParent(parent);
        return new JsonResult<>(200, data);
    }
}
