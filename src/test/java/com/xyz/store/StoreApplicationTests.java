package com.xyz.store;



import com.xyz.store.mapper.CartMapper;
import com.xyz.store.mapper.FavoriteMapper;
import com.xyz.store.mapper.SearchMapper;
import com.xyz.store.mapper.UserMapper;
import com.xyz.store.pojo.*;
import com.xyz.store.service.ICartService;
import com.xyz.store.service.IFavoriteService;
import com.xyz.store.service.IUserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootTest
@MapperScan("com.xyz.store.mapper")
class StoreApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IFavoriteService favoriteService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }
    @Test
    void findUsername() {
        BaseEntity baseEntity=new User();
        User baseEntity1 = (User) baseEntity;
        System.out.println(baseEntity1);


    }
    @Test
    void updataPassword(){
        userService.changePassword(9,"123","1234","123");
    }
    @Test
    void updateInfoByUid(){
        User user=new User();
        user.setUid(9);
        user.setPhone("13611487102");
        user.setEmail("207907003@qq.com");
        user.setCreatedUser("123");
        user.setCreatedTime(new Date());
        user.setModifiedUser("123");
        user.setModifiedTime(new Date());

        userMapper.updateInfoByUid(user);
    }
    @Test
    void updateAvatar(){

        Integer integer = userMapper.updateAvatarByUid(9, "/users/avatar", "123", new Date());
    }
    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 7, 8, 9, 10};
        List<CartVO> list = cartMapper.findVOByCids(cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }
    @Test
    public void addFavorite() {
        Favorite favorite=new Favorite();
        favorite.setFid(1);
        favorite.setUid(1);
        favorite.setPid(1);
        favorite.setTitle("123");
        favorite.setPrice(200);
        favorite.setImages("/aac");
        favorite.setCreatedUser("123");
        favorite.setCreatedTime(new Date());
        favoriteService.addFavorite(favorite);
        }

    @Test
    public void testS() {
        List<Product> daier = searchMapper.searchIndex("戴尔");
        System.out.println(daier);

    }
    @Test
    public void testSb() {

        String a="abc";
        String b="abd";
        String c="通话";
        String d="重题";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());


    }
}



