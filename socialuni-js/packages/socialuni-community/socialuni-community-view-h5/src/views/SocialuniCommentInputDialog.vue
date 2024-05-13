<template>
  <q-dialog ref="dialog" title="评论">
    <div>
      <talk-item :talk="talk"
                 talk-tab-type="首页"
      />
      {{ msgInputPlaceholder }}
    </div>
  </q-dialog>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import QTabs from 'qing-ui/src/components/QTabs/QTabs.vue'
import QIcon from 'qing-ui/src/components/QIcon.vue'
import TalkTabVO from "socialuni-api-base/src/model/talk/SocialuniTalkTabRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import CommonUtil from "qing-util/src/util/CommonUtil";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import Constants from "socialuni-constant/constant/Constant";
import SocialuniTalkTabCircleRO from "socialuni-api-base/src/model/community/circle/SocialuniTalkTabCircleRO";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import TalkQOFactory from "socialuni-community-sdk/src/factory/TalkQOFactory";
import {socialLocationModule} from "socialuni-community-sdk/src/store/SocialLocationModule";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import TalkItem from "socialuni-community-ui/src/components/talkItem/TalkItem.vue";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import SocialuniUserExtendDetailRO from "socialuni-expand-api/src/model/SocialuniUserExtendDetailRO";
import SocialuniUserExtendFriendQueryQO from "socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import SocialuniExpandAPI from "socialuni-expand-api/src/api/SocialuniExpandAPI";
import TalkQueryVO from "socialuni-api-base/src/model/talk/TalkQueryVO";
import {socialCircleModule} from "socialuni-community-sdk/src/store/SocialCircleModule";
import TalkTabType from "socialuni-constant/constant/TalkTabType";
import QEnumLink from 'qing-ui-h5/src/components/QEnumLink.vue'
import CommonEventUtil from "qingjs/src/util/CommonEventUtil";
import SocialuniImEventKey from "socialuni-im-api/src/constant/SocialuniMusicEventConst";
import JsonUtil from "qing-util/src/util/JsonUtil";
import QNavMenu from "qing-ui-h5/src/components/QNavMenu.vue";
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import MusicList from "socialuni-music-ui-h5/src/components/MusicList.vue";
import UserCheckUtil from "socialuni-user-sdk/src/util/UserCheckUtil";
import PlatformUtils from "socialuni-user-sdk/src/util/PlatformUtils";

// todo 后台可控制是否显示轮播图
@toNative
@Component({
  components: {
    MusicList,
    QNavMenu,
    QIcon,
    QTabs,
    QDialog,
    QEnumLink,
    TalkItem
  }
})
export default class SocialuniCommentInputDialog extends Vue {
  $refs: {
    dialog: QDialog
  }

  open() {
    this.$refs.dialog.open()
  }

  content = ''

  get currentContent() {
    return socialTalkModule.currentContent
  }

  get talk() {
    return socialTalkModule.talk
  }


  get user() {
    return socialuniUserModule.mineUser
  }

  inputContentFocusInput = false

  get inputContentFocus() {
    return socialTalkModule.inputContentFocus
  }

  @Watch('inputContentFocus')
  inputContentFocusWatch() {
    setTimeout(() => {
      this.inputContentFocusInput = this.inputContentFocus
      console.log(this.inputContentFocusInput)
    }, 0)
  }

  get msgInputPlaceholder() {
    if (this.currentContent) {
      return '回复：' + this.currentContent
    } else {
      return ''
    }
  }

  // formid有坑，只能发送给自己openid
  sendComment() {
    UserCheckUtil.checkUserBindPhoneNum()
    // 登录才能发表评论
    // 如果有值
    if (this.content) {
      /*if (!this.user.identityAuth) {
        ModelContentCheckUtil.hasUn18Content(this.content)
      }*/
      socialTalkModule.addComment({content: this.content})
      this.content = ''
      // 申请订阅
      PlatformUtils.requestSubscribeComment()
    } else {
      QingAppUtil.ToastUtil.toast('不能发表内容为空的评论')
    }
  }

  inputContentBlur() {
    socialTalkModule.inputContentBlur()
  }
}
</script>
