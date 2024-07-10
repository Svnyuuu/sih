import request from '@/utils/request'

// 登录方法
export function login(username, password, code, uuid) {
  return request({
    // url: '/auth/login',
    url:'/login/login.json',
    headers: {
      isToken: false,
      repeatSubmit: false
    },
    method: 'post',
    data: { username, password, code, uuid }
  })
}

// 注册方法
export function register(data) {
  return request({
    url: '/auth/register',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
}

// 刷新方法
export function refreshToken() {
  return request({
    url: '/auth/refresh',
    method: 'post'
  })
}

// 获取用户详细信息
export function getInfo() {
  return request({
    // url: '/system/user/getInfo',
    url: '/login/getInfo.json',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    // url: '/auth/logout',
    url: '/login/loginOut.json',
    method: 'delete'
  })
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: '/code',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}