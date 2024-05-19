<template>
  <!--  可点击的行-->
  <div class="q-box-between bg-click" :class="{'bb':border}" @click="click">
    <slot></slot>
  </div>
</template>
<script lang="ts">
import {Emit, Component, Vue, toNative, Prop} from 'vue-facing-decorator'

//和row-line区别，这个有上下pd那个没有
/*
显示出来已经选了的城市，给她画上钩
* */
@toNative
@Component({})
export default class QRowItem extends Vue {
  @Prop({default: false, type: Boolean}) border: boolean

  @Emit()
  click() {
    return
  }
}
</script>

