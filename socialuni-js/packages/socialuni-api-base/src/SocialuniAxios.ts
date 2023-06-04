import axios, {AxiosRequestConfig} from 'axios'
import uniAxiosAdapter from "./uniAxiosAdapter";
import {socialuniPluginsModule} from "socialuni-base/src/store/SocialuniPluginsModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import SocialuniRequestHeaderName from "socialuni-constant/constant/SocialuniRequestHeaderName";

const SocialuniAxiosCreate = (config?: AxiosRequestConfig) => {
    try {
        if (uni) {
            config.adapter = uniAxiosAdapter
        }
    } catch (e) {

    }
    config.withCredentials = true
    config.timeout = 120000
    const socialuniAxios = axios.create(config)

    socialuniAxios.interceptors.request.use(
        config => {
            for (const socialuniPlugin of socialuniPluginsModule.plugins) {
                socialuniPlugin.onRequestInterceptors(config)
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
            for (const plugin of socialuniPluginsModule.plugins) {
                if (plugin.onRequestInterceptors().response) {

                }
            }
        }
    )
    return socialuniAxios
}

export default SocialuniAxiosCreate
