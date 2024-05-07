<template>
  <div class="w600">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator'

@toNative
@Component({})
export default class QPcModel extends Vue {
}
</script>
