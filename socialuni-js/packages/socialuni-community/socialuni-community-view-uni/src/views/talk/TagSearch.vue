<template>
  <div class="h100p flex-col">
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
    <div v-if="searchContent">
      <div v-if="searchContent&&isAdd" class="article-row solid-bottom color-blue" @click="addTag">
        未找到相关标签，可点击
        <q-icon icon="arrow_right_alt"></q-icon>
        此处去创建
      </div>
      <div v-for="tag in showTags" :key="tag.id" @click="change(tag)"
            class="article-row row-between solid-bottom">
        <span>
          #{{ tag.name }}
        </span>
        <div v-if="tag.count" class="row-col-center">
          <q-icon addClass="color-red" icon="mdi-fire"></q-icon>
          {{ tag.count }}
        </div>
      </div>
    </div>
    <div v-else class="flex-col flex-1 overflow-hidden">
      <div class="q-box solid-bottom">
        <div class="flex-row">
          <span class="cuIcon-title text-green"></span>
          <span class="text-md font-bold">历史话题</span>
        </div>
        <div class="row-wrap">
          <template v-if="historyTags.length">
            <div v-for="tag in historyTags"
                  class="ml-xs mt-sm q-tag-theme lg round"
                  :key="tag.id"
                  @click="change(tag)">
              #{{ tag.name }}
            </div>
          </template>
          <div v-else class="pl-sm mt-sm text-md text-gray">
            空
          </div>
        </div>
      </div>

      <q-sidebar :dataList="tagTypes" class="flex-1 flex-row overflow-hidden" :right-scroll="false">
        <template #leftRow="{item,index,current}">
          <div class="q-sidebar-item" :class="{'q-sidebar-item-active':index === current}">
            <div class="row-all-center flex-1">
              <span class="uni-ellipsis">{{ item.name }}</span>
            </div>
          </div>
        </template>
        <template #rightRow="{item}">
          <div class="bg-white">
            <div class="q-box-row">
              <span class="cuIcon-title text-green margin-right-xs"></span>
              <span class="font-bold">{{ item.name }}</span>
            </div>

            <div>
              <q-row-item :border="true" v-for="tag in item.tags" :key="tag.id" @click="change(tag)">
                <div class="row-col-center can-click" @click.stop="change(tag)">
                  <img class="cu-avatar radius lg flex-none"
                         :src="tag.avatar"
                  />
                  <div class="ml-sm overflow-hidden">
                    <div>
                      {{ tag.name }}
                    </div>
                    <div class="text-gray text-sm text-ellipsis">
                      帖子：{{ tag.count }}
                    </div>
                  </div>
                </div>
                <q-icon icon="arrow-right" class="text-md margin-right-sm"></q-icon>
              </q-row-item>
            </div>
          </div>
        </template>
      </q-sidebar>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch, toNative} from 'vue-facing-decorator'

import QRowItem from 'qing-ui/src/components/QRowItem.vue'
import QIcon from 'qing-ui/src/components/QIcon.vue'
import QNavbar from 'qing-ui-uni/src/components/QNavbar/QNavbar.vue'
import QSearch from 'qing-ui-uni/src/components/QSearch/QSearch.vue'
import QSidebar from 'qing-ui-uni/src/components/QSidebar/QSidebar.vue'
import TagVO from "socialuni-api-base/src/model/community/tag/TagVO";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import TagUtil from "socialuni-community-sdk/src/util/TagUtil";


@toNative
@Component({
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
    return socialuniTagModule.tagTypes
  }

  get tags() {
    return socialuniTagModule.tags
  }

  mounted() {
    socialuniTagModule.getTagTypesAction()
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

  @Emit('update:modelValue')
  input() {
    this.clearSearchContent()
    return false
  }

  @Emit('update:modelValue')
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
