import axios, {AxiosRequestConfig} from 'axios'
import uniAxiosAdapter from "./uniAxiosAdapter";


const SocialuniAxiosCreate = (config?: AxiosRequestConfig) => {
  if (uni?.request) {
    config.adapter = uniAxiosAdapter
  }
  const socialuniAxios = axios.create(config)
  socialuniAxios.
  return socialuniAxios
}

export default SocialuniAxiosCreate
