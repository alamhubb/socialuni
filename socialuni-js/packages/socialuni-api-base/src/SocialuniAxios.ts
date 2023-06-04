import axios, {AxiosRequestConfig} from 'axios'
import uniAxiosAdapter from "./uniAxiosAdapter";

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

    return socialuniAxios
}

export default SocialuniAxiosCreate
