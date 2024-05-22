<template>
    <div v-if="followUser && !followUser.isMine" class="row-col-center mr-xs">
        <div v-if="FollowStatus.eachFollow === userFollowStatus" class="color-content" @click.stop="addFollow">
            {{ userFollowStatus }}
        </div>
        <q-button v-else @click="addFollow">
            {{ userFollowStatus }}
        </q-button>
    </div>
</template>

<script lang="ts">
import {Component, Prop, Vue, Inject, toNative} from 'vue-facing-decorator'
import QButton from "qing-ui/src/components/QButton.vue";
import FollowStatus from "socialuni-constant/constant/FollowStatus";
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO";
import FollowAddVO from "socialuni-api-base/src/model/FollowAddVO";
import FollowAPI from "socialuni-community-api/src/api/FollowAPI";
import {inject} from "vue";
import SocialuniUserProvideKeys from "socialuni-user-sdk/src/constant/SocialuniUserProvideKeys";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import QIcon from "qing-ui/src/components/QIcon.vue";

@toNative
@Component({
    components: {
      QIcon,
        QButton
    }
})
export default class SocialuniFollowTag extends Vue {
    @Prop() user: CenterUserDetailRO

    FollowStatus = FollowStatus
    followBtnDisabled = false

    @Inject({from: SocialuniUserProvideKeys.detailUser}) injectUser: { user: CenterUserDetailRO }

    get followUser() {
        if (this.user) {
            return this.user
        } else if (this.injectUser) {
            return this.injectUser.user
        }
        return null
    }

    get userFollowStatus() {
        return FollowStatus.getFollowStatus(this.followUser)
    }

    async addFollow() {
        if (!this.followBtnDisabled) {
            this.followBtnDisabled = true
            const followAdd: FollowAddVO = new FollowAddVO(this.followUser.id)
            // 如果已经关注
            if (this.followUser.hasFollowed) {
                this.followUser.hasFollowed = false
                if (this.followUser.fansNum || this.followUser.fansNum === 0) {
                    this.followUser.fansNum -= 1
                }
                try {
                    // 进行取消关注操作
                    await FollowAPI.cancelFollowAPI(followAdd)
                    this.$emit('change', this.followUser)
                } catch (e) {
                    if (this.followUser.fansNum || this.followUser.fansNum === 0) {
                        this.followUser.fansNum += 1
                    }
                    this.followUser.hasFollowed = true
                } finally {
                    this.followBtnDisabled = false
                }
            } else {
                this.followUser.hasFollowed = true
                if (this.followUser.fansNum || this.followUser.fansNum === 0) {
                    this.followUser.fansNum += 1
                }
                try {
                    await FollowAPI.addFollowAPI(followAdd)

                    this.$emit('change', this.followUser)
                } catch (e) {
                    this.followUser.hasFollowed = false
                    if (this.followUser.fansNum || this.followUser.fansNum === 0) {
                        this.followUser.fansNum -= 1
                    }
                } finally {
                    this.followBtnDisabled = false
                }
            }
        }
    }
}
</script>
