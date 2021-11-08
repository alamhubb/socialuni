<template>
  <qc-login></qc-login>
</template>


<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import QcLogin from '@/pages/login/QcLogin.vue'

@Component({
  components: {
    QcLogin
  }
})
export default class LoginPage extends Vue {

}
</script>
