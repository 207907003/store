package com.xyz.store.mapper;

import com.xyz.store.pojo.Cart;
import com.xyz.store.pojo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** 处理购物车数据的持久层接口 */
    public interface CartMapper {
        /**
         * 插入购物车数据
         * @param cart 购物车数据
         * @return 受影响的行数
         */
        Integer insert(Cart cart);

        /**
         * 修改购物车数据中商品的数量
         * @param cid 购物车数据的id
         * @param num 新的数量
         * @param modifiedUser 修改执行人
         * @param modifiedTime 修改时间
         * @return 受影响的行数
         */
        Integer updateNumByCid(
                @Param("cid") Integer cid,
                @Param("num") Integer num,
                @Param("modifiedUser") String modifiedUser,
                @Param("modifiedTime") Date modifiedTime);

        /**
         * 根据用户id和商品id查询购物车中的数据
         * @param uid 用户id
         * @param pid 商品id
         * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
         */
        Cart findByUidAndPid(
                @Param("uid") Integer uid,
                @Param("pid") Integer pid);

        /**
         * 查询某用户的购物车数据
         * @param uid 用户id
         * @return 该用户的购物车数据的列表
         */
         List<CartVO> findVOByUid(Integer uid);

        /**
         * 根据购物车数据id查询购物车数据详情
         * @param cid 购物车数据id
         * @return 匹配的购物车数据详情，如果没有匹配的数据则返回null
         */
        Cart findByCid(Integer cid);
        /**
         * 根据若干个购物车数据id查询详情的列表
         * @param cids 若干个购物车数据id
         * @return 匹配的购物车数据详情的列表
         */
        List<CartVO> findVOByCids(Integer[] cids);

    /**
     *
     * @param uid 用户id
     * @param pid 产品id
     * @return 受影响的行数
     */
        Integer deleteCart(Integer uid,Integer pid);

    /**
     * 用于删除复选框的购物车内容
     * @param cids 购物车id
     * @return 受影响的行数
     */
    Integer deleteCartCheckBox(Integer cids[]);
    /**
     * 插入购物车数据,用于立即购买
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insertTemp(Cart cart);
    /**
     * 查询某用户的购物车数据,用于临时购物车
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    CartVO buyFIndVOByUid(Integer uid,Integer cid);

    /**
     * 用于临时购物车的cid查询
     * @return cart
     */
    Cart findAll();

    }
