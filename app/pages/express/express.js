var flag = false;
Page({
  data: {
    name_j: "",
    tel_j: "",
    addre_j: "",
    name_s: "",
    tel_s: "",
    addre_s: "",
    dateValue: '预约日期',
    timeValue: '预约时间',
    display1_j: "flex",
    display2_j: "none",
    display1_s: "flex",
    display2_s: "none",
    marks: [
      { name: 'ThreeC', value: '数码3c 　',checked:'' },
      { name: 'clothe', value: ' 衣物', checked: '' },
      { name: 'others', value: ' 其他', checked: '' }
    ],
    comment: ""
  },

  onLoad: function (e) {
    var options_j = wx.getStorageSync("currentAddressData_ji");
    var options_s = wx.getStorageSync("currentAddressData_shou")
    var flag_j =wx.getStorageSync("flag_j");
    var flag_s = wx.getStorageSync("flag_s");
    //对寄件人进行设置
    if (flag_j != 1) {
      this.setData({
        display1_j: "flex",
        display2_j: "none",
      })
    } else {
      this.setData({
        display1_j: "none",
        display2_j: "flex",
        name_j: options_j.name,
        tel_j: options_j.tel,
        addre_j: options_j.address
      })
    }
    //对收件人进行设置
    if (flag_s != 1) {
      this.setData({
        display1_s: "flex",
        display2_s: "none",
      })
    } else {
      this.setData({
        display1_s: "none",
        display2_s: "flex",
        name_s: options_s.name,
        tel_s: options_s.tel,
        addre_s: options_s.address
      })
    }
  },

  toChooseAddre(e) {
    var item = e.currentTarget.dataset.item
    console.log(item)
    console.log(e)
    wx.redirectTo({
      url: '../personal/address/address?flag='+item
    });
  },

  timePickerBindchange: function (e) {
    this.setData({
      timeValue: e.detail.value
    })
  },
  datePickerBindchange: function (e) {
    this.setData({
      dateValue: e.detail.value
    })
  },
  //点击立即预约
  formSubmit: function (e) {
   wx.showToast({
     title: '已下单，等待快递员上门揽件',
     duration: 5000,
     icon:'none'
   })
    wx.setStorageSync("flag_j", 0)
    wx.setStorageSync("flag_s", 0)
    this.onLoad(e)
  }
})