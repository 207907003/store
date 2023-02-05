package com.xyz.store.service.impl;

import com.xyz.store.mapper.CartMapper;
import com.xyz.store.pojo.Cart;
import com.xyz.store.pojo.CartVO;
import com.xyz.store.pojo.Product;
import com.xyz.store.service.ICartService;
import com.xyz.store.service.IProductService;
import com.xyz.store.service.ex.CartNotFoundException;
import com.xyz.store.service.ex.DeleteException;
import com.xyz.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;
    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
// 根据参数pid和uid查询购物车中的数据
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date now = new Date();
        // 判断查询结果是否为null
        if (result == null) {
            // 是：表示该用户并未将该商品添加到购物车
            // 创建Cart对象
            Cart cart = new Cart();
            // 封装数据：uid,pid,amount
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 调用productService.findById(pid)查询商品数据，得到商品价格
            Product product = productService.findById(pid);
            // 封装数据：price
            cart.setPrice(product.getPrice());
            // 封装数据：4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            // 调用insert(cart)执行将数据插入到数据表中
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入商品数据时出现未知错误，请联系系统管理员");
            }
        } else {
            // 否：表示该用户的购物车中已有该商品
            // 从查询结果中获取购物车数据的id
            Integer cid2 = result.getCid();
            // 从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer num = result.getNum() + amount;
            // 执行更新数量
            Integer rows = cartMapper.updateNumByCid(cid2, num, username, now);
            if (rows != 1) {
                throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {

        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() + 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public Integer subNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() - 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);

        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }

    @Override
    public void deleteCart(Integer uid, Integer pid) {
        Integer integer = cartMapper.deleteCart(uid, pid);
        if (integer!=1){
            throw new DeleteException("删除时产生未知异常!");
        }
    }

    @Override
    public void deleteCartCheckBox(Integer[] cids) {
        Integer result = cartMapper.deleteCartCheckBox(cids);
        if (result==null){
            throw new DeleteException("未选择任何商品进行删除!");
        }
    }

    @Override
    public void buyAddToCart(Integer uid, Integer pid, Integer amount, String username) {


        Date now = new Date();



            // 创建Cart对象
            Cart cart = new Cart();
            // 封装数据：uid,pid,amount
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 调用productService.findById(pid)查询商品数据，得到商品价格
            Product product = productService.findById(pid);
            // 封装数据：price
            cart.setPrice(product.getPrice());
            // 封装数据：4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            // 调用insert(cart)执行将数据插入到数据表中
            Integer rows = cartMapper.insertTemp(cart);
            if (rows != 1) {
                throw new InsertException("插入商品数据时出现未知错误，请联系系统管理员");
            }
        }

    @Override
    public CartVO buyGetVOByCids(Integer uid) {
        Cart all = cartMapper.findAll();

        CartVO list = cartMapper.buyFIndVOByUid(uid,all.getCid());
        if (list==null){
            throw new CartNotFoundException("购买时候发生异常,请联系管理员");
        }
        return list;
    }

}








