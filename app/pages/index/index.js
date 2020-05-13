var Base64 = require('../../utils/base64.js').Base64;
var MD5 = require('../../utils/md5.min.js');
var util = require('../../utils/util.js');
var MockData = require('../../utils/mockdata.js');

//引入外部的js文件中的方法
import { request } from "../../request/index.js"
Page({
  data: {
    result: {},
    focus: false,
    historySearchList: [],
    //index页面的三个图片：logo，二维码扫描，删除
    logocatSrc:'',
    scanningSrc:'',
    deleteSrc:''
  },

  onLoad: function () {
    wx.showLoading({
      title: '加载中',
    });
  },

//获取图片信息----------------------------------------------------------------------
getImages(){
  request({
    url: "http://127.0.0.1:8080/api/images/pageIndex"
  }).then(result=>{
    var list=result.data;
    var logocatSrc;
    var scanningSrc;
    var deleteSrc;
    list.forEach(element => {
      if(element.name==='logocat'){
        logocatSrc = element.imageSrc;
      }else if(element.name==='scanning'){
        scanningSrc = element.imageSrc;
      }else{
        deleteSrc = element.imageSrc;
      } 
    });
    this.setData({
      logocatSrc,
      scanningSrc,
      deleteSrc
    })
  })
},
//---------------------------------------------------------------------------------
  onShow: function () {
    setTimeout(function () {
      wx.hideLoading()
    }, 100);
    this.showHistory();
  },
//用户提交快递单号，进行快递单号的查询工作
  formSubmit: function (e) {
    var that = this
    //去除单号中的空格
    let eorder = util.trim(e.detail.value.expressorder);
//快递单号错误提示语
    if (!eorder) {
      wx.showModal({
        title: '提示',
        content: '快递单号不能为空！',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
            that.setData({
              focus: true
            })
          }
        }
      })
      return;
    }
//获取被查询快递的详细信息
    this.searchExpress(eorder);
  },

  //删除所选的快递信息
  deleteHistory: function (e) {
    console.log(e)
    var self = this;
    try {
      let historySearchList = wx.getStorageSync('historySearchList');

      let newList = historySearchList.filter(function (val) {
        return (val.expressNumber != e.currentTarget.dataset.expressnumber);
      });

      wx.setStorage({
        key: "historySearchList",
        data: newList,
        success: function () {
          self.showHistory();
        }
      })
    } catch (e) {
      console.log(e);
    }

  },

  showHistory: function () {
    var self = this;
    wx.getStorage({
      key: 'historySearchList',
      success: function (res) {
        self.setData({
          historySearchList: res.data
        });
      }
    })
  },

  scanCode: function () {
    let self = this;
    wx.scanCode({
      success: (res) => {
        if (res.result) {
          self.searchExpress(util.trim(res.result));
        } else {
          wx.showModal({
            title: '提示',
            content: '快递单号不能为空！',
            showCancel: false,
            success: function (res) {
              if (res.confirm) {
                self.setData({
                  focus: true
                })
              }
            }
          })
        }
      }
    })
  },
  showDetail(){

    wx.navigateTo({
      url: '../myexpress/myexpress'
    })

  },


  //获取快递信息
  searchExpress: function (eorder) {
      let self = this;
      //api调用密匙
      let appKey = "VQGep1D690af32ddeb0625fccddf11c1b742d39800c3d67";
      //订单号
      let expressNumber = eorder;
      //发起请求
      wx.request({
        url: 'https://api.apishop.net/common/express/getExpressInfo',
        data: {
          //api调用密匙
          apiKey:appKey,
          //快递物流单号
          expressNumber:expressNumber,
          //物流公司，填空为自动 识别
          expressType:""
        },
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded;charset=utf-8'
        },
        success: function (res) {
          let resData = res.data;
          console.info(resData);
          let requestStatus = resData.result.deliverystatus;
          if(!requestStatus){//订单号出现错误
            wx.showModal({
                    title: '提示',
                    content: '快递单号错误，请重新填写',
                    showCancel:false,
                    success: function (res) {
                    }
                  })
            }else {//查询成功
                //将查询出的物流信息保存到查询记录中
                let historySearchList = wx.getStorageSync("historySearchList");
                if(!historySearchList){
                  historySearchList = [];
                }
                //过滤器，
                let newList = historySearchList.filter(function (val) {
                  return (val.expressNumber != expressNumber);
                });
                newList.push({
                  "expressNumber": expressNumber,
                  "expressName": resData.result.expName,
                  "expressCode": resData.result.type,
                  "logoSrc":resData.result.logo,
                  "lastInfo":resData.result.list[0],
                })
                
                wx.setStorageSync("historySearchList",newList);

              //将当前查询出的物流信息保存在缓存中
              wx.setStorageSync("expressDetail", resData);
              //转发到详情页面
              wx.navigateTo({
                          url: '../detail/detail'
                        })
            }
        }
      })
  }
})
