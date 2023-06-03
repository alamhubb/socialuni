import axios, {AxiosRequestConfig} from 'axios'
import uniAxiosAdapter from "../../modules/socialuni-api-base/src/uniAxiosAdapter";

const axiosCreate = axios.create

axios.create = (config?: AxiosRequestConfig) => {
    if (uni?.request) {
        config.adapter = uniAxiosAdapter
    }
    return axiosCreate(config)
}

export default axios
