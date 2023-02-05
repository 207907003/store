package com.xyz.store.service;



import com.xyz.store.mapper.UserMapper;
import com.xyz.store.pojo.BaseEntity;
import com.xyz.store.pojo.User;
import com.xyz.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.xyz.store.mapper")
class ServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    void contextLoads() {
        try {
            User user=new User();
            user.setUsername("12345");
            user.setPassword("1234");
            iUserService.reg(user);
            System.out.println("ok");
        }
        catch (ServiceException e){
            //获取类的对象和名称
            System.out.println(e.getClass().getSimpleName());
            //获取异常具体信息
            System.out.println(e.getMessage());
        }

    }
    @Test
    void login() {
        User login = iUserService.login("123", "123");
        System.out.println(login);

    }

}
