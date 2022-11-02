<template>
  <!--  可点击的行-->
  <view class="q-box-between bb bg-click" @click="click">
    <slot></slot>
  </view>
</template>
<script lang="ts">
import {Options, Vue, Emit} from 'vue-property-decorator'

//和row-line区别，这个有上下pd那个没有
/*
显示出来已经选了的城市，给她画上钩
* */
@Options({})
export default class QRowItem extends Vue {
  @Emit()
  click() {
    return ''
  }
}
</script>

