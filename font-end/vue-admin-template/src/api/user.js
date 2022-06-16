import request from '@/utils/request'
import qs from 'qs'
import store from '../store'

export function login(data) {
  console.log(data)
  // if(store.state.identity==='user')
  return request({
    url: `/${store.state.identity}/login`,
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify({
      userId: data.username,
      password: data.password
    })
    // data: {
    //   userName: 'admin',
    //   password: '123',
    //   userId: '201911701427'
    // }
  })
}

export function getInfo(token) {
  return request({
    url: 'user/userInfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}
