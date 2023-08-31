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
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class YPageHeader extends Vue {
}
</script>
