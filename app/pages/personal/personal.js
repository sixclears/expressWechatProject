var app = getApp()
var Util = require('../../utils/util.js');
Page({
  data: {
    userInfo: {}
  },

  onLoad: function () {
    wx.showLoading({
      title: '加载中',
    })
    //获取userInfo
    var that = this
    app.getUserInfo(function (userInfo) {
      that.setData({
        userInfo: userInfo
      })
      console.log("这是用户的所有信息")
      console.log(that.data.userInfo)
    })
  },

  //获取缓存中的快递信息
  onShow: function () {
    setTimeout(function () {
      wx.hideLoading()
    }, 100);
  }

})