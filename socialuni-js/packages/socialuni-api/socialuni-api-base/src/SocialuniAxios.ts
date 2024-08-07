import axios, {AxiosRequestConfig} from 'axios'
import uniAxiosAdapter from "./uniAxiosAdapter";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import SocialuniAPIHeaderName from "./SocialuniAPIHeaderName";
import SocialuniAPIConfig from "./SocialuniAPIConfig";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";

const SocialuniAxiosCreate = (config?: AxiosRequestConfig) => {
    try {
        if (socialuniSystemModule.isUniApp) {
            config.adapter = uniAxiosAdapter
        }
    } catch (e) {

    }
    config.withCredentials = true
    config.timeout = 120000
    const socialuniAxios = axios.create(config)

    socialuniAxios.interceptors.request.use(
        config => {
            if (SocialuniAPIConfig.socialuniSecretKey) {
                config.headers[SocialuniAPIHeaderName.socialuniSecretKeyName] = SocialuniAPIConfig.socialuniSecretKey
            }
            for (const socialuniPlugin of socialuniPluginsModule.plugins) {
                if (socialuniPlugin.onRequestInterceptors) {
                    socialuniPlugin.onRequestInterceptors(config)
                }
            }
            return config
        }
    )


    socialuniAxios.interceptors.response.use(
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
            for (const socialuniPlugin of socialuniPluginsModule.plugins) {
                if (socialuniPlugin.onResponseErrorInterceptors) {
                    socialuniPlugin.onResponseErrorInterceptors(error)
                }
            }
            throw new Error(error.errorMsg || error.message || error.errMsg)
        }
    )
    return socialuniAxios
}

export default SocialuniAxiosCreate
