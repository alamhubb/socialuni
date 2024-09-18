<template>
  <div ref="scroll" class="uniapp-web-scorll-view overflow-auto">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative, Emit, Watch} from 'vue-facing-decorator'


@Component({
  directives: {}
})
class ScrollView extends Vue {
  $refs: {
    scroll: HTMLElement
  }
  //滚动到指定位置
  @Prop() scrollTop: number

  @Watch('scrollTop')
  scrollTopWatch() {
    this.$refs.scroll.scrollTop = this.scrollTop
  }

}
export default toNative(ScrollView)
</script>
<style lang="scss">
/*调整滚动条样式*/
.uniapp-web-scorll-view::-webkit-scrollbar {
  width: 10px;
  top: 2px;
}

.uniapp-web-scorll-view::-webkit-scrollbar-thumb {
  border-radius: 1em;
  background-color: rgba(144, 147, 153, .3);
}

</style>
