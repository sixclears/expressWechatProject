var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //页面切换
    currentIndex1: true,//违背代取
    currentIndex3: false,//等待收货
    currentIndex4: false,//已完成
    loadNum: 0,//违背领取个数
    inNum: 0,//等待收获个数
    finishNum: 0,//已完成个数
    //未被领取数据
    loadList: [
    ],
    //等待收货数据
    outList: [
    ],
    //已完成数据
    finishList: [
    ],
    //在仓的导航栏样式
    kong2: false
  },
  //待入仓
  currentIndex1: function (e) {
    // this.onShow()
    this.setData({
      kong2: false,
      currentIndex1: true,
      currentIndex2: false,
      currentIndex3: false,
      currentIndex4: false
    })
  },
  //出仓
  currentIndex3: function (e) {
    // this.onShow()
    this.setData({
      kong2: false,
      currentIndex1: false,
      currentIndex2: false,
      currentIndex3: true,
      currentIndex4: false
    })
  },
  //已完成
  currentIndex4: function (e) {
    // this.onShow()
    this.setData({
      kong2: false,
      currentIndex1: false,
      currentIndex2: false,
      currentIndex3: false,
      currentIndex4: true
    })
  },
  //待入仓查看详情
  loadDetails(e) {
   console.log(e.currentTarget.dataset.item);
    var id = e.currentTarget.dataset.item;
   wx.navigateTo({
     url: '/pages/personal/myorder/details/details?id='+id
   })
  },
  //在仓查看详情
  inDetails() {
    wx.navigateTo({
      url: '../inDetails/inDetails',
    })
  },
  //删除订单
  delOrder(e) {
    console.log(e)
    var id = e.currentTarget.dataset.item;
    var that = this;
    wx.request({
      url: 'http://127.0.0.1:8080/daina',
      data: {
        'id':id
        },
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      method: 'delete',
      success: function (res) {//成功回调函数
        wx.showToast({
          title: '删除订单成功',
          icon: "none",
          duration: 1000
        })
        //重新刷新数据
        that.load();
      }
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
  onLoad:function(e){
      this.load();
  },


  //load()获取所有的数据
  load:function(e){
    //获取所有的代拿订单
    var that=this;
    var token= app.globalData.token;
    if(token){
      wx.request({
        url: 'http://127.0.0.1:8080/daina',
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'token': token
        },
        method: 'get',
        success: function (res) {//成功回调函数
        console.log(res)
        //进行数据的封装
        that.dataSetter(res.data.data);
        }
      })
    }
  },
  dataSetter:function(e){
    //刷新页面时初始化数据
    this.setData({
      loadList:[],
      outList:[],
      finishList:[],
      loadNum:0,
      inNum:0,
      finishNum:0
    })
    var that=this;
    var loadList1=that.data.loadList;
    var outList1=that.data.outList;
    var finishList1=that.data.finishList;
    var loadNum=that.data.loadNum;//违背领取个数
    var inNum=that.data.inNum;//等待收获个数
    var finishNum=that.data.finishNum;//已完成个数 
    for(var i=0;i<e.length;i++){
      if(e[i].status===1){//未被领取
        loadList1.push(e[i]);
        loadNum += 1;
      } else if (e[i].status === 2){//等待收货
        outList1.push(e[i]);
        inNum += 1;
      }else{//已完成
        finishList1.push(e[i]);
        finishNum += 1;
      }
    }
    that.setData({
      loadList:loadList1,
      outList:outList1,
      finishList:finishList1,
      loadNum,
      inNum,
      finishNum
    })
    //存入缓存
    wx.setStorageSync("orderData",e);
  },
  // onUnload: function () {
  //   wx.reLaunch({
  //     url: '/pages/personal/personal',
  //   })
  // },
  //确认收货
  sureget:function(e){
    //获取订单编号
    var id = e.currentTarget.dataset.item;
    var that = this;
    wx.request({
      url: 'http://127.0.0.1:8080/daina/one',
      data:{
        'id':id
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded',
      },
      method: 'put',
      success: function (res) {//成功回调函数
      wx.showToast({
        title: '确认收获成功',
        icon:"none",
        duration:1000
      })
      //重新刷新数据
      that.load();
      }
    })
  }
})