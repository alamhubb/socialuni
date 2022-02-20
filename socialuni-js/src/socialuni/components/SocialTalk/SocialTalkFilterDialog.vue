<template>
  <q-popup v-model="showCircleSearch" bottom :modal="false">
    <div class="h87vh py-sm px-sm">
      <div class="row-col-center mb-sm">
        <div class="mr-sm">性别:</div>
        <div class="q-tag-white bg-click">不限</div>
        <div class="q-tag-white bg-click">女生</div>
        <div class="q-tag-white bg-click">男生</div>
        <div class="q-tag-white bg-click">仅女生可见</div>
        <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
      </div>
      <div class="row-col-center mb-sm">
        <div class="mr-sm">年龄:</div>
        <q-input class="w50 mr-xs"></q-input>岁 — <q-input class="w50 ml-sm mr-xs"></q-input>岁
        <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
      </div>
      <div class="row-col-center">
        <div class="mr-sm">地区:</div>


        <q-input class="w70 mr-xs"></q-input>岁 — <q-input class="w70 ml-sm mr-xs"></q-input>岁
        <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
      </div>
      <div>年龄</div>
      <div class="flex-row">
        <div class="q-tag-white bg-click">女生</div>
        <div class="q-tag-white bg-click">男生</div>
        <div class="q-tag-white bg-click">仅女生可见</div>
        <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
      </div>

      <div>圈子</div>
      <div>地区</div>


    </div>


    <city-picker v-model="showCityDialog" :district="district" @confirm="cityChange"></city-picker>

  </q-popup>
</template>

<script lang="ts">
import { Component, Emit, Vue } from 'vue-property-decorator'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import QSlider from '@/qing-ui/components/QSlider/QSlider.vue'
import { socialTagStore, socialTalkModule } from '@/socialuni/store'
import TagTypeVO from '@/socialuni/model/community/tag/TagTypeVO'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import TagVO from '@/socialuni/model/community/tag/TagVO'
import TalkFilterUtil from '@/socialuni/utils/TalkFilterUtil'
import CityPicker from '@/socialuni/components/CityPicker.vue'


@Component({
  components: {
    CityPicker,
    QInput,
    QPopup,
    QSlider
  }
})
export default class SocialTalkFilterDialog extends Vue {
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]
  @socialTagStore.State('tags') readonly tags: TagVO []
  rangeValue: number[] = [socialTalkModule.userMinAge, socialTalkModule.userMaxAge]
  showCircleSearch = false
  circleSearchText = ''
  // 筛选相关
  rangeMin: number = TalkFilterUtil.minAgeFilterDefault
  rangMax: number = TalkFilterUtil.maxAgeFilterDefault

  open () {
    this.showCircleSearch = true
  }

  close () {
    this.showCircleSearch = false
  }

  @Emit()
  change (circle: SocialCircleRO) {
    return circle
  }
}
</script>
