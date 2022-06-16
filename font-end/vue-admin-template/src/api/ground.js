import request from '@/utils/request'
import qs from 'qs'

// 获取表单列表
export function searchG() {
    return request({
        url: '/ground/searchG',
        method: 'get'
    })
}

// 添加场地
export function addG(data) {
    return request({
        url: '/ground/addG',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 更新场地费用
export function updateG(data) {
    return request({
        url: '/ground/updateG',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 获取表单列表
export function searchGB() {
    return request({
        url: '/ground/searchGB',
        method: 'get'
    })
}

// 添加场地借出表
export function addGB(data) {
    return request({
        url: '/ground/addGB',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 场地借出归还
export function updateGB(data) {
    return request({
        url: '/ground/updateGB',
        method: 'post',
        data: qs.stringify(data)
    })
}

// 删除场地归还
export function deleteGB(data) {
    return request({
        url: '/ground/deleteGB',
        method: 'delete',
        data: qs.stringify(data)
    })
}