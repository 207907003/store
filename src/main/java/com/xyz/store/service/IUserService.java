package com.xyz.store.service;

import com.xyz.store.pojo.User;
import com.xyz.store.service.util.JsonResult;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface IUserService {
    /**
     *
     * @param user 用户注册对象
     */
    void reg(User user);

    /**
     *
     * @param username 用户账号
     * @param password 用户秘密
     * @return 返回对象存在session或者cookie中 用于 用户数据展示 没有就返回null
     */
    User login(String username,String password);

    void changePassword(Integer uid,String username,String oldPassword,String newPassword);

    /**
     * 修改用户资料,电话,邮箱,性别
     */


    void InfoUserDate(User user);

    /**
    用于展示资料页面
     */
    User InfoUserDateShow(Integer uid);

    /**
     *
     * @param uid 用户id
     * @param avatar 用户头像路径
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     */
    void changeUserAvatar(Integer uid,
                           String avatar,
                           String modifiedUser,
                           Date modifiedTime);
}