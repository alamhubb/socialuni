<template>
  <div class="w600">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'

@toNative
@Component({})
export default class QPcModel extends Vue {
}
</script>
