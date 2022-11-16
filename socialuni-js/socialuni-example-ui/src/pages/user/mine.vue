<template>
  <view class="bg-default h100p flex-col">
    <mine-view class="h100p"></mine-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import MineView from 'socialuni-sdk/src/views/user/MineView.vue'

@Options({components: {MineView}})
export default class MinePage extends Vue {
}
</script>
