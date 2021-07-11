/**
 * @Author qingchi
 * @Date 2021-06-21 23:38
 * @Version 1.0
 */
import { PluginObject } from 'vue'
import uniStore from './store'
import { Store } from 'vuex'
import { namespace } from 'vuex-class'
import UniSystemModule from './store/UniSystemModule'
import UniStoreModuleName from './store/UniStoreModuleName'

export let uniSystemModule: UniSystemModule
const unisdk: PluginObject<null> = {
  install (Vue, options: { store?: Store<any> }) {
    let store
    if (options && options.store) {
      store = options.store
    } else {
      //如果不传入store
      store = uniStore
    }
    Vue.prototype.$store = store
    uniSystemModule = new UniSystemModule({ store, name: UniStoreModuleName.uniSystem })
  }
}
export default unisdk

export const uniSystemStore = namespace(UniStoreModuleName.uniSystem)
