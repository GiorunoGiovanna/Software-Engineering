import request from '@/utils/request'
import qs from 'qs'

// 获取表单列表
export function getAllHR() {
  return request({
    url: '/getAllHR',
    method: 'get'
  })
}

//测试上传文件
// export function upload(data) {
//   return request({
//     url: '/admin/file',
//     method: 'post',
//     data: qs.stringify(data)
//   })
// }

// 删除表单列表
export function deleteHR(param) {
  return request({
    url: `/deleteHR?id=${param}`,
    method: 'delete'
  })
}

// 新增表单列表
export function addHR(data) {
  return request({
    url: '/addHR',
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify(data)
  })
}

// 修改表单
export function updateHR(data) {
  return request({
    url: '/updateHR',
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify(data)
  })
}