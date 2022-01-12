<template>
  <qc-login v-if="isAuthApp"></qc-login>
  <social-phone v-else></social-phone>
</template>


<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import QcLogin from '@/pages/login/QcLogin.vue'
import SocialPhone from '@/pages/phone/SocialPhone.vue'
import SocialuniConfig from '@/socialuni/config/SocialuniConfig'

@Component({
  components: {
    SocialPhone,
    QcLogin
  }
})
export default class PhonePage extends Vue {
  isAuthApp = SocialuniConfig.authApp
}
</script>
