<template>
  <div style="height: 500px;overflow: auto" v-infinite-scroll="scrollToLower" :infinite-scroll-immediate="false"
       :infinite-scroll-distance="200" :infinite-scroll-delay="2000">
    <div v-for="i in count">{{ i}}</div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'

@toNative
@Component({})
export default class App extends Vue {
  count = 50

  scrollToLower() {
    console.log('gundongdao dibu le ')
    setTimeout(() => {
      this.count += 10
    }, 1000)
  }
}
</script>
