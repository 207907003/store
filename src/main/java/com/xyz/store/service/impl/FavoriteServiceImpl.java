package com.xyz.store.service.impl;

import com.xyz.store.controller.UserController;
import com.xyz.store.mapper.FavoriteMapper;
import com.xyz.store.pojo.Favorite;
import com.xyz.store.service.ICartService;
import com.xyz.store.service.IFavoriteService;
import com.xyz.store.service.ex.AlreadyExistsException;
import com.xyz.store.service.ex.DeleteException;
import com.xyz.store.service.ex.FavoriteNotFoundException;
import com.xyz.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class FavoriteServiceImpl implements IFavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private ICartService cartService;
    @Override
    public void addFavorite(Favorite favorite) {


        List<Favorite> favoriteByUid = favoriteMapper.findFavoriteByUid(favorite.getUid());
        for (Favorite favorite1 : favoriteByUid) {
            System.out.println(favorite1.getPid());
            System.out.println(favorite.getPid()+" "+"123");
            if(favorite1.getPid().equals(favorite.getPid())){
                throw new AlreadyExistsException("收藏已经有该物品!");
            }
        }
        favorite.setCreatedTime(new Date() );
        Integer result = favoriteMapper.addFavorite(favorite);
        if (result!=1){
            throw new InsertException("插入收藏数据失败!");
        }
    }

    @Override
    public List<Favorite> findFavoriteByUid(Integer uid, String username) {
        List<Favorite> result = favoriteMapper.findFavoriteByUid(uid);
        System.out.println(result);
        if (result==null){
            throw new FavoriteNotFoundException("查询收藏列表时出现未知异常!");
        }

        return result;
    }

    @Override
    public void FavoriteToCart(Integer uid, Integer pid,  String username) {
        List<Favorite> favoriteByUid = favoriteMapper.findFavoriteByUid(uid);
        cartService.addToCart(uid,pid,1,username);

    }

    @Override
    public void deleteFavorite(Integer uid, Integer pid) {
        Integer integer = favoriteMapper.deleteFavorite(uid, pid);
        if (integer!=1){
            throw new DeleteException("删除时候发生未知异常!");
        }
    }


}
