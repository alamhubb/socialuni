<template>
  <view class="q-model modal-index bg-modal row-all-center position-fixed"
        @touchmove.stop.prevent
        @click="click"
  >
    <slot></slot>
  </view>
</template>
<script lang="ts">
import {Emit, Component, Vue, toNative} from 'vue-facing-decorator'

/*
显示出来已经选了的城市，给她画上钩
* */
@toNative
@Component({})
export default class QModel extends Vue {
  @Emit()
  click() {
    return
  }
}
</script>
<style lang="scss">
//model index 以100开始
//因为h5端navbar和tabbar为998
.q-model {
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}
</style>
