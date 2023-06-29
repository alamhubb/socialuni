<template>
    <socialuni-user-detail-view>
        <template #navBarRight="{user}">
            <slot name="navBarRight" :user="user"></slot>
        </template>
        <template #userBaseInfoRight="{user}">
            <div class="col-center flex-none">
                <view class="row-end-center mr-xss">
                    <view v-if="user.hasBeFollowed && !user.hasFollowed"
                          class="px-5 py-1 bg-default">
                        <div class="color-content font-12">
                            对方关注了您
                        </div>
                    </view>
                </view>
                <!--              不为自己才展示-->
                <view class="row-col-center mt-sm">
                    <!--                不为自己且未关注-->
                    <!--            不为ios，或者不为付费，则显示-->
                    <!--              <q-button v-if="!isIos||!user.chat.needPayOpen" @click="toMessagePage">-->

                    <slot name="userBaseInfoRightPre">

                    </slot>
                    <socialuni-follow-tag></socialuni-follow-tag>
                    <!--              <button v-else class="cu-btn round bd-gray bg-white" @click.stop="addFollow">已关注</button>-->
                </view>
            </div>
        </template>

        <q-popup ref="applyUserFriendDialog" title="申请添加好友111"
                 @confirm="socialuniCommunityUserDetailViewService.addFriend()">
            <div class="pd">cccc</div>
        </q-popup>

        <talk-operate @deleteTalk="talkId=>socialuniCommunityUserDetailViewService.deleteTalk(talkId)"></talk-operate>
        <!--  #ifdef MP-WEIXIN -->
        <!--      <ad v-if="talks.length>1" class="bg-white mb-5 w100vw" unit-id="adunit-65c8911d279d228f" ad-type="video"
                  ad-theme="white"></ad>-->
        <!--  #endif -->
        <!--qq平台显示的广告-->
        <!--  #ifdef MP-QQ -->
        <!--      <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" unit-id="72d8cb09a1bae9fa30d9e03e7cb8a25d"
                  type="feeds" ad-height="160"></ad>-->
        <!--  #endif -->
        <!--  #ifdef APP-PLUS -->
        <!--      <ad v-if="talks.length>0" class="bg-white mb-5 w100vw" adpid="1890536227"></ad>-->
        <!--  #endif -->

        <div class="px-smm mb-xs">动态</div>
        <view v-for="talk in socialuniCommunityUserDetailViewService.talks" :key="talk.id" class="px-sm">
            <talk-item :talk="talk"
                       @deleteTalk="talkId=>socialuniCommunityUserDetailViewService.deleteTalk(talkId)"></talk-item>
        </view>

        <!--wx平台显示的广告-->
        <!--  #ifdef MP-WEIXIN -->
        <ad class="bg-white mt-10 w100vw" unit-id="adunit-ffa7bc1c73c7d46a"></ad>
        <!--  #endif -->
        <!--qq平台显示的广告-->
        <!--  #ifdef MP-QQ -->
        <ad class="bg-white mt-10 w100vw" unit-id="b10fe0e7c39b9ca9e7ce19660f6d0761" test-banner-type="one"></ad>
        <!--  #endif -->
        <!--  #ifdef APP-PLUS -->
        <ad class="bg-white mt-10 w100vw" adpid="1890536227"></ad>
        <!--  #endif -->


    </socialuni-user-detail-view>
</template>

<script setup lang="ts">
import SocialuniUserDetailView from "socialuni-user-view-uni/src/views/user/SocialuniUserDetailView.vue";
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue";
import TalkOperate from "../talk/talkOperate.vue";
import TalkItem from "../../components/talkItem/TalkItem.vue";
import socialuniCommunityUserDetailViewService
    from "socialuni-community-sdk/src/logic/SocialuniCommunityUserDetailViewService";
import {getCurrentInstance} from "vue";
import socialuniUserDetailViewService from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";
import QButton from "socialuni-ui-uni/src/components/QButton/QButton.vue";
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue";
import SocialuniFollowTag from "../../components/SocialuniFollow/SocialuniFollowTag.vue";

socialuniCommunityUserDetailViewService.initService(getCurrentInstance())
</script>
