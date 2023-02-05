package com.xyz.store.service;

import com.xyz.store.pojo.Address;

import java.util.List;

public interface IAddressService {
    /**
     * 用于增加收货地址
     * @param uid
     * @param username
     * @param address
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 用于展示用户收货地址
     * @param uid
     * @return
     */
    List<Address> findAddressByUid(Integer uid);

    /**
     * 修改默认地址业务层
     * @param aid
     * @param uid
     * @param username
     */
    void setDefault(Integer aid,Integer uid,String username);

    /**
     * 删除收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void delete(Integer aid, Integer uid, String username);
    /**
     * 根据收货地址数据的id，查询收货地址详情
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @return 匹配的收货地址详情
     */
    Address getByAid(Integer aid, Integer uid);
}
