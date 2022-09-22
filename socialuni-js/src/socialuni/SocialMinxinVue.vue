<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import RouterUtil from '@/socialuni/utils/RouterUtil'
import ObjectUtil from '@/socialuni/utils/ObjectUtil'
import PageUtil from '@/socialuni/utils/PageUtil'

@Component
export default class SocialMinxinVue extends Vue {
  pageOnloadDate = new Date()
/*
  onLoad () {
    this.pageOnloadDate = new Date()
    // console.log('onLoad')
  }

  created () {
    this.pageOnloadDate = new Date()
  }

  mounted () {
    const timeDiff = new Date().getTime() - this.pageOnloadDate.getTime()
    if (timeDiff > 1000) {
      const data = this.$data
      const keyList = []
      for (const key in data) {
        keyList.push(key)
      }
      console.log(ObjectUtil.toJson(keyList))
      console.log(timeDiff)
      console.log(RouterUtil.getCurrentPageURI())
      console.log('updated')
    }
  }

  beforeUpdate () {
    this.pageOnloadDate = new Date()
  }

  updated () {
    const timeDiff = new Date().getTime() - this.pageOnloadDate.getTime()
    if (timeDiff > 1000) {
      const data = this.$data
      const keyList = []
      for (const key in data) {
        keyList.push(key)
      }
      console.log(ObjectUtil.toJson(keyList))
      console.log(timeDiff)
      console.log(RouterUtil.getCurrentPageURI())
      console.log('updated')
    }
  }

  onReady () {
    const timeDiff = new Date().getTime() - this.pageOnloadDate.getTime()
    if (timeDiff > 1000) {
      const data = this.$data
      const keyList = []
      for (const key in data) {
        keyList.push(key)
      }
      console.log(ObjectUtil.toJson(keyList))
      console.log(timeDiff)
      console.log(RouterUtil.getCurrentPageURI())
      console.log('updated')
    }
  }*/

  onShareAppMessage () {
    const title = '年轻人生活分享社区'
    const path = '/pages/talk'
    const imageUrl = 'https://cdxapp-1257733245.file.myqcloud.com/qingchi/home/full.jpg!avatar'
    return {
      title: title,
      path: path,
      imageUrl: imageUrl
    }
  }
}
</script>
