<template>
  <span class="use-click line-h1" :class="[
      prefixClass?prefixClass:'u-iconfont',
      prefixClass?icon:'uicon-' + icon,
      addClass
      ]"
        :style="{'font-size':iconSize+'px','color':color}" @click="click">
  </span>
</template>

<script lang="ts">
import { Emit, Component, Prop, Vue , toNative} from 'vue-facing-decorator'


@Component({})
class QIcon extends Vue {
  @Prop({ type: [String, Number] }) readonly size: string | number
  @Prop({
    type: String,
    default: ''
  }) readonly icon: string
  @Prop({ type: String }) readonly color: string
  @Prop({ type: String }) readonly addClass: string
  @Prop({
    type: String,
    default: ''
  }) readonly prefix: string

  get iconSize () {
    if (this.size) {
      return this.size
    } else {
      if (this.isMdi) {
        return 18
      } else {
        return 18
      }
    }
  }

  get isMdi () {
    const prefixs: string[] = this.icon.split('-')
    if (prefixs.length > 1) {
      if (prefixs[0] === 'mdi') {
        return true
      }
    }
    return false
  }

  get prefixClass () {
    if (this.isMdi) {
      return 'mdi mdi-set'
    }
    return this.prefix
  }

  @Emit()
  click () {
    return
  }
}
export default toNative(QIcon)
</script>
