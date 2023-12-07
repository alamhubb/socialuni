import Axios from 'axios'

// create an axios instance
const musicRequest = Axios.create({
    baseURL: 'http://47.93.249.129:3000/',
    withCredentials: true, // send cookies when cross-domain requests
    timeout: 120000 // request timeout
})

musicRequest.interceptors.response.use(
    response => {
        return response.data
    }
)

export default musicRequest
