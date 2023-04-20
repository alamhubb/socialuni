import UniAppHttpRequest, {Config, Interceptor, RequestConfig, Response} from "./UniAppHttpRequest";
import { socialuniPluginsModule } from '../store/SocialuniPluginsModule'

export default class SocialuniHttpRequest extends UniAppHttpRequest {
    config: Config = socialuniPluginsModule.requestConfig.getConfig()

    protected requestBefore: (config: RequestConfig) => RequestConfig = (config: RequestConfig) => {
        return socialuniPluginsModule.requestConfig.requestBefore(config)
    }

    protected requestComFun: (response: Response) => any = (response: Response) => {
        return socialuniPluginsModule.requestConfig.responseSuccess(response)
    }

    protected responseFail: (response: Response) => Response = (response: Response) => {
        return socialuniPluginsModule.requestConfig.responseFail(response)
    }

    interceptor: Interceptor = {
        request: (callback: (config: RequestConfig) => RequestConfig) => {
            const oldFun = this.requestBefore
            this.requestBefore = (config: RequestConfig) => {
                const oldConfig = oldFun(config)
                return callback(oldConfig)
            }
        },
        response: (cb: (rep: Response) => any, ecb: (rep: Response) => any) => {
            this.responseSuccess = cb

            const oldFun = this.responseFail
            this.responseFail = (config: Response) => {
                const oldConfig = oldFun(config)
                return ecb(oldConfig)
            }
        }
    }
}
