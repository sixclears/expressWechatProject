//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 500,
    circular: true,
    inputdata:''
  },
  //轮播
  onLoad(e){
      this.setData({
        inputdata:[]
      })

  },
  changeIndicatorDots(e) {
    this.setData({
      indicatorDots: !this.data.indicatorDots
    })
  },
  changeAutoplay(e) {
    this.setData({
      autoplay: !this.data.autoplay
    })
  },
  intervalChange(e) {
    this.setData({
      interval: e.detail.value
    })
  },
  durationChange(e) {
    this.setData({
      duration: e.detail.value
    })
  },
  //存放
  order() {
    var data = this.data.inputdata;
    if (data.length === null || data.width === null || data.hieght === null || data.length === 0 || data.width === 0 || data.hieght === 0){
      wx.showToast({
        title: '填写信息不能为空或者0',
        icon: 'none',
        duration: 1000//持续的时间
      })
    }
    else{
      var volume =data.length*data.hieght*data.width;
      console.log(volume)
      var money=0;
      if(volume<=8000){
        money=2;
      }else if(volume<=64000&&volume>8000){
        money=3;
      }else{
        money=5;
      }
      wx.navigateTo({
        url: '../order/order?money='+money,
      })
    }
  },
 //保存输入的数据
  inputeidt: function (e) {
    //获取输入的数值
    var tt = e.currentTarget.dataset.item;
    //获取空数组
    var ad = this.data.inputdata;
    
    //将‘属性名’：‘属性值’存入数组
     ad[tt] = e.detail.value;
    //打印数组
    this.setData({
      inputdata: ad
    })
  }
})
