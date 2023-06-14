<template>
  <div class="row-col-center">
    <s-tag v-if="user.gender==='girl'" type="girl" :border="border" size="nn" round>
      <div class="font-xs">{{ user.age }}</div>
      <q-icon class="ml-nn" add-class="mdi-rotate-45"
              size="12"
              :icon="getGenderIcon(user)"/>
    </s-tag>
    <s-tag v-else type="boy" :border="border" size="nn" round>
      <div class="font-xs">{{ user.age }}</div>
      <q-icon class="ml-nn"
              size="12"
              :icon="getGenderIcon(user)"/>
    </s-tag>
  </div>
</template>

<script lang="ts">
import {Options, Prop, Vue} from 'vue-property-decorator'
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import STag from "socialuni-ui-uni/src/components/STag.vue";
import UserUtil from "socialuni-user-sdk/src/util/UserUtil";

@Options({
  components: {
    QIcon, STag
  }
})
export default class SocialGenderTag extends Vue {
  @Prop() user: CenterUserDetailRO
  @Prop({default: false, type: Boolean}) border: boolean

  getGenderIcon(user: CenterUserDetailRO) {
    return UserUtil.getGenderIcon(user)
  }
}
</script>
