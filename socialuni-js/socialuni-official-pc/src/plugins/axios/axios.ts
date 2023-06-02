import axios, {AxiosRequestConfig} from 'axios'
import uniAxiosAdapter from "./uniAxiosAdapter";

const axiosCreate = axios.create

axios.create = (config?: AxiosRequestConfig) => {
    if (uni?.request) {
        config.adapter = uniAxiosAdapter
    }
    return axiosCreate(config)
}

export default axios
