// require lib
import Vue from 'vue'
import './index.scss'
import YTableColumn from '@/components/YComponent/YTableColumn/YTableColumn.vue'
import TableGroup from '@/components/YComponent/TableGroup/TableGroup.vue'
import YButton from '@/components/YComponent/YButton.vue'
import DialogBox from '@/components/YComponent/dialogBox/index.vue'
import TableBox from '@/components/YComponent/tableBox/index.vue'
import YScrollbar from '@/components/YComponent/YScrollbar.vue'
import YTree from '@/components/YComponent/YTree/YTree.vue'
import YPageHeader from "@/components/YComponent/YPageHeader.vue";

Vue.component('YTableColumn', YTableColumn)
Vue.component('YPageHeader', YPageHeader)
Vue.component('TableGroup', TableGroup)
Vue.component('YScrollbar', YScrollbar)
Vue.component('YButton', YButton)
// @ts-ignore
Vue.component('DialogBox', DialogBox)
Vue.component('TableBox', TableBox)
Vue.component('YTree', YTree)
