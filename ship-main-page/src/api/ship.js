import request from '@/utils/request'

export function getAlarm() {
    return request({
        url: '/sjcj/alarm/list',
        method: 'get'
    })
}


export function getSolution() {
    return request({
        url: '/sjcj/solution/list',
        method: 'get'
    })
}


export function getCurrentEnergyInfo(){
    return request({
        url: '/sjcj/home/getCurrentEnergyInfo',
        // url: '/home/getCurrentEnergyInfo',
        method: 'get'
    })
}

// 获取岸端建议
// export function getAlarm() {
//     return request({
//         url: '/sjcj/solution/list',
//         method: 'get'
//     })
// }
