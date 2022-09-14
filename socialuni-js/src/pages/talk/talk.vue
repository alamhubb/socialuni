<template>
  <social-talk-page ref="socialTalk"></social-talk-page>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import SocialTalkPage from "@/components/SocialPages/SocialTalkPage.vue";

@Component({
  components: {SocialTalkPage}
})
export default class TalkPage extends Vue {
  $refs: {
    socialTalk: SocialTalkPage
  }

  onHide() {
    //@ts-ignore
    this.$refs.socialTalk.tabsTalkOnHide()
  }

  onPageScroll(e) {
    //@ts-ignore
    this.$refs.socialTalk.socialTalkScroll(e)
  }
}
</script>
