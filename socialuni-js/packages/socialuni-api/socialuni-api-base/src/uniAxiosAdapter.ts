import RequestSuccessCallbackResult = UniNamespace.RequestSuccessCallbackResult;
import GeneralCallbackResult = UniNamespace.GeneralCallbackResult;
import {AxiosRequestConfig, AxiosResponse} from "axios";


export default function uniAxiosAdapter(config: AxiosRequestConfig) {
    return new Promise<AxiosResponse>((resolve, reject) => {
        uni.request({
            ...config,
            url: config.baseURL + '/' + config.url,
            data: config.data,
            method: config.method as any,
            header: config.headers,
            timeout: config.timeout,
            complete: function (res: RequestSuccessCallbackResult & GeneralCallbackResult) {
                const response = {
                    ...res,
                    status: res.statusCode,
                    statusText: res.errMsg,
                    headers: res.header,
                    config: config
                };
                if (response.statusCode === 200) { // 成功
                    resolve(response)
                } else {
                    reject(response)
                }
            }
        })
    })
}
