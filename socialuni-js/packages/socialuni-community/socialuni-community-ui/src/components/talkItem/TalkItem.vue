<template>
  <div v-if="talk" class="bg-theme-light py-10 bd-radius-10 mb-sm elevation-4">
    <talk-item-head :talk-prop="talk" :talk-tab-type="talkTabType" @deleteTalk="deleteTalk"/>
    <talk-item-content :talk="talk"/>
    <talk-item-comment v-if="!talk.globalTop || talk.globalTop===1" :talk-prop="talk"
                       :showAllComment="showAllComment"/>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Prop, Vue, toNative} from 'vue-facing-decorator'
import TalkItemHead from './TalkItemHead.vue'
import TalkItemContent from './TalkItemContent.vue'
import TalkItemComment from './TalkItemComment.vue'
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";


@Component({
  components: {
    TalkItemComment,
    TalkItemContent,
    TalkItemHead
  }
})
class TalkItem extends Vue {
  @Prop() talk: TalkVO
  @Prop() talkTabType: string
  @Prop({
    type: Boolean,
    default: false
  }) readonly showAllComment: boolean

  @Emit()
  deleteTalk(talkId: string) {
    return talkId
  }
}
export default toNative(TalkItem)
</script>
