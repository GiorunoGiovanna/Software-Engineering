import request from '@/utils/request'
import qs from 'qs'

// 获取表单列表
export function searchET() {
    return request({
        url: '/euqipment/searchET',
        method: 'get'
    })
}

// 删除器材种类
export function deleteET(param) {
    return request({
        url: `/euqipment/deleteET?name=${param}`,
        method: 'delete'
    })
}

// 新增器材
export function addET(data) {
    return request({
        url: 'euqipment/addET',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 获取器材购入表
export function searchE() {
    return request({
        url: 'euqipment/searchE',
        method: 'get'
    })
}

// 新增器材
export function addE(data) {
    return request({
        url: 'euqipment/addE',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 查询器材借出表
export function searchER() {
    return request({
        url: '/euqipment/searchER',
        method: 'get'
    })
}

//归还器材接口
export function returnER(data) {
    return request({
        url: 'euqipment/returnER',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 添加器材借出表
export function addER(data) {
    return request({
        url: 'euqipment/addER',
        method: 'post',
        data: qs.stringify(data)
    })
}