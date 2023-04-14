<template>
  <view v-if="talk" class="card mb-sm elevation-4">
    <talk-item-head :talk-prop="talk" :talk-tab-type="talkTabType" @deleteTalk="deleteTalk"/>
    <talk-item-content :talk="talk"/>
    <talk-item-comment v-if="!talk.globalTop || talk.globalTop===1" :talk-prop="talk"
                       :showAllComment="showAllComment"/>
  </view>
</template>

<script lang="ts">
import {Options, Emit, Prop, Vue} from 'vue-property-decorator'
import TalkItemHead from './TalkItemHead.vue'
import TalkItemContent from './TalkItemContent.vue'
import TalkItemComment from './TalkItemComment.vue'
import TalkVO from "socialuni-base-api/src/model/talk/TalkVO";

@Options({
  components: {
    TalkItemComment,
    TalkItemContent,
    TalkItemHead
  }
})
export default class TalkItem extends Vue {
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
</script>
