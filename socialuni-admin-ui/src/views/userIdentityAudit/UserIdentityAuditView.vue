<template>
  <div>
    123
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import Talk from '@/model/talk/Talk'
import UserIdentityAuditAPI from '@/api/UserIdentityAuditAPI'

@Component
export default class UserIdentityAuditView extends Vue {
  content = '5'
  talks: Talk[] = []

  created() {
    UserIdentityAuditAPI.queryUserIdentityAuthAuditListAPI()
    /* TestAPI.testKeywordsAPI(this.content).then(res => {
      this.talks = res.data
    })*/
  }
}
</script>

<style scoped>

</style>
