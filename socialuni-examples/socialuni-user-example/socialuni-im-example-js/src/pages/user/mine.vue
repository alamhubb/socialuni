<template>
  <view class="h100p">
    <div @click="hha">123</div>
    <mine-view class="h100p"></mine-view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import MineView from "socialuni-user/src/views/user/MineView";

@Options({
  components: {
    MineView
  }
})
export default class MinePage extends Vue {

  hha(){
    console.log(456456)
  }
}
</script>
