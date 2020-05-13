// pages/myexpress/myexpress.js
var app = getApp()
var Util = require('../../utils/util.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    expressList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

   //获取缓存中的快递信息
   onShow: function () {
    setTimeout(function () {
      wx.hideLoading()
    }, 100);
    //获取快递的信息
    this.showMyExpress();
  },

  showMyExpress: function () {
    var self = this;
    //获取快递查询记录
    let historySearchList =wx.getStorageSync("historySearchList");
    console.log(historySearchList)
    self.setData({
      expressList:historySearchList
    })
  },

  //将缓存中的expressDetail覆盖
  showDetail: function (event) {
    //再次发送请求查询，获取最新的数据
    let self = this;
    //api调用密匙
    let appKey = "VQGep1D690af32ddeb0625fccddf11c1b742d39800c3d67";
    //订单号
    //传输的数据
    let expData = event.currentTarget.dataset;
    let expressNumber =expData.expressnumber;
    let expressType=expData.expresscode;
    //发起请求
    wx.request({
      url: 'https://api.apishop.net/common/express/getExpressInfo',
      data: {
        //api调用密匙
        apiKey:appKey,
        //快递物流单号
        expressNumber:expressNumber,
        //物流公司，填空为自动 识别
        expressType:expressType
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
      },
      success: function (res) {
            let resData = res.data;
            //更新缓存中的数据
            wx.setStorageSync("expressDetail", resData);
            //更新缓存中的查询记录
            let historySearchList = wx.getStorageSync("historySearchList");
            if(!historySearchList){
              historySearchList = [];
            }
            //过滤器，将除此单号外的所有单号信息提取出来
            let newList = historySearchList.filter(function (val) {
              return (val.expressNumber != expressNumber);
            });
            //更新缓存
            newList.push({
              "expressNumber": expressNumber,
              "expressName": resData.result.expName,
              "expressCode": resData.result.type,
              "logoSrc":resData.result.logo,
              "lastInfo":resData.result.list[0],
            })
            
            wx.setStorageSync("historySearchList",newList);
            //转发到详情页面
            wx.navigateTo({
                        url: '../detail/detail'
                      })
          }
    })
  }
})