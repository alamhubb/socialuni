<template>
  <div>
    <div class="px-15" @click="toTalkDetailVue" selectable @longpress="copyContent(talk)">
      {{ talk.content }}
    </div>
    <div v-if="talk.imgs.length" class="card-text-row mt-10">
      <div v-for="(img,index) in talk.imgs.slice(0,3)" :key="img.id"
           class="position-relative card-text-img flex-row overflow-hidden"
           :style="{'max-width':talk.imgs.length===1?Math.min(200*img.aspectRatio,230)+'px':'','max-height':talk.imgs.length===1?'200px':668/Math.min(talk.imgs.length,3)/2+'px'}">
        <img mode="aspectFill"
               :class="{'bl-radius':index===0,'br-radius':index===talk.imgs.length-1}"
               class="flex-1"
               :style="{'max-width':talk.imgs.length===1?Math.min(200*img.aspectRatio,230)+'px':'','max-height':talk.imgs.length===1?'200px':668/Math.min(talk.imgs.length,3)/2+'px'}"
               :src="getTalkSmallImgUrl(talk.user.id,img.src)"
               :show-menu-by-longpress="true"
               @click.stop="previewImage(index)"
        />
        <!--        <div class="bd-round size18 position-absolute mr-nn mt-nn right-0 row-all-center">
                  <q-icon @click.stop="toIdentityAuth"
                          class="color-success"
                          size="16" icon="level"/>
                </div>-->
      </div>
    </div>
    <div class="px-smm row-wrap" v-if="!talk.globalTop || talk.globalTop===1">
      <template v-if="talk.tags&&talk.tags.length">
        <div v-for="tag in talk.tags" :key="tag.id" @click.stop="chooseTags(tag.name)"
              class="mt-sm color-blue_dark mr-sm">
          <span class="color-blue mr-nn">#</span>
          {{ tag.name }}
        </div>
      </template>
    </div>

    <div v-if="!talk.globalTop || talk.globalTop===1" class="row-col-center px-sm row-wrap"
          @click="toTalkDetailVue">
      <div v-if="talk.circles&&talk.circles.length">
        <div v-for="circleName in talk.circles" :key="circleName" @click.stop="chooseCircle(circleName)"
              class="q-tag mr-sm mt-xss">
          <!--          <q-icon icon="moments" class="color-purple mr-mn" size="12"></q-icon>-->
          <q-icon icon="mdi-send" class="color-purple mr-mn mdi-rotate-315" size="12"></q-icon>
          <span class="pt-nn">{{ circleName }}</span>
<!--          圈-->
        </div>
      </div>

      <div class="q-tag mt-xss">
        <q-icon icon="map-fill" class="color-purple mr-mn" size="12"></q-icon>
        <!--        有市区的名称就不显示省的名称-->
        <span v-if="!talk.district.cityName || !talk.district.districtName">{{ talk.district.provinceName }}</span>
        <span v-if="talk.district.cityName">
          <span v-if="!talk.district.districtName">-</span>
          {{ talk.district.cityName }}
        </span>
        <span v-if="talk.district.districtName">-{{ talk.district.districtName }}</span>

        <div class="row-col-center" v-if="talk.distance|| talk.distance===0">
          <div class="px-xs row-col-center">|</div>
          <span v-if="talk.distance<0.5">{{ 0.5 }}公里</span>
          <span v-else-if="talk.distance<1">{{ 1 }}公里</span>
          <span v-else-if="talk.distance<5">{{ 5 }}公里</span>
          <span v-else>{{ numFixed1(talk.distance) }}公里</span>
        </div>
      </div>
      <!--        <div class="color-sub text-sm h25 row-col-center ml-smm">
                {{ home.updateTime| formatTime }}
              </div>-->

      <!--      <div class="row-col-center">
              &lt;!&ndash;      只有非单性才需要这样显示&ndash;&gt;
              <template v-if="appGenderType === GenderTypeAll">
                <div v-if="home.visibleGender === GenderTypeGirl" class="cu-tag round bg-pink light">
                  女生可见
                </div>
                <div v-else-if="home.visibleGender === GenderTypeBoy" class="cu-tag round bg-blue light">
                  男生可见
                </div>
              </template>
              &lt;!&ndash; 三方数据才显示数据来源&ndash;&gt;
              <div v-if="home.threeContent" class="ml-sm cu-tag round bg-orange light"
                    @click.stop="goToThreeAppClick(home.threeAppId,home.threeTalkPath)">
                {{ home.threeAppName }}
                <q-icon v-if="home.threeAppId" icon="mdi-near-me" class="ml-xs" size="14"></q-icon>
              </div>
            </div>-->
    </div>

  </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'
import PagePath from "socialuni-constant/constant/PagePath";
import TalkVO from "socialuni-api-base/src/model/talk/TalkVO";
import QIcon from "qing-ui/src/components/QIcon.vue";
import MsgUtil from "socialuni-app-sdk/src/util/MsgUtil";
import NumUtil from "qing-util/src/util/NumUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";
import UserPagePath from "socialuni-user-sdk/src/constant/UserPagePath";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import CommunityPagePath from "socialuni-community-sdk/src/constant/CommunityPagePath";
import CommunityPageUtil from "socialuni-community-sdk/src/util/CommunityPageUtil";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import {socialuniTagModule} from "socialuni-community-sdk/src/store/SocialTagModule";

@toNative
@Component({
  components: {QIcon}
})
export default class TalkItemContent extends Vue {
  @Prop() talk: TalkVO

  toTalkDetailVue() {
    if (QingAppUtil.RouterUtil.getCurrentPageURI() !== CommunityPagePath.talkDetail) {
        CommunityPageUtil.toTalkDetail(this.talk.id)
    }
  }

  numFixed1(num) {
    return NumUtil.numFixed1(num)
  }

  chooseCircle(circleName) {
      QingAppUtil.AlertUtil.confirm(`是否进入${circleName}圈`).then(() => {
      if (QingAppUtil.RouterUtil.getCurrentPageURI() === UserPagePath.talk) {
        socialTalkModule.setCircleNameUpdateCurTabIndex(circleName)
      } else {
        CommonUtil.delayTime(500).then(() => {
          socialTalkModule.setCircleNameUpdateCurTabIndex(circleName)
        })
          CommunityPageUtil.toTalkPage()
      }
    })
  }

  chooseTags(tagName) {
      QingAppUtil.AlertUtil.confirm(`是否筛选${tagName}话题的内容`).then(() => {
      socialuniTagModule.setSelectTagName(tagName)
    })
  }

  getTalkLargeImgUrl(userId: string, src: string) {
    return SocialuniImgUtil.getTalkLargeImgUrl(userId, src)
  }

  getTalkSmallImgUrl(userId: string, src: string) {
    return SocialuniImgUtil.getTalkSmallImgUrl(userId, src)
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
    QingAppUtil.RouterUtil.navigateToMp(appId, path)
  }

  toIdentityAuth() {
    UserMsgUtil.identityAuthHint()
  }

  copyContent(talk:TalkVO){
      QingAppUtil.NativeUtil.showCopyAction(talk.content)
  }
}
</script>
