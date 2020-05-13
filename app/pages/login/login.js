// pages/login/login.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
      userInfo:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    app.getUserInfo(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
    })
    console.log("globalData中是否有token:"+app.getPreInfo());
    //如果有token存于redis中，则发送tokenauth请求
    if (app.getPreInfo()){
      wx:wx.request({
        url: 'http://127.0.0.1:8080/wechat/tokenauth',
        header: {
          'content-type':  'application/x-www-form-urlencoded',
          'token': app.globalData.token
        },
        method: 'GET',
        success: function(res) {
          console.log(res)
          wx.setStorageSync("userInformation", res.data.data);
          wx.switchTab({
            url: '/pages/index/index',
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    var that = this;
    if (e.detail.userInfo) {
      wx.login({
        //这个是微信像微信服务器请求code的回调函数
        success(res) {
          if (res.code) {
            //发起网络请求
            wx.request({
              url: 'http://127.0.0.1:8080/wechat/login',
              method: 'post',
              header: {
                'content-type': 'application/x-www-form-urlencoded'
              },
              data: {
                code: res.code,
                name: e.detail.userInfo.nickName
              },
              success(result) {
                //将token传入globalData中
                wx.setStorageSync("token", result.data.data.token)
                wx.setStorageSync("userInformation", result.data.data);
                app.globalData.token=result.data.data.token
                that.load();
                wx:wx.switchTab({
                  url: '/pages/index/index'
                })
              }
            })
          } else {
            console.log('登录失败！' + res.errMsg)
          }
        }
      })
    }
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
          //设置全局变量
          app.globalData.userAddress = userAddress;
        }
      }
    })
  }
})