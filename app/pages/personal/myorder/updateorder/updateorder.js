// pages/personal/myorder/updateorder/updateorder.js
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id:'',
    order:{},
    currentorderData:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      console.log(" 传到updateorder的id值");
      this.readyData(options.id)
      this.setData({
        id: options.id
      })
      console.log(this.data.order)
      //准备数据
  },

 //数据准备
  readyData:function(e){
    var that =this;
    var orders=wx.getStorageSync("orderData");
    console.log(e)
    console.log(orders)
    for(var i=0;i<orders.length;i++){
      if(e===orders[i].id){
        that.setData({
          order:{
            tip: orders[i].tip,
            expressType: orders[i].expressType,
            expressNumber: orders[i].expressNumber,
            name: orders[i].name,
            tel: orders[i].tel,
            address: orders[i].address,
            myAddress: orders[i].myAddress
          }
        })
        break;
      }
    }
  },
  //修改数据时的动态绑定
  updateedit:function(e){
    var name = e.currentTarget.dataset.item;
    var currentorderData1 = this.data.currentorderData;
    currentorderData1[name] = e.detail.value;
    this.setData({
      currentorderData: currentorderData1
    })
  },
  //执行修改命令
  updateorder:function(e){
    //将id添加进数据
    this.data.currentorderData['id']=this.data.id;
    console.log(this.data.currentorderData);
    var self=this;
    wx.request({
      url: 'http://127.0.0.1:8080/daina',
      data: self.data.currentorderData,
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
        wx.navigateTo({
          url: '/pages/personal/myorder/myorder'
        })
      }
    })
    
  }
})