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
    <view v-if="searchContent">
      <view v-if="searchContent&&isAdd" class="article-row solid-bottom text-blue" @click="addTag">
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
      <q-card>
        <div class="mb-sm">
          <text class="cuIcon-title"></text>
          <text class="font-md font-bold">历史话题</text>
        </div>
        <div class="row-grid">
          <template v-if="historyTags.length">
            <view v-for="tag in historyTags"
                  class="q-tag-theme"
                  :key="tag.id"
                  @click="change(tag)">
              {{ tag.name }}
            </view>
          </template>
          <view v-else class="pl-sm text-md text-gray">
            空
          </view>
        </div>
      </q-card>

      <div class="box-sm">
        <div>
          <text class="cuIcon-title"></text>
          <text class="font-md font-bold">圈子</text>
        </div>
        <div>
          <div class="row-col-center mb-sm">
            <q-tabs v-model="current" :tabs="['游戏','漫画','情感']"
                    class="bd-radius pd-mn flex-1">
              <template #default="{tab}">
                <div class="h30 px-xs row-all-center font-md">
                  {{ tab }}
                  <!--            费劲啊实力哈哈-->
                </div>
              </template>
            </q-tabs>
            <div class="row-col-center bg-click flex-none ml-sm font-md">更多
              <q-icon icon="mdi-chevron-right"></q-icon>
            </div>
          </div>

          <div>
            <swiper circular class="h90 bd-radius mb-sm" :current="current" @change="switchCircleTabValue">
              <swiper-item class="bd-radius bg-white" v-for="item in 3">
                <div class="h100p flex-col flex-none bd-radius py-sm px-xs overflow-hidden">
                  <!--                        class="radius flex-none h100p"-->
                  <div class="row-nowrap overflow-hidden">
                    <div v-for="(item,index) in tags" v-if="index<5"
                         class="col-row-center mx-xs overflow-hidden h70 flex-1">
                      <img class="bd-round size50" :src="item.avatar"/>
                      <div class="font-cut">{{ item.name }}</div>
                    </div>
                  </div>
                </div>
              </swiper-item>
            </swiper>
          </div>
        </div>
      </div>

      <div class="box-sm">
        <div>
          <text class="cuIcon-title"></text>
          <text class="font-md font-bold">话题</text>
        </div>
        <div>
          <div class="row-col-center mb-sm">
            <q-tabs v-model="current" :tabs="['游戏','漫画','情感']"
                    class="bd-radius pd-mn flex-1">
              <template #default="{tab}">
                <div class="h30 px-xs row-all-center font-md">
                  {{ tab }}
                  <!--            费劲啊实力哈哈-->
                </div>
              </template>
            </q-tabs>
            <div class="row-col-center bg-click flex-none ml-sm font-md">更多
              <q-icon icon="mdi-chevron-right"></q-icon>
            </div>
          </div>

          <div>
            <swiper circular class="h90 bd-radius mb-sm" :current="current" @change="switchCircleTabValue">
              <swiper-item class="bd-radius bg-white" v-for="item in 3">
                <div class="h100p flex-col flex-none bd-radius py-sm px-xs overflow-hidden">
                  <!--                        class="radius flex-none h100p"-->
                  <div class="row-grid overflow-hidden">
                    <div v-for="(item,index) in tags"
                         class="col-row-center overflow-hidden flex-none mb-sm">
                      <div class="q-tag-theme" @click="change(tag)">{{ item.name }}</div>
                    </div>
                  </div>
                </div>
              </swiper-item>
            </swiper>
          </div>
        </div>
      </div>

      <!--      <q-sidebar :dataList="tagTypes" class="flex-1 flex-row overflow-hidden">
              <template #leftRow="{item,index,current}">
                <view class="q-sidebar-item" :class="{'q-sidebar-item-active':index === current}">
                  <view class="row-all-center flex-auto">
                    <text class="uni-ellipsis">{{item.name}}</text>
                  </view>
                </view>
              </template>
              <template #rightRow="{item}">
                <view class="bg-white">
                  <view class="q-box-row">
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
            </q-sidebar>-->
    </view>
  </view>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'


import { socialTagStore } from '@/socialuni/store'
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


@Component({
  components: {
    QTabs,
    QRowItem,
    QSidebar,
    QSlider,
    QIcon,
    QSearch,
    QNavbar
  }
})
export default class TagSearchPage extends Vue {
  // 只有从新增talk界面进入时才可新增标签
  @Model('input') readonly value: boolean

  @Prop({
    type: Boolean,
    default: false
  }) readonly isAdd: boolean
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]
  // 输入内容查询时显示的列表tag
  // 进入页面只查询前20个，点击了输入内容才查询所有
  @socialTagStore.State('tags') readonly tags: TagVO []

  current = 0

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

  switchCircleTabValue (event: { detail: any }) {
    const detail: {
      current: number,
      source: number
    } = event.detail
    this.current = detail.current
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
