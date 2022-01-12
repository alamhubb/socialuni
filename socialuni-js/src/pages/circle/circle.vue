<template>
  <div class="h100p">
    <div v-if="pageType === TalkSearchPageType.talkSearch" class="h100p flex-col">
      <q-navbar>
        <q-search class="flex-1 mx-sm bg-default">
          <q-icon class="mx-xs text-gray flex-none" icon="search" size="16"></q-icon>
          <input class="flex-1" v-model="searchContent" :adjust-position="false" type="text" @focus="showSearchView"
                 focus
                 placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
          <q-icon v-if="searchContent" class="mr-sm text-gray row-all-center flex-none" icon="close" size="16"
                  @click="clearSearchContent"
          ></q-icon>
        </q-search>
        <div @click="input" class="flex-none mr-sm">取消</div>
      </q-navbar>

      <div v-if="searchContent">
        <div>
          <div>圈子</div>
          <div></div>
        </div>
        <div>
          <div>话题</div>
          <div></div>
        </div>
      </div>
      <!--    搜索的时候就是一个 圈子，一个话题-->

      <div v-else class="flex-col flex-1 overflow-hidden">
        <div class="box-sm">
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
        </div>

        <div class="box-sm">
          <div>
            <text class="cuIcon-title"></text>
            <text class="font-md font-bold">圈子</text>
          </div>
          <div>
            <div class="row-col-center mb-sm">
              <q-tabs v-model="circleCurrent" :tabs="circleTypes"
                      class="bd-radius pd-mn flex-1">
                <template #default="{tab}">
                  <div class="h30 px-xs row-all-center font-md">
                    {{ tab.name }}
                    <!--            费劲啊实力哈哈-->
                  </div>
                </template>
              </q-tabs>
              <div class="row-col-center bg-click flex-none ml-sm font-md" @click="pageTypeToCircleSearch">更多
                <q-icon icon="mdi-chevron-right"></q-icon>
              </div>
            </div>

            <div>
              <swiper circular class="h90 bd-radius mb-sm" :current="circleCurrent" @change="switchCircleTabValue">
                <swiper-item class="bd-radius bg-white" v-for="circleType in circleTypes">
                  <div class="h100p flex-col flex-none bd-radius py-sm px-xs overflow-hidden">
                    <!--                        class="radius flex-none h100p"-->
                    <div class="row-nowrap overflow-hidden">
                      <div v-for="(circle,index) in circleType.circles" v-if="index<5"
                           class="col-row-center mx-xs overflow-hidden h70">
                        <img class="bd-round size50" :src="circle.avatar"/>
                        <div class="font-cut">{{ circle.name }}</div>
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
              <q-tabs v-model="tagCurrent" :tabs="tagTypes"
                      class="bd-radius pd-mn flex-1">
                <template #default="{tab}">
                  <div class="h30 px-xs row-all-center font-md">
                    {{ tab.name }}
                    <!--            费劲啊实力哈哈-->
                  </div>
                </template>
              </q-tabs>
              <div class="row-col-center bg-click flex-none ml-sm font-md">更多
                <q-icon icon="mdi-chevron-right"></q-icon>
              </div>
            </div>

            <div>
              <swiper circular class="h80 bd-radius mb-sm" :current="tagCurrent" @change="switchTagTabValue">
                <swiper-item class="bd-radius bg-white" v-for="tagType in tagTypes">
                  <div class="h100p flex-col flex-none bd-radius py-sm px-xs overflow-hidden">
                    <!--                        class="radius flex-none h100p"-->
                    <div class="row-grid overflow-hidden">
                      <div v-for="(tag,index) in tagType.tags"
                           class="col-row-center overflow-hidden flex-none mb-sm">
                        <div class="q-tag-theme" @click="change(tag)">{{ tag.name }}</div>
                      </div>
                    </div>
                  </div>
                </swiper-item>
              </swiper>
            </div>
          </div>
        </div>
      </div>
    </div>


    <circle-search v-else-if="pageType === TalkSearchPageType.circleSearch"
                   @input="pageTypeToTalkSearch">
    </circle-search>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'


import { socialCircleStore, socialTagStore } from '@/socialuni/store'
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
import TalkSearchPageType from '@/socialuni/const/TalkSearchPageType'
import CircleSearch from '@/socialuni/components/SCircleSearch.vue'


@Component({
  components: {
    CircleSearch,
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
export default class CirclePage extends Vue {
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

  TalkSearchPageType = TalkSearchPageType
  pageType = TalkSearchPageType.talkSearch

  historyTags: TagVO [] = TagUtil.getStorageHistoryTags()

  onLoad () {
  }

  pageTypeToCircleSearch () {
    console.log('触发了')
    console.log(this.pageType)
    this.pageType = TalkSearchPageType.circleSearch
    console.log(this.pageType)
  }

  pageTypeToTalkSearch () {
    this.pageType = TalkSearchPageType.talkSearch
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
      // this.showSearch = false
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
    // this.showSearch = true
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
