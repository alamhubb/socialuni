<template>
  <view class="h100vh bg-default">
<!--    <msg-input>
    </msg-input>-->
    <u-navbar :is-back="false">
      <view class="flex-row w100vw px flex-auto">
        <view class="col-center bg-active color-default flex-none" @click="goBack">
          <q-icon icon="arrow-left"></q-icon>
        </view>
        <view class="col-center bg-active color-default ml-lg flex-none" @click="goHome">
          <q-icon icon="home"></q-icon>
        </view>
        <view class="ml-lg font-bold text-md flex-none color-default">
          动态详情
        </view>
      </view>
    </u-navbar>
    <view class="pb-155px bg-default" v-if="talk">
      <talk-item :talk="talk" showAllComment
                 @deleteTalk="deleteTalk"
      />
      <talk-operate @deleteTalk="deleteTalk"></talk-operate>
      <!--wx平台显示的广告-->
      <!--  #ifdef MP-WEIXIN -->
      <ad class="bg-white mt-10px w100vw" unit-id="adunit-65c8911d279d228f" ad-type="video"
          ad-theme="white"></ad>
      <!--  #endif -->
      <!--qq平台显示的广告-->
      <!--  #ifdef MP-QQ -->
      <ad class="bg-white mt-10px w100vw" unit-id="bcc21923107071ac3f8aa076c7e00229" type="card"></ad>
      <!--  #endif -->

      <!--头条平台显示的广告-->
      <!--  #ifdef MP-TOUTIAO -->
      <ad class="bg-white mt-10px w100vw" type="banner video large"
          unit-id="3snract0gqnc3fn16d"></ad>
      <!--  #endif -->

      <!--  #ifdef APP-PLUS -->
      <ad class="bg-white mt-10px w100vw" adpid="1890536227"></ad>
      <!--  #endif -->
    </view>
  </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import TalkVO from '@/model/talk/TalkVO'
import TalkAPI from '/socialuni/api/TalkAPI'
import TalkItem from '@/pages/talk/TalkItem.vue'
import PageUtil from '@/utils/PageUtil'
import { namespace } from 'vuex-class'
import TalkOperate from '@/pages/talk/talkOperate.vue'
import MsgInput from '/socialuni/components/MsgInput.vue'
import RouterUtil from '@/utils/RouterUtil'

const appStore = namespace('app')

@Component({
  components: {
    MsgInput,
    TalkItem,
    TalkOperate
  }
})
export default class TalkDetail extends Vue {
  talk: TalkVO = null
  showMsgInput = false
  @appStore.State('appConfig') readonly appConfig: object

  deleteTalk () {
    RouterUtil.goBack()
  }

  onLoad (params: any) {
    const talkId = params.talkId
    TalkAPI.queryTalkDetailAPI(talkId).then((res: any) => {
      this.talk = res.data
      // this.$store.commit('talk/setTalk', this.talk)
    })
  }

  goHome () {
    PageUtil.goHome()
  }

  goBack () {
    RouterUtil.goBack()
  }
}
</script>
