<template>
  <div ref="scroll" v-scrolle="loadPre">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative, Emit} from 'vue-facing-decorator'
import {vInfiniteScroll} from '@vueuse/components'

@toNative
@Component({
  directives: {
    scrolle: vInfiniteScroll
  }
})
export default class QScroll extends Vue {
  $refs: {
    scroll: HTMLElement
  }
  @Prop({type: Number, default: 10}) readonly distance: number
  @Prop({type: Number, default: 100}) readonly interval: number

  loadPre() {
    console.log('chufale preload')
    const scroll = this.$refs.scroll
    if (scroll) {
      const clientHeight = scroll.clientHeight
      const scrollHeight = scroll.scrollHeight
      if (scrollHeight > clientHeight) {
        this.load()
      }
    }
  }

  @Emit()
  load() {
    console.log(11111)
    console.log(this.$refs.scroll)
    console.log(2222)
    console.log('触发了加载')
  }

  scroll() {
    console.log('触发了滚动')
  }

  scrollEnd() {
    console.log('触发了滚动end')
  }
}
</script>
