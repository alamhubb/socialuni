<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { userModule } from '@/store'

@Component
export default class AppPage extends Vue {
  // routers: RouteConfig [] = routers

  created() {
    userModule.getUserAction()
  }

  toImgIdentity(router) {
    console.log(router)
    this.$router.push({ name: router.name })
  }
}
</script>
