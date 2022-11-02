<template>
  <view class="q-tab">
    <slot></slot>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

@Options({})
export default class QTab extends Vue {

}
</script>
