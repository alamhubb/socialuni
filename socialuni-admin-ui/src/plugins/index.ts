import axios from "@/plugins/axios"
import RouterName from "@/constants/RouterName"

export default (Vue) => {
  Vue.prototype.$routerName = RouterName
  Vue.prototype.$axios = axios
  Vue.prototype.$post = axios.post
  Vue.prototype.$imgUrl = process.env.VUE_APP_BASE + 'img/'
  Vue.prototype.$console = console
}
