/* eslint-disable */
/**
 * Request 0.0.9
 * @Class Request
 * @description luch-request 0.0.9 http请求插件
 * @Author lu-ch
 * @Date 2019-08-20
 * http://ext.dcloud.net.cn/plugin?id=392
 */
import RequestOptions = UniApp.RequestOptions
import ResultRO from "../model/social/ResultRO";

interface header { // header 接口
    'Content-Type'?: string;

    [propName: string]: any;
}

interface Config { // init 全局config接口
    baseUrl: string;
    header: header;
    method: string;
    dataType: string;
    responseType: string;

    [propName: string]: any;
}

export interface Interceptor { // init 拦截器接口
    request: (fun: (config: RequestConfig) => RequestConfig) => void
    response: (fun: (rep: Response) => Response, fun1: (rep: Response) => Response) => void
}

interface options { // request 方法配置参数（public）
    url: string;
    dataType?: string;
    data?: object;
    header?: header;
    method?: string;
    responseType?: string;
}

interface handleOptions { // get/post 方法配置参数（public）
    header?: header;
    dataType?: string;
    responseType?: string;
}

interface Response { // 响应体 (public)
    statusCode?: number;
    config: Record<string, any>;
    errMsg: string;

    [propName: string]: any;
}

interface RequestConfig { // 请求之前参数配置项 (public)
    readonly baseUrl: string;
    url: string;
    dataType: string;
    data: object;
    header: header;
    method: string;
    complete?: Function;
    responseType: string;
}

export default class UniAppHttpRequest {
    config: Config = {
        baseUrl: '',
        header: {
            'Content-Type': 'application/json'
        },
        method: 'GET',
        dataType: 'json',
        responseType: 'text'
    }

    static posUrl(url: string): boolean { /* 判断url是否为绝对路径 */
        return /(WebAPI|https|http):\/\/([\w.]+\/?)\S*/.test(url)
    }

    interceptor: Interceptor = {
        request: (callback: (config: RequestConfig) => RequestConfig) => {
            if (callback) {
                this.requestBeforeFun = callback
            }
        },
        response: (cb: (rep: Response) => any, ecb: (rep: Response) => any) => {
            if (cb && ecb) {
                this.requestComFun = cb
                this.requestComFail = ecb
            }
        }
    }

    protected requestBeforeFun: (config: RequestConfig, cancel?: Function) => RequestConfig = (config: RequestConfig) => {
        return config
    }

    protected requestComFun: (response: Response) => any = (response: Response) => {
        return response
    }

    protected requestComFail: (response: Response) => Response = (response: Response) => {
        return response
    }

    setConfig(f: Function) {
        this.config = f(this.config)
    }

    request<T>(options: options) {
        const _options: RequestConfig = {
            baseUrl: this.config.baseUrl,
            dataType: options.dataType || this.config.dataType,
            responseType: options.responseType || this.config.responseType,
            url: UniAppHttpRequest.posUrl(options.url) ? options.url : (this.config.baseUrl + options.url),
            data: options.data || {},
            header: options.header || this.config.header,
            method: options.method || this.config.method
        }
        return new Promise<ResultRO<T>>((resolve: Function, reject: Function) => {
            let next = true
            let _config: object = {}
            _options.complete = (response: Response) => {
                response.config = _config
                if (response.statusCode === 200) { // 成功
                    resolve(this.requestComFun(response))
                } else {
                    reject(this.requestComFail(response))
                }
            }
            const cancel = (t = 'handle cancel', config = _options): void => {
                const err = {
                    errMsg: t,
                    config: config
                }
                reject(err)
                next = false
            }
            _config = {...this.requestBeforeFun(_options, cancel)}
            if (!next) return
            uni.request(_config as RequestOptions)
        })
    }

    get<T>(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request<T>({
            url,
            data,
            method: 'GET',
            ...options
        })
    }

    post<T>(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request <T>({
            url,
            data,
            method: 'POST',
            ...options
        })
    }

    // #ifndef MP-ALIPAY
    put(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request({
            url,
            data,
            method: 'PUT',
            ...options
        })
    }

    // #endif

    // #ifdef APP-PLUS || H5 || MP-WEIXIN || MP-BAIDU
    delete(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request({
            url,
            data,
            method: 'DELETE',
            ...options
        })
    }

    // #endif

    // #ifdef APP-PLUS || H5 || MP-WEIXIN
    connect(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request({
            url,
            data,
            method: 'CONNECT',
            ...options
        })
    }

    // #endif

    // #ifdef APP-PLUS || H5 || MP-WEIXIN || MP-BAIDU
    head(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request({
            url,
            data,
            method: 'HEAD',
            ...options
        })
    }

    // #endif

    // #ifdef APP-PLUS || H5 || MP-WEIXIN || MP-BAIDU
    options(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request({
            url,
            data,
            method: 'OPTIONS',
            ...options
        })
    }

    // #endif

    // #ifdef APP-PLUS || H5 || MP-WEIXIN
    trace(url: string, data: object = {}, options: handleOptions = {}) {
        return this.request({
            url,
            data,
            method: 'TRACE',
            ...options
        })
    }

    // #endif
}

export {
    options,
    handleOptions,
    Config,
    RequestConfig,
    Response
}
