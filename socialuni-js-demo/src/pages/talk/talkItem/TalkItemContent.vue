<template>
  <view>
    <div class="card-text text-df" @click="toTalkDetailVue" selectable>
      {{ talk.content }}
    </div>
    <view v-if="talk.imgs.length" class="card-text-row mt-10">
      <div v-for="(img,index) in talk.imgs.slice(0,3)" :key="img.id"
           class="position-relative card-text-img flex-row overflow-hidden"
           :style="{'max-width':talk.imgs.length===1?Math.min(200*img.aspectRatio,230)+'px':'','max-height':talk.imgs.length===1?'200px':668/Math.min(talk.imgs.length,3)/2+'px'}">
        <image mode="aspectFill"
               :class="{'bl-radius':index===0,'br-radius':index===talk.imgs.length-1}"
               class="flex-1"
               :style="{'max-width':talk.imgs.length===1?Math.min(200*img.aspectRatio,230)+'px':'','max-height':talk.imgs.length===1?'200px':668/Math.min(talk.imgs.length,3)/2+'px'}"
               :src="getTalkSmallImgUrl(talk.user.id,img.src)"
               :show-menu-by-longpress="true"
               @click.stop="previewImage(index)"
        ></image>
        <!--        <div class="bd-round size18 position-absolute mr-nn mt-nn right-0 row-all-center">
                  <q-icon @click.native.stop="toIdentityAuth"
                          class="color-success"
                          size="16" icon="level"/>
                </div>-->
      </div>
    </view>
    <div class="px-smm row-wrap" v-if="!talk.globalTop || talk.globalTop===1">
      <template v-if="talk.tags&&talk.tags.length">
        <view v-for="tag in talk.tags" :key="tag.id" @click.stop="chooseTags(tag.name)"
              class="mt-sm color-blue_dark mr-sm">
          <text class="color-blue mr-nn">#</text>
          {{ tag.name }}
        </view>
      </template>
    </div>

    <view v-if="!talk.globalTop || talk.globalTop===1" class="row-col-center px-sm row-wrap"
          @click="toTalkDetailVue">
      <div v-if="talk.circles&&talk.circles.length">
        <view v-for="circleName in talk.circles" :key="circleName" @click.stop="chooseCircle(circleName)"
              class="q-tag mr-sm mt-xss">
          <!--          <q-icon icon="moments" class="color-purple mr-mn" size="12"></q-icon>-->
          <q-icon icon="mdi-send" class="color-purple mr-mn mdi-rotate-315" size="12"></q-icon>
          <text class="pt-nn">{{ circleName }}圈</text>
        </view>
      </div>

      <view class="q-tag mt-xss">
        <q-icon icon="map-fill" class="color-purple mr-mn" size="12"></q-icon>
        <!--        有市区的名称就不显示省的名称-->
        <text v-if="!talk.district.cityName || !talk.district.districtName">{{ talk.district.provinceName }}</text>
        <text v-if="talk.district.cityName">
          <text v-if="!talk.district.districtName">-</text>
          {{ talk.district.cityName }}
        </text>
        <text v-if="talk.district.districtName">-{{ talk.district.districtName }}</text>

        <view class="row-col-center" v-if="talk.distance|| talk.distance===0">
          <div class="px-xs row-col-center">|</div>
          <text v-if="talk.distance<0.5">{{ 0.5 }}公里</text>
          <text v-else-if="talk.distance<1">{{ 1 }}公里</text>
          <text v-else-if="talk.distance<5">{{ 5 }}公里</text>
          <text v-else>{{ numFixed1(talk.distance) }}公里</text>
        </view>
      </view>
      <!--        <div class="color-sub text-sm h25 row-col-center ml-smm">
                {{ talk.updateTime| formatTime }}
              </div>-->

      <!--      <view class="row-col-center">
              &lt;!&ndash;      只有非单性才需要这样显示&ndash;&gt;
              <template v-if="appGenderType === GenderTypeAll">
                <view v-if="talk.visibleGender === GenderTypeGirl" class="cu-tag round bg-pink light">
                  女生可见
                </view>
                <view v-else-if="talk.visibleGender === GenderTypeBoy" class="cu-tag round bg-blue light">
                  男生可见
                </view>
              </template>
              &lt;!&ndash; 三方数据才显示数据来源&ndash;&gt;
              <view v-if="talk.threeContent" class="ml-sm cu-tag round bg-orange light"
                    @click.stop="goToThreeAppClick(talk.threeAppId,talk.threeTalkPath)">
                {{ talk.threeAppName }}
                <q-icon v-if="talk.threeAppId" icon="mdi-near-me" class="ml-xs" size="14"></q-icon>
              </view>
            </view>-->
    </view>

  </view>
</template>

<script lang="ts">
import {Options, Prop, Vue} from 'vue-property-decorator'
import RouterUtil from "socialuni-sdk/src/utils/RouterUtil";
import PagePath from "socialuni-constant/constant/PagePath";
import TalkVO from "socialuni-api/src/model/talk/TalkVO";
import QIcon from "../../../qing-ui/components/QIcon/QIcon.vue";
import {socialTagModule, socialTalkModule} from "socialuni-sdk/src/store/store";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import ImgUtil from "socialuni-sdk/src/utils/ImgUtil";
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil";
import PageUtil from "socialuni-sdk/src/utils/PageUtil";
import NumUtil from "socialuni-sdk/src/utils/NumUtil";

@Options({
  components: {QIcon}
})
export default class TalkItemContent extends Vue {
  @Prop() talk: TalkVO

  toTalkDetailVue() {
    if (RouterUtil.getCurrentPageURI() !== PagePath.talkDetail) {
      RouterUtil.navigateTo(PagePath.talkDetail + '?talkId=' + this.talk.id)
    }
  }

  numFixed1(num) {
    return NumUtil.numFixed1(num)
  }

  chooseCircle(circleName) {
    AlertUtil.confirm(`是否进入${circleName}圈`).then(() => {
      socialTalkModule.setCircleNameUpdateCurTabIndex(circleName)
    })
  }

  chooseTags(tagName) {
    AlertUtil.confirm(`是否筛选${tagName}话题的内容`).then(() => {
      socialTagModule.setSelectTagName(tagName)
    })
  }

  getTalkLargeImgUrl(userId: string, src: string) {
    return ImgUtil.getTalkLargeImgUrl(userId, src)
  }

  getTalkSmallImgUrl(userId: string, src: string) {
    return ImgUtil.getTalkSmallImgUrl(userId, src)
  }

  /* previewImage(e) {
    constant current = e.target.dataset.src
    uni.previewImage({
      current: current,
      urls: this.showsImgSrcs
    })
  } */

  previewImage(index) {
    uni.previewImage({
      current: index,
      urls: this.talk.imgs.map(item => this.getTalkLargeImgUrl(this.talk.user.id, item.src))
    })
  }

  goToThreeAppClick(appId, path) {
    PageUtil.navigateToMp(appId, path)
  }

  toIdentityAuth() {
    MsgUtil.identityAuthHint()
  }
}
</script>
