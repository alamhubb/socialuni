<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import { userModule } from '@/store'


@Component
class AppPage extends Vue {
  // routers: RouteConfig [] = routers

  created() {
    userModule.getUserAction()
  }

  toImgIdentity(router) {
    console.log(router)
    this.$router.push({ name: router.name })
  }
}
export default toNative(AppPage)
</script>
