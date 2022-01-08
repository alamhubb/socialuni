<template>
  <!--  <qc-login></qc-login>-->
  <social-phone></social-phone>
</template>


<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import QcLogin from '@/pages/login/QcLogin.vue'
import SocialPhone from '@/pages/phone/SocialPhone.vue'

@Component({
  components: {
    SocialPhone,
    QcLogin
  }
})
export default class PhonePage extends Vue {

}
</script>
