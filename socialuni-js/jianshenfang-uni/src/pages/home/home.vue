<template>
  <div class="flex-col h100p bg-theme-bg">
    <div class="bg-theme-gradual px-smm pb-smm pt-100">
      <div class="row-col-center">
        <img
            class="card-title-avatar bd bd-round flex-none"
            mode="aspectFill"
            src="https://cdxapp-1257733245.file.myqcloud.com/socialuni/common/app/defaultAvatar/girl/8326cffc1e178a8264b5aa6cb303738da877e876.jpg!avatar"
        />
        <div class="flex-1 row-between-center py-xs">
          <div class="flex-col flex-1">
            <div class="row-between-center">
              <div class="row-col-center">
                <span class="font-bold">明天的月光</span>
                <social-gender-tag class="ml-xs" :user="{gender:'boy',age:20}"></social-gender-tag>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="mt">
        <div class="flex-row px-sm">
          <div class="flex-1 row-all-center  py-10 bd-radius-10 mb-sm elevation-4 mr-sm bg-orange3 color-white">
            余额：500元
          </div>
          <div class="flex-1 row-all-center  py-10 bd-radius-10 mb-sm elevation-4 bg-red6 color-white">
            会员：200天
          </div>
        </div>
      </div>

      <div class="flex-col mt-md elevation-4 bg-white bd-radius-20 pd-sm">
        <div>
          <!--          二维码-->
        </div>
        <div class="row-all-center">
          <img class="size200" src="/static/img/qrcode.png"/>
        </div>
      </div>

      <div class="flex-col mt elevation-4 bg-white bd-radius">
        <q-row-item>
          课程预约
          <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
        </q-row-item>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import TalkView from "socialuni-community-view-uni/src/views/talk/TalkView.vue"
import SocialGenderTag from "socialuni-user-ui/src/components/SUserGenderTag.vue";
import QRowItem from "qing-ui/src/components/QRowItem.vue";
import QIcon from "qing-ui/src/components/QIcon.vue";

@toNative
@Component({
  components: {
    SocialGenderTag,
    QIcon,
    QRowItem,
    TalkView
  }
})
export default class TalkPage extends Vue {
}
</script>
