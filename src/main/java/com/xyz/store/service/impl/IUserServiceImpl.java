package com.xyz.store.service.impl;

import com.xyz.store.mapper.UserMapper;
import com.xyz.store.pojo.User;
import com.xyz.store.service.IUserService;
import com.xyz.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMpper;

    @Override
    public void reg(User user) {
        //获取username
        String username = user.getUsername();
        //调用findByusername（username）判断用户是否被注册
        User result = userMpper.findByUsername(username);
        //判断结果是不是为null 不为null说明数据库已经有了数据被占用了
        if(result!=null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用！");
        }
        //生成盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //盐值加入数据库
        user.setSalt(salt);
        //加密
        String md5password = getMD5(user.getPassword(), salt);
        user.setPassword(md5password);
        //补全数据 is_delete(0)
        user.setIsDelete(0);
        //补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date=new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        //执行注册
        Integer rows = userMpper.insert(user);
        if(rows !=1){
            throw new InsertException("插入过程出现未知异常!");
        }

    }

    @Override
    public User login(String username, String password) {
        //查找用户名,如果null代表用户不存在
        User result = userMpper.findByUsername(username);
        if(username==null){
            throw new UserNotFoundException("用户不存在");
        }
        //匹配密码
        //0.取得密码
        String oldpassword = result.getPassword();
        //1.取得盐值
        String salt = result.getSalt();
        //2.加密
        String newmd5 = getMD5(password, salt);
        //3.正式匹配
        if(!newmd5.equals(oldpassword)){
            throw new PasswordNotMatchException("密码不匹配");
        }
        //判断is_delete字段的值是不是为1
        if(result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        // 创建新的User对象
        User user = new User();
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        // 返回新的user对象
        return user;
    }

    public void changePassword(Integer uid, String username, String oldPassword, String newPassword){
        User result = userMpper.findByUid(uid);
        //检验密码是否一致前,查看数据库是否有用户数据或者是否被删除(可能在中途被管理设置已经删除)
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户不存在");
        }
        //获取输入的旧密码
        String mdOldPassWord = getMD5(oldPassword, result.getSalt());
        //与现有密码是否一致
        if(mdOldPassWord.equals(result.getPassword())==false){
            throw new PasswordNotMatchException("输入密码与原密码不匹配!");
        }
        //加密新密码
        String md5NewPassWord = getMD5(newPassword, result.getSalt());
        Integer integer = userMpper.updatePasswordByUid(result.getUid(), md5NewPassWord, username, new Date());
        if (integer==0){
            throw new UpdataException("更新过程中出现异常!");
        }


    }

    /**
     * 修改用户电话,邮箱,性别
     */
    @Override
    public void InfoUserDate(User user) {

        User result = userMpper.findByUid(user.getUid());
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在!");
        }

        Integer integer = userMpper.updateInfoByUid(user);
        if (integer!=1){
            throw  new UpdataException("更新数据时出现异常!");
        }


    }



    /**
     * 用于开始的资料页面显示
     * @param
     */
    @Override
    public User InfoUserDateShow(Integer uid) {
        User result = userMpper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在!");
        }

        return result;
    }

    @Override
    public void changeUserAvatar(Integer uid, String avatar, String username, Date modifiedTime) {
        User result = userMpper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在!");
        }
        Integer integer = userMpper.updateAvatarByUid(uid, avatar, username, modifiedTime);
        if (integer!=1){
            throw new UpdataException("插入过程中出现异常!");

        }
    }


    /**获取盐值+密码+颜值md5加密的结果**/
    private String getMD5(String password,String salt){
         password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes());
         return password.toUpperCase();
    }
}
