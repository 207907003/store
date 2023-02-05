package com.xyz.store.service;

import com.xyz.store.pojo.CartVO;

import java.util.List;

public interface ICartService {
    /**
     * 将商品添加到购物车
     * @param uid 当前登录用户的id
     * @param pid 商品的id
     * @param amount 增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);
    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> getVOByUid(Integer uid);
    /**
     * 将购物车中某商品的数量加1
     * @param cid 购物车数量的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    /**
     * 将购物车中某商品的数量减1
     * @param cid 购物车数量的id
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @return 增加成功后新的数量
     */
    Integer subNum(Integer cid, Integer uid, String username);
    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param uid 当前登录的用户的id
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVO> getVOByCids(Integer uid, Integer[] cids);

    /**
     * 用于购物车的单独删除功能
     * @param uid 用户id
     * @param pid 产品id
     */
    void deleteCart(Integer uid,Integer pid);

    /**
     * 删除购物车复选框内容
     * @param cids 多选的购物车id
     */
    void deleteCartCheckBox(Integer cids[]);

    /**
     * 将商品添加到购物车
     * @param uid 当前登录用户的id
     * @param pid 商品的id
     * @param amount 增加的数量
     * @param username 当前登录的用户名
     */
    void buyAddToCart(Integer uid, Integer pid, Integer amount, String username);


    /**
     * 根据若干个临时购物车数据id查询详情的列表
     * @param uid 当前登录的用户的id
     * @return 匹配的购物车数据详情的列表
     */
    CartVO buyGetVOByCids(Integer uid);

}
