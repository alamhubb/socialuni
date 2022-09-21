<template>
  <q-popup ref="dialog" bottom @confirm="confirm">
    <div class="h40vh pd flex-col">
      <div class="mb">
        联系方式：
      </div>
      <div>
        <q-input class="w100p" v-model="contactInfoValue" placeholder="在此输入您的联系方式"></q-input>
      </div>
      <div class="mt">填写联系方式，方便他人联系您</div>
      <div class="mt">他人可通过付费1元的方式获取您的联系方式</div>
    </div>
  </q-popup>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QSearch from '@/qing-ui/components/QSearch/QSearch.vue'
import QIcon from '@/qing-ui/components/QLocation/QIcon.vue'
import SocialuniCircleAPI from '@/socialuni/api/socialuni/SocialuniCircleAPI'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import { socialUserModule } from '@/socialuni/store'
import QSelect from '@/qing-ui/components/QSelect/QSelect.vue'
import AlertUtil from '@/socialuni/utils/AlertUtil'
import SocialuniUserExpandAPI from '@/socialuni/api/socialuni/SocialuniUserExpandAPI'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import ToastUtil from '@/socialuni/utils/ToastUtil'

@Component({
  components: {
    QInput,
    QPopup
  }
})
export default class UserContactInfoEditDialog extends Vue {
  $refs!: {
    dialog: QPopup;
  }

  contactInfoValue = null

  open () {
    this.contactInfoValue = null
    if (socialUserModule.user.contactInfo) {
      this.contactInfoValue = socialUserModule.user.contactInfo
    }
    this.$refs.dialog.open()
  }

  async confirm () {
    if (!this.contactInfoValue){
      ToastUtil.error('联系方式不能为空')
    }
    try {
      await AlertUtil.confirm('是否确认将联系方式设置为' + this.contactInfoValue)
      this.$refs.dialog.close()
      const res = await SocialuniUserExpandAPI.editUserContactInfoAPI(this.contactInfoValue)
      socialUserModule.setUser(res.data)
    } finally {

    }
  }
}
</script>
