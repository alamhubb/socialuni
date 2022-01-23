import Axios from 'axios'
import TokenUtil from '@/utils/TokenUtil'

// create an axios instance
const request = Axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API, // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 30000 // request timeout
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
export default request
