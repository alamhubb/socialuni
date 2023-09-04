<template>
  <mine-view class="h100p"></mine-view>
</template>

<script lang="ts">
import {Components, Vue} from 'vue-facing-decorator'
import MineView from "@socialuni/socialuni-user-view-uni/src/views/user/MineView.vue";

@Component({
  components: {
    MineView
  }
})
export default class MinePage extends Vue {
}
</script>
