import axios from '@/plugins/axios'
import SocialuniAPIConfig from '@/modules/socialuni-api-base/src/SocialuniAPIConfig'
import uniAxiosAdapter from '@/modules/socialuni-api-base/src/uniAxiosAdapter'

const socialuniImRequest = axios.create({
    // baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
    baseURL: SocialuniAPIConfig.socialuniImUrl,
    // send cookies when cross-domain requests
    withCredentials: true,
    // request timeout
    timeout: 120000,
})
const axiosCreate = axios.create

axios.create = (config?: AxiosRequestConfig) => {
    if (uni?.request) {
        config.adapter = uniAxiosAdapter
    }
    return axiosCreate(config)
}

export default socialuniImRequest
