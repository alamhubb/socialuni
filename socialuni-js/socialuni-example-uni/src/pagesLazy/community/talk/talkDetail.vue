<template>
  <div class="h100vh bg-default">
    <msg-input v-if="showMsgInput">
    </msg-input>
    <q-navbar>
      <div class="flex-row w100vw px flex-1">
        <div class="col-center bg-click color-default flex-none" @click="goBack">
          <q-icon icon="arrow-left"></q-icon>
        </div>
        <div class="col-center bg-click color-default ml-lg flex-none" @click="goHome">
          <q-icon icon="home"></q-icon>
        </div>
        <div class="ml-lg font-bold text-md flex-none color-default">
          动态详情
        </div>
      </div>
    </q-navbar>
    <div class="pb-155px bg-default" v-if="talk">
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
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import QIcon from "qing-ui/src/components/QIcon.vue";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";
import TalkOperate from "socialuni-community-view-uni/src/views/talk/talkOperate.vue";
import QNavbar from "qing-ui-uni/src/components/QNavbar/QNavbar.vue";

@toNative
@Component({
  components: {
    QNavbar,
    QIcon,
    MsgInput,
    TalkItem,
    TalkOperate
  }
})
export default class TalkDetail extends Vue {
  talk: TalkVO = null
  showMsgInput = false

  get appConfig() {
    return socialuniConfigModule.appConfig
  }

  created() {
    QingAppUtil.NativeUtil.showShareMenu()
    onLoad((params) => {
      const talkId = params.talkId
      SocialuniTalkAPI.queryTalkDetailAPI(talkId).then((res: any) => {
        this.talk = res.data
        // this.$store.commit('home/setTalk', this.home)
      })
    })
  }


  deleteTalk() {
    QingAppUtil.RouterUtil.goBackOrHome()
  }

  goHome() {
      QingAppUtil.RouterUtil.goHome()
  }

  goBack() {
      QingAppUtil.RouterUtil.goBackOrHome()
  }

  onShow() {
    this.showMsgInput = true
  }

  onHide() {
    this.showMsgInput = false
  }
}
</script>

