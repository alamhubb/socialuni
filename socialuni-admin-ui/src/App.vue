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
  created() {
    userModule.getUserAction()
  }
}
</script>
