<template>
  <q-popup ref="circleChooseDialog" bottom hide-modal hide-confirm>
    <template #headerLeft>
      <q-input class="w100p ml-sm bd-round" v-model="circleSearchText"></q-input>
    </template>
    <div class="h80vh">
      <scroll-view class="h100p" scroll-y v-if="circleSearchText">
        <view v-for="tag in showCircles" :key="tag.id" @click="change(tag)"
              class="article-row row-between solid-bottom">
          <text>
            #{{ tag.name }}
          </text>
          <view v-if="tag.count" class="row-col-center">
            <q-icon addClass="color-red" icon="mdi-fire"></q-icon>
            {{ tag.count }}
          </view>
        </view>
      </scroll-view>
      <q-sidebar v-if="circleTypes.length" :dataList="circleTypes" class="flex-1 flex-row overflow-hidden"
                 :right-scroll="false">
        <template #leftRow="{item,index,current}">
          <view class="q-sidebar-item" :class="{'q-sidebar-item-active':index === current}">
            <view class="row-all-center flex-1">
              <text class="uni-ellipsis">{{ item.name }}</text>
            </view>
          </view>
        </template>
        <template #rightRow="{item}">
          <view class="mx-sm mt-sm bg-white">
            <view class="q-box-row mb-xs">
              <text class="cuIcon-title text-green margin-right-xs"></text>
              <text class="font-bold font-md">{{ item.name }}</text>
            </view>

            <div v-if="item.circles" class="row-wrap overflow-hidden">
              <div v-for="(circle,index) in item.circles"
                   class="col-all-center ml-sm overflow-hidden mb-sm bg-click" @click="change(circle)">
                <img class="bd-round size50" :src="circle.avatar"/>
                <div class="font-cut">{{ circle.name }}</div>
                <div class="font-cut">{{ circle.count }}</div>
              </div>
            </div>
          </view>
        </template>
      </q-sidebar>
      <div v-else class="row-all-center pd">数据加载中，请稍等...</div>
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Component, Emit, Vue} from 'vue-facing-decorator'
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue";
import SocialCircleRO from "socialuni-api-base/src/model/community/circle/SocialCircleRO";
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import QInput from "socialuni-ui-uni/src/components/QInput/QInput.vue";
import QSidebar from "socialuni-ui-uni/src/components/QSidebar/QSidebar.vue";
import {socialCircleModule} from "socialuni-community-sdk/src/store/SocialCircleModule";


@Component({
  components: {
    QIcon,
    QInput,
    QSidebar,
    QPopup
  }
})
export default class SocialCirclePicker extends Vue {
  $refs: {
    circleChooseDialog: QPopup
  }

  get circleTypes() {
    return socialCircleModule.circleTypes
  }

  get showCircles() {
    if (this.circleTypes.length) {
      //去除热门，要不然会存在重复
      return this.circleTypes.slice(1, this.circleTypes.length - 1).flatMap(item => {
            if (item && item.circles) {
              return item.circles.filter(tag => tag.name.includes(this.circleSearchText))
            }
            return []
          })
    }
    return []
  }

  circleSearchText = ''


  openDialog() {
    this.circleSearchText = ''
    socialCircleModule.getCircleTypesAction()
    this.$refs.circleChooseDialog.open()
  }

  closeDialog() {
    this.$refs.circleChooseDialog.close()
  }

  @Emit('update:modelValue')
  change(circle: SocialCircleRO) {
    this.closeDialog()
    return circle
  }
}
</script>
