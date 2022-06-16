const express = require('express')
//创建路由对象
const router = express.Router()
//引入用户路由处理函数模块
const financialHandler = require('../router-handler/financial')

// 添加场地
router.get('/searchF', financialHandler.searchF)

//将路由对象共享出去
module.exports = router