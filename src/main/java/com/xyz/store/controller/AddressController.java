package com.xyz.store.controller;

import com.xyz.store.pojo.Address;
import com.xyz.store.service.IAddressService;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private IAddressService addressService;
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address){
        String username = UserController.getUsernameFromSession(session);
        Integer uid = UserController.getUidFromSession(session);

        addressService.addNewAddress(uid,username,address);
        return new JsonResult<>(200,null);


    }
    //用于展示收货地址
    @RequestMapping("show_address")
    public JsonResult<List<Address>> showAddress(HttpSession session){
        Integer uid = UserController.getUidFromSession(session);
        List<Address> addressByUid = addressService.findAddressByUid(uid);
        return new JsonResult<>(200,addressByUid);

    }
    //用于修改默认收货地址
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(200,null);
    }

    //用于修改默认收货地址
    @RequestMapping("{aid}/delete_address")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);

        addressService.delete(aid,uid,username);
        return new JsonResult<>(200,null);
    }

}
