var Base64 = require('../../utils/base64.js').Base64;
var MD5 = require('../../utils/md5.min.js');
var Util = require('../../utils/util.js');
var MockData = require('../../utils/mockdata.js');

//引入外部的js文件中的方法
import { request } from "../../request/index.js"
Page({
    data: {
        //物流轨迹
        detailList: {},
        //物流状态：签收或未签收
        expStatus:'',
        //物流公司名称
        expressName: "",
        //物流单号
        expressOrder: "",
        //物流公司代码
        expressCode: "",
        //物流公司logo
        logoSrc:'',
        //物流状态图
      express:'../../images/express_icon.png',
      expressgary:'../../images/express_icon_grey.png'
    },
    onLoad: function (options) {
        wx.showLoading({
            title: '加载中',
        })
        //获取物流的详细信息
        this.getExpressDetail();
        wx.setNavigationBarTitle({
            title: '物流详情'
        })
        
    },

    //获取图片信息----------------------------------------------------------------------
// getImages(){
//     request({
//       url: "http://127.0.0.1:8080/api/images/detail"
//     }).then(result=>{
//       //编写图片图片的url
//       let list=result.data;
//       this.setData({
//           express:list[0].imageSrc,
//           expressgary:list[1].imageSrc
//       })
//     })
//   },
  //---------------------------------------------------------------------------------

    //获取物流详情
    getExpressDetail: function (data) {

        //从缓存中获取当前物流的信息
        let expressDetail =wx.getStorageSync("expressDetail");
        this.setData({
            detailList: expressDetail.result.list,
            expressName: expressDetail.result.expName,
            expressOrder: expressDetail.result.number,
            expressCode: expressDetail.result.type,
            logoSrc: expressDetail.result.logo,
            expStatus:expressDetail.result.deliverystatus
        })
    },

    onPullDownRefresh: function () {
        let self = this;
        let data = {
            ShipperName: self.data.expressName,
            LogisticCode: self.data.expressOrder,
            ShipperCode: self.data.expressCode
        }
        this.getExpressDetail(data)
        wx.showLoading({
            title: "正在加载中"
        })
    },

    onShow: function () {
        setTimeout(function () {
            wx.hideLoading()
        }, 100);
    }
})