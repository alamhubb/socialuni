<template>
  <div class="row-all-center line-h1" :class="[themeClass]" :style="tagStyle">
    <slot></slot>
    <q-icon v-if="showClose" class="row-all-center ml-xs" :size="size" icon="close" @click="deleteFun"></q-icon>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative, Emit} from 'vue-facing-decorator'
import QIcon from "./QIcon.vue";


@Component({
  components: {
    QIcon
  }
})
class QTag extends Vue {
  @Prop({default: false, type: Boolean}) border: boolean
  @Prop({default: false, type: Boolean}) round: boolean
  @Prop({default: false, type: Boolean}) showClose: boolean
  @Prop({default: '', type: String}) type: string
  @Prop({default: '12', type: String}) size: string

  get tagStyle(){
    return {
      'font-size': this.size + 'px'
    }
  }


  get themeClass() {
    let className = 'q-tag'
    if (this.type) {
      className += ('-' + this.type)
    }
    if (this.border) {
      className += ('-bd')
    }
    if (this.size) {
      className += (' box-' + this.size)
    }
    if (this.round) {
      className += (' bd-round')
    }
    return className
  }

  @Emit('delete')
  deleteFun () {
    return
  }
}
export default toNative(QTag)
</script>
