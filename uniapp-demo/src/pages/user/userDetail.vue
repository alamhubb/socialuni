<template>
 <social-user-detail-page></social-user-detail-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialUserDetailPage from 'socialuni/components/SocialPages/SocialUserDetailPage.vue'

@Component({
  components: { SocialUserDetailPage }
})
export default class UserDetailPage extends Vue {
}
</script>
