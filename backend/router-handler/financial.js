/**
 * 在这里定义和器材相关的路由处理函数
 */

//导入数据库操作模块
const db = require('../db/index')

//查看财务总表
exports.searchF = (req, res) => {
    const data = req.body
    const sqlStr = 'select * from financial'
    db.query(sqlStr, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        return res.send({
            status: 200,
            message: result
        })
    })
}