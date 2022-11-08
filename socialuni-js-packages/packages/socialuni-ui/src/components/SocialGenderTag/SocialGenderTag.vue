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
import {Options, Prop, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import UserUtil from "socialuni-common/src/utils/UserUtil";
import QIcon from "../../qing-ui/components/QIcon/QIcon.vue";

@Options({
  components: {
    QIcon
  }
})
export default class SocialGenderTag extends Vue {

  @Prop() user: CenterUserDetailRO

  getGenderIcon(user: CenterUserDetailRO) {
    return UserUtil.getGenderIcon(user)
  }
}
</script>
