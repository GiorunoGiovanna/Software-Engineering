/**
 * 在这里定义和器材相关的路由处理函数
 */

//导入数据库操作模块
const db = require('../db/index')

//添加器材种类
exports.addET = (req, res) => {
    const data = req.body
    const sqlStr = 'insert into equipmenttype set ?'
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

//删除器材种类
exports.deleteET = (req, res) => {
    const data = req.query
    const sqlStr = 'delete from equipmenttype where eTypeName=?'
    db.query(sqlStr, data.name, (err, result) => {
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

// 查
exports.searchET = (req, res) => {
    const data = req.body
    const sqlStr = 'select * from equipmenttype'
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

// 查询所有购入器材
exports.searchE = (req, res) => {
    const data = req.body
    const sqlStr = 'select * from equipment'
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


// 新增购入器材
exports.addE = (req, res) => {
    const data = req.body
    const sqlStr = 'insert into equipment set ?'
    db.query(sqlStr, data, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        // 把新增器材数量加到总器材数量中
        const sqlStr2 = `update equipmenttype set totalNum=totalNum+? where eTypeId=${data.eTypeId}`
        db.query(sqlStr2, data.buyNum, (err, result) => {
            if (err) {
                return res.cc(err)
            }
            const sqlStr3 = 'insert into financial set ?'
            db.query(sqlStr3, { fIn: -data.buyCost, fInType: 2, date: data.buyDate }, (err, result) => {
                if (err) {
                    return res.cc(err)
                }
                return res.send({
                    status: 200,
                    message: '添加成功'
                })
            })
        })
    })
}

// 新增器材借出
exports.addER = (req, res) => {
    const data = req.body
    const sqlStr3 = `select eFee from equipmenttype where eTypeId=${data.eTypeId}`
    db.query(sqlStr3, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        data.borrowFee = result[0].eFee * data.borrowNum
        const sqlStr = 'insert into equipmentrent set ?'
        db.query(sqlStr, data, (err, result) => {
            if (err) {
                return res.cc(err)
            }
            const sqlStr2 = `update equipmenttype set borrowNum=borrowNum+? where eTypeId=${data.eTypeId}`
            db.query(sqlStr2, data.borrowNum, (err, result) => {
                if (err) {
                    return res.cc(err)
                }
                return res.send({
                    status: 200,
                    message: '添加成功'
                })
            })
        })
    })
}

// 查询接口
exports.searchER = (req, res) => {
    const data = req.body
    const sqlStr = 'select * from equipmentrent'
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

// 归还器材接口
exports.returnER = (req, res) => {
    const data = req.body
    console.log(data.status, data.eRentId)
    const sqlStr = `update equipmentrent set status=${data.status} where eRentId=${data.eRentId}`
    db.query(sqlStr, (err, result) => {
        if (err) {
            return res.cc(err)
        }
        const sqlStr2 = `update equipmenttype set borrowNum=borrowNum-? where eTypeId=${data.eTypeId}`
        db.query(sqlStr2, data.borrowNum, (err, result) => {
            if (err) {
                return res.cc(err)
            }
            // return res.send({
            //     status: 200,
            //     message: '添加成功'
            // })
            const sqlStr3 = 'insert into financial set ?'
            console.log(data)
            db.query(sqlStr3, { fIn: data.borrowFee, fInType: 1, date: new Date() }, (err, result) => {
                if (err) {
                    return res.cc(err)
                }
                return res.send({
                    status: 200,
                    message: '添加成功'
                })
            })
        })
    })
}
