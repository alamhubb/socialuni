<template>
  <div class="w600">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'

@Component({})
export default class QPcModel extends Vue {
}
</script>
