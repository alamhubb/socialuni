import Vue from 'vue'
import store from '@/store/index'
/**
 * 接受主应用的传参
 * @param props 主应用穿的公共数据
 */
function setCommonData(props) {
  const { data } = props
  const { publicPath, router, utils, permissionAxiosURL } = data
  Vue.prototype.$utils = utils
  Vue.prototype.$publicPath = publicPath
  Vue.prototype.$parentRouter = router// 主应用实例
  Vue.prototype.$permissionAxiosURL = permissionAxiosURL
  // Vue.prototype.$http = http;
  // Vue.use(commonUi);// 注册公共组件
  return {
    router,
    utils,
    publicPath,
    permissionAxiosURL
    // commonUi
  }
}

/**
 * 设置微应用全局状态
 * @param props 主应用穿的公共数据
 */
function initGlState(props) {
  // console.log('父应用传的值', props);
  Vue.prototype.$onGlobalStateChange = props.onGlobalStateChange
  Vue.prototype.$setGlobalState = props.setGlobalState
  // 设置通讯
  props.onGlobalStateChange((state, prev) => {
    // state: 变更后的状态; prev 变更前的状态

    // 修改console菜单选中状态
    if (state.activeMenuIndex) store.dispatch('global/setMenuActiveIndex', state.activeMenuIndex)

    // 修改大数据平台菜单选中状态
    if (state.ActiveBigdataMenuIndex) store.dispatch('global/setBigdataMenuActiveIndex', state.ActiveBigdataMenuIndex)

    // 监听主应用切换集群操作
    if (state.jiQunValueChange) {
      store.dispatch('global/refreshMenus')
      store.dispatch('user/qiankunSetClusterId')
    }

    // 监听主应用获取用户信息和过去菜单接口数据返回
    if (state.getMenusListTime) store.commit('global/SET_GETMENUSLISTTIME', true)
  //  alert('子应用监听到主应用改变啦');
  }, true)
}
export default {
  setCommonData,
  initGlState
}

