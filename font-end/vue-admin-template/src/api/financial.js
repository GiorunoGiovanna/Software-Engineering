import request from '@/utils/request'
import qs from 'qs'

// 获取表单列表
export function searchF() {
    return request({
        url: '/financial/searchF',
        method: 'get'
    })
}
