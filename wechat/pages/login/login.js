// pages/login/login.js
//获取应用实例
const app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {

    },

    login: function() {
        wx.switchTab({
            url: '/pages/home/index/index'
        });
    }
});
