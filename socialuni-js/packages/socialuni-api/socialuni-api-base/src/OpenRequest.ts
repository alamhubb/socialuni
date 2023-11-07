import axios from "axios";
import CommonUtil from "@socialuni/socialuni-util/src/util/CommonUtil";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";


const openRequest = axios.create({
    withCredentials: true,
    timeout: 120000
})

// request interceptor
openRequest.interceptors.request.use(
    config => {
        const Basic = 'Basic MWE5N2IyZmU3NjY2NGVmNjhiZmRkZjcyNTZjZjkxZDM6OTk5YzA2ODljYzc5NDEyOGI0NTBjMWQ3MDJmMGUyZjM='
        const uidHeader = 'qqq'
        const tokenHeader = '007eJxTYDi+2Ftdds/mXxN/+iw6/7xHWSjI6YDypDMbBSd0H7p/zr9FgcE01czC0MTQINHcxNgkOdUyKSnRONXIzMLIyCw51dTY/EXXv5SGQEYGm9KbTIwMTAyMQAjiMzMUFhYCAHfPIUU='

        const authValue = `agora token=${tokenHeader}`

        // config.headers['x-agora-uid'] = uidHeader
        // config.headers['x-agora-token'] = tokenHeader
        // config.headers['Authorization'] = authValue
        config.headers['Authorization'] = Basic
        config.headers['X-Request-ID'] = UUIDUtil.getUUID()
        // do something before request is sent
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

export default openRequest
