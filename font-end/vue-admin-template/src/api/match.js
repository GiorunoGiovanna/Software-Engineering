import request from '@/utils/request'
import qs from 'qs'

// 获取表单列表
export function searchM() {
    return request({
        url: '/match/searchM',
        method: 'get'
    })
}

// 添加场地
export function addM(data) {
    return request({
        url: '/match/addM',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 删除赛事
export function deleteM(data) {
    return request({
        url: `/match/deleteM?mId=${data}`,
        method: 'delete',
    })
}

// 修改赛事
export function updateM(data) {
    return request({
        url: '/match/updateM',
        method: 'post',
        data: qs.stringify(data)
    })
}