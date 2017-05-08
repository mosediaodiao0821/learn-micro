### 迁移功能：

普通登录／第三方账号登录

注册／第三方账号注册

第三方账号绑定

用户资料更改

邮件认证（用户激活）

用户找回密码


### 功能调整：

用户收藏，用户评论 相应功能抽离出来放在deal网站中

### 代码库

user_center_website  user_center_api

php基于 yii2 框架创建应用，使用代码库user_center_website

java基于 spirng cloud 创建应用，使用代码库user_center_api


### 数据库字典



### 接口文档

结构：

ERROR:
   code : 0
   msg : 操作成功
ACCOUNT:
   openId : 343438430
   type : "sina"
   unionid : "sdfd-sdfd-sdfd"
   nickname : "昵称"
USER:
   id: 1,
   isAdmin: true,
   name : "username",
   email : "user@gmail.com"
   status : "active"
   createTime : 1339456823
   isDefaultAvatar : false
   avatar :  "http://ubbs.dealmoon.com/uc_server/avatar.php?uid=70001&size=small"
   sex : "men"
   birthday : "1983-2-23"
   language : "cn"
   mobile : "18611163715"
   introduce: "这里是我的简介"
   isSubscribingNewsletter : 1
   location   : "beijing"
   thirdPartyAccounts:[<ACCOUNT>]

前端接口（用户登陆状态访问）

### [POST] user/info 通过token获取用户信息
   type : "iphone"
   ua:
   token : "121|dfdfldsfkjlsejklj"
   
   response:
   result : <ERROR>
   userInfo : <USER>


### [POST]user/login  登陆／第三方登陆
   email : "用户email"
   password : "rsa加密密码"
   bindUser : 1
   bindInfo : <ACCOUNT>
   
   response:
   result : <ERROR>
   userInfo : <USER>

### [POST]user/logout 用户退出
   token
   
   response：
   result : <ERROR>


### [POST]user/register 用户注册
  userInfo :
     name : "姓名"
     email : "user@gmail.com"
     password : "rsa加密密码"
  newsletter : 0 
  ip : "127.0.0.1"
  bindInfo : <ACCOUNT>
  
  response:
    result : <ERROR>
    token : "121|dfdfldsfkjlsejklj"
    userInfo : <USER>


### [POST]user/update 修改信息
    token : "121|dfdfldsfkjlsejklj"
    name : "username"
    password : 
       oldPassword : "1232"
       newPassword : "dfdf"
    sex : "men"
    birthday : "1983-02-23"
    language : "cn"
    mobile : "18611163715"
    introduce "简介

response:
   result : <ERROR>
   userInfo : <USER>


后端接口(验证？oauth?）

通过id批量获取用户信息

通过email批量获取用户信息







