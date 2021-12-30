<template>
  <view class="h100p flex-col">
    <q-navbar>
      <q-search class="flex-1 mx-sm bg-default">
        <q-icon class="mx-xs text-gray flex-none" icon="search" size="16"></q-icon>
        <input class="flex-1" v-model="searchContent" :adjust-position="false" type="text" @focus="showSearchView" focus
               placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
        <q-icon v-if="searchContent" class="mr-sm text-gray row-all-center flex-none" icon="close" size="16"
                @click="clearSearchContent"
        ></q-icon>
      </q-search>
      <div @click="input" class="flex-none mr-sm">取消</div>
    </q-navbar>

    <q-sidebar :dataList="circleTypes" class="flex-1 flex-row overflow-hidden">
      <template #leftRow="{item,index,current}">
        <view class="q-sidebar-item" :class="{'q-sidebar-item-active':index === current}">
          <view class="row-all-center flex-auto">
            <text class="uni-ellipsis">{{ item.name }}</text>
          </view>
        </view>
      </template>
      <template #rightRow="{item}">
        <view class="bg-white">
          <view class="q-box-row">
            <text class="cuIcon-title text-green margin-right-xs"></text>
            <text class="font-bold">{{ item.name }}</text>
          </view>

          <view>
            <div class="h100p flex-col flex-none bd-radius py-sm px-xs overflow-hidden">
              <div class="row-wrap overflow-hidden">
                <div v-for="(circle) in item.circles" class="col-row-center mx-xs overflow-hidden h70 mb-xs">
                  <img class="bd-round size50" :src="circle.avatar"/>
                  <div class="font-cut">{{ circle.name }}</div>
                </div>
              </div>
            </div>
          </view>
        </view>
      </template>
    </q-sidebar>
  </view>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'


import { socialCircleModule, socialCircleStore, socialTagStore } from '@/socialuni/store'
import TagVO from '@/socialuni/model/community/tag/TagVO'
import TagTypeVO from '@/socialuni/model/community/tag/TagTypeVO'
import TagUtil from '@/socialuni/utils/TagUtil'
import QNavbar from '@/qing-ui/components/QNavbar/QNavbar.vue'
import QSearch from '@/qing-ui/components/QSearch/QSearch.vue'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import QSlider from '@/qing-ui/components/QSlider/QSlider.vue'
import QSidebar from '@/qing-ui/components/QSidebar/QSidebar.vue'
import QRowItem from '@/qing-ui/components/QRowItem/QRowItem.vue'
import QTabs from '@/qing-ui/components/QTabs/QTabs.vue'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'
import QTab from '@/qing-ui/components/QTab/QTab.vue'


@Component({
  components: {
    QTab,
    QTabs,
    QRowItem,
    QSidebar,
    QSlider,
    QIcon,
    QSearch,
    QNavbar
  }
})
export default class CircleSearch extends Vue {
  // 只有从新增talk界面进入时才可新增标签
  @Model('input') readonly value: boolean

  @Prop({
    type: Boolean,
    default: false
  }) readonly isAdd: boolean
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]
  @socialCircleStore.State('circleTypes') readonly circleTypes: CircleTypeRO[]
  // 输入内容查询时显示的列表tag
  // 进入页面只查询前20个，点击了输入内容才查询所有
  @socialTagStore.State('tags') readonly tags: TagVO []

  circleCurrent = 0
  tagCurrent = 0

  searchContent = ''
  showSearch = false
  historyTags: TagVO [] = TagUtil.getStorageHistoryTags()

  onLoad () {
    socialCircleModule.getCircleTypesAction()
    console.log(this.circleTypes)
  }

  get showTags (): TagVO[] {
    if (this.searchContent) {
      return this.tags.filter(tag => tag.name.indexOf(this.searchContent) > -1)
    } else {
      return this.tags
    }
  }

  @Emit()
  input () {
    this.clearSearchContent()
    return false
  }

  @Emit()
  change (tag: TagVO) {
    this.input()
    const tagIndex: number = this.historyTags.findIndex(item => item.id === tag.id)
    if (tagIndex > -1) {
      this.historyTags.splice(tagIndex, 1)
    }
    this.historyTags.unshift(tag)
    this.historyTags = this.historyTags.slice(0, 5)
    TagUtil.setStorageHistoryTags(this.historyTags)
    return tag
  }

  @Watch('searchContent')
  watchSearchContent () {
    if (!this.searchContent) {
      this.showSearch = false
    }
  }

  switchCircleTabValue (event: { detail: any }) {
    const detail: {
      current: number,
      source: number
    } = event.detail
    this.circleCurrent = detail.current
  }

  switchTagTabValue (event: { detail: any }) {
    const detail: {
      current: number,
      source: number
    } = event.detail
    this.tagCurrent = detail.current
  }

  showSearchView () {
    this.showSearch = true
  }

  clearSearchContent () {
    this.searchContent = ''
  }

  @Emit()
  addTag () {
    return ''
  }

  // 事件列表
  // 输入
  // 清空输入
  // 返回
  // 选择
  // 取消仅仅关闭页面
  // 选择关闭页面，并且回传tag
}
</script>
