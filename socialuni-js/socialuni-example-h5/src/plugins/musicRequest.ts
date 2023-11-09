import Axios from 'axios'

// create an axios instance
const musicRequest = Axios.create({
    baseURL: '/',
    withCredentials: true,
    timeout: 120000 // request timeout
})

musicRequest.interceptors.response.use(
    response => {
        return response.data
    }
)

export default musicRequest
