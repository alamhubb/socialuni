<template>
  <el-scrollbar @scroll="scrollbar" ref="scrollbar" class="overflow-x-hidden overflow-hidden">
    <slot></slot>
  </el-scrollbar>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import {ElScrollbar} from "element-plus";


@Component({})
class QScrollbar extends Vue {
  $refs: {
    scrollbar: ElScrollbar
  }

  scrollbar({scrollTop}) {
    console.log(scrollTop)
    console.log('scrollTop')
    console.log('chufale123123')
    console.log('chufale123123')
  }
}
export default toNative(QScrollbar)
</script>
<style lang="scss">
.overflow-x-hidden > .el-scrollbar__wrap {
  overflow-x: hidden;
  //修复配置修改页面底部使用滚动条被隐藏一部分的问题
  margin-bottom: 0 !important;
}

//如果内部还包含el-scroller则会导致冲突问题
.overflow-x-hidden > .el-scrollbar__bar.is-horizontal {
  display: none;
}
</style>
