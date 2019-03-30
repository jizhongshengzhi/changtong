# changtong
畅通--依托于智能马桶的人体健康助手

# 目录说明
* `document`本项目文档,包括策划书，原型等
* `wechat`微信小程序

# git操作指南
* `git clone https://github.com/jizhongshengzhi/changtong.git`克隆项目文件（首次执行）
* `git branch <分支号>`分支号与issues号对应，避免与其他人发生冲突
* `git branch`查看本地所有分支
* `git checkout <分支号>`切换本地分支
* `git status`查看本地分支状态
* `git checkout .`将此路径下所有文件的改动取消
* `git add .`将此路径下所有文件暂存
* `git commit -m "你对此次改动的描述"`本地保存,需要先执行git add .（执行前先测试一下，确认程序无误后再commit）
* `git push origin <本地分支号>:<远程分支号>`将本地的代码上传到远程分支上，执行前先commit
* `git pull`将远程的分支拉取到本地

## 只查看项目的效果，不进行代码的修改
1. `git clone https://github.com/jizhongshengzhi/changtong.git`首次执行
2. `git pull`更新最新版本

## 代码开发人员
1. `git status`查看当前分支状态，确认当前分支是干净的，否则保存或者取消保存当前分支改动的内容
2. `git checkout master`切换到主分支
3. `git pull`拉取最新版本代码
4. 新建issues
5. `git branch <分支号>`分支号为issues号
6. `git checkout <分支号>`同上
7. `git status`写完代码后查看改动的文件
8. `git add .`需要先cd到项目的根目录执行暂存操作
9. `git commit -m “xxxxx”`保存此分支内容
10. `git checkout master`切换到主分支
11. `git pull`拉取最新版本代码
12. `git checkout <分支号>`回到你刚才的分支
13. `git merge master`将主分支与你当前的分支进行合并，如果与冲突（conflit），找对应路径下的文件修改冲突,然后执行git add,git commit
14. `git push origin <分支号>:<分支号>`将你本地的分支上传到远程
15. 建立pull request，提交合并代码的申请
16. 千万不要修改master分支上的内容，如果修改了，记得千万不要git add,git commit
