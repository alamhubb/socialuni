<template>
  <div class="y-page-header h50 px row-between-center bb flex-none">
    <div class="font-16 color-special font-bold">
      <slot>
      </slot>
    </div>
    <slot name="right"></slot>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'


@Component
class YPageHeader extends Vue {
}
export default toNative(YPageHeader)
</script>
