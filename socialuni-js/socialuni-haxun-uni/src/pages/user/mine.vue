<template>
  <mine-view class="h100p"></mine-view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import MineView from "socialuni-user-view-uni/src/views/user/MineView.vue";

@toNative
@Component({
  components: {
    MineView
  }
})
export default class MinePage extends Vue {
}
</script>
