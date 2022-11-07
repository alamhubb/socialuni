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
import {socialConfigModule, socialSystemModule, socialUserModule} from "socialuni-sdk/src/store/store";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import ErrorConst from "socialuni-constant/constant/ErrorConst";
import UserService from "socialuni-sdk/src/service/UserService";
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil";

interface header { // header 接口
    'Content-Type'?: string;

    [propName: string]: any;
}

interface config { // init 全局config接口
    baseUrl: string;
    header: header;
    method: string;
    dataType: string;
    responseType: string;
}

interface interceptor { // init 拦截器接口
    request: Function;
    response: Function;
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

interface newOptions { // 定义新的配置接口
    baseUrl: string;
    url: string;
    dataType: string;
    data: object;
    header: header;
    method: string;
    complete?: Function;
    responseType: string;
}

interface requestCb { // 请求拦截器回调
    (x: object, y: Function): object;
}

interface responseCb { // 相应拦截器回调
    (x: object): object;
}

interface response { // 响应体 (public)
    statusCode?: number;
    config: Record<string, any>;
    errMsg: string;

    [propName: string]: any;
}

interface requestConfig { // 请求之前参数配置项 (public)
    readonly baseUrl: string;
    url: string;
    dataType: string;
    data: object;
    header: header;
    method: string;
    readonly complete: Function;
    responseType: string;
}

export default class HttpRequest {
    config: config = {
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

    interceptor: interceptor = {
        request: (f: requestCb) => {


            if (f) {
                this.requestBeforeFun = f
            }
        },
        response: (cb: responseCb, ecb: responseCb) => {
            if (cb && ecb) {
                this.requestComFun = cb
                this.requestComFail = ecb
            }
        }
    }

    private requestBeforeFun(config: any, cancel?: Function): object {
        const token = socialUserModule.token
        if (token) {
            config.header.token = token
            //下次尝试把这里删除
        } else {
            config.header.token = null
        }
        // config.header.socialuniSecretKey = socialConfig.secretKey
        config.header.provider = socialSystemModule.mpPlatform
        config.header.platform = socialSystemModule.platform
        config.header.system = socialSystemModule.system

        /* else {
          //如果未登录，只允许查询talk，其他全部提示要登录
          constant configUrl: string = config.url
          if (configUrl.indexOf('queryTalks') < 0 && configUrl.indexOf('wxLogin') < 0) {
            MsgUtil.unLoginMessage()
            cancel('用户未登录请求了没有权限的接口', config)
          }
        } */
        return config
    }

    private requestComFun(response: any): object {
        return response.data
    }

    private requestComFail(error: object): object {
        //第一步，先判断 有没有error
        //判断data类型，如果没类型，直接走
        //有内容则为本系统？也不一定，判断拿内容类型
        //然后本系统处理
        if (error && error.statusCode) {
            const result = error.data
            if (result) {
                if (!result.errorMsg) {

                }
            }
        }
        UniUtil.hideLoading()
        const result = error.data
        if (error.statusCode && result) {
            const errorMsg = result.errorMsg
            if (errorMsg) {
                switch (error.statusCode) {
                    case ErrorConst.not_logged:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        UserService.userLogout()
                        MsgUtil.unLoginMessage()
                        break
                    case ErrorConst.banned:
                        // 理论上不需要，因为token不会失效，也不会错误
                        // 已知可能，切换环境导致token不同
                        UserService.userLogout()
                        AlertUtil.hint(errorMsg)
                        break
                    case ErrorConst.custom:
                        break
                    default:
                        AlertUtil.hint(errorMsg)
                        SocialuniAppAPI.sendErrorLogAPI(error.config.url, errorMsg)
                        break
                }
            } else {
                if (typeof result === 'string') {
                    //之前存在返回xml的情况
                    if (result.startsWith('<?xml')) {
                        try {
                            const errorXml = XmlUtil.parseXml(result)
                            const resultXml = errorXml.elements[0]
                            const resultRO: XmlResultRO = {} as XmlResultRO
                            for (const element of resultXml.elements) {
                                if (element.elements) {
                                    resultRO[element.name] = element.elements[0].text
                                }
                            }
                            const msg: string = socialConfigModule.appMoreConfig.errorMsg604SystemError
                            AlertUtil.hint(resultRO.Message + '，请重试，' + msg)
                            // 返回接口返回的错误信息
                            return result
                        } finally {
                        }
                    }
                }
                MsgUtil.systemErrorMsg()
                SocialuniAppAPI.sendErrorLogAPI(error.config.url, result)
            }
            // 返回接口返回的错误信息
            return result
        }
        MsgUtil.systemErrorMsg()
        SocialuniAppAPI.sendErrorLogAPI(error.config.url, ObjectUtil.toJson(error))
        return error
    }

    setConfig(f: Function) {
        this.config = f(this.config)
    }

    request<T>(options: options) {
        const _options: newOptions = {
            baseUrl: this.config.baseUrl,
            dataType: options.dataType || this.config.dataType,
            responseType: options.responseType || this.config.responseType,
            url: HttpRequest.posUrl(options.url) ? options.url : (this.config.baseUrl + options.url),
            data: options.data || {},
            header: options.header || this.config.header,
            method: options.method || this.config.method
        }
        return new Promise<ResultRO<T>>((resolve: Function, reject: Function) => {
            let next = true
            let _config: object = {}
            _options.complete = (response: response) => {
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
    config,
    requestConfig,
    response
}
