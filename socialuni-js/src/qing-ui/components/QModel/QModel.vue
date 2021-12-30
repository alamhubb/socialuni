<template>
  <view class="q-model"
        @touchmove.stop.prevent
        @click="click"
  >
    <slot></slot>
  </view>
</template>
<script lang="ts">
import { Component, Emit, Vue } from 'vue-property-decorator'

/*
显示出来已经选了的城市，给她画上钩
* */
@Component
export default class QModel extends Vue {
  @Emit()
  click () {
    return ''
  }
}
</script>
