<template>
  <div v-on="$listeners">
    <slot></slot>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'


@Component
class YDiv extends Vue {

}
export default toNative(YDiv)
</script>

