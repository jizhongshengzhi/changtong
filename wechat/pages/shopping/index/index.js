const app = getApp();
Page({
  data: {
    CustomBar: app.globalData.CustomBar,
    icon: []
  },
  searchIcon(e) {
    let key = e.detail.value.toLowerCase();
    let list = this.data.icon;
    for (let i = 0; i < list.length; i++) {
      let a = key;
      let b = list[i].name.toLowerCase();
      if (b.search(a) != -1) {
        list[i].isShow = true
      } else {
        list[i].isShow = false
      }
    }
    this.setData({
      icon: list
    })
  },
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar
  },
  isCard(e) {
    this.setData({
      isCard: e.detail.value
    })
  }
  
})
// Page({
//   data: {
//     StatusBar: app.globalData.StatusBar,
//     CustomBar: app.globalData.CustomBar
//   },
//   isCard(e) {
//     this.setData({
//       isCard: e.detail.value
//     })
//   },
// });