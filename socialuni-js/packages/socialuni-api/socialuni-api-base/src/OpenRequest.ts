import axios from "axios/index";


const openRequest = axios.create({
    withCredentials: true,
    timeout: 120000
})

export default openRequest
