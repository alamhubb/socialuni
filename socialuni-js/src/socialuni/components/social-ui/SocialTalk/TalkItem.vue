<template>
    <view v-if="talk" class="card mb-sm elevation-4" hover-class="uni-list-cell-hover">
        <talk-item-head :talk-prop="talk" :talk-tab-type="talkTabType" @deleteTalk="deleteTalk"/>
        <talk-item-content :talk="talk"/>
        <talk-item-comment v-if="!talk.globalTop || talk.globalTop===1" :talk-prop="talk"
                           :showAllComment="showAllComment"/>
    </view>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'
import TalkVO from '../../../model/talk/TalkVO'
import TalkItemHead from './TalkItemHead.vue'
import TalkItemContent from './TalkItemContent.vue'
import TalkItemComment from './TalkItemComment.vue'

@Component({
  components: {
    TalkItemComment,
    TalkItemContent,
    TalkItemHead
  }
})
export default class TalkItem extends Vue {
    @Prop() talk: TalkVO
    @Prop() talkTabType: string
    @Prop({ type: Boolean, default: false }) readonly showAllComment: boolean

    @Emit()
    deleteTalk (talkId: number) {
      return talkId
    }
}
</script>
