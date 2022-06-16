const express = require('express')
//创建路由对象
const router = express.Router()
//引入用户路由处理函数模块
const userHandler = require('../router-handler/user')

//注册新用户
router.post('/register', userHandler.regUser)

//登录
router.post('/login', userHandler.login)

//获取用户信息
router.get('/userInfo', userHandler.userInfo)

//登出
router.post('/loginOut', userHandler.loginOut)

//将路由对象共享出去
module.exports = router