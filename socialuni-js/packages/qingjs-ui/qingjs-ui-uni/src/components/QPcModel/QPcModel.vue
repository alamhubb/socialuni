<template>
  <div class="w600">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'


@Component({})
class QPcModel extends Vue {
}
export default toNative(QPcModel)
</script>
