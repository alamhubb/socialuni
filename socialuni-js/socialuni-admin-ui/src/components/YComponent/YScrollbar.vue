<template>
  <el-scrollbar class="overflow-x-hidden overflow-hidden">
    <slot></slot>
  </el-scrollbar>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'

@toNative
@Component
export default class YScrollbar extends Vue {

}

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
