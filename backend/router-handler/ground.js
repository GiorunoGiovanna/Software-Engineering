/**
 * 在这里定义和器材相关的路由处理函数
 */

//导入数据库操作模块
const db = require('../db/index')

//添加场地
exports.addG = (req, res) => {
    const data = req.body
    const sqlStr = 'insert into ground set ?'
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

// 修改场地租金
exports.updateG = (req, res) => {
    const data = req.body
    const sqlStr = `update ground set gFee=${data.gFee} where gId=${data.gId}`
    db.query(sqlStr, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        return res.send({
            status: 200,
            message: '修改成功'
        })
    })
}

// 查询场地
exports.searchG = (req, res) => {
    const data = req.body
    const sqlStr = `select * from ground`
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

// 查询场地借出
exports.searchGB = (req, res) => {
    const data = req.body
    const sqlStr = `select * from groundbook`
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

// 添加场地借用,价钱自动算
exports.addGB = (req, res) => {
    const data = req.body
    let hours = (new Date(data.bEndTime) - new Date(data.bStartTime)) / 1000 / 60 / 60
    // 查找这个场地的收费标准
    const sqlStr1 = 'select gFee from ground where gId=?'
    db.query(sqlStr1, data.gId, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        data.bFee = result[0].gFee * hours
        const sqlStr = 'insert into groundbook set ?'
        console.log(data)
        db.query(sqlStr, data, (err, result) => {
            if (err) {
                return res.cc(err)
            }
            return res.send({
                status: 200,
                message: '添加成功'
            })
        })
    })
}

// 更新场地借用状态
exports.updateGB = (req, res) => {
    const data = req.body
    const sqlStr = `update groundbook set bStatus=${data.bStatus} where bId=${data.bId}`
    db.query(sqlStr, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        // return res.send({
        //     status: 200,
        //     message: '修改成功'
        // })
        const sqlStr3 = 'insert into financial set ?'
        db.query(sqlStr3, { fIn: data.bFee, fInType: 1, date: new Date() }, (err, result) => {
            if (err) {
                return res.cc(err)
            }
            return res.send({
                status: 200,
                message: '添加成功'
            })
        })
    })
}

//删除场地借用状态
exports.deleteGB = (req, res) => {
    const data = req.body
    const sqlStr = `delete from groundbook where bId=${data.bId}`
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