<template>
  <div class="row-col-center">
    <q-tag v-if="user.gender==='girl'" type="girl" :border="border" size="nn" round>
      <div class="font-xs">{{ user.age }}</div>
      <q-icon class="ml-nn" add-class="mdi-rotate-45"
              size="12"
              :icon="getGenderIcon(user)"/>
    </q-tag>
    <q-tag v-else type="boy" :border="border" size="nn" round>
      <div class="font-xs">{{ user.age }}</div>
      <q-icon class="ml-nn"
              size="12"
              :icon="getGenderIcon(user)"/>
    </q-tag>
  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import QIcon from "qing-compat-js-ui/src/components/QIcon.vue";
import QTag from "qing-compat-js-ui/src/components/QTag.vue";
import UserUtil from "socialuni-user-sdk/src/util/UserUtil";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";

@toNative
@Component({
  components: {
    QIcon, QTag
  }
})
export default class SUserGenderTag extends Vue {
  @Prop() user: SocialuniUserRO
  @Prop({default: false, type: Boolean}) border: boolean

  getGenderIcon(user: CenterUserDetailRO) {
    return UserUtil.getGenderIcon(user)
  }
}
</script>
