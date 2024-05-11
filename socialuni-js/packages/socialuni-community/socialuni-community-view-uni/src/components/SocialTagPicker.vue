<template>
  <q-popup ref="dialog" bottom hide-modal hide-confirm>
    <template #headerLeft>
      <q-input class="w100p ml-sm bd-round" v-model="searchText"></q-input>
    </template>
    <div class="h80vh">
      <scroll-view class="h100p" scroll-y v-if="searchText">
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
      </scroll-view>
      <q-sidebar v-else :dataList="tagTypes" class="h100p flex-row overflow-hidden"
                 :right-scroll="false">
        <template #leftRow="{item,index,current}">
          <div class="q-sidebar-item" :class="{'q-sidebar-item-active':index === current}">
            <div class="row-all-center flex-1">
              <span class="uni-ellipsis">{{ item.name }}</span>
            </div>
          </div>
        </template>
        <template #rightRow="{item}">
          <div class="mx-sm mt-sm bg-white">
            <div class="q-box-row mb-xs">
              <span class="cuIcon-title text-green margin-right-xs"></span>
              <span class="font-bold font-md">{{ item.name }}</span>
            </div>

            <div v-if="item.tags" class="row-wrap overflow-hidden">
              <div v-for="(tag,index) in item.tags"
                   class="col-all-center ml-sm overflow-hidden mb-sm bg-click" @click="change(tag)">
                <img class="bd-round size50" :src="tag.avatar"/>
                <div class="font-cut">{{ tag.name }}</div>
                <div class="font-cut">{{ tag.count }}</div>
              </div>
            </div>
          </div>
        </template>
      </q-sidebar>
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Component, Emit, Vue, toNative} from 'vue-facing-decorator'
import QIcon from "qing-ui/src/components/QIcon.vue";
import QInput from "qing-ui/src/components/QInput.vue";
import QSidebar from "qing-ui-uni/src/components/QSidebar/QSidebar.vue";
import QPopup from "qing-ui/src/components/QPopup.vue";
import SocialCircleRO from "socialuni-api-base/src/model/community/circle/SocialCircleRO";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";
import TagTypeVO from "socialuni-api-base/src/model/community/tag/TagTypeVO";


@toNative
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

  get tagTypes(): TagTypeVO[] {
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

  @Emit('update:modelValue')
  change(tag: SocialCircleRO) {
    this.close()
    return tag
  }
}
</script>
