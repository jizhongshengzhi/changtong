// pages/personal/edit/edit.js
const app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        backOrNot: true,
        userInfo: {},
        tempFilePaths: '',
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        email: 'xxx@xxx.com',
        autograph: '测试签名',
        sex: 0
    },
    onLoad: function () {
        if (app.globalData.userInfo) {
            this.setData({
                userInfo: app.globalData.userInfo,
                hasUserInfo: true
            })
        } else if (this.data.canIUse){
            // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
            // 所以此处加入 callback 以防止这种情况
            app.userInfoReadyCallback = res => {
                this.setData({
                    userInfo: res.userInfo,
                    hasUserInfo: true
                })
            }
        } else {
            // 在没有 open-type=getUserInfo 版本的兼容处理
            wx.getUserInfo({
                success: res => {
                    app.globalData.userInfo = res.userInfo
                    this.setData({
                        userInfo: res.userInfo,
                        hasUserInfo: true
                    })
                }
            })
        }
    },
    getUserInfo: function(e) {
        app.globalData.userInfo = e.detail.userInfo
        this.setData({
            userInfo: e.detail.userInfo,
            hasUserInfo: true
        })
    },
    save: function () {
        wx.showToast({
            title: '修改成功',
            icon: 'success',
        });
        setTimeout(function () {
            wx.navigateBack({ delta: 1 })
        }, 500);
    },
    chooseImage: function () {
        wx.chooseImage({
            count: 1, // 默认9
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
                // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
                that.setData({
                    tempFilePaths: res.tempFilePaths
                })
            }
        })
    }
});
