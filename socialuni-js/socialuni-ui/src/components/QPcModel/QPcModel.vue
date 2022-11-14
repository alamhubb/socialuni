<template>
  <div class="w600">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

@Options({})
export default class QPcModel extends Vue {
}
</script>
