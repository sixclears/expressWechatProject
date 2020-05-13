// pages/order/order.js
var app=getApp();
Page({
  data: {
    // 服务热线的样式
    display: "fixed",
    height: "188rpx",
    money:'',
    orderInfo:'',
    modalName:''
  },
  onLoad: function (options) {
    var that = this;
    var screenHeight, heights
    wx.getSystemInfo({
      success: function (res) {
        screenHeight = res.screenHeight
        // console.log(res.screenHeight)
      }
    });
    //创建节点选择器
    var query = wx.createSelectorQuery();
    query.select('.main').boundingClientRect()
    query.exec(function (res) {
      //res就是 所有标签为xxxx的元素的信息 的数组
      // console.log(res);
      //取高度
      heights = res[0].height;
      if (screenHeight - heights <= 124) {
        that.setData({
          display: '',
          height: "188rpx"
        })
      } else {
        that.setData({
          display: 'fixed',
          height: "88rpx"
        })
      }
    })
    //添加money数据
    this.setData({
      money: options.money,
      orderInfo:[]
    })
  },
  submitorder:function(e){
    var that = this;
    //获取输入的信息
    var data = that.data.orderInfo;
    var token = app.globalData.token;
    console.log(data);
    if (token) {//token存在
      wx.request({
        url: 'http://127.0.0.1:8080/daina',
        data: data,
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'token': token
        },
        method: 'post',
        success: function (res) {//成功回调函数
          //显示成功弹框
          that.setData({
            modalName:'tip'
          })
        }
      })
    }
  },
  inputedit:function(e){
    var tt = e.currentTarget.dataset.item;
    //获取空数组
    var ad = this.data.orderInfo;
    //将‘属性名’：‘属性值’存入数组
    ad[tt] = e.detail.value;
    //打印数组
    this.setData({
      orderInfo: ad
    })
  },
  redirect:function(e){
    wx.switchTab({
      url: '/pages/daina/daina',
    })
  }
})