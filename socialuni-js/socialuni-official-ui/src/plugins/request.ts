import axios from 'axios'
import TokenUtil from "@/utils/TokenUtil";
import ErrorCode from "@/constant/ErrorCode";
import ToastUtil from "@/utils/ToastUtil";
import MsgUtil from "@/utils/MsgUtil";
import AppConst from "@/constant/AppConst";
import UserStore from "@/store/UserStore";
// create an axios instance

const request = axios.create({
  // baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  baseURL: AppConst.baseUrl,
    // send cookies when cross-domain requests
  withCredentials: true,
    // request timeout
  timeout: 120000,
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
        return response.data
    },
    error => {
        console.log(777)
        const response = error.response
        console.log(response)
        if (response){
            // do something with request error
            if (response.status) {
                const result = response.data
                switch (response.status) {
                    case ErrorCode.not_logged:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        // userModule.clearUserInfo()
                        UserStore.clearUser()
                        if (result && result.errorMsg) {
                            SocialuniAppUtil.ToastUtil.error(result.errorMsg)
                        } else {
                            MsgUtil.unLoginMessage()
                        }
                        // PageUtil.toHome()
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
        }
        MsgUtil.systemErrorMsg()
        return Promise.reject(error)
    }
)


export default request
