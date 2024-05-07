<template>
  <view class="h30 font-md">
    <slot></slot>
  </view>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator'

@toNative
@Component({})
export default class QTab extends Vue {

}
</script>
