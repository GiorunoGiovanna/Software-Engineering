/**
 * 在这里定义和用户相关的路由处理函数，供/router/user.js模块进行调用
 */

//导入数据库操作模块
const db = require('../db/index')
//导入加密插件bcryptjs
const bcryptjs = require('bcryptjs')
//导入jwt相关的包
const jwt = require('jsonwebtoken')
const config = require('../config')

//注册用户的处理函数
exports.regUser = (req, res) => {
	//获取用户提交的表单数据
	const userInfo = req.body
	//对表单中的数据进行合法性的校验
	if (!userInfo.username || !userInfo.password) {
		return res.send({ status: 1, message: '用户名或者密码不合法！' })
	}
	//定义sql语句，查询用户名是否被占用
	const sqlStr = 'select * from user where uId=?'
	db.query(sqlStr, userInfo.userId, (err, results) => {
		//执行sql语句失败
		if (err) {
			return res.cc(err)
		}
		//判断用户名是否被占用
		if (results.length > 0) {
			return res.cc('该用户名已被占用！')
		}
		//没有问题，写入数据，加密密码
		//对密码进行加密
		userInfo.password = bcryptjs.hashSync(userInfo.password, 10)
		const sqlStr2 = 'insert into user set ?'
		db.query(sqlStr2, { uId: userInfo.userId, uName: userInfo.username, uPassword: userInfo.password, status: 0 }, (err, results) => {
			if (err) {
				return res.cc(err)
			}
			// res.send({
			// 	status: 200,
			// 	message: '注册成功！'
			// })
			res.cc('注册成功！', 200)
		})
	})
}

//登录的处理函数
exports.login = (req, res) => {
	//获取客户端提交的信息
	const userinfo = req.body
	//对表单中的数据进行合法性校验
	if (!userinfo.userId || !userinfo.password) {
		return res.cc('用户名或密码不合法')
	}
	//查看用户是否存在
	const sqlStr = 'select * from user where uId=?'
	db.query(sqlStr, userinfo.userId, (err, result) => {
		if (err) {
			return res.cc(err)
		}
		if (result.length === 0) {
			return res.cc('此用户不存在！')
		}
		//用户名存在，验证密码
		if (bcryptjs.compareSync(userinfo.password, result[0].uPassword)) {
			const user = { ...result[0], password: '', user_pic: '' }
			const tokenStr = jwt.sign(user, config.jwtSecretKey, { expiresIn: config.expiresIn })
			return res.send({
				status: 200,
				message: {
					name: userinfo.username,
					power: result.power ? result.power : 0
				},
				token: 'Bearer ' + tokenStr
			})
		} else {
			return res.cc('密码错误！')
		}
	})
}

//登出的处理函数
exports.loginOut = (req, res) => {
	//提取客户端提交的信息
	const userinfo = req.headers.authorization
	// console.log(userinfo)
	//需要登录的token

	//然后使token过期，完成登出
	return res.cc('登出成功', 200)
}

// 返回token携带的信息
exports.userInfo = (req, res) => {
	// const { token } = req.query
	// console.log(req.user)
	return res.send({
		status: 200,
		message: req.user
	})
}