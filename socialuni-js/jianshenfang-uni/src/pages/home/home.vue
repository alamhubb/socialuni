<template>
  <div class="bg-home px pb-smm pt-100">
    <div class="row-col-center">
      <img
          class="card-title-avatar bd bd-round flex-none"
          mode="aspectFill"
          src="https://cdxapp-1257733245.file.myqcloud.com/socialuni/common/app/defaultAvatar/girl/8326cffc1e178a8264b5aa6cb303738da877e876.jpg!avatar"
      />
      <div class="row-between-center flex-1">
        <div class="row-col-center">
          <span class="font-bold font-18">明天的月光</span>
          <social-gender-tag class="ml-xs" :user="{gender:'boy',age:20}"></social-gender-tag>
        </div>
        <div>
          <div class="row-end-center">
            <q-icon class="ml" icon="setting" size="20"></q-icon>
          </div>
        </div>
      </div>
    </div>

    <div class="mt">
      <div class="flex-row">
        <!--          bg-orange3-->
        <div class="flex-1 font-bold row-all-center py-10 bd-radius-10 mb-sm elevation-4 bg-white">
          <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 mr-sm color-white" style="background-color: #ff8b36">-->
          我的卡卷
        </div>
        <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 bg-red5 color-white">
                    会员：200天
                  </div>-->
      </div>
    </div>

    <div>
      <q-row-item class="px-sm">
        <template #title>
          <span class="font-16 font-bold">个人数据</span>
        </template>
        <div>我的名片</div>
      </q-row-item>
      <div class="mt-sm row-col-center py-10 bd-radius-10 mb-sm elevation-4 bg-white bg-orange_light py">
        <div class="w60p flex-col">
          <div class="row-around-center">
            <div>
              <div class="bg-orange color-white bd-round size30 row-all-center font-12">本月</div>
            </div>
            <div>
              <div class="color-sub font-12">健身时长</div>
              <div>
                <span class="font-18 font-bold">0</span>
                分钟
              </div>
            </div>
            <div>
              <div class="color-sub font-12">健身次数</div>
              <div>
                <span class="font-18 font-bold">0</span>
                分钟
              </div>
            </div>
          </div>
          <div class="row-around-center mt">
            <div>
              <div class="bg-orange color-white bd-round size30 row-all-center font-12">本月</div>
            </div>
            <div>
              <div class="color-sub font-12">健身时长</div>
              <div>
                <span class="font-18 font-bold">0</span>
                分钟
              </div>
            </div>
            <div>
              <div class="color-sub font-12">健身次数</div>
              <div>
                <span class="font-18 font-bold">0</span>
                分钟
              </div>
            </div>
          </div>
        </div>
        <div class="w40p col-all-center">
          <div class="bd-round bg-white bd-orange size70 row-all-center">
            <span class="font-20 font-bold">0</span> /100分
          </div>
          <div class="bg-black color-white bd-radius-20 px-sm mt-xs font-12">本次训练评估</div>
        </div>
      </div>
    </div>


    <div>
      <div class="font-16 font-bold px-sm">订单</div>
      <div class="flex-row mt-sm">
        <!--          bg-orange3-->
        <div class="flex-1 col-all-center py-10 bd-radius-10 mb-sm elevation-4 bg-white mr-sm">
          <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 mr-sm color-white" style="background-color: #ff8b36">-->
          <div>
            <q-icon icon="mdi-calendar-text-outline color-red6" :size="30"></q-icon>
          </div>
          <div class="font-12 mt-xs">
            团操订单
          </div>
        </div>
        <div class="flex-1 col-all-center py-10 bd-radius-10 mb-sm elevation-4 bg-white">
          <div>
            <q-icon icon="mdi-bullhorn color-orange5" :size="34"></q-icon>
          </div>
          <div class="font-12 mt-xs">
            活动订单
          </div>
        </div>
        <!--          <div class="flex-1 row-all-center py-10 bd-radius-10 mb-sm elevation-4 bg-red5 color-white">
                    会员：200天
                  </div>-->
      </div>
    </div>


    <div>
      <div class="font-16 font-bold px-sm">工具中心</div>
      <div class="flex-col mt-sm elevation-4 bg-white bd-radius">
        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-face-agent" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">在线客服</div>
          </div>
        </q-row-item>

        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-office-building" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">企业客户</div>
          </div>
        </q-row-item>

        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-cash-100" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">我的定金</div>
          </div>
        </q-row-item>

        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-gavel" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">定金下单</div>
          </div>
        </q-row-item>

        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-file-document-edit" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">我的合同</div>
          </div>
        </q-row-item>

        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-wallet-bifold" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">自助开卡</div>
          </div>
        </q-row-item>

        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-message-text" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">CEO直通车</div>
          </div>
        </q-row-item>

        <q-row-item border>
          <div class="row-col-center">
            <q-icon icon="mdi-cog" size="24" add-class="color-theme"></q-icon>
            <div class="ml-sm">设置</div>
          </div>
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
