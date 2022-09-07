<template>
  <div>
    <div v-if="user.gender==='girl'" class="q-tag-girl bd-round bd-none q-box-nn text-xs">
      <div class="font-xs">{{ user.age }}</div>
      <q-icon class="ml-nn" add-class="mdi-rotate-45"
              size="12"
              :icon="getGenderIcon(user)"/>
    </div>
    <div v-else class="q-tag-boy bd-round bd-none q-box-nn">
      <div class="font-xs">{{ user.age }}</div>
      <q-icon class="ml-nn"
              size="12"
              :icon="getGenderIcon(user)"/>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import UserUtil from '@/socialuni/utils/UserUtil'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'

@Component({
  components: {
    QIcon
  }
})
export default class SocialGenderTag extends Vue {

  @Prop() user: CenterUserDetailRO

  getGenderIcon (user: CenterUserDetailRO) {
    return UserUtil.getGenderIcon(user)
  }
}
</script>
