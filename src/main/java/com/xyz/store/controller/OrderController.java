package com.xyz.store.controller;

import com.xyz.store.pojo.Order;
import com.xyz.store.service.IOrderService;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.create(aid, cids, uid, username);
        // 返回成功与数据
        return new JsonResult<Order>(200, data);
    }

    @RequestMapping("show_order")
    public JsonResult<HashMap> showOrder() {
        HashMap<Integer, List<Order>> ListHashMap = orderService.showOrder();
        return new JsonResult<>(200,ListHashMap);
    }

    @RequestMapping("{id}/show_order_info")
    public JsonResult showOrderInfo(@PathVariable("id") Integer id) {
        Order order = orderService.showOrderInfo(id);
        return new JsonResult<>(200,order);
    }
}
