package com.wy.service;

import com.wy.bean.Address;
import com.wy.dao.AddressMapper;
import com.wy.utils.SigninUtils;
import com.wy.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    StringUtils stringUtils;
    @Autowired
    SigninUtils signinUtils;

    //添加地址
    public void add(Address address){
        //根据uuid设置地址的id
        address.setId(stringUtils.getID());
        //signinUtils根据token获取到用户的id
        address.setUserId(signinUtils.getUser().getId());
        addressMapper.add(address);

    }

    //根据userId获取当前用户所有的地址
    public List<Address> getAddressByUserId(String userId){
        List<Address> addressList = addressMapper.getAddressByUserId(userId);
        return addressList;
    }
    //根据token值获取地址
    public List<Address> getAddressByToken(){
        List<Address> addressList = getAddressByUserId(signinUtils.getUser().getId());
        return addressList;
    }

    //根据userId删除指定地址
    public void delByUserId(String userId){
        addressMapper.delByUserId(userId);
    }

    //更新个人地址
    public void updateAddrById(Address address){
        addressMapper.updateById(address);
    }


    //根据地址id删除指定地址
    public void delAddressById(String id){
        addressMapper.delById(id);

    }

}
