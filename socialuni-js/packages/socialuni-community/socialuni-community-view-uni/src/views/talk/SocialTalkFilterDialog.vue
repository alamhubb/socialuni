<template>
  <q-popup ref="filterDialog" bottom hide-modal @confirm="confirm">
    <div class="h80vh">
      <div class="h100p py-sm px">
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
import {Emit, Options, Vue, Watch} from 'vue-facing-decorator'
import DistrictVO from "@socialuni/socialuni-api-base/src/model/DistrictVO";
import GenderType from "@socialuni/socialuni-constant/constant/GenderType";
import TagSearch from "./TagSearch.vue";
import QInput from "@socialuni/socialuni-ui-uni/src/components/QInput/QInput.vue";
import QIcon from "@socialuni/socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import QSlider from "@socialuni/socialuni-ui-uni/src/components/QSlider/QSlider.vue";
import QPopup from "@socialuni/socialuni-ui-uni/src/components/QPopup/QPopup.vue";
import SocialTagPicker from '@socialuni/socialuni-community-view-uni/src/components/SocialTagPicker.vue';
import SocialCirclePicker from "@socialuni/socialuni-community-view-uni/src/components/SocialCirclePicker.vue";
import {socialuniTagModule} from "@socialuni/socialuni-community-sdk/src/store/SocialTagModule";
import {socialCircleModule} from "@socialuni/socialuni-community-sdk/src/store/SocialCircleModule";
import {socialLocationModule} from "@socialuni/socialuni-community-sdk/src/store/SocialLocationModule";
import {socialTalkModule} from "@socialuni/socialuni-community-sdk/src/store/SocialTalkModule";
import QCityPicker from "@socialuni/socialuni-community-view-uni/src/components/QCityPicker/QCityPicker.vue";
import QCityInfo from "@socialuni/socialuni-community-view-uni/src/components/QCityInfo/QCityInfo.vue";
import TalkFilterUtil from "@socialuni/socialuni-community-sdk/src/util/TalkFilterUtil";
import TagVO from "@socialuni/socialuni-api-base/src/model/community/tag/TagVO";
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialCircleRO from "@socialuni/socialuni-api-base/src/model/community/circle/SocialCircleRO";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";


@Component({
  components: {
    SocialTagPicker,
    SocialCirclePicker,
    TagSearch,
    QCityPicker,
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

  get user() {
    return socialuniUserModule.mineUser
  }

  get tagTypes() {
    return socialuniTagModule.tagTypes
  }

  get tags() {
    return socialuniTagModule.tags
  }

  get mineTagsTop10() {
    return socialuniTagModule.mineTagsTop10
  }

  get circles() {
    return socialCircleModule.circles
  }

  get mineCirclesTop10() {
    return socialCircleModule.mineCirclesTop10
  }

  get location() {
    return socialLocationModule.location
  }


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
  selectTagName: string = socialuniTagModule.selectTagName

  @Watch('location')
  locationWatch() {
    this.district = this.location
  }


  open() {
    this.selectTagName = socialuniTagModule.selectTagName
    this.selectCircleName = socialCircleModule.circleName
    this.initFilterValue()
    this.$refs.filterDialog.open()
  }

  close() {
    this.showCircleSearch = false
  }

  @Emit('update:modelValue')
  change(circle: SocialCircleRO) {
    return circle
  }

  get visibleGenders() {
    if (this.user) {
      if (this.user.gender === GenderType.girl) {
        return GenderType.talkQueryGirlEnums
      } else {
        return GenderType.talkQueryBoyEnums
      }
    }
    return GenderType.talkQueryEnums
  }

  genderChange({detail}) {
    this.genderTypeValue = detail.value
  }

  format() {
    return ''
  }

  handleRangeChange(e) {
    console.log(e)
    this.rangeValue = e
  }

  checkCircleName(circleName: string) {
    this.selectCircleName = circleName
  }

  @Emit()
  confirm() {
    socialTalkModule.setCircleNameUpdateCurTabIndex(this.selectCircleName)
    socialuniTagModule.setSelectTagName(this.selectTagName)
    socialLocationModule.setLocation(this.district)
    socialTalkModule.setFilterData(this.genderTypeValue, this.rangeValue[0], this.rangeValue[1])
  }

  async clearCheckedTags() {
    await SocialuniAppUtil.AlertUtil.confirm('是否确认清空已选择的话题？')
    this.checkedTags = []
  }

  // tag
  changeTagName(tagName: string) {
    this.selectTagName = tagName
  }

  deleteTag() {
    this.selectTagName = null
  }

  initFilterValue() {
    this.genderTypeValue = socialTalkModule.userGender
    this.rangeValue = [socialTalkModule.userMinAge, socialTalkModule.userMaxAge]
  }

  // tag
  changeTag(tag: TagVO) {
    socialuniTagModule.setMineHistoryTagNames(tag.name)
    this.changeTagName(tag.name)
  }

  openTagSearchVue() {
    this.$refs.tagPicker.open()
  }

  circleChange(circle: SocialCircleRO) {
    socialCircleModule.setMineHistoryCircleNames(circle.name)
    this.checkCircleName(circle.name)
  }

  openCircleDialog() {
    this.$refs.circleDialog.openDialog()
  }
}
</script>
