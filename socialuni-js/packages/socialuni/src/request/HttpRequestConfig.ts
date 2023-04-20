import UniUtil from "socialuni-util/src/util/UniUtil"
import { Config, RequestConfig, Response } from "./UniAppHttpRequest"
import { socialuniTokenModule } from "../store/SocialuniTokenModule"
import { HttpRequestConfigInterface } from './HttpRequestConfigInterface'

export default class HttpRequestConfig implements HttpRequestConfigInterface{
  getConfig() {
    const config: Config = {
      baseUrl: '/',
      timeout: 10 * 1000,
      header: {
        'Content-Type': 'application/json'
      },
      method: 'GET',
      dataType: 'json',
      responseType: 'text'
    }
    return config
  }

  requestBefore: (config: RequestConfig) => RequestConfig = (config: RequestConfig) => {
    const token = socialuniTokenModule.token
    if (token) {
      config.header.token = token
      //下次尝试把这里删除
    } else {
      config.header.token = null
    }
    return config
  }

  responseSuccess: (response: Response) => any = (response: Response) => {
    return response.data
  }

  responseFail: (response: Response) => Response = (response: Response) => {
    UniUtil.hideLoading()
    return response
  }
}
