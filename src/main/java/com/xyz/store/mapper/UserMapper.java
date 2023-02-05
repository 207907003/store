package com.xyz.store.mapper;

import com.xyz.store.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper{
    /**
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     *
     * @param username 用户名
     * @return 是否找到 找到返回数据 没找到返回null值
     */
    User findByUsername(String username);

    /**
     * 根据uid更新用户的密码
     * @param uid 用户的id
     * @param password 新密码
     * @param modifiedUser 最后修改执行人
     * @param modifiedTime 最后修改时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id查询用户数据
     * @param uid 用户id
     * @return 匹配的用户数据，如果没有匹配的用户数据，则返回null
     */
    User findByUid(Integer uid);

    /**
     *
     * @param user 用于修改用户的电话号码 电子邮箱 性别
     * @return 返回是否成功更新结果
     */
    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime );
}
