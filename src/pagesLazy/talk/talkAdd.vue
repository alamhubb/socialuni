<template>
  <view class="h100p bg-white">
    <city-picker v-model="showSearch" :district="district" @confirm="cityChange"></city-picker>

    <view v-if="showTagSearch">
      <talk-add-tag-search :tags="tags" :is-add="true" :select-tags="selectTags" @change="changeTag"
                           @close="closeTagSearch"
                           @delete-tag="deleteTag" @add-tag="showAddTag">
      </talk-add-tag-search>
    </view>

    <view v-if="showTagAdd">
      <tag-add @change="addTagCheckTag" @close="closeTagAddVue"/>
    </view>
    <view v-show="!showSearch&&!showTagSearch&&!showTagAdd">
      <view class="px-smm py-sm">
        <textarea class="h140px w100p" :maxlength="200"
                  placeholder="这是专属女生的小世界，和集美们畅所欲言吧！"
                  v-model.trim="talkContent"
                  :show-confirm-bar="false"
        />
      </view>
      <!--      禁止发布违法乱纪、涉污涉黄、暴露不雅、广告内容，发布违规内容会影响用户在社交软件联盟中的信用评级-->

      <view class="uni-list list-pd pb-xs">
        <view class="uni-list-cell cell-pd">
          <view class="uni-uploader">
            <view class="uni-uploader-body">
              <view class="uni-uploader__files">
                <block v-for="(image,index) in showImgUrls" :key="index">
                  <view class="uni-uploader__file position-relative">
                    <view
                      class="close-view position-absolute z-index-button text-black size20 bg-gray bg-half-transparent row-all-center topRight bd-bl-radius"
                      @click="deleteImg(index)">×
                    </view>
                    <image class="uni-uploader__img" mode="aspectFill" :src="image"
                           :data-src="image" @click="previewImage"/>
                  </view>
                </block>
                <view class="uni-uploader__input-box" v-show="showsImgSrcs.length < 3">
                  <view class="uni-uploader__input" @click="chooseImage"/>
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>
      <view class="px-sm pt-sm mt-xs row-between">
        <view v-if="district" class="q-tag q-round bg-orange-plain" @click="openSearchVue">
          <q-icon v-if="district.isLocation || !district.adCode" icon="map-fill"/>
          <block v-if="district.adCode">
            {{ district.provinceName }}
            <text v-if="district.cityName">
              -{{ district.cityName }}
            </text>
            <text v-if="district.districtName">
              -{{ district.districtName }}
            </text>
          </block>
          <text v-else>
            {{ district.adName }}
          </text>
        </view>
        <view class="col-center">
          图片数量：{{ showsImgSrcs.length }}/{{ imgMaxSize }}
        </view>
      </view>
      <view class="px-sm pt-sm">
        <view class="pb-sm">
          已选话题：
        </view>
        <view class="grid">
          <view v-for="tag in selectTags" :key="tag.id" class="row-center pb-10px px-mn">
            <view class="q-tag q-round bg-pink-plain">
              <text @click="openTagSearchVue(false)">#{{ tag.name }}</text>
              <q-icon v-if="tag.id" class="ml-5px" icon="close-circle-fill" size="16"
                      @click.native.stop="deleteTag(tag)"/>
            </view>
          </view>
        </view>
        <view class="pb-sm">
          热门话题：
        </view>
        <view class="row-wrap">
          <view v-for="tag in unSelectTags" :key="tag.id" class="row-center pb-10px px-mn">
            <view class="q-tag q-round bg-pink-plain" @click="checkTag(tag)">
              #{{ tag.name }}
            </view>
          </view>
          <view class="row-all-center pb-10px px-mn">
            <view class="q-tag q-round bg-orange-plain" @click="openTagSearchVue(false)">
              更多
              <q-icon icon="arrow-right" class="ml-5px"/>
            </view>
          </view>
        </view>
      </view>

      <view class="row-col-center pa-sm">
        <div class="flex-row" @click="showVisibleRangeSelect=true">
          可见范围：
          <view class="text-gray pr-xs">
            <text class="font-mdg text-gray font-mdg">{{ visibleType.label }}</text>
            <q-icon size="14" class="text-gray" icon="arrow-right"/>
          </view>
          <u-select v-model="showVisibleRangeSelect" mode="single-column" :list="visibleTypes"
                    @confirm="selectVisibleTypeChange"></u-select>
        </div>
      </view>
      <view class="row-center pt">
        <button class="cu-btn lg bg-pink-light2 w300px" :disabled="buttonDisabled" @click="addTalk">发布</button>
      </view>
    </view>
  </view>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import TalkAPI from '@/api/TalkAPI'
import PagePath from '@/const/PagePath'
import UniUtil from '@/plugins/uni/UniUtil'
import DistrictVO from '@/model/DistrictVO'
import { namespace } from 'vuex-class'
import JsonUtils from '@/utils/JsonUtil'
import TagVO from '@/model/tag/TagVO'
import TagUtil from '@/utils/TagUtil'
import ImgUtil from '@/utils/ImgUtil'
import ImgFileVO from '@/model/ImgFileVO'
import CosUtil from '@/utils/CosUtil'
import { locationModule, tagModule } from '@/plugins/store'
import PlatformUtils from '@/utils/PlatformUtils'
import UserVO from '@/model/user/UserVO'
import QIcon from '@/components/q-icon/q-icon.vue'
import CityPicker from '@/components/CityPicker.vue'
import TalkAddTagSearch from '@/pagesLazy/talk/TalkAddTagSearch.vue'
import TagAdd from '@/pages/tag/TagAdd.vue'
import RouterUtil from '@/utils/RouterUtil'
import Alert from '../../utils/Alert'
import LocationUtil from '@/utils/LocationUtil'
import ContentVisibleType from '@/const/ContentVisibleType'
import EnumStrVO from '@/const/EnumStrVO'
import Toast from '@/utils/Toast'

const userStore = namespace('user')
const tagStore = namespace('tag')
const locationStore = namespace('location')
@Component({
  components: {
    TagAdd,
    TalkAddTagSearch,
    CityPicker,
    QIcon
  }
})
export default class TalkAddVue extends Vue {
  @locationStore.State('districts') readonly districts: DistrictVO[]
  @tagStore.State('tags') readonly storeTags: TagVO []
  @userStore.State('user') readonly user: UserVO

  showVisibleRangeSelect = false
  visibleType = ContentVisibleType.smartRecommend
  talkContent = ''
  buttonDisabled = false

  visibleTypes = ContentVisibleType.Values

  district: DistrictVO = locationModule.location
  showsImgSrcs: ImgFileVO [] = []
  tags: TagVO [] = []
  imgMaxSize = 3
  showSearch = false
  showTagSearch = false
  showTagAdd = false

  //进入talk页面，需要加载下当前地理位置，发布时携带
  onLoad () {
    this.tags = JsonUtils.deepClone(this.storeTags)
    this.district = locationModule.location

    //默认获取当前位置，可以修改
    //发布时获取下没问题，不应该使用筛选条件的，使用webapi获取大概位置，不需要用户授权的
    LocationUtil.getLocationNotAuth().then((district: DistrictVO) => {
      this.district = district
    })
  }

  selectVisibleTypeChange (visibleTypes: EnumStrVO[]) {
    this.visibleType = visibleTypes[0]
  }

  onReady () {
    // this.showSearch = true
  }

  onUnload () {
    this.talkContent = ''
    this.showsImgSrcs = []
  }

  get showImgUrls () {
    return this.showsImgSrcs.map((item: any) => item.path)
  }

  openTagSearchVue (query: boolean) {
    if (query || this.tags.length < 11) {
      tagModule.getTagsAction()
    }
    this.showTagSearch = true
  }

  get selectTags (): TagVO[] {
    const selectTags = this.tags.filter(item => item.selected)
    if (selectTags.length > 0) {
      return selectTags
    } else {
      return TagUtil.initTagAry
    }
  }

  @Watch('storeTags')
  watchStoreHotTagsChange () {
    const tags = JsonUtils.deepClone(this.storeTags)
    this.selectTags.forEach(item => {
      const tag: TagVO = tags.find(tag => item.id === tag.id)
      if (tag) {
        tag.selected = true
      }
    })
    this.tags = tags
  }

  get unSelectTags (): TagVO[] {
    return this.tags.filter((item, index) => !item.selected && index < 7)
  }

  addTagCheckTag (tag: TagVO) {
    this.closeTagAddVue()
    this.checkTag(tag)
    this.openTagSearchVue(true)
  }

  closeTagAddVue () {
    this.showTagAdd = false
  }

  checkTag (tag: TagVO) {
    if (this.selectTags.length > 4) {
      // todo 后台还没有校验
      Alert.info('最多选择5个话题')
      return
    }
    let tagInTags: TagVO = this.tags.find(item => item.id === tag.id)
    if (!tagInTags) {
      this.storeTags.push(tag)
      this.tags.push(tag)
      tagInTags = this.tags.find(item => item.id === tag.id)
    }
    tagInTags.selected = true
  }

  get selectTagIds () {
    return this.selectTags.map(item => item.id)
  }

  changeTag (tag: TagVO) {
    this.checkTag(tag)
    this.closeTagSearch()
  }

  deleteTag (tag: TagVO) {
    const tagInTags: TagVO = this.selectTags.find(item => item.id === tag.id)
    tagInTags.selected = false
  }

  closeTagSearch () {
    this.showTagSearch = false
  }

  showAddTag () {
    this.closeTagSearch()
    this.showTagAdd = true
  }

  openSearchVue () {
    // 如果第二个没有子节点且或者子节点为0
    if (!this.districts[1].childs || !this.districts[1].childs.length) {
      locationModule.getDistrictsAction()
    }
    this.showSearch = true
  }

  cityChange (district: DistrictVO) {
    // 如果没使用定位，则使用之前的定位
    this.district = district
  }

  addTalk () {
    this.buttonDisabled = true
    if (this.talkContent || this.showsImgSrcs.length) {
      if (this.talkContent && this.talkContent.length > 200) {
        return Alert.hint('动态最多支持200个字，请精简动态内容')
      }
      this.addTalkHandler()
      // 申请订阅
      PlatformUtils.requestSubscribeTalk()
    } else {
      Toast.toastLong('不能发布文字和图片均为空的动态')
      this.buttonDisabled = false
    }
  }

  addTalkHandler () {
    uni.showLoading({ title: '发布中' })
    if (this.showsImgSrcs.length === 0) {
      this.publishTalk()
    } else {
      this.uploadImgList()
      this.publishTalk()
    }
  }

  publishTalk () {
    TalkAPI.addTalkAPI(this.talkContent, this.showsImgSrcs, this.district, this.selectTagIds, this.visibleType.value)
      .then(() => {
        this.buttonDisabled = false
        uni.hideLoading()
        RouterUtil.reLaunch(PagePath.talk + '?load=true')
      })
      .catch(() => {
        this.buttonDisabled = false
        uni.hideLoading()
      })
  }


  uploadImgList () {
    CosUtil.postObjectList(this.showsImgSrcs, this.user.id)
  }

  deleteImg (e) {
    this.showsImgSrcs.splice(e, 1)
  }

  /**
   * 图片前台压缩，往后台传一个压缩后的可看清的图，然后后台弄出来一个压缩图，
   */
  async chooseImage () {
    if (this.showsImgSrcs.length >= this.imgMaxSize) {
      const isContinue = await this.isFullImg()
      console.log('是否继续?', isContinue)
      if (!isContinue) {
        return
      }
    }
    const count = this.imgMaxSize - this.showsImgSrcs.length
    UniUtil.chooseImage(count).then(imgFiles => {
      for (const imgFile of imgFiles) {
        //前台记录用户上传的图片，点击发布的时候才保存到后台
        imgFile.src = ImgUtil.getTalkUploadFormat(this.user.id, imgFile.path)
        this.showsImgSrcs.push(imgFile)
      }
    })
  }

  isFullImg () {
    return new Promise((resolve) => {
      uni.showModal({
        content: '已经有' + this.imgMaxSize + '张图片了,是否清空现有图片？',
        success: (e) => {
          if (e.confirm) {
            this.showsImgSrcs = []
            resolve(true)
          } else {
            resolve(false)
          }
        },
        fail: () => {
          resolve(false)
        }
      })
    })
  }

  previewImage (e) {
    const current = e.target.dataset.src
    uni.previewImage({
      current: current,
      urls: this.showImgUrls
    })
  }
}
</script>
