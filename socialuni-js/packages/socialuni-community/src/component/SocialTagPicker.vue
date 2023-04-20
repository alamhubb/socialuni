<template>
  <q-popup ref="dialog" bottom hide-modal hide-confirm>
    <template #headerLeft>
      <q-input class="w100p ml-sm bd-round" v-model="searchText"></q-input>
    </template>
    <div class="h80vh">
      <scroll-view class="h100p" scroll-y v-if="searchText">
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
      </scroll-view>
      <q-sidebar v-else :dataList="tagTypes" class="h100p flex-row overflow-hidden"
                 :right-scroll="false">
        <template #leftRow="{item,index,current}">
          <view class="q-sidebar-item" :class="{'q-sidebar-item-active':index === current}">
            <view class="row-all-center flex-1">
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
import {Options, Emit, Vue} from 'vue-property-decorator'
import QIcon from "socialuni-ui/src/components/QIcon/QIcon.vue";
import QInput from "socialuni-ui/src/components/QInput/QInput.vue";
import QSidebar from "socialuni-ui/src/components/QSidebar/QSidebar.vue";
import QPopup from "socialuni-ui/src/components/QPopup/QPopup.vue";
import SocialCircleRO from "socialuni/src/model/community/circle/SocialCircleRO";


@Options({
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

  get tagTypes() {
    return socialuniTagModule.tagTypes
  }

  get showTags() {
    //去除热门，要不然会存在重复
    return this.tagTypes.slice(1, this.tagTypes.length - 1)
        .flatMap(item => {
          if (item && item.tags) {
            return item.tags.filter(tag => tag.name.includes(this.searchText))
          }
          return []
        })
  }

  searchText = ''

  open() {
    this.searchText = ''
    socialuniTagModule.getTagTypesAction()
    this.$refs.dialog.open()
  }

  close() {
    this.$refs.dialog.close()
  }

  @Emit()
  change(tag: SocialCircleRO) {
    this.close()
    return tag
  }
}
</script>
