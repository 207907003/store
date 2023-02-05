package com.xyz.store.service.impl;

import com.xyz.store.mapper.AddressMapper;
import com.xyz.store.pojo.Address;
import com.xyz.store.service.IAddressService;
import com.xyz.store.service.IDistrictService;
import com.xyz.store.service.ex.AddressCountLimitException;
import com.xyz.store.service.ex.DeleteException;
import com.xyz.store.service.ex.InsertException;
import com.xyz.store.service.ex.UpdataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //检测是否超出20条数据
        Integer count = addressMapper.countByUid(uid);
        if (count >20){
            throw new AddressCountLimitException("超出最大数量收货地址!");
        }
        //设置uid和默认地址
        address.setUid(uid);
        Integer isDefault=count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        //补全日志
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        // 补全数据：省、市、区的名称
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        Integer result = addressMapper.insert(address);

        if (result!=1){
            throw new InsertException("插入数据发生异常!");
        }
    }

    @Override
    public List<Address> findAddressByUid(Integer uid) {
        List<Address> allAddress = addressMapper.findAllAddress(uid);
        return allAddress;
    }

    /**
     * 设置默认地址
     * @param aid 地址id
     * @param uid 用户id
     * @param username 用户名
     */
    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        //用户地址所有设置非默认0
        Integer result = addressMapper.changeDefaultAddressOne(uid);
        if (result<1){
            throw new UpdataException("更新数据时出现未知异常！");
        }
        //用户所选地址设置成默认0
        Integer integer = addressMapper.changeDefaultAddressTwo(aid, username, new Date());
        if (integer!=1){
            throw new UpdataException("更新数据时出现未知异常！");
        }

    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address addressAid = addressMapper.findByAid(aid);
        System.out.println(addressAid.getIsDefault());
        //如果删除的地址是不是默认地址.删除
        if (addressAid.getIsDefault() == 0){
            Integer result = addressMapper.deleteByAid(aid);
        }
        //删除所选地址,如果是默认地址,删除+设置最近修改的地址为默认地址
       else if (addressAid.getIsDefault() == 1){
            Integer result = addressMapper.deleteByAid(aid);

            if (result!=1){
                throw new DeleteException("删除数据时出现未知异常!");
            }
            //出现最近修改的地址
            Address lastModified = addressMapper.findLastModified(uid);

            Integer integer = addressMapper.changeDefaultAddressTwo(lastModified.getAid(), username, new Date());
            if (integer!=1){
                throw new UpdataException("更新默认地址异常!");
            }

        }




    }


    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);

        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
