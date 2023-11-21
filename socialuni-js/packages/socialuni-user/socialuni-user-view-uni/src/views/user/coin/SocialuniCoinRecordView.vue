<template>
    <view class="h100p flex-col bg-white">
        <q-tabs :tabs="tabs" :value="currentTabIndex" type="full" @input="tabsChange"
                class="flex-none bd-radius flex-1 mr-sm mt-sm">
            <template #default="{tab,index,value}">
                <view class="h30 px-xs row-all-center font-md" :class="{'font-md':value===index}">{{ tab }}</view>
            </template>
        </q-tabs>
        <div class="flex-1 overflow-hidden">
            <swiper class="h100p" :current="currentTabIndex" @change="talkSwiperChange">
                <swiper-item v-for="(item, swiperIndex) in tabsPageQueryUtil" :key="swiperIndex">
                    <div v-if="!item.queryQO.listData.length" class="row-all-center h100 color-content">
                        <div v-if="swiperIndex===0">您还没有关注任何人</div>
                        <div v-else>您还没有粉丝</div>
                    </div>
                    <template v-else>
                        <div class="flex-row px-smm py-sm bb" v-for="user in item.queryQO.listData" :key="user.id"
                             @click="toUserDetailVue(user)">
                            <image
                                    class="card-title-avatar bd"
                                    mode="aspectFill"
                                    :src="user.avatar"
                            />
                            <view class="flex-1 row-between">
                                <view class="col-between py-xs">
                                    <view class="row-col-center">
                                        <text :class="{'text-red':user.vipFlag}">{{ user.nickname }}</text>
                                        <view v-if="user.vipFlag" class="ml-5px cu-tag bg-orange radius sm"
                                              @click.stop="openVip">
                                            VIP
                                        </view>
                                        <social-gender-tag class="ml-xs" :user="user"></social-gender-tag>
                                    </view>
                                    <view class="color-content mt-xs">
                                        粉丝：{{ user.fansNum }}
                                    </view>
                                </view>
                                <view class="col-center">
                                    <socialuni-follow-tag :user="user"
                                                          @change="userFollowChange"></socialuni-follow-tag>
                                </view>
                            </view>
                        </div>
                    </template>
                </swiper-item>
            </swiper>

            <!--      <div>
                    <view class="w100r flex-col">
                      <view class="text-xl text-bold">
                        历史获得和消费记录：
                      </view>
                      <view class="pt-xs">
                        <view v-if="shellOrders.length">
                          <u-table>
                            <u-tr>
                              <u-th>贝壳</u-th>
                              <u-th>类型</u-th>
                              <u-th>时间</u-th>
                            </u-tr>
                            <u-tr v-for="shellOrder in shellOrders" :key="shellOrder.id">
                              <u-td>{{ shellOrder.shell }}</u-td>
                              <u-td>{{ shellOrder.type }}</u-td>
                              <u-td>{{ shellOrder.createTime | parseTime }}</u-td>
                            </u-tr>
                          </u-table>
                        </view>
                        <view v-else class="text-lg text-gray row-center">暂无</view>
                      </view>
                    </view>
                  </div>-->

        </div>
    </view>
</template>

<script lang="ts">
import {Vue, Component, Prop, Emit} from 'vue-facing-decorator'
import SocialuniFollowType from "socialuni-constant/constant/user/SocialuniFollowType";
import SocialUserContentRO from "socialuni-api-base/src/model/social/SocialUserContentRO";
import {onLoad, onPullDownRefresh, onReachBottom, onShow} from "@dcloudio/uni-app";
import QTabs from "socialuni-ui-uni/src/components/QTabs/QTabs.vue";
import SocialuniPageQueryUtil from "socialuni-api-base/src/model/common/SocialuniPageQueryUtil";
import SocialGenderTag from "../../../components/SocialGenderTag/SocialGenderTag.vue";
import FollowAPI from "socialuni-community-api/src/api/FollowAPI";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";

@Component({
    components: {SocialGenderTag, QTabs}
})
export default class SocialuniCoinRecordView extends Vue {
    tabs = ['收入', '消费', '充值']
    currentTabIndex = 0

    tabsPageQueryUtil: SocialuniPageQueryUtil<SocialUserContentRO, string>[] = [new SocialuniPageQueryUtil(), new SocialuniPageQueryUtil(), new SocialuniPageQueryUtil()]

    created() {
        this.tabsPageQueryUtil = [new SocialuniPageQueryUtil(FollowAPI.queryUserFollowsAPI), new SocialuniPageQueryUtil(FollowAPI.queryUserFollowsAPI)]

        onPullDownRefresh(async () => {
            await this.initQuery()
        })
        onReachBottom(() => {
            this.tabsPageQueryUtil[this.currentTabIndex].nextPageQuery(this.tabs[this.currentTabIndex])
        })

        onLoad((params: { followType: string }) => {
            if (params) {
                if (params.followType === SocialuniFollowType.fans) {
                    this.currentTabIndex = 1
                } else {
                    this.currentTabIndex = 0
                }
            }
            this.$nextTick(() => {
                uni.startPullDownRefresh(null)
            })
        })
    }

    async initQuery() {
        await this.tabsPageQueryUtil[this.currentTabIndex].initQuery(this.tabs[this.currentTabIndex])
        uni.stopPullDownRefresh()
    }

    openVip() {
        UserPageUtil.toVipPage()
    }

    toUserDetailVue(user: SocialUserContentRO) {
        UserPageUtil.toUserDetail(user.id)
    }

    // tabs通知swiper切换
    tabsChange(index) {
        this.currentTabIndex = index
        if (this.tabsPageQueryUtil[this.currentTabIndex].queryQO.firstLoad) {
            uni.startPullDownRefresh(null)
            this.initQuery()
        }
    }

    // talkSwipe
    talkSwiperChange(e) {
        this.tabsChange(e.detail.current)
    }

    //同步更新粉丝和关注列表状态
    userFollowChange(user: SocialUserContentRO) {
        for (const socialuniPageQueryUtil of this.tabsPageQueryUtil) {
            for (const listDatum of socialuniPageQueryUtil.queryQO.listData) {
                if (listDatum.id === user.id) {
                    listDatum.hasFollowed = user.hasFollowed
                }
            }
        }
    }
}
</script>
