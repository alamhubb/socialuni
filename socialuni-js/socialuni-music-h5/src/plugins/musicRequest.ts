import Axios from 'axios'

// create an axios instance
const musicRequest = Axios.create({
    baseURL: '/music',
    withCredentials: true, // send cookies when cross-domain requests
    timeout: 120000 // request timeout
})

musicRequest.interceptors.response.use(
    response => {
        return response.data
    }
)

export default musicRequest