<template>
  <view class="h100p flex-col">
    <q-navbar v-if="value">
      <q-icon class="ml" icon="arrow-leftward" @click="input"></q-icon>
      <q-search class="flex-auto">
        <q-icon class="mx-5px text-gray" icon="search" size="16"></q-icon>
        <input v-model="searchContent" :adjust-position="false" type="text" @focus="showSearchView" focus
               placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
        <q-icon v-if="searchContent" class="mr text-gray row-all-center" icon="close" size="16"
                @click="clearSearchContent"
        ></q-icon>
      </q-search>
    </q-navbar>
    <view v-if="searchContent">
      <view v-if="searchContent&&isAdd" class="article-row solid-bottom text-blue" @click="addTag">
        未找到相关标签，可点击
        <q-icon icon="arrow_right_alt"></q-icon>
        此处去创建
      </view>
      <view v-for="tag in showTags" :key="tag.id" @click="change(tag)"
            class="article-row row-between solid-bottom">
        <text>
          #{{tag.name}}
        </text>
        <view v-if="tag.count" class="row-col-center">
          <q-icon addClass="color-red" icon="mdi-fire"></q-icon>
          {{tag.count}}
        </view>
      </view>
    </view>
    <view v-else class="flex-col flex-1 overflow-hidden">
      <q-card class="solid-bottom">
        <q-card-row>
          <text class="cuIcon-title text-green"></text>
          <text class="text-md font-bold">历史话题</text>
        </q-card-row>
        <q-card-grid class="mt-sm">
          <view v-if="historyTags.length">
            <view v-for="tag in historyTags"
                  class="ml-xs margin-right-xs cu-tag lg round bg-green-plain margin-bottom-sm"
                  :key="tag.id"
                  @click="change(tag)">
              {{tag.name}}
            </view>
          </view>
          <view v-else class="pl-sm text-md text-gray">
            空
          </view>
        </q-card-grid>
      </q-card>

      <q-sidebar :dataList="tagTypes" class="flex-1 flex-row overflow-hidden">
        <template #leftRow="{item,index,current}">
          <view class="q-sidebar-item" :class="{'q-sidebar-item-active':index === current}">
            <view class="row-all-center flex-auto">
              <text class="uni-ellipsis">{{item.name}}</text>
            </view>
          </view>
        </template>
        <template #rightRow="{item}">
          <view class="bg-white">
            <view class="q-row">
              <text class="cuIcon-title text-green margin-right-xs"></text>
              <text class="font-bold">{{item.name}}</text>
            </view>

            <view if="item.childs">
              <q-row-item v-for="tag in item.tags" :key="tag.id" @click="change(tag)">
                <view class="row-col-center can-click" @click.stop="change(tag)">
                  <image class="cu-avatar radius lg flex-none"
                         :src="tag.avatar"
                  />
                  <view class="ml-sm overflow-hidden">
                    <view>
                      {{tag.name}}
                    </view>
                    <view class="text-gray text-sm text-ellipsis">
                      帖子：{{tag.talkCount}}
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
import { Vue, Component, Prop, Model, Watch, Emit } from 'vue-property-decorator'

import TagVO from 'socialuni/model/tag/TagVO'
import TagTypeVO from 'socialuni/model/tag/TagTypeVO'
import QRowItem from 'socialuni/components/q-row-item/q-row-item.vue'
import QIcon from 'socialuni/components/q-icon/q-icon.vue'
import { namespace } from 'vuex-class'
import TagUtil from 'socialuni/utils/TagUtil'
import QNavbar from "socialuni/components/q-navbar/q-navbar.vue";
import QSearch from "socialuni/components/q-search/q-search.vue";
import QCard from "socialuni/components/q-card/q-card.vue";
import QCardRow from "socialuni/components/q-card-row/q-card-row.vue";
import QCardGrid from "socialuni/components/q-card-grid/q-card-grid.vue";
import QSidebar from "socialuni/components/q-sidebar/q-sidebar.vue";
import { socialTagStore } from 'socialuni/store'


@Component({
  components: {
    QSidebar,
    QCardGrid,
    QCardRow,
    QCard,
    QSearch,
    QNavbar,
    QRowItem, QIcon
  }
})
export default class TagSearchPage extends Vue {
  // 只有从新增talk界面进入时才可新增标签
  @Model('input') readonly value: boolean

  @Prop({ type: Boolean, default: false }) readonly isAdd: boolean
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]
  // 输入内容查询时显示的列表tag
  // 进入页面只查询前20个，点击了输入内容才查询所有
  @socialTagStore.State('tags') readonly tags: TagVO []

  searchContent = ''
  showSearch = false
  historyTags: TagVO [] = TagUtil.getStorageHistoryTags()

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
