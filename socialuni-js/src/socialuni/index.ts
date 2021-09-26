/**
 * @Author qingchi
 * @Date 2021-09-06 13:28
 * @Version 1.0
 */
import { PluginObject } from 'vue'
import SocialuniConfig from './model/SocialuniConfig'
import { registerSocialStore } from './store'
import uView from 'uview-ui'
import SocialMinxinVue from '@/socialuni/SocialMinxinVue.vue'

export let socialConfig: SocialuniConfig

const socialuni: PluginObject<SocialuniConfig> = {
  install (Vue, options?: SocialuniConfig) {
    Vue.use(uView)
    socialConfig = new SocialuniConfig(options)
    registerSocialStore(options.store)
    // 全局混入social必要内容
    Vue.mixin(SocialMinxinVue)
  }
}
export default socialuni
