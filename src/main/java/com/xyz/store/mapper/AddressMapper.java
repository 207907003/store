package com.xyz.store.mapper;

import com.xyz.store.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    /**
     * 插入用户收货地址,用于新增收货地址
     * @param address 地址pojo
     * @return 是否插入成功
     */
    Integer insert(Address address);

    /**
     * 通过uid查询所属收货地址有多少,大于20判断超出最大数量
     * @param uid 用户id
     * @return 返回数据条数
     */
    Integer countByUid(Integer uid);

    /**
     * 用于展示收货地址
     * @param uid
     * @return
     */
    List<Address> findAllAddress(Integer uid);

    /**
     * 用于设置默认收货地址,把所有地址设置成非默认
     */
    Integer changeDefaultAddressOne(Integer uid);

    /**
     * * 用于设置默认收货地址,把选中地址设置成默认
     * @param
     * @return
     */
    Integer changeDefaultAddressTwo(@Param("aid") Integer aid,
                                    @Param("modifiedUser") String modifiedUser,
                                    @Param("modifiedTime" ) Date modifiedTime);
    /**
     * 根据收货地址id删除数据
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询某用户最后修改的收货地址
     * @param uid 归属的用户id
     * @return 该用户最后修改的收货地址，如果该用户没有收货地址数据则返回null
     */
    Address findLastModified(Integer uid);

    /**
     * 用于查询所选地址是不是默认地址
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);
}

