<template>
  <q-popup ref="filterDialog" bottom hide-modal @confirm="confirm">
    <div class="h80vh">
      <div class="h100p py-sm px" v-show="!showTagSearch">
        <div class="row-col-center mt-sm mb-sm">
          <div class="mr-sm">性别:</div>
          <radio-group @change="genderChange" class="flex-1">
            <div class="row-col-center row-wrap">
              <div v-for="report in visibleGenders" :key="report.value" class="mb-sm">
                <label>
                  <radio :value="report.value" :checked="report.value===genderTypeValue"></radio>
                  <text class="mr-sm">{{ report.label }}</text>
                </label>
              </div>
            </div>
          </radio-group>
          <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
        </div>
        <div class="row-col-center mb-sm">
          <div class="mr-sm">年龄: {{ rangeValue[0] }} - {{ rangeValue[1] }}</div>
          <q-slider
            class="flex-1"
            :value="rangeValue"
            :min="rangeMin"
            :max="rangMax"
            :bar-height="3"
            active-color="#FF6B00"
            :format="format"
            @change="handleRangeChange"
          ></q-slider>
          <!--        <q-input class="w50 mr-xs"></q-input>
                  岁 —
                  <q-input class="w50 ml-sm mr-xs"></q-input>
                  岁-->
          <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
        </div>
        <div class="row-col-center mb-sm">
          <div class="mr-sm">地区:</div>
          <q-city-info v-model="district" picker></q-city-info>
          <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
        </div>
        <div>
          <div class="row-between-center mb-sm">
            <div>圈子:</div>
            <div class="row-col-center bg-click" @click="openCircleDialog">
              <div>更多
                <q-icon icon="mdi-chevron-right"></q-icon>
              </div>
            </div>
          </div>
          <div class="row-wrap">
            <div v-for="item in mineCirclesTop10" class="flex-none bg-click mb-sm"
                 :class="selectCircleName===item?'q-tag-green-bd':'q-tag-white'" @click="checkCircleName(item)">
              {{ item || '不限' }}
            </div>
          </div>
        </div>
        <div>
          <div class="row-between-center mb-sm mt-sm">
            <div>话题:</div>
            <div class="row-col-center">
              <!--            <div class="mr-md row-col-center bg-click color-sub">
                            清空
                            <q-icon icon="mdi-close"></q-icon>
                          </div>-->
              <div class="row-col-center bg-click" @click="openTagSearchVue">更多
                <q-icon icon="mdi-chevron-right"></q-icon>
              </div>
            </div>
          </div>
          <div class="row-wrap">
            <div v-for="item in mineTagsTop10" class="flex-none bg-click mb-sm"
                 :class="(selectTagName===item)?'q-tag-green-bd':'q-tag-white'"
                 @click="changeTagName(item)">
              {{ item || '不限' }}
            </div>
          </div>
        </div>
      </div>

      <social-tag-picker ref="tagPicker" @change="changeTag"></social-tag-picker>

      <social-circle-picker ref="circleDialog" @change="circleChange"></social-circle-picker>
    </div>
  </q-popup>
</template>

<script lang="ts">
import { Component, Emit, Vue, Watch } from 'vue-property-decorator'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import QCityInfo from '@/components/QCityInfo/QCityInfo.vue'
import QSlider from '@/qing-ui/components/QSlider/QSlider.vue'
import {
  socialCircleModule,
  socialCircleStore,
  socialLocationModule,
  socialLocationStore,
  socialTagModule,
  socialTagStore,
  socialTalkModule,
  socialUserStore
} from '@/socialuni/store'
import TagTypeVO from '@/socialuni/model/community/tag/TagTypeVO'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import TagVO from '@/socialuni/model/community/tag/TagVO'
import TalkFilterUtil from '@/socialuni/utils/TalkFilterUtil'
import CityPicker from '@/components/QCityPicker/QCityPicker.vue'
import DistrictVO from '@/socialuni/model/DistrictVO'
import GenderType from '@/socialuni/constant/GenderType'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import AlertUtil from '@/socialuni/utils/AlertUtil'
import TagSearch from '@/pages/talk/TagSearch.vue'
import SocialCirclePicker from '@/components/SocialCirclePicker.vue'
import SocialTagPicker from '@/components/SocialTagPicker.vue'


@Component({
  components: {
    SocialTagPicker,
    SocialCirclePicker,
    TagSearch,
    CityPicker,
    QInput,
    QCityInfo,
    QIcon,
    QPopup,
    QSlider
  }
})
export default class SocialTalkFilterDialog extends Vue {
  $refs: {
    filterDialog: QPopup;
    circleDialog: SocialCirclePicker;
    tagPicker: SocialTagPicker;
  }
  @socialUserStore.State('user') user: CenterUserDetailRO
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]
  @socialTagStore.State('tags') readonly tags: TagVO []
  @socialCircleStore.State('circles') readonly circles: SocialCircleRO []
  @socialCircleStore.Getter('mineCirclesTop10') readonly mineCirclesTop10: string []
  @socialTagStore.Getter('mineTagsTop10') readonly mineTagsTop10: string []
  @socialLocationStore.Getter('location') location: DistrictVO

  rangeValue: number[] = [socialTalkModule.userMinAge, socialTalkModule.userMaxAge]
  showCircleSearch = false
  circleSearchText = ''
  // 筛选相关
  rangeMin: number = TalkFilterUtil.minAgeFilterDefault
  rangMax: number = TalkFilterUtil.maxAgeFilterDefault
  showCityDialog = false
  district: DistrictVO = socialLocationModule.location
  // 组件内的值
  genderTypeValue: string = socialTalkModule.userGender

  selectCircleName = socialCircleModule.circleName
  checkedTags: TagVO[] = []
  selectTagName: string = socialTagModule.selectTagName


  @Watch('location')
  locationWatch () {
    this.district = this.location
  }


  open () {
    this.selectTagName = socialTagModule.selectTagName
    this.selectCircleName = socialCircleModule.circleName
    this.initFilterValue()
    this.$refs.filterDialog.open()
  }

  close () {
    this.showCircleSearch = false
  }

  @Emit()
  change (circle: SocialCircleRO) {
    return circle
  }

  get visibleGenders () {
    if (this.user) {
      if (this.user.gender === GenderType.girl) {
        return GenderType.talkQueryGirlEnums
      } else {
        return GenderType.talkQueryBoyEnums
      }
    }
    return GenderType.talkQueryEnums
  }

  genderChange ({ target }) {
    this.genderTypeValue = target.value
  }

  format () {
    return ''
  }

  handleRangeChange (e) {
    console.log(e)
    this.rangeValue = e
  }

  checkCircleName (circleName: string) {
    this.selectCircleName = circleName
  }

  @Emit()
  confirm () {
    socialTalkModule.setCircleNameUpdateCurTabIndex(this.selectCircleName)
    socialTagModule.setSelectTagName(this.selectTagName)
    socialLocationModule.setLocation(this.district)
    socialTalkModule.setFilterData(this.genderTypeValue, this.rangeValue[0], this.rangeValue[1])
  }

  async clearCheckedTags () {
    await AlertUtil.confirm('是否确认清空已选择的话题？')
    this.checkedTags = []
  }

  // tag
  changeTagName (tagName: string) {
    this.selectTagName = tagName
  }

  deleteTag () {
    this.selectTagName = null
  }

  initFilterValue () {
    this.genderTypeValue = socialTalkModule.userGender
    this.rangeValue = [socialTalkModule.userMinAge, socialTalkModule.userMaxAge]
  }

  // tag
  changeTag (tag: TagVO) {
    socialTagModule.setMineHistoryTagNames(tag.name)
    this.changeTagName(tag.name)
  }

  openTagSearchVue () {
    this.$refs.tagPicker.open()
  }

  circleChange (circle: SocialCircleRO) {
    socialCircleModule.setMineHistoryCircleNames(circle.name)
    this.checkCircleName(circle.name)
  }

  openCircleDialog () {
    this.$refs.circleDialog.openDialog()
  }
}
</script>
