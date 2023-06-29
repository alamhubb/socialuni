<template>
    <view class="bg-default">
<!--        <socialuni-community-user-detail-view ref="userDetail">

        </socialuni-community-user-detail-view>-->
        <!--    <msg-input v-if="showMsgInput">
            </msg-input>-->
        <socialuni-user-detail-view></socialuni-user-detail-view>
    </view>
</template>

<script setup lang="ts">
import SocialuniCommunityUserDetailView
    from "socialuni-community-view-uni/src/views/user/SocialuniCommunityUserDetailView.vue";
import SocialuniUserDetailView
    from "socialuni-user-view-uni/src/views/user/SocialuniUserDetailView.vue";


</script>
