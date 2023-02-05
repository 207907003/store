package com.xyz.store.controller;


import com.xyz.store.pojo.CartVO;
import com.xyz.store.service.ICartService;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("carts")
    public class CartController  {
        @Autowired
        private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = UserController.getUidFromSession(session);
        String username =UserController.getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, pid, amount, username);
        // 返回成功
        return new JsonResult<Void>(200,null);
    }
    @GetMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        // 从Session中获取uid
        Integer uid = UserController.getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByUid(uid);
        // 返回成功与数据
        return new JsonResult<List<CartVO>>(200, data);
    }

    /**
     * 用于添加购物车商品
     * @param cid
     * @param session
     * @return
     */
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        // 返回成功
        return new JsonResult<Integer>(200, data);
    }
    /**
     * 用于减去购物车商品
     * @param cid
     * @param session
     * @return
     */
    @RequestMapping("{cid}/num/sub")
    public JsonResult<Integer> subNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.subNum(cid, uid, username);
        // 返回成功
        return new JsonResult<Integer>(200, data);
    }
    @GetMapping("list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        // 从Session中获取uid
        Integer uid = UserController.getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByCids(uid, cids);
        // 返回成功与数据
        return new JsonResult<>(200, data);
    }
    @GetMapping("del_cart")
    public JsonResult<Void> delCart(Integer pid, HttpSession session) {
        // 从Session中获取uid
        Integer uid = UserController.getUidFromSession(session);
        cartService.deleteCart(uid,pid);
        return new JsonResult<>(200, null);
    }
    @RequestMapping("del_cart_checkbox")
    public JsonResult<Void> delCartBox(Integer cids[]) {
        // 从Session中获取uid
        cartService.deleteCartCheckBox(cids);
        return new JsonResult<>(200, null);
    }
    @PostMapping("buy_add_to_cart")
    public JsonResult<Void> buyAddToCart(Integer pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = UserController.getUidFromSession(session);
        String username =UserController.getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.buyAddToCart(uid, pid, amount, username);
        // 返回成功
        return new JsonResult<Void>(200,null);
    }
    //用于展示临时购物车的物品
    @RequestMapping("buy_cart_temp")
    public JsonResult<CartVO> buyFindGetVOByUid(HttpSession session) {
        // 从Session中获取uid
        Integer uid = UserController.getUidFromSession(session);
        // 调用业务对象执行查询数据
       CartVO data = cartService.buyGetVOByCids(uid);
        // 返回成功与数据
        return new JsonResult<CartVO>(200, data);
    }
    }

