<template>
  <q-popup v-model="showCircleSearch" bottom :modal="false">
    <div class="h90vh py-sm">
      <div class="px mb-sm">
        <q-input v-model="circleSearchText"></q-input>
      </div>
      <q-sidebar :dataList="tagTypes" class="flex-1 flex-row overflow-hidden">
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

            <view v-if="item.tags">
              <div class="row-wrap overflow-hidden">
                <div v-for="(circle,index) in item.tags"
                     class="col-all-center ml-sm overflow-hidden mb-sm">
                  <img class="bd-round size50" :src="circle.avatar"/>
                  <div class="font-cut">{{ circle.name }}</div>
                  <div class="font-cut">{{ circle.talkCount }}</div>
                </div>
              </div>
            </view>
          </view>
        </template>
      </q-sidebar>
    </div>
  </q-popup>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import QPopup from '@/socialuni/components/QPopup/QPopup.vue'
import QSidebar from '@/socialuni/components/QSidebar/QSidebar.vue'
import QInput from '@/socialuni/components/QInput/QInput.vue'
import { socialTagStore } from '@/socialuni/store'
import TagTypeVO from '@/socialuni/model/community/tag/TagTypeVO'


@Component({
  components: {
    QInput,
    QSidebar,
    QPopup
  }
})
export default class SCircleSearch extends Vue {
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]


  showCircleSearch = false
  circleSearchText = ''

  openDialog () {
    this.showCircleSearch = true
  }

}
</script>
