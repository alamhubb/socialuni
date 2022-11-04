import HttpRequest, {requestConfig} from "./httpRequest";

const request: HttpRequest = new HttpRequest()

const httpBaseUrl = import.meta.env.VITE_APP_OPEN_IM_USER_URL

request.setConfig(config => { /* 设置全局配置 */
    config.baseUrl = httpBaseUrl + '/'/* 根域名不同 */
    config.timeout = 10 * 1000
    return config
})
request.interceptor.request((config: requestConfig) => { /* 请求之前拦截器 */
    return config
})

request.interceptor.response(
    response => {
        return response.data
    },
    error => {
        return error
    }
)
export default request
