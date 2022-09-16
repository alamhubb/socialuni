<template>
  <q-popup ref="circleChooseDialog" bottom hide-modal hide-confirm>
    <template #headerLeft>
      <q-input class="w100p ml-sm" v-model="circleSearchText"></q-input>
    </template>
    <div class="h80vh">
      <q-sidebar v-if="circleTypes.length" :dataList="showCircleTypes" class="flex-1 flex-row overflow-hidden"
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
                <div class="font-cut">{{ circle.talkCount }}</div>
              </div>
            </div>
          </view>
        </template>
      </q-sidebar>
      <div v-else class="row-all-center pd">数据加载中，请稍等</div>
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Component, Emit, Vue} from 'vue-property-decorator'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QSidebar from '@/qing-ui/components/QSidebar/QSidebar.vue'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import {socialCircleModule, socialCircleStore} from '@/socialuni/store'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'
import ObjectUtil from '@/socialuni/utils/ObjectUtil'


@Component({
  components: {
    QInput,
    QSidebar,
    QPopup
  }
})
export default class SocialCirclePicker extends Vue {
  $refs: {
    circleChooseDialog: QPopup
  }

  @socialCircleStore.State('circleTypes') readonly circleTypes: CircleTypeRO[]

  circleSearchText = ''

  get showCircleTypes() {
    if (this.circleSearchText) {
      const showCircleTypes = this.circleTypes.reduce((all, item) => {
        const data = item.circles.filter(circle => circle.name.includes(this.circleSearchText))
        if (data.length) {
          const itemCopy = ObjectUtil.deepClone(item)
          itemCopy.circles = data
          all.push(itemCopy)
        }
        return all
      }, [])
      return showCircleTypes
    } else {
      return this.circleTypes
    }
  }

  openDialog() {
    this.circleSearchText = ''
    if (!this.circleTypes.length) {
      socialCircleModule.getCircleTypesAction()
    }
    this.$refs.circleChooseDialog.open()
  }

  closeDialog() {
    this.$refs.circleChooseDialog.close()
  }

  @Emit()
  change(circle: SocialCircleRO) {
    this.closeDialog()
    return circle
  }
}
</script>
