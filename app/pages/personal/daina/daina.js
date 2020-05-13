// pages/goods/goods.js
var app = getApp();
Page({
  data: {
    //页面切换
    currentIndex1: true,//待入仓
    currentIndex2: false,//在仓
    currentIndex4: false,//已完成
    loadNum: 2,//待入仓个数
    inNum: 3,//在仓个数
    finishNum: 3,//已完成个数
    loadList: [],
    inList: [],
    //已完成数据
    finishList: [],
    //导航栏样式
    kong2: false,
    currentwait:{}
  },
  currentIndex1: function (e) {

    this.setData({
      kong2: false,
      currentIndex1: true,
      currentIndex2: false,
      currentIndex3: false,
      currentIndex4: false
    })
  },
  currentIndex2: function (e) {
    this.setData({
      kong2: false,
      currentIndex1: false,
      currentIndex2: true,
      currentIndex3: false,
      currentIndex4: false
    })
  },
  //已完成
  currentIndex4: function (e) {
    this.setData({
      kong2: false,
      currentIndex1: false,
      currentIndex2: false,
      currentIndex3: false,
      currentIndex4: true
    })
  },
  //点击帮拿
  loadEdit(e) {
    console.log(e)
    var that = this;
    //获取当前订单的id
    var id =e.currentTarget.dataset.item;
    var token =app.globalData.token;
    if(token){
      wx.request({
        url: 'http://127.0.0.1:8080/daina/put',
        data:{
          'id':id
          },
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'token':token
        },
        method: 'put',
        success: function (res) {//成功回调函数
          wx.showToast({
            title: '帮拿成功',
            icon:'none',
            duration:1000
          })
          that.load();
        }
      })
    }
  },
  //点击已完成的查看详情
  finishLook() {
    wx.navigateTo({
      url: '../finishDetails/finishDetails',
    })
  },
  //点击已完成的查看详情
  outLook() {
    wx.navigateTo({
      url: '../outDetails/outDetails',
    })
  },
  //待入仓查看详情
  loadDetails() {
    wx.navigateTo({
      url: '../details/details',
    })
  },
  //等待配送查看详情
  inDetails(e) {
    var id=e.currentTarget.dataset.item;
    wx.navigateTo({
      url: '/pages/personal/daina/details/details?id=' + id
    })
    
  },
  //页面卸载
  onHide: function () {
    this.setData({
      isCheckbox: false,
      select_all: false,
      kong2: false
    })
  },
  onLoad(options){
    //进行等待帮拿快递的数据查询数据的查询
    this.load();
  },
  load:function(e){
    var that= this;
    wx.request({
      url: 'http://127.0.0.1:8080/daina/all',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'get',
      success: function (res) {//成功回调函数
        console.log(res)
        console.log(wx.getStorageSync("userInformation"))
        //进行数据的分类
        that.dataSetter(res.data.data);
      }
    })
  },
  //进行数据的绑定
  dataSetter:function(e){
    var userInformation =wx.getStorageSync("userInformation");
      console.log(e)
    //刷新页面时初始化数据
    this.setData({
      loadList: [],
      inList: [],
      finishList: [],
      loadNum: 0,
      inNum: 0,
      finishNum: 0
    })
    var that = this;
    var loadList1 = that.data.loadList;
    var inList1 = that.data.inList;
    var finishList1 = that.data.finishList;
    var loadNum = that.data.loadNum;//违背领取个数
    var inNum = that.data.inNum;//等待收获个数
    var finishNum = that.data.finishNum;//已完成个数 
    for (var i = 0; i < e.length; i++) {
      if (e[i].status === 1&&userInformation.id!=e[i].userId) {//等待背领取
        loadList1.push(e[i]);
        loadNum += 1;
      } else if (e[i].status === 2&&userInformation.id===e[i].who) {//我接的单
        inList1.push(e[i]);
        inNum += 1;
      } else if(e[i].who===userInformation.id&&e[i].status===3){//已完成
        finishList1.push(e[i]);
        finishNum += 1;
      }
    }
    that.setData({
      loadList: loadList1,
      inList: inList1,
      finishList: finishList1,
      loadNum,
      inNum,
      finishNum
    })
    //将等待送递和已完成订单添加进缓存
    wx.setStorageSync("allorder",e);
  }
})