<template>
  <div>
      <mine-view class="h100p"></mine-view>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import MineView from "@socialuni/socialuni-user-view-uni/src/views/user/MineView.vue";

@Options({
  components: {
    MineView
  }
})
export default class MinePage extends Vue {

}
</script>
