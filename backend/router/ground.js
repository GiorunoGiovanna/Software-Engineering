const express = require('express')
//创建路由对象
const router = express.Router()
//引入用户路由处理函数模块
const groundHandler = require('../router-handler/ground')

// 添加场地
router.post('/addG', groundHandler.addG)

// 修改场地费用
router.post('/updateG', groundHandler.updateG)

// 查询场地
router.get('/searchG', groundHandler.searchG)

// 查询场地借出
router.get('/searchGB', groundHandler.searchGB)

// 添加场地借出
router.post('/addGB', groundHandler.addGB)

// 修改场地借出
router.post('/updateGB', groundHandler.updateGB)

// 删除场地借出
router.delete('/deleteGB', groundHandler.deleteGB)

//将路由对象共享出去
module.exports = router