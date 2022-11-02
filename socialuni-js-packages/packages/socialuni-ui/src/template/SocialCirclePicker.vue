<template>
  <q-popup ref="circleChooseDialog" bottom hide-modal hide-confirm>
    <template #headerLeft>
      <q-input class="w100p ml-sm bd-round" v-model="circleSearchText"></q-input>
    </template>
    <div class="h80vh">
      <scroll-view class="h100%" scroll-y v-if="circleSearchText">
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
            <view class="row-all-center flex-auto">
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
import { Component, Emit, Vue } from 'vue-property-decorator'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QSidebar from '@/qing-ui/components/QSidebar/QSidebar.vue'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import { socialCircleModule } from '@/socialuni/store'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import ObjectUtil from '@/socialuni/utils/ObjectUtil'
import QIcon from '@/qing-ui/components/QLocation/QIcon.vue'


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

  get circleTypes () {
    return socialCircleModule.circleTypes
  }

  get showCircles () {
    if (this.circleTypes.length) {
      //去除热门，要不然会存在重复
      return this.circleTypes.slice(1, this.circleTypes.length - 1)
        .flatMap(item => {
          if (item && item.circles) {
            return item.circles.filter(tag => tag.name.includes(this.circleSearchText))
          }
          return []
        })
    }
    return []
  }

  circleSearchText = ''


  openDialog () {
    this.circleSearchText = ''
    socialCircleModule.getCircleTypesAction()
    this.$refs.circleChooseDialog.open()
  }

  closeDialog () {
    this.$refs.circleChooseDialog.close()
  }

  @Emit()
  change (circle: SocialCircleRO) {
    this.closeDialog()
    return circle
  }
}
</script>
