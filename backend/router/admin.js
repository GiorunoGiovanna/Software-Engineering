const express = require('express')
//创建路由对象
const router = express.Router()
//引入用户路由处理函数模块
const adminHandler = require('../router-handler/admin')

//登录管理员
router.post('/login', adminHandler.login)

//登出
// router.post('/register', adminHandler.regUser)

//将路由对象共享出去
module.exports = router