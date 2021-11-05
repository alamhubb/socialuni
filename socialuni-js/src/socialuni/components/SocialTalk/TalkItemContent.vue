<template>
  <view>
    <text class="card-text text-dff" @click="toTalkDetailVue" selectable>
      {{ talk.content }}
    </text>
    <view v-if="talk.imgs.length" class="card-text-row mt-10">
      <image mode="aspectFill" class="card-text-img" v-for="(img,index) in talk.imgs.slice(0,3)" :key="img.id"
             :style="{'max-width':talk.imgs.length===1?Math.min(200*img.aspectRatio,230)+'px':'','max-height':talk.imgs.length===1?'200px':668/Math.min(talk.imgs.length,3)/2+'px'}"
             :src="getTalkSmallImgUrl(talk.user.id,img.src)"
             :show-menu-by-longpress="true"
             @click.stop="previewImage(index)"
      ></image>
    </view>
    <view v-if="!talk.globalTop || talk.globalTop===1" class="row-between-center px-smm pt-10"
          @click="toTalkDetailVue">
      <view class="flex-row">
        <view class="box-mn bd-round bg-orange-light font-sm">
          <q-icon icon="map-fill" size="14"></q-icon>
          <!--        有市区的名称就不显示省的名称-->
          <text v-if="talk.district.districtName">{{ talk.district.districtName }}</text>
          <text v-else>{{ talk.district.provinceName }}</text>
          <text v-if="talk.district.cityName">-{{ talk.district.cityName }}</text>
        </view>
        <view v-if="talk.distance|| talk.distance===0" class="box-sm bd-round bg-orange-light font-sm ml-sm color-orange">
          <text v-if="talk.distance<0.5">{{ 0.5 }}公里</text>
          <text v-else-if="talk.distance<1">{{ 1 }}公里</text>
          <text v-else-if="talk.distance<5">{{ 5 }}公里</text>
          <text v-else>{{ talk.distance | numFixed1 }}公里</text>
        </view>
      </view>

      <view class="row-col-center">
        <!--      只有非单性才需要这样显示-->
        <template v-if="appGenderType === GenderTypeAll">
          <view v-if="talk.visibleGender === GenderTypeGirl" class="cu-tag round bg-pink light">
            女生可见
          </view>
          <view v-else-if="talk.visibleGender === GenderTypeBoy" class="cu-tag round bg-blue light">
            男生可见
          </view>
        </template>
        <!-- 三方数据才显示数据来源-->
        <view v-if="talk.threeContent" class="ml-sm cu-tag round bg-orange light"
              @click.stop="goToThreeAppClick(talk.threeAppId,talk.threeTalkPath)">
          {{ talk.threeAppName }}
          <q-icon v-if="talk.threeAppId" icon="mdi-near-me" class="ml-xs" size="14"></q-icon>
        </view>
      </view>
    </view>
    <view v-if="talk.tags&&talk.tags.length && !talk.globalTop || talk.globalTop===1" class="card-text-row grid"
          @click="toTalkDetailVue">
      <view v-for="tag in talk.tags" :key="tag.id"
            class="box-mn font-sm color-tab mr-sm bd-radius mt-10" style="background: #dceffe">
          #{{ tag.name }}
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import TalkVO from '../../model/talk/TalkVO'
import PagePath from '../../const/PagePath'
import ImgUtil from '../../utils/ImgUtil'
import RouterUtil from '../../utils/RouterUtil'
import QIcon from '../q-icon/q-icon.vue'
import GenderType from '../../const/GenderType'
import SocialuniConfig from '../../model/SocialuniConfig'

@Component({
  components: { QIcon }
})
export default class TalkItemContent extends Vue {
  @Prop() talk: TalkVO

  GenderTypeAll = GenderType.all
  GenderTypeGirl = GenderType.girl
  GenderTypeBoy = GenderType.boy
  appGenderType = SocialuniConfig.appGenderType

  toTalkDetailVue () {
    if (RouterUtil.getCurrentPageURI() !== PagePath.talkDetail) {
      RouterUtil.navigateTo(PagePath.talkDetail + '?talkId=' + this.talk.id)
    }
  }

  getTalkLargeImgUrl (userId: string, src: string) {
    return ImgUtil.getTalkLargeImgUrl(userId, src)
  }

  getTalkSmallImgUrl (userId: string, src: string) {
    return ImgUtil.getTalkSmallImgUrl(userId, src)
  }

  /* previewImage(e) {
    const current = e.target.dataset.src
    uni.previewImage({
      current: current,
      urls: this.showsImgSrcs
    })
  } */

  previewImage (index) {
    uni.previewImage({
      current: index,
      urls: this.talk.imgs.map(item => this.getTalkLargeImgUrl(this.talk.user.id, item.src))
    })
  }

  goToThreeAppClick (appId, path) {
    RouterUtil.navigateToMp(appId, path)
  }
}
</script>
