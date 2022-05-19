<template>
  <!--  <social-talk-page ref="socialTalk"></social-talk-page>-->
  <social-talk-mobile-page ref="socialTalk"></social-talk-mobile-page>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import SocialTalkMobilePage from "@/socialuni/components/SocialPages/SocialTalkMobilePage.vue";

@Component({
  components: {SocialTalkMobilePage}
})
export default class TalkPage extends Vue {
  $refs: {
    socialTalk: SocialTalkMobilePage
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
