<template>
  <div v-on="$listeners">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'

@toNative
@Component
export default class YDiv extends Vue {

}
</script>

