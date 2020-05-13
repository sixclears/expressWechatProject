package com.wy.controller;

import com.wy.bean.Address;
import com.wy.entity.ResultEntity;
import com.wy.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;


    @PostMapping
    public ResultEntity addAddress(Address address){
        addressService.add(address);
        //返回只带code和msg的结果
        return ResultEntity.success();
    }

    //获取当前用户所有的地址
    @GetMapping("/all")
    public ResultEntity<List<Address>> getAll(){
        List<Address> addressList = addressService.getAddressByToken();
        return ResultEntity.success(addressList);
    }

    //删除指定地址
    @DeleteMapping
    public ResultEntity delAddressById(String id){
        System.out.println(id);
        System.out.println("进入了delete请求的处理方法中");
        addressService.delAddressById(id);
        return ResultEntity.success();
    }
    //更新选中的地址
    @PutMapping
    public ResultEntity updateAddressById(Address address){
        addressService.updateAddrById(address);
        return ResultEntity.success();
    }


}
