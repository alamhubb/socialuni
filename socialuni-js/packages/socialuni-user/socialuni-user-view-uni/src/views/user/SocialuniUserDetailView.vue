<template>
    <view class="bg-default">
        <q-navbar show-back title="用户详情">
            <div class="row-end-center flex-1">
                <q-icon icon="list-dot" size="20" @click="socialuniUserDetailViewService.openMoreMenu()"></q-icon>
            </div>
        </q-navbar>
        <view v-if="socialuniUserDetailViewService.user" class="bg-default pb-100">
            <view class="mb-sm pt-200 bg-no-repeat"
                  style="background-image: url('https://cdxapp-1257733245.file.myqcloud.com/socialuni/common/app/userDefaultTopImg.jpg')">
                <view class="pd-sm bg-white bt-radius-10">
                    <div class="row-between py-sm">
                        <div class="flex-row">
                            <image
                                    class="size65 bd-radius-xs mr-sm bd"
                                    mode="aspectFill"
                                    :src="socialuniUserDetailViewService.user.avatar"
                                    @click="socialuniUserDetailViewService.seeAvatarDetail()"
                            />
                            <view class="flex-1 flex-col h65 py-xs">
                                <!--                                :class="{'color-red':socialuniUserDetailViewService.user.vipFlag}"-->
                                <view class="text-md font-bold">
                                    {{ socialuniUserDetailViewService.user.nickname }}
                                </view>
                                <view class="row-col-center mt-sm">
                                    <social-gender-tag
                                            :user="socialuniUserDetailViewService.user"></social-gender-tag>
                                    <!--                <view v-if="userProp.vipFlag" class="cu-tag bg-red radius" @click="openVip">VIP</view>
                                                    <view v-else class="cu-tag bg-grey radius" @click="openVip">VIP</view>-->
                                </view>
                            </view>
                        </div>

                        <slot name="userBaseInfoRight" :user="socialuniUserDetailViewService.user">

                        </slot>
                    </div>


                    <view class="row-between-center mt-sm py-xs pr-xs">
                        <view class="flex-row flex-1 row-around">
                            <view class="px-lg line-height-1 col-all-center">
                                <text class="text-lg font-bold color-main row-center">
                                    {{ socialuniUserDetailViewService.user.followNum }}
                                </text>
                                <text class="font-12 color-content mt-xs">关注</text>
                            </view>
                            <view class="px-lg line-height-1 col-all-center">
                                <text class="text-lg font-bold color-main row-center">
                                    {{ socialuniUserDetailViewService.user.fansNum }}
                                </text>
                                <text class="font-12 color-content mt-xs">被关注</text>
                            </view>
                        </view>
                    </view>

                    <div class="flex-row my-sm">
                        <view v-if="socialuniUserDetailViewService.user.city" class="row-col-center q-tag">
                            <q-icon icon="map-fill" class="color-purple mr-mn" size="12"/>
                            {{ socialuniUserDetailViewService.user.city || '' }}
                        </view>
                        <view v-if="socialuniUserDetailViewService.user.schoolName" class="row-col-center q-tag">
                            <q-icon class="color-blue mr-xs" icon="mdi-school" size="12"/>
                            {{ socialuniUserDetailViewService.user.schoolName }}
                        </view>
                    </div>
                </view>
            </view>


            <div v-if="!socialuniUserDetailViewService.isIosAndMpQQ && socialuniUserDetailViewService.user.contactInfo"
                 class="row-between-center bg-white px-sm py-sm bd-radius-10 elevation-4 mx-sm mt-smm mb-sm">
                <div class="row-col-center">
                    <q-icon class="color-sub mr-xs" prefix="uni-icons" icon="uniui-chat" size="14"/>
                    联系方式：
                    <!--          如果开启了，则代表获取过，无需再次获取，点击为复制-->
                    <div v-if="socialuniUserDetailViewService.user.openContactInfo" class="use-click row-col-center">
                        <q-button light @click="socialuniUserDetailViewService.copyContactInfo()">
                            {{ socialuniUserDetailViewService.user.contactInfo }}
                            <div class="color-content ml-xs font-12">
                                ( 点击复制 )
                            </div>
                        </q-button>
                    </div>
                    <div v-else class="use-click row-col-center">
                        <q-button light @click="socialuniUserDetailViewService.getOpenContactInfo()"
                                  :disabled="socialuniUserDetailViewService.showUserContactBtnDisabled">
                            {{ socialuniUserDetailViewService.user.contactInfo }}
                            <div class="color-content ml-xs font-12">
                                (点击获取)
                            </div>
                        </q-button>
                    </div>
                </div>
            </div>

            <socialuni-user-info-img :user="socialuniUserDetailViewService.user"
                                     v-if="socialuniUserDetailViewService.user.imgs.length"></socialuni-user-info-img>


            <slot>

            </slot>
        </view>
    </view>
</template>

<script setup lang="ts">
import SocialuniUserInfoImg from "./SocialuniUserInfoImg.vue"
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue"
import QButton from "socialuni-ui-uni/src/components/QButton/QButton.vue"
import QNavbar from "socialuni-ui-uni/src/components/QNavbar/QNavbar.vue"
import SocialuniFollowTag from "socialuni-community-view-uni/src/components/SocialuniFollow/SocialuniFollowTag.vue"
import SocialGenderTag from "../../components/SocialGenderTag/SocialGenderTag.vue"
import socialuniUserDetailViewService from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";
import {getCurrentInstance, provide, reactive} from "vue";
import SocialuniUserProvideKeys from "socialuni-user-sdk/src/constant/SocialuniUserProvideKeys";

socialuniUserDetailViewService.initService(getCurrentInstance())

provide(SocialuniUserProvideKeys.detailUser, socialuniUserDetailViewService)
</script>
