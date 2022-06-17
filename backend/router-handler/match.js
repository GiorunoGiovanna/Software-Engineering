/**
 * 在这里定义和赛事相关的路由处理函数
 */

//导入数据库操作模块
const db = require('../db/index')

//添加赛事
exports.addM = (req, res) => {
    const data = req.body
    const sqlStr = 'insert into matchList set ?'
    db.query(sqlStr, data, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        return res.send({
            status: 200,
            message: '添加成功'
        })
    })
}

// 删
exports.deleteM = (req, res) => {
    const data = req.query
    // console.log(data.mId)
    const sqlStr = `delete from matchList where mId =${data.mId}`
    db.query(sqlStr, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        return res.send({
            status: 200,
            message: '删除成功'
        })
    })
}

// 改
exports.updateM = (req, res) => {
    const data = req.body
    const sqlStr = `update matchList set ? where mId=${data.mId}`
    db.query(sqlStr, { mName: data.mName, text: data.text }, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        return res.send({
            status: 200,
            message: '更新成功'
        })
    })
}


// 查
exports.searchM = (req, res) => {
    const data = req.body
    const sqlStr = 'select * from matchList'
    db.query(sqlStr, data, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        return res.send({
            status: 200,
            message: result
        })
    })
}
