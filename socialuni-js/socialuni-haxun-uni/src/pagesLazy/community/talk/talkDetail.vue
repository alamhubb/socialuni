<template>
  <talk-detail-view></talk-detail-view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import TalkDetailView from "socialuni-community-view-uni/src/views/talk/TalkDetailView.vue";

@toNative
@Component({
  components: {
    TalkDetailView
  }
})
export default class TalkDetail extends Vue {
}
</script>

