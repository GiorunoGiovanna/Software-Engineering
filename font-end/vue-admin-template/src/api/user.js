import request from '@/utils/request'
import qs from 'qs'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify(data)
  })
}

export function getInfo(token) {
  return request({
    url: '/getUserInfo',
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
