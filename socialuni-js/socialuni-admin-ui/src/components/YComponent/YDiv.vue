<template>
  <div v-on="$listeners">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'

@Component
export default class YDiv extends Vue {

}
</script>

