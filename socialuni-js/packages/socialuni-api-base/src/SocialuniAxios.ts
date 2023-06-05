import axios, {AxiosRequestConfig} from 'axios'
import uniAxiosAdapter from "./uniAxiosAdapter";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";

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

    console.log(socialuniPluginsModule)
    console.log(socialuniPluginsModule.plugins)
    console.log(socialuniPluginsModule.plugins.length)
    socialuniAxios.interceptors.request.use(
        config => {
            console.log(socialuniPluginsModule)
            console.log(socialuniPluginsModule.plugins.length)
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
                if (socialuniPlugin.onRequestInterceptors) {
                    socialuniPlugin.onRequestInterceptors(error)
                }
            }
        }
    )
    return socialuniAxios
}

export default SocialuniAxiosCreate
