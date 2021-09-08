/**
 * @Author qingchi
 * @Date 2021-09-06 13:28
 * @Version 1.0
 */
import { PluginObject } from 'vue'
import SocialConfig from './model/SocialConfig'
import { registerSocialStore } from './store'

export let socialConfig: SocialConfig

const socialuni: PluginObject<SocialConfig> = {
  install (Vue, options?: SocialConfig) {
    socialConfig = new SocialConfig(options)
    registerSocialStore(options.store)
    // 全局混入social必要内容
    // Vue.mixin(SocialMinxinVue)
  }
}
export default socialuni
