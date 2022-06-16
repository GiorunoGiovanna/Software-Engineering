// 设备相关接口

const express = require('express')
//创建路由对象
const router = express.Router()
//引入用户路由处理函数模块
const equipmentHandler = require('../router-handler/equipment')


router.post('/addET', equipmentHandler.addET)

router.delete('/deleteET', equipmentHandler.deleteET)

router.get('/searchET', equipmentHandler.searchET)

router.get('/searchE', equipmentHandler.searchE)

router.post('/addE', equipmentHandler.addE)

router.post('/addER', equipmentHandler.addER)

router.get('/searchER', equipmentHandler.searchER)

router.post('/returnER', equipmentHandler.returnER)

//将路由对象共享出去
module.exports = router