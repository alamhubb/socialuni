<template>
  <!--  <qc-login></qc-login>-->
  <login></login>
</template>


<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import QcLogin from '@/pages/login/QcLogin.vue'
import Login from '@/pages/login/login.vue'

@Component({
  components: {
    Login,
    QcLogin
  }
})
export default class LoginPage extends Vue {

}
</script>
