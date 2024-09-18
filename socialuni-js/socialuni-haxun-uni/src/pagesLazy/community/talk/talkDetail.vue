<template>
  <talk-detail-view></talk-detail-view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import TalkDetailView from "socialuni-community-view-uni/src/views/talk/TalkDetailView.vue";


@Component({
  components: {
    TalkDetailView
  }
})
class TalkDetail extends Vue {
}
export default toNative(TalkDetail)
</script>

