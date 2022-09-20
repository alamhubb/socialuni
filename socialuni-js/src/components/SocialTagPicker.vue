<template>
  <q-popup ref="dialog" bottom hide-modal hide-confirm>
    <template #headerLeft>
      <q-input class="w100p ml-sm" v-model="searchText"></q-input>
    </template>
    <div class="h80vh">
      <scroll-view class="h100p" scroll-y v-if="searchText">
        <view v-for="tag in showTags" :key="tag.name" @click="change(tag)"
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
      <q-sidebar v-else :dataList="tagTypes" class="h100p flex-row overflow-hidden"
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

            <div v-if="item.tags" class="row-wrap overflow-hidden">
              <div v-for="(tag,index) in item.tags"
                   class="col-all-center ml-sm overflow-hidden mb-sm bg-click" @click="change(tag)">
                <img class="bd-round size50" :src="tag.avatar"/>
                <div class="font-cut">{{ tag.name }}</div>
                <div class="font-cut">{{ tag.count }}</div>
              </div>
            </div>
          </view>
        </template>
      </q-sidebar>
    </div>
  </q-popup>
</template>

<script lang="ts">
import { Component, Emit, Vue } from 'vue-property-decorator'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QSidebar from '@/qing-ui/components/QSidebar/QSidebar.vue'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import { socialTagModule } from '@/socialuni/store'
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
export default class SocialTagPicker extends Vue {
  $refs: {
    dialog: QPopup
  }

  get tagTypes () {
    return socialTagModule.tagTypes
  }

  get showTags () {
    //去除热门，要不然会存在重复
    return this.tagTypes.slice(1, this.tagTypes.length - 1).flatMap(item => item.tags.filter(tag => tag.name.includes(this.searchText)))
  }

  searchText = ''

  open () {
    this.searchText = ''
    socialTagModule.getTagTypesAction()
    this.$refs.dialog.open()
  }

  close () {
    this.$refs.dialog.close()
  }

  @Emit()
  change (tag: SocialCircleRO) {
    this.close()
    return tag
  }
}
</script>
