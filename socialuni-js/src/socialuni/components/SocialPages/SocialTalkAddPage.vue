<template>
  <view class="h100p bg-white">
    <view v-if="showTagSearch">
      <talk-add-tag-search :tags="tags" :is-add="true" :select-tags="selectTags" @change="changeTag"
                           @close="closeTagSearch"
                           @delete-tag="deleteTag" @add-tag="showAddTag">
      </talk-add-tag-search>
    </view>

    <view v-if="showTagAdd">
      <social-tag-add @change="addTagCheckTag" @close="closeTagAddVue"></social-tag-add>
    </view>
    <view v-show="!showCityDialog&&!showTagSearch&&!showTagAdd">
      <q-navbar show-back>
        <div class="w100p row-col-center">
          <view class="font-bold text-md flex-1">
            发布动态
          </view>
          <q-button class="text-bold" theme :disabled="buttonDisabled" @click="addTalk">发布</q-button>
        </div>
      </q-navbar>
      <city-picker v-model="showCityDialog" :district="district" @confirm="cityChange"></city-picker>
      <view class="px-smm py-sm">
        <textarea class="h140 w100p" :maxlength="200"
                  placeholder="分享记录生活、交朋友、想说啥就说啥，不用再顾虑别人的看法了，放飞自己，享受自由吧！禁止发布违法乱纪、涉污涉黄、暴露不雅、广告内容，发布违规内容会影响用户在社交软件联盟中的信用评级！"
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
                      class="close-view position-absolute z-index-button color-content size20 bg-grey8 bg-half-transparent row-all-center topRight bd-bl-radius"
                      @click="deleteImg(index)">×
                    </view>
                    <image class="uni-uploader__img" mode="aspectFill" :src="image"
                           :data-src="image" @click="previewImage"/>
                  </view>
                </block>
                <view class="uni-uploader__input-box" v-show="showImgFiles.length < 3">
                  <view class="uni-uploader__input" @click="chooseImage"/>
                </view>
              </view>
            </view>
          </view>
        </view>
      </view>

      <view class="px-sm pt-sm mt-xs row-between">
        <q-city-info v-model="district" picker></q-city-info>
        <div class="row-between-center bg-click" @click="openCircleSearchDialog">
          <div>{{ circleName || '选择圈子' }}</div>
          <q-icon icon="arrow-right" class="text-md margin-right-sm" size="12"></q-icon>
        </div>
      </view>
      <view class="px-sm pt-sm">
        <view v-if="!selectTags.length" class="row-grid pb-10">
          <view class="q-tag bd-round bg-pink-plain">
            <text @click="openTagSearchVue(false)">#添加话题</text>
          </view>
        </view>
        <view v-else class="row-grid">
          <view v-for="tag in selectTags" :key="tag.id" class="pb-10 px-mn">
            <view class="q-tag bd-round bg-pink-plain">
              <text @click="openTagSearchVue(false)">#{{ tag.name }}</text>
              <q-icon v-if="tag.id" class="ml-5" icon="close-circle-fill" size="16"
                      @click.native.stop="deleteTag(tag)"/>
            </view>
          </view>
        </view>
        <view class="pb-sm">
          热门话题：
        </view>
        <view class="row-wrap">
          <view v-for="tag in unSelectTags" :key="tag.id" class="pb-10 px-mn">
            <view class="q-tag bd-round bg-pink-plain" @click="checkTag(tag)">
              #{{ tag.name }}
            </view>
          </view>
          <view class="row-all-center pb-10 px-mn">
            <view class="q-tag bd-round bg-orange-plain" @click="openTagSearchVue(false)">
              更多
              <q-icon icon="arrow-right" size="12" class="ml-5"/>
            </view>
          </view>
        </view>
      </view>

      <view class="row-col-center pd-sm">
        <div class="flex-row text-md">
          可见范围：
          <view class="text-gray row-all-center bg-grey10 w100 mr-xs px-xs" @click="showVisibleTypeSelect=true">
            <text class="text-md text-gray mr-xs">{{ visibleType.label }}</text>
            <q-icon size="14" class="text-gray" icon="arrow-down"/>
            <!--            <u-select v-model="showVisibleTypeSelect" mode="single-column" :list="visibleTypes"
                                  :default-value="visibleTypeValueIndex"
                                  @confirm="selectVisibleTypeChange"></u-select>-->
          </view>
          <!--          {{GenderType.all}}&#45;&#45;{{appGenderType}}&#45;&#45;{{GenderType.all === appGenderType}}-->
          <!--          只有不为单性app才显示-->
          <template v-if="GenderTypeAll === appGenderType">
            <div>，</div>
            <view class="text-gray row-all-center bg-grey10 w100 mx-xs px-xs" @click="showVisibleGenderSelect=true">
              <text class="text-md text-gray mr-xs">{{ visibleGender.label }}</text>
              <q-icon size="14" class="text-gray" icon="arrow-down"/>
              <!--              <u-select v-model="showVisibleGenderSelect" mode="single-column" :list="visibleGenders"
                                      :default-value="visibleGenderValueIndex"
                                      @confirm="selectVisibleGenderChange"></u-select>

                            <u-picker :show="show" :columns="visibleGenders"></u-picker>-->
            </view>
          </template>
          <div class="ml-xs">可见</div>
        </div>
      </view>

      <social-circle-picker ref="circleSearch" @change="circleChange"></social-circle-picker>
    </view>
  </view>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-property-decorator'
import TalkAPI from '../../api/TalkAPI'
import UniUtil from '../../utils/UniUtil'
import DistrictVO from '../../model/DistrictVO'
import JsonUtils from '../../utils/ObjectUtil'
import TagVO from '../../model/community/tag/TagVO'
import CosUtil from '../../utils/CosUtil'
import {
  socialCircleModule,
  socialLocationModule,
  socialLocationStore,
  socialTagModule,
  socialTagStore,
  socialTalkModule,
  socialUserStore
} from '../../store'
import PlatformUtils from '../../utils/PlatformUtils'
import CenterUserDetailRO from '../../model/social/CenterUserDetailRO'
import QIcon from '../../../qing-ui/components/QIcon/QIcon.vue'
import CityPicker from '../QCityPicker/QCityPicker.vue'
import TalkAddTagSearch from '../SocialTagAdd/TalkAddTagSearch.vue'
import AlertUtil from '../../utils/AlertUtil'
import LocationUtil from '../../utils/LocationUtil'
import VisibleType from '../../constant/VisibleType'
import EnumStrVO from '../../constant/EnumStrVO'
import ToastUtil from '../../utils/ToastUtil'
import QNavbar from '../../../qing-ui/components/QNavbar/QNavbar.vue'
import QButton from '../../../qing-ui/components/QButton/QButton.vue'
import PageUtil from '../../utils/PageUtil'
import GenderType from '../../constant/GenderType'
import SocialuniConfig from '../../config/SocialuniConfig'
import DomFile from '../../model/DomFile'
import CosAuthRO from '../../model/cos/CosAuthRO'
import AppUtilAPI from '../../api/AppUtilAPI'
import RouterUtil from '../../utils/RouterUtil'
import PagePath from '../../constant/PagePath'
import SocialTagAdd from '../SocialTagAdd/SocialTagAdd.vue'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QSidebar from '@/qing-ui/components/QSidebar/QSidebar.vue'
import TagTypeVO from '@/socialuni/model/community/tag/TagTypeVO'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import SocialCirclePicker from '@/socialuni/components/SocialCirclePicker.vue'
import AppMsg from '@/socialuni/constant/AppMsg'
import QCityInfo from '@/socialuni/components/QCityInfo/QCityInfo.vue'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import CosUploadResult from '@/socialuni/model/cos/CosUploadResult'
import TencentCosAPI from '@/api/TencentCosAPI'
import MsgUtil from '@/socialuni/utils/MsgUtil'
import ModelContentCheckUtil from '@/socialuni/utils/ModelContentCheckUtil'

@Component({
  components: {
    SocialCirclePicker,
    QCityInfo,
    QInput,
    QSidebar,
    QPopup,
    SocialTagAdd,
    QButton,
    QNavbar,
    TalkAddTagSearch,
    CityPicker,
    QIcon
  }
})
export default class SocialTalkAddPage extends Vue {
  $refs: {
    circleSearch: SocialCirclePicker
  }

  @socialLocationStore.State('districts') readonly districts: DistrictVO[]
  @socialTagStore.State('tags') readonly storeTags: TagVO []
  @socialUserStore.State('user') readonly user: CenterUserDetailRO
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]
  // @socialTalkStore.State('circleName') circleName: string

  circleSearchText = ''

  showVisibleTypeSelect = false
  showVisibleGenderSelect = false

  //默认全网
  visibleTypeValue = VisibleType.fullNetwork.value
  //默认为软件的性别
  visibleGenderValue = SocialuniConfig.appGenderType
  appGenderType = SocialuniConfig.appGenderType
  GenderTypeAll = GenderType.all

  talkContent = ''
  circleName = ''
  buttonDisabled = false

  visibleTypes = VisibleType.enums

  district: DistrictVO = socialLocationModule.cityLocation
  showImgFiles: DomFile [] = []
  tags: TagVO [] = []
  imgMaxSize = 3
  showCityDialog = false
  showTagSearch = false
  showTagAdd = false

  cosAuthRO: CosAuthRO = null

  openCircleSearchDialog () {
    this.$refs.circleSearch.openDialog()
  }

  //根据用户性别显示不同内容
  get visibleGenders () {
    //如果已登录
    if (this.user && this.user.gender === GenderType.girl) {
      return GenderType.talkAddGirlEnums
    }
    return GenderType.talkAddBoyEnums
  }

  get visibleType () {
    return this.visibleTypes.find(item => item.value === this.visibleTypeValue)
  }

  get visibleGender () {
    return this.visibleGenders.find(item => item.value === this.visibleGenderValue)
  }

  get visibleTypeValueIndex () {
    return [this.visibleTypes.findIndex(item => item.value === this.visibleType.value)]
  }

  get visibleGenderValueIndex () {
    return [this.visibleGenders.findIndex(item => item.value === this.visibleGender.value)]
  }

  //进入talk页面，需要加载下当前地理位置，发布时携带
  created () {
    this.cosAuthRO = null
    this.showImgFiles = []
    this.tags = JsonUtils.deepClone(this.storeTags)
    this.circleName = socialCircleModule.circleName
    this.district = socialLocationModule.cityLocation

    socialTagModule.getTagTypesAction()

    //默认获取当前位置，可以修改
    //发布时获取下没问题，不应该使用筛选条件的，使用webapi获取大概位置，不需要用户授权的
    LocationUtil.getLocationNotAuth().then((district: DistrictVO) => {
      this.district = district
    })
  }

  selectVisibleTypeChange (visibleTypes: EnumStrVO[]) {
    this.visibleTypeValue = visibleTypes[0].value
  }

  selectVisibleGenderChange (visibleGenders: EnumStrVO[]) {
    this.visibleGenderValue = visibleGenders[0].value
  }

  onReady () {
    // this.showSearch = true
  }

  onUnload () {
    this.talkContent = ''
    this.showImgFiles = []
  }

  get showImgUrls () {
    return this.showImgFiles.map((item: DomFile) => item.path)
  }

  openTagSearchVue (query: boolean) {
    if (query || this.tags.length < 11) {
      socialTagModule.getTagsAction()
    }
    this.closeTagAddVue()
    this.showTagSearch = true
  }

  get selectTags (): TagVO[] {
    const selectTags = this.tags.filter(item => item.selected)
    if (selectTags.length > 0) {
      return selectTags
    }
    return []
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
      AlertUtil.hint('最多选择5个话题')
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

  get selectTagNames () {
    return this.selectTags.map(item => item.name)
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

  openCityDialog () {
    // 如果第二个没有子节点且或者子节点为0
    if (!this.districts[1].childs || !this.districts[1].childs.length) {
      socialLocationModule.getDistrictsAction()
    }
    this.showCityDialog = true
  }

  cityChange (district: DistrictVO) {
    // 如果没使用定位，则使用之前的定位
    this.district = district
  }

  addTalk () {
    if (!this.user.identityAuth) {
      ModelContentCheckUtil.hasUn18Content(this.talkContent)
      for (const showImgFile of this.showImgFiles) {
        if (showImgFile.needAuth) {
          MsgUtil.uploadImgNeedAuthMsg()
          return
        }
      }
    }
    this.buttonDisabled = true
    if (this.talkContent || this.showImgFiles.length) {
      if (this.talkContent && this.talkContent.length > 200) {
        return AlertUtil.hint('动态最多支持200个字，请精简动态内容')
      }
      this.addTalkHandler()
      // 申请订阅
      PlatformUtils.requestSubscribeTalk()
    } else {
      ToastUtil.toastLong('不能发布文字和图片均为空的动态')
      this.buttonDisabled = false
    }
  }


  async addTalkHandler () {
    uni.showLoading({ title: '发布中' })
    this.publishTalk()
  }

  async publishTalk () {
    try {
      await TalkAPI.addTalkAPI(this.talkContent, this.showImgFiles, this.district, this.visibleTypeValue, this.visibleGenderValue, this.circleName, this.selectTagNames)
      if (socialTalkModule.curTabIsCircle) {
        //设置当前圈子，暂时不联动外面，等以后内容多了再联动外面
        socialTalkModule.setCircleNameUpdateCurTabIndex(this.circleName)
      } else {
        socialCircleModule.setCircleName(this.circleName)
      }
      RouterUtil.reLaunch(PagePath.talk + '?load=true')
    } finally {
      this.buttonDisabled = false
      uni.hideLoading()
      this.cosAuthRO = null
    }
  }

  async uploadImgList () {
    //设置图片路径，必须在这里设置，如果放到获取的地方，可能多次上传，就不行了
    this.showImgFiles.forEach(item => {
      //只有不包含，才赋值src，有值代表已经赋值过了
      if (item.src.indexOf('https') < 0) {
        item.src = this.cosAuthRO.uploadImgPath + 'talk/' + item.src
      }
      // item.src = ImgUtil.imgUrl + item.cosSrc
    })
    const uploadRes: CosUploadResult[] = await CosUtil.postImgList(this.showImgFiles, this.cosAuthRO)
    if (!this.user.identityAuth) {
      for (const item of uploadRes) {
        const index = uploadRes.findIndex(uploadResItem => uploadResItem === item)
        const res: string = await TencentCosAPI.getImgTagAPI(item.Location, this.showImgFiles[index].src, this.cosAuthRO) as any
        console.log('包含人物：' + res.includes('人'), res)
        if (res.includes('人')) {
          this.showImgFiles[index].needAuth = true
          MsgUtil.uploadImgNeedAuthMsg()
        }
      }
    }
  }

  deleteImg (e) {
    this.showImgFiles.splice(e, 1)
  }

  /**
   * 图片前台压缩，往后台传一个压缩后的可看清的图，然后后台弄出来一个压缩图，
   */
  async chooseImage () {
    if (this.showImgFiles.length >= this.imgMaxSize) {
      const isContinue = await this.isFullImg()
      console.log('是否继续?', isContinue)
      if (!isContinue) {
        return
      }
    }
    const count = this.imgMaxSize - this.showImgFiles.length
    const imgFiles: DomFile[] = await UniUtil.chooseImage(count)
    this.showImgFiles.push(...imgFiles)
    //获取cos认证信息
    this.cosAuthRO = await CosUtil.getCosAuthRO()
    if (this.cosAuthRO) {
      await this.uploadImgList()
    } else {
      AppUtilAPI.sendErrorLogAPI(null, '用户发表动态失败，未获取上传图片所需要的认证信息')
      AlertUtil.error(AppMsg.uploadFailMsg)
    }
  }

  isFullImg () {
    return new Promise((resolve) => {
      uni.showModal({
        content: '已经有' + this.imgMaxSize + '张图片了,是否清空现有图片？',
        success: (e) => {
          if (e.confirm) {
            this.showImgFiles = []
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

  goBackPage () {
    if (this.showTagSearch) {
      this.closeTagSearch()
    } else if (this.showTagAdd) {
      this.closeTagAddVue()
      this.openTagSearchVue(false)
    } else {
      PageUtil.toTalkPage()
    }
  }

  circleChange (circle: SocialCircleRO) {
    this.circleName = circle.name
  }
}
</script>
