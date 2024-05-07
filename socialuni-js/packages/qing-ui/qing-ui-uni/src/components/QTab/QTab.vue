<template>
  <view class="h30 font-md">
    <slot></slot>
  </view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'

@toNative
@Component({})
export default class QTab extends Vue {

}
</script>
