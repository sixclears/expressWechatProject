//app.js
App({

  globalData:{
    //用户的所有地址
    userAddress: [],
    //用于存储返回的token
     'token':"",
     userInformation:{}
  },
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    wx.setStorageSync("flag_j",0)
    wx.setStorageSync("flag_s",0)
  },
  getUserInfo:function(cb){
    var that = this
    if(this.globalData.userInfo){
      typeof cb == "function" && cb(this.globalData.userInfo)
    }else{
      //调用登录接口
      wx.login({
        success: function () {
          wx.getUserInfo({
            success: function (res) {
              that.globalData.userInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo)
            }
          })
        }
      })
    }
  },
  //预加载所有信息
  getPreInfo(){
    //从缓存中获取token
    var token = wx.getStorageSync('token') || [];
    //从缓存中获取用户所有地址
    var userAddress = wx.getStorageSync('userAddress') || [];
    //从缓存中获取用户信息
    var userInformation = wx.getStorageSync('userInformation') || [];
    //将缓存中的值存放在golbalData中
    this.globalData.userAddress = userAddress;
    this.globalData.token = token;
    this.globalData.userInformation = userInformation
    
    //判断是否有token存在
    if (token.length > 0) {
      return true;
    } else {
      return false;
    }
  }
})