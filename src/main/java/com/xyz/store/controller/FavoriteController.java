package com.xyz.store.controller;

import com.xyz.store.pojo.District;
import com.xyz.store.pojo.Favorite;
import com.xyz.store.service.IDistrictService;
import com.xyz.store.service.IFavoriteService;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 用于添加收藏
     * @param favorite 收藏实体类
     * @param session session
     * @return 200
     */
    @RequestMapping ( "/fav")
    //@RequestMapping(method={RequestMethod.GET})
    public JsonResult<Void> getByParent(Favorite favorite, HttpSession session) {
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        favorite.setUid(uid);
        favorite.setCreatedUser(username);
       favoriteService.addFavorite(favorite);
       return new JsonResult<>(200,null);
    }

    /**
     * 展示收藏列表
     * @param session session
     * @return  收藏列表给前端
     */
    @RequestMapping ( "/show_fav")
    public JsonResult<List<Favorite>> showFav( HttpSession session) {
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        List<Favorite> favoriteByUid = favoriteService.findFavoriteByUid(uid,username);
        return new JsonResult<>(200,favoriteByUid);
    }
    @RequestMapping ( "/fav_to_cart")
    public JsonResult<Void> FavToCart(HttpSession session,Integer pid) {
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        favoriteService.FavoriteToCart(uid,pid,username);
        return new JsonResult<>(200,null);
    }
    @RequestMapping ( "/delete_favorite")
    public JsonResult<Void> deleteFavorite(HttpSession session,Integer pid) {
        Integer uid = UserController.getUidFromSession(session);
        String username = UserController.getUsernameFromSession(session);
        favoriteService.deleteFavorite(uid,pid);
        return new JsonResult<>(200,null);
    }
}
