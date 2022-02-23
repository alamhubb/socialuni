<template>
  <q-popup ref="filterDialog" bottom hide-modal>
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
        <q-input class="w50 mr-xs"></q-input>
        岁 —
        <q-input class="w50 ml-sm mr-xs"></q-input>
        岁
        <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
      </div>
      <div class="row-col-center">
        <div class="mr-sm">地区:</div>
        <q-city-info v-model="district" picker></q-city-info>
        <!--      根据当前用户性别显示  <div class="q-tab">仅男生可见</div>-->
      </div>

      <div>
        <div class="row-between-center">
          <div>话题</div>
          <div class="row-col-center">
            <div>更多 <q-icon icon="mdi-chevron-right"></q-icon></div>
          </div>
        </div>
        <div class="row-wrap">
          <div class="flex-none q-tag-white">
            全部
          </div>
          <div v-for="item in circles" class="flex-none q-tag-white">
            {{ item.name }}
          </div>
        </div>
      </div>
      <div>
        <div class="row-between-center">
          <div>话题</div>
          <div class="flex-row">
            <div>清空</div>
            <div>更多 <q-icon icon="mdi-chevron-right"></q-icon></div>
          </div>
        </div>
        <div class="row-wrap">
          <div v-for="item in tags" class="flex-none q-tag-white">
            {{ item.name }}
          </div>
        </div>
      </div>


    </div>
  </q-popup>
</template>

<script lang="ts">
import { Component, Emit, Vue } from 'vue-property-decorator'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'
import QInput from '@/qing-ui/components/QInput/QInput.vue'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import QCityInfo from '@/socialuni/components/QCityInfo/QCityInfo.vue'
import QSlider from '@/qing-ui/components/QSlider/QSlider.vue'
import { socialCircleStore, socialLocationModule, socialTagStore, socialTalkModule } from '@/socialuni/store'
import TagTypeVO from '@/socialuni/model/community/tag/TagTypeVO'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import TagVO from '@/socialuni/model/community/tag/TagVO'
import TalkFilterUtil from '@/socialuni/utils/TalkFilterUtil'
import CityPicker from '@/socialuni/components/QCityPicker/QCityPicker.vue'
import DistrictVO from '@/socialuni/model/DistrictVO'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'


@Component({
  components: {
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
  }

  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]
  @socialTagStore.State('tags') readonly tags: TagVO []
  @socialCircleStore.State('circleTypes') readonly circleTypes: CircleTypeRO []
  @socialCircleStore.State('circles') readonly circles: SocialCircleRO []
  rangeValue: number[] = [socialTalkModule.userMinAge, socialTalkModule.userMaxAge]
  showCircleSearch = false
  circleSearchText = ''
  // 筛选相关
  rangeMin: number = TalkFilterUtil.minAgeFilterDefault
  rangMax: number = TalkFilterUtil.maxAgeFilterDefault
  showCityDialog = false
  district: DistrictVO = socialLocationModule.location

  open () {
    this.$refs.filterDialog.open()
    console.log(this.district)
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
