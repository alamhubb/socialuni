<template>
  <q-popup ref="dialog" bottom @confirm="confirm">
    <div class="h50vh pd flex-col">
      <div>填写联系方式，方便他人联系您</div>
      <div class="mt">
        联系方式：
      </div>
      <div class="mt">
        <q-input class="w100p" v-model="contactInfoValue" placeholder="在此输入您的联系方式"></q-input>
      </div>
      <div class="mt row-col-center">
        他人是否可获取您的联系方式：
      </div>
      <div class="row-col-center mt">
        <q-switch v-model="openContactInfo">
          {{ openContactInfo ? '他人可获取' : '他人不可获取' }}
        </q-switch>
      </div>
      <div class="mt">打开开关，他人可通过使用{{getContactExpenseCoin}}金币的方式获取您的联系方式</div>
      <div class="mt-sm">提示：允许使用金币获取联系方式功能交友效果更佳哦</div>
<!--      <div class="mt-xs">未来会给您提供50%收益分成（目前不支持）</div>-->
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import QInput from "qingjs-ui/src/components/QInput.vue";
import QPopup from "qingjs-ui/src/components/QPopup.vue";
import SSwitch from "qingjs-ui-uni/src/components/SSwitch.vue";
import SocialuniUserExpandAPI from "socialuni-user-api/src/api/SocialuniUserExpandAPI";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";


@Component({
  components: {
    SSwitch,
    QInput,
    QPopup
  }
})
class UserContactInfoEditDialog extends Vue {
  $refs!: {
    dialog: QPopup;
  }

  contactInfoValue: string = null
  openContactInfo: boolean = true

  open() {
    this.contactInfoValue = null
    this.openContactInfo = true
    if (socialuniUserModule.mineUser.contactInfo) {
      this.contactInfoValue = socialuniUserModule.mineUser.contactInfo
      this.openContactInfo = socialuniUserModule.mineUser.openContactInfo
    }
    this.$refs.dialog.open()
  }

  get getContactExpenseCoin(){
    return socialuniConfigModule.appMoreConfig.contactExpenseShell
  }

  async confirm() {
    if (!this.contactInfoValue) {
      QingAppUtil.ToastUtil.error('联系方式不能为空')
    }
    try {
      let msg = '是否确认将联系方式设置为' + this.contactInfoValue
      if (this.openContactInfo) {
        msg += '，并设置为他人可使用金币获取方式'
      } else {
        msg += '，并设置为他人不可获取'
      }
      await QingAppUtil.AlertUtil.confirm(msg)
      this.$refs.dialog.close()
      const res = await SocialuniUserExpandAPI.editUserContactInfoAPI(this.contactInfoValue, this.openContactInfo)
      socialuniUserModule.setUser(res.data)
    } finally {

    }
  }
}
export default toNative(UserContactInfoEditDialog)
</script>
