import Axios from 'axios'
import TokenUtil from '@/utils/TokenUtil'
import ToastUtil from '@/utils/ToastUtil'
import ErrorCode from '@/constant/ErrorCode'
import MsgUtil from '@/utils/MsgUtil'
import { userModule } from '@/store'
import PageUtil from '@/utils/PageUtil'

// create an axios instance
const request = Axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 120000 // request timeout
})
// request interceptor
request.interceptors.request.use(
  config => {
    const token = TokenUtil.get()
    if (token) {
      config.headers.token = token
    } else {
      config.headers.token = null
    }
    // do something before request is sent
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// response interceptor
request.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    /* if (res.code === -1000) {
      return MessageBox.confirm('登录超时，请重新登录', '提示', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        userModule.setUser(null)
        router.push({
          path: '/login'
        })
        return Promise.reject(res)
      })
    } else if (res.code > 300) {
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(res)
    }*/
    /* // if the custom code is not 20000, it is judged as an error.
    if (res.code !== 20000) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }*/
    return response.data
  },
  error => {
    console.log(777)
    const response = error.response
    console.log(response)
    // do something with request error
    if (response.status) {
      const result = response.data
      switch (response.status) {
        case ErrorCode.not_logged:
          // 理论上不需要，因为token不会失效，也不会错误
          // 已知可能，切换环境导致token不同
          userModule.clearUserInfo()
          if (result && result.errorMsg) {
            SocialuniAppUtil.ToastUtil.error(result.errorMsg)
          } else {
            MsgUtil.unLoginMessage()
          }
          PageUtil.toHome()
          break
        case ErrorCode.custom:
          break
        default:
          if (result && result.errorMsg) {
            SocialuniAppUtil.ToastUtil.error(result.errorMsg)
          } else {
            MsgUtil.systemErrorMsg()
          }
          break
      }
      console.log(result)
      return Promise.reject(result) // 返回接口返回的错误信息
    }
    MsgUtil.systemErrorMsg()
    return Promise.reject(error)
  }
)

export default request
