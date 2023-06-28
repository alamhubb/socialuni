<template>
    <socialuni-user-detail-view>
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

        <q-popup ref="moreActionMenu" bottom>
            <uni-list class="pb-xl">
                <uni-list-item title="解除好友" link
                               @click="socialuniCommunityUserDetailViewService.deleteFriend()"></uni-list-item>
                <uni-list-item title="加入黑名单" link
                               @click="socialuniCommunityUserDetailViewService.addBlack()"></uni-list-item>
                <uni-list-item title="从黑名单移除" link
                               @click="socialuniCommunityUserDetailViewService.removeBlack()"></uni-list-item>
            </uni-list>
        </q-popup>
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

socialuniCommunityUserDetailViewService.initService(getCurrentInstance())
</script>
