// pages/personal/address/address.js
var app=getApp();
Page({
  data: {
    //用户的所有地址
    addr: [],
    bool:0,
    //新增地址的所有属性和属性值
    creatAddr: null,
    changeAddress:[],
    modalName:''
  },
  onLoad: function (options) {
      this.load()
      this.setData({
        bool:options.flag
      })
  },

  //添加数据的动态绑定
  inputeidt: function (e) {
    //查看添加时的参数
    //获取绑定的属性名
    var tt = e.currentTarget.dataset.item;
    //获取空数组
    var ad = this.data.creatAddr;
    //将‘属性名’：‘属性值’存入数组
    ad[tt] = e.detail.value;
    //打印数组
    this.setData({
      creatAddr: ad
    })
  },
  //修改数据的动态绑定
  updateedit:function(e){
    var name = e.currentTarget.dataset.item;
    var currentAddress = this.data.changeAddress;
    currentAddress[name] = e.detail.value;
    this.setData({
      changeAddress: currentAddress
    })
  },

//添加地址的逻辑
  addCreat: function (e) {
    var self = this;
    var token = app.globalData.token;
    if(token){//token存在
      wx.request({
        url: 'http://127.0.0.1:8080/address',
        data:self.data.creatAddr,
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'token': token
        },
        method: 'POST',
        success: function (res) {//成功回调函数
        //显示成功弹框
          wx.showToast({
            title: '添加成功',
            icon: 'none',
            duration: 1000//持续的时间
          })
          self.load();
        }
      })
    }else{//token不存在
      wx.request({
        url: '',
      })
    }
  },
//修改选中的地址
  updateAddress:function(e){
    console.log("修改后的changeAddress:-------------------------------------------------------")
    console.log(this.data.changeAddress)
    var self=this;
    //进行数据库更新
    wx.request({
      url: 'http://127.0.0.1:8080/address',
      data: self.data.changeAddress,
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'put',
      success: function (res) {//成功回调函数
        //显示成功弹框
        wx.showToast({
          title: '修改成功',
          icon: 'none',
          duration: 1000//持续的时间
        })
        self.load();
      }
    })

  },
  //各个模态框的显示
  showModal(e) {
    //拿到data-target的值
    var name = e.currentTarget.dataset.target;
    console.log(e)
    //新增地址
    if (name == "creatAdd") {
      //赋值creatAddr为空数组
      this.setData({
        creatAddr: []
      })
    } else if (name == "bottomModal" || name == "DialogModal1") {//删除或编辑
    //获得索引
      var index = e.currentTarget.dataset.index;
      this.setData({
        changeAddress: this.data.addr[index]
      })
    }
    //设置modalName的值
    this.setData({
      modalName: name
    })
    console.log("修改前的changeAddress:------------------------------------------")
    console.log(this.data.changeAddress)
  },
  //隐藏modal框的选定,将所有临时变量更改为null
  hideModal(e) {
    this.setData({
      creatAddr: null,
      upAddr: null,
      //将modalName的值显示为null，以此隐藏modal框
      modalName: null
    })
  },

  //数据处理
  load: function (e) {
    var that = this;
    //查询出所有地址，进行存储
    wx: wx.request({
      url: 'http://127.0.0.1:8080/address/all',
      header: {
        'content-type': 'application/x-www-form-urlencoded',
        'token': app.globalData.token
      },
      method: 'get',
      success: function (res) {//成功回调函数
        if (res.data.code == 0) {
          //获取所有地址
          var userAddress = res.data.data;
          //存入缓存
          wx.setStorageSync("userAddress",userAddress);
          //更新当前页面的地址
          that.setData({
            addr:userAddress
          })
          //设置全局变量
          app.globalData.userAddress=userAddress;
          that.hideModal();
        }
      }
    })
  },

  //删除指定的地址
  delAddress: function (e) {
    var that =this;
   //获取到指定地址的id
   var id=this.data.changeAddress.id;
   console.log("选中的地址id为：")
   console.log(id);
  wx.request({
    url: 'http://127.0.0.1:8080/address',
    data: {
      //使用键值对的形式传输数据
      'id': id
    },
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'delete',
    success:function(res){
      console.log(res)
      wx.showToast({
        title: '删除成功',
        icon: 'none',
        duration: 1000//持续的时间
      });
      that.load();

    }

  })
   
  },
  chooseAddress(e){
    var b = this.data.bool;
    if(b!=1&&b!=2)
    {
      console.log("选择了该地址")
    }else if(b==1){
      //进行寄快递页面的数据回显
      console.log("选择你的地址")
      console.log(e);
      var id = e.currentTarget.dataset.item
      var currentAddressData_ji = [];
      for (var i = 0; i < this.data.addr.length; i++) {
        if (this.data.addr[i].id == id) {
          currentAddressData_ji = this.data.addr[i];
        }
      }    
      //将当前的数据保存在缓存中
      wx.setStorageSync("currentAddressData_ji", currentAddressData_ji);
      wx.setStorageSync("flag_j", 1)
      wx.switchTab({
        url: '../../express/express'
      });  
    }else if(b==2){
      console.log("进行收件人的数据回显")
      //进行寄快递页面的数据回显
      console.log("选择你的地址")
      console.log(e);
      var id = e.currentTarget.dataset.item
      var currentAddressData_shou = [];
      for (var i = 0; i < this.data.addr.length; i++) {
        if (this.data.addr[i].id == id) {
          currentAddressData_shou = this.data.addr[i];
        }
      }
      //将当前的数据保存在缓存中
      wx.setStorageSync("currentAddressData_shou", currentAddressData_shou);
      wx.setStorageSync("flag_s", 1)
      wx.switchTab({
        url: '../../express/express'
      });  
    }
    
  }
})