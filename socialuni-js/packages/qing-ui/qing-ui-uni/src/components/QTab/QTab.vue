<template>
  <div class="h30 font-md">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'

@toNative
@Component({})
export default class QTab extends Vue {

}
</script>
