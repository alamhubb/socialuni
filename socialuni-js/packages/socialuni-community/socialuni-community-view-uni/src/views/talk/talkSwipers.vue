<template>
    <div v-if="showSwipers">
        <!--  <div class="bg-primary px-smm pt-xs">-->
        <swiper class="w100p" :indicator-dots="true" :autoplay="true" circular :interval="2500"
                :style="{'height':swiperHeight+'px'}">
            <swiper-item class="bd-radius" v-for="(item,index) in homeSwipers" @click="skipWebView(index)">
                <!--        class="radius flex-none h100p"-->
                <img
                        class="h100p bd-radius w100p"
                        mode="aspectFill"
                        :src="item.imgUrl"
                />
            </swiper-item>
        </swiper>
    </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SkipType from "socialuni-constant/constant/SkipType";
import {socialAppModule} from "socialuni-app-sdk/src/store/SocialAppModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SocialuniAppPageUtil from "socialuni-app-sdk/src/util/SocialuniAppPageUtil";


// todo 后台可控制是否显示轮播图

@Component({})
class TalkSwipers extends Vue {
    get homeSwipers() {
        return socialAppModule.homeSwipers
    }

    get swiperHeight() {
        return socialuniConfigModule.appMoreConfig.swiperHeight
    }

    get showSwipers() {
        return this.homeSwipers && this.homeSwipers.length
    }

    skipWebView(current: number) {
        //这里要考虑，跳转其他小程序，跳转其他app，跳转本应用页面
        //区分跳转类型，跳转web，local，小程序，app
        //特殊处理小程序无法跳转app
        const homeSwiper = this.homeSwipers[current]
        //需要跳转
        if (homeSwiper.skip) {
            if (homeSwiper.skipType === SkipType.app) {
                if (socialuniSystemModule.isMp) {
                    SocialuniAppPageUtil.navigateToAll(homeSwiper.standType, homeSwiper.standUrl, homeSwiper.name)
                } else {
                    SocialuniAppPageUtil.navigateToWeb(homeSwiper.skipUrl, homeSwiper.name)
                }
            } else {
                SocialuniAppPageUtil.navigateToAll(homeSwiper.skipType, homeSwiper.standUrl, homeSwiper.name)
            }
        }
    }
}
export default toNative(TalkSwipers)
</script>
