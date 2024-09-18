<template>
  <div class="h30 font-md">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'


@Component({})
class QTab extends Vue {

}
export default toNative(QTab)
</script>
