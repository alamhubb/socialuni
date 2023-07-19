import DialogBox from './dialogBox'
import TableBox from './tableBox/index.vue'

const components = [TableBox, DialogBox]

const install = function(Vue) {
  components.forEach(component => {
    Vue.component(component.name, component)
  })
}

if (typeof window !== 'undefined' && window.Vue) {
  install(window.Vue)
}

export default {
  install,
  TableBox,
  DialogBox
}
