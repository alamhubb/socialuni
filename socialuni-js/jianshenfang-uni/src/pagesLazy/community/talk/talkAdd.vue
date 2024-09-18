<template>
  <talk-add-view class="h100p"></talk-add-view>
</template>
<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import TalkAddView from 'socialuni-community-view-uni/src/views/talk/TalkAddView.vue';


@Component({
  components: {
    TalkAddView
  }
})
class TalkAddPage extends Vue {
}
export default toNative(TalkAddPage)
</script>
