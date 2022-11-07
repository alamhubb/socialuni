<template>
  <view class="h100p flex-col">
    <q-navbar v-if="value">
      <q-icon class="ml-smm" icon="arrow-leftward" @click="input"></q-icon>
      <q-search class="flex-1 mx-sm bg-default">
        <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
        <input class="flex-1" v-model="searchContent" :adjust-position="false" type="text" @focus="showSearchView" focus
               placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
        <q-icon v-if="searchContent" class="mr text-gray row-all-center" icon="close" size="16"
                @click="clearSearchContent"
        ></q-icon>
      </q-search>
    </q-navbar>
    <view v-if="searchContent">
      <view v-if="searchContent&&isAdd" class="article-row solid-bottom color-blue" @click="addTag">
        未找到相关标签，可点击
        <q-icon icon="arrow_right_alt"></q-icon>
        此处去创建
      </view>
      <view v-for="tag in showTags" :key="tag.id" @click="change(tag)"
            class="article-row row-between solid-bottom">
        <text>
          #{{ tag.name }}
        </text>
        <view v-if="tag.count" class="row-col-center">
          <q-icon addClass="color-red" icon="mdi-fire"></q-icon>
          {{ tag.count }}
        </view>
      </view>
    </view>
    <view v-else class="flex-col flex-1 overflow-hidden">
      <div class="q-box solid-bottom">
        <div class="flex-row">
          <text class="cuIcon-title text-green"></text>
          <text class="text-md font-bold">历史话题</text>
        </div>
        <div class="row-wrap">
          <template v-if="historyTags.length">
            <view v-for="tag in historyTags"
                  class="ml-xs mt-sm q-tag-theme lg round"
                  :key="tag.id"
                  @click="change(tag)">
              #{{ tag.name }}
            </view>
          </template>
          <view v-else class="pl-sm mt-sm text-md text-gray">
            空
          </view>
        </div>
      </div>

      <q-sidebar :dataList="tagTypes" class="flex-1 flex-row overflow-hidden" :right-scroll="false">
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
              <q-row-item v-for="tag in item.tags" :key="tag.id" @click="change(tag)">
                <view class="row-col-center can-click" @click.stop="change(tag)">
                  <image class="cu-avatar radius lg flex-none"
                         :src="tag.avatar"
                  />
                  <view class="ml-sm overflow-hidden">
                    <view>
                      {{ tag.name }}
                    </view>
                    <view class="text-gray text-sm text-ellipsis">
                      帖子：{{ tag.count }}
                    </view>
                  </view>
                </view>
                <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
              </q-row-item>
            </view>
          </view>
        </template>
      </q-sidebar>
    </view>
  </view>
</template>

<script lang="ts">
import {Options, Emit, Model, Prop, Vue, Watch} from 'vue-property-decorator'

import QRowItem from '../../qing-ui/components/QRowItem/QRowItem.vue'
import QIcon from '../../qing-ui/components/QIcon/QIcon.vue'
import QNavbar from '../../qing-ui/components/QNavbar/QNavbar.vue'
import QSearch from '../../qing-ui/components/QSearch/QSearch.vue'
import QSidebar from '../../qing-ui/components/QSidebar/QSidebar.vue'
import {socialTagModule} from "socialuni-sdk/src/store/store";
import TagVO from "socialuni-api/src/model/community/tag/TagVO";
import TagUtil from "../socialuni-js-packages/packages/socialuni-common/src/utils/TagUtil";


@Options({
  components: {
    QSidebar,
    QSearch,
    QNavbar,
    QRowItem,
    QIcon
  }
})
export default class TagSearch extends Vue {
  // 只有从新增talk界面进入时才可新增标签
  @Model('input') readonly value: boolean

  @Prop({
    type: Boolean,
    default: false
  }) readonly isAdd: boolean

  get tagTypes() {
    return socialTagModule.tagTypes
  }

  get tags() {
    return socialTagModule.tags
  }

  mounted() {
    socialTagModule.getTagTypesAction()
  }

  openTagSearchDialog() {

  }

  searchContent = ''
  showSearch = false
  historyTags: TagVO [] = TagUtil.getStorageHistoryTags()

  get showTags(): TagVO[] {
    if (this.searchContent) {
      return this.tags.filter(tag => tag.name.indexOf(this.searchContent) > -1)
    } else {
      return this.tags
    }
  }

  @Emit()
  input() {
    this.clearSearchContent()
    return false
  }

  @Emit()
  change(tag: TagVO) {
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
  watchSearchContent() {
    if (!this.searchContent) {
      this.showSearch = false
    }
  }

  showSearchView() {
    this.showSearch = true
  }

  clearSearchContent() {
    this.searchContent = ''
  }

  @Emit()
  addTag() {
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
