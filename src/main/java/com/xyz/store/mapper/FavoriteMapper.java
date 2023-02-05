package com.xyz.store.mapper;

import com.xyz.store.pojo.Favorite;

import java.util.List;

/**
 * 收藏接口
 */
public interface FavoriteMapper {
    /**
     * 添加收藏
     * @param favorite 收藏实体类
     * @return 受影响的行数
     */
    Integer addFavorite(Favorite favorite);

    /**
     * 用于罗列收藏列表,用于收藏页面显示
     * @param uid 通过uid查询收藏
     * @return 收藏列表
     */
    List<Favorite> findFavoriteByUid(Integer uid);

    /**
     * 用于取消收藏
     * @param uid 用户uid
     * @param pid 产品id
     * @return 受影响的行数
     */
    Integer deleteFavorite(Integer uid,Integer pid);

}
