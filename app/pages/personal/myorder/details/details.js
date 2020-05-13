// pages/personal/myorder/details/details.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      order:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log("订单详情：");
    console.log(options)
    this.dataSetter(options.id);
  },
  dataSetter:function(e){
    var that = this;
    var orderData = wx.getStorageSync("orderData");
    for(var i =0;i<orderData.length;i++){
      if(e===orderData[i].id){
        that.setData({
          order:orderData[i]
        });
        break;
      }
    }
  }
})