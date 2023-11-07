import Axios from 'axios'

// create an axios instance
const musicRequest = Axios.create({
    baseURL: import.meta.env.VITE_APP_WY_MUSIC_API,
    withCredentials: true,
    timeout: 120000 // request timeout
})

musicRequest.interceptors.response.use(
    response => {
        return response.data
    }
)

export default musicRequest
