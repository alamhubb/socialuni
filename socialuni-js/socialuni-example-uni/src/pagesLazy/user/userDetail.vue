<template>
    <view class="bg-default">
        <socialuni-community-user-detail-view>
            <template #userBaseInfoRightPre>
                <q-button @click="socialuniUserDetailViewService.toMessagePage()" class="mr-sm">
                    <q-icon icon="mdi-chat-outline" size="14"></q-icon>
                    私信
                </q-button>
            </template>
        </socialuni-community-user-detail-view>
    </view>
</template>

<script setup lang="ts">
import SocialuniCommunityUserDetailView
    from "socialuni-community-view-uni/src/views/user/SocialuniCommunityUserDetailView.vue";
import socialuniUserDetailViewService from "socialuni-user-sdk/src/logic/SocialuniUserDetailViewService";
import QButton from "socialuni-ui-uni/src/components/QButton/QButton.vue";
import QIcon from "socialuni-ui-uni/src/components/QIcon/QIcon.vue";
</script>
