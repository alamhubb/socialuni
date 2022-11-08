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
        <s-switch v-model="openContactInfo">
          {{ openContactInfo ? '他人可获取' : '他人不可获取' }}
        </s-switch>
      </div>
      <div class="mt">打开开关，他人可通过付费1元的方式获取您的联系方式</div>
      <div class="mt-sm">提示：打开付费获取联系方式功能交友效果更佳哦</div>
<!--      <div class="mt-xs">未来会给您提供50%收益分成（目前不支持）</div>-->
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import QInput from "socialuni-ui/src/components/QInput/QInput.vue";
import QPopup from "socialuni-ui/src/components/QPopup/QPopup.vue";
import SSwitch from "socialuni-ui/src/component/SSwitch.vue";
import {socialUserModule} from 'socialuni-user/src/store/store';
import ToastUtil from "socialuni-use/src/utils/ToastUtil";
import AlertUtil from "socialuni-use/src/utils/AlertUtil";
import SocialuniUserExpandAPI from "socialuni-api/src/api/socialuni/SocialuniUserExpandAPI";

@Options({
  components: {
    SSwitch,
    QInput,
    QPopup
  }
})
export default class UserContactInfoEditDialog extends Vue {
  $refs!: {
    dialog: QPopup;
  }

  contactInfoValue: string = null
  openContactInfo: boolean = true

  open() {
    this.contactInfoValue = null
    this.openContactInfo = true
    if (socialUserModule.user.contactInfo) {
      this.contactInfoValue = socialUserModule.user.contactInfo
      this.openContactInfo = socialUserModule.user.openContactInfo
    }
    this.$refs.dialog.open()
  }

  async confirm() {
    if (!this.contactInfoValue) {
      ToastUtil.error('联系方式不能为空')
    }
    try {
      let msg = '是否确认将联系方式设置为' + this.contactInfoValue
      if (this.openContactInfo) {
        msg += '，并设置为他人可付费获取方式'
      } else {
        msg += '，并设置为他人不可获取'
      }
      await AlertUtil.confirm(msg)
      this.$refs.dialog.close()
      const res = await SocialuniUserExpandAPI.editUserContactInfoAPI(this.contactInfoValue, this.openContactInfo)
      socialUserModule.setUser(res.data)
    } finally {

    }
  }
}
</script>
