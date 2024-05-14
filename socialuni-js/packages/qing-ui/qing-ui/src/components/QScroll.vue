<template>
  <div ref="scroll" class="q-scroll overflow-auto h100p">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component,Vue, toNative} from 'vue-facing-decorator'

@toNative
@Component({
})
export default class QScroll extends Vue {
  $refs: {
    scroll: HTMLElement
  }
}
</script>
<style lang="scss">
/*调整滚动条样式*/
.q-scroll::-webkit-scrollbar {
  width: 5px;
  top: 2px;
}

.q-scroll::-webkit-scrollbar-thumb {
  border-radius: 1em;
  background-color: rgba(144, 147, 153, .3);
}

</style>
