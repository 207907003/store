package com.xyz.store.controller;

import com.xyz.store.service.ex.*;
import com.xyz.store.service.ex.FileUploadException.*;
import com.xyz.store.service.util.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseController {

    @ExceptionHandler({ServiceException.class,FileUploadIOException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("插入过程出现未知异常");

        }
        else if (e instanceof UserNotFoundException) {
            result.setState(5001);
            result.setMessage("用户数据不存在异常");

        }
        else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("密码不匹配异常");

        }
        else if (e instanceof UpdataException) {
            result.setState(5003);
            result.setMessage("数据更新异常");

        }
        else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空");

        }
        else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件尺寸异常");

        }
        else if (e instanceof FileTypeException) {
            result.setState(6002);
            result.setMessage("文件类型异常");

        }
        else if (e instanceof FileStateException) {
            result.setState(6003);
            result.setMessage("文件状态异常");

        }
        else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件读写异常");

        }
        else if (e instanceof AddressCountLimitException) {
            result.setState(6005);
            result.setMessage("收货地址数量上限异常");

        }
        else if (e instanceof ProductNotFoundException) {
            result.setState(6006);
            result.setMessage("商品找不到异常");
        }
        else if (e instanceof CartNotFoundException) {
            result.setState(6007);
            result.setMessage("购物车找不到异常");
        }
        else if (e instanceof FavoriteNotFoundException) {
            result.setState(6008);
            result.setMessage("收藏数据找不到异常");
        }
        else if (e instanceof AlreadyExistsException) {
            result.setState(6009);
            result.setMessage("数据已经存在异常!");
        }
        else if (e instanceof OrderNotFoundException) {
            result.setState(60010);
            result.setMessage("没有订单异常!");
        }


        return  result;

    }
}
