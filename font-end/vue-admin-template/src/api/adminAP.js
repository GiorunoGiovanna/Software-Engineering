import request from '@/utils/request'
import qs from 'qs'

// 获取表单列表
export function getAllAP() {
  return request({
    url: '/admin/getAllAP',
    method: 'get'
  })
}

//特例，通过时间区间查询
export function getAPByTime(data) {
  return request({
    url: `/admin/getAPByTime`,
    method: 'post',
    data: qs.stringify(data)
  })
}

//模糊查询
export function getAP(keyword,type) {
  return request({
    url: `/admin/getAP?keyword=${keyword}&type=${type}`,
    method: 'get'
  })
}

// 删除表单
export function deleteAP(param) {
  return request({
    url: `/admin/deleteAP?id=${param}`,
    method: 'delete'
  })
}

// 添加表单
export function addAP(data) {
  return request({
    url: '/admin/addAP',
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify(data)
  })
}

// 修改表单
export function updateAP(data) {
  return request({
    url: '/admin/updateAP',
    method: 'post',
    // 参数的格式是Request Payload，具体也没看懂是什么，总之知道就是参数格式不对
    // 经典ajax请求可以成功，使用qs将请求参数拼接后上传
    data: qs.stringify(data)
  })
}
