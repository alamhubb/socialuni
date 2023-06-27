<template>
  <view class="h30 font-md">
    <slot></slot>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

@Options({})
export default class QTab extends Vue {

}
</script>
