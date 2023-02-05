package com.xyz.store.service;

import com.xyz.store.pojo.Favorite;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 收藏服务层接口
 */
public interface IFavoriteService {
    /**
     * 添加收藏
     * @param favorite 收藏实体类
     */
    void addFavorite(Favorite favorite);

    /**
     * 收藏列表服务层
     * @param uid 用户id
     * @return 收藏列表
     */
    List<Favorite> findFavoriteByUid(Integer uid,String username );

    /**
     * 用于从收藏页面添加到购物车
     * @param pid 商品id
     */
    void FavoriteToCart(Integer uid, Integer pid, String username);

    /**
     * 用于取消收藏
     * @param uid 用户id
     * @param pid 产品id
     */
    void deleteFavorite(Integer uid,Integer pid);
}
