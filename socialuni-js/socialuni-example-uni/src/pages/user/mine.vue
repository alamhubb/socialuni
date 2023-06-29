<template>
  <mine-view class="h100p"></mine-view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import MineView from "socialuni-user-view-uni/src/views/user/MineView.vue";

@Options({
  components: {
    MineView
  }
})
export default class MinePage extends Vue {

}
</script>
