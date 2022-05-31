import request from '@/utils/request'
import qs from 'qs'

// 获取表单列表
export function getAllAHR() {
  return request({
    url: '/admin/getAllAHR',
    method: 'get'
  })
}

// 删除表单列表
export function deleteAHR(param) {
  return request({
    url: `/admin/deleteAHR?id=${param}`,
    method: 'delete'
  })
}

// 新增表单列表
export function addAHR(data) {
  return request({
    url: '/admin/addAHR',
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify(data)
  })
}

// 修改表单
export function updateAHR(data) {
  return request({
    url: '/admin/updateAHR',
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify(data)
  })
}
