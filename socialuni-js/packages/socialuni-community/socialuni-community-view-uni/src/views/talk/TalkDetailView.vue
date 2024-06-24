<template>
  <div class="h100vh bg-default">
    <msg-input class="w100p" v-if="showMsgInput">
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
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import {onHide, onLoad, onShow} from "@dcloudio/uni-app";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import QIcon from "qing-compat-js-ui/src/components/QIcon.vue";
import MsgInput from "socialuni-ui/src/components/MsgInput.vue";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";
import TalkOperate from "socialuni-community-view-uni/src/views/talk/talkOperate.vue";
import QNavbar from "qing-compat-js-ui-uni/src/components/QNavbar/QNavbar.vue";

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

