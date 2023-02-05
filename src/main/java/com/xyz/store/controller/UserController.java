package com.xyz.store.controller;

import com.xyz.store.pojo.User;
import com.xyz.store.service.IUserService;
import com.xyz.store.service.ex.FileUploadException.*;
import com.xyz.store.service.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController  {
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg( User user){
        JsonResult<Void> result=new JsonResult<>();

            userService.reg(user);
            result.setState(200);
            result.setMessage("ok");
            return result;
    }
    @RequestMapping("login")
    public JsonResult<User> reg(String username, String password, HttpSession session){


        User user = userService.login(username, password);
        JsonResult<User> result=new JsonResult<User>(200,user);
        //把UID和Username存入Session
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        return  result;
    }
    @RequestMapping("change_password")
    public JsonResult<User> changpassword(String oldPassword,String newPassword, HttpSession session){


        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        System.out.println(uid+" "+username+" "+oldPassword+" "+newPassword);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return  new JsonResult<>(200,null);
    }
    @RequestMapping("get_by_data")
    public JsonResult<User> getByIdData(HttpSession session){
        Integer uidFromSession = getUidFromSession(session);
        User user = userService.InfoUserDateShow(uidFromSession);
        return  new JsonResult<>(200,user);
    }

    /**
     * 用于点击修改按钮后更新信息
     * @param phone
     * @param email
     * @param gender
     * @param session
     * @return
     */
    @RequestMapping("info_data")
    public JsonResult infodata(String phone,String email,Integer gender,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        User user=new User();
        user.setUid(uid);
        user.setPhone(phone);
        user.setEmail(email);
        user.setGender(gender);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        System.out.println(user);
        userService.InfoUserDate(user);
        return  new JsonResult<>(200,user);

    }

    /**
     * 文件最大尺寸
     */
     private  static final Integer AVATAR_MAX_SIZE=10 * 1024 * 1024;

    /**
     * 文件类型限制
     */
    static final ArrayList AVATAR_TYPES=new ArrayList();
     static {

             AVATAR_TYPES.add("image/jpeg");
             AVATAR_TYPES.add("image/png");
             AVATAR_TYPES.add("image/bmp");
             AVATAR_TYPES.add("image/gif");
         }

    /**
     * 0     * 改变用户头像
     *
     * @param session
     * @param file
     * @return
     */
    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file){
        //判断文件是否为空
         if(file.isEmpty()){
             System.out.println("file-NUll");
             throw new FileEmptyException("文件为空！");

         }
        //判断文件类型是否为规定类型
        String contentType = file.getContentType();
        System.out.println(contentType);
         if (!AVATAR_TYPES.contains(contentType)){
             throw  new FileTypeException("文件类型异常");
         }
         //判断文件尺寸是否规定大小
        if(file.getSize()>AVATAR_MAX_SIZE){
            throw  new FileSizeException("文件大小超出10M！");
        };
        //获取项目路径+upload
        String uplaod = "C:\\Users\\Administrator\\IdeaProjects\\store\\src\\main\\resources\\static\\user_image";
        //创建抽象路径
        File dir=new File(uplaod);
        //不存在就创建（用于存放头像）
        if (!dir.exists()){
            dir.mkdirs();
        }

        String s = UUID.randomUUID().toString().toUpperCase();
        String OriginalFilename=file.getOriginalFilename();

        //获取新文件名
        String newFileName = s + OriginalFilename.substring(file.getOriginalFilename().lastIndexOf("."));

        File newFileNamepath=new File(dir,newFileName);
        System.out.println(newFileNamepath);
        try {
            file.transferTo(newFileNamepath);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重新尝试");
        }

        // 头像路径
        String avatar = "../user_image/" + newFileName;
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        userService.changeUserAvatar(uid,avatar,username,new Date());

        // 返回成功头像路径
        return new JsonResult<String>(200, avatar);
    }

    //获取Session里面的UID和Username
    public static Integer getUidFromSession(HttpSession session){
        return Integer.parseInt(session.getAttribute("uid").toString());
    }
    public static String getUsernameFromSession(HttpSession session){

        return session.getAttribute("username").toString();
    }



}
