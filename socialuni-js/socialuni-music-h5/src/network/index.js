import axios from "axios";
import {path} from "@/assets/song/path.js";
import { ElLoading } from 'element-plus'
import { ElMessage } from 'element-plus'
import NProgress from 'nprogress'
import {loading} from '@/components/loading.js'

let timer
let loadingInstance

export function request(config){
    const instance = axios.create({
        baseURL:'https://music-api.heheda.top',
        timeout:10000,
        withCredentials:true
    })

    instance.interceptors.request.use(config => {
        NProgress.start();
        if (!path.includes(config.url)){
            loading(true)
        }
        if (config.method === 'post') {
            config.data = {
                ...config.data,
                _t: Date.parse(new Date()) / 1000
            }
        } else if (config.method === 'get') {
            config.params = {
                _t: Date.parse(new Date()) / 1000,
                ...config.params
            }
        }
        return config
    },error => {})

    instance.interceptors.response.use(response => {
        NProgress.done();
        loading(false)
        if (response.data.code === '301'){
            localStorage.clear()
            ElMessage({
                message: '请刷新重新登录',
                type: 'error',
            })
        }
        return response
    },error => {
        clearTimeout(timer)
        NProgress.done();
        loading(false)
        timer = setTimeout(() => {
            ElMessage({
                message: '网络异常请重新刷新',
                type: 'error',
            })
        },1000)
    })

    return instance(config)
}
