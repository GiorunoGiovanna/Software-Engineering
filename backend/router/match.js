const express = require('express')
//创建路由对象
const router = express.Router()
//引入用户路由处理函数模块
const matchHandler = require('../router-handler/match')

//添加赛事
router.post('/addM', matchHandler.addM)

// 删除赛事
router.delete('/deleteM', matchHandler.deleteM)

// 修改赛事
router.post('/updateM', matchHandler.updateM)

// 查赛事
router.get('/searchM', matchHandler.searchM)


//将路由对象共享出去
module.exports = router