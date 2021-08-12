<template>
  <!--  此层div必须要有，否则样式会加到页面上-->
  <div>
    <transition name="fade-transform" mode="out-in">
      <router-view :key="$route.path"/>
    </transition>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

@Component
export default class AppMain extends Vue {

}
</script>

