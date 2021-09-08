<template>
    <view>
        <view class="card" v-for="(notify,index) in notifies" :key="index">
            <view class="card-title row-between" @click="toTalkDetailVue(notify.talkId)">
                <view class="flex-row flex-auto">
                    <image class="card-title-avatar flex-none" mode="aspectFill"
                           :src="notify.avatar"></image>
                    <view class="flex-auto">
                        <view :class="notify.vipFlag?'color-red':'color-blue-dark'" class="row-col-center py-mn">
                            <text>{{notify.nickname}}</text>
                            <view v-if="notify.vipFlag" class="ml-5px cu-tag bg-red radius sm text-sm font-bold">
                                VIP
                            </view>
                        </view>
                        <view v-text="notify.content" class="body-2">
                        </view>
                        <view class="mt-5px text-gray">
                            {{notify.createTime | formatTime}}
                        </view>
                    </view>
                </view>

                <view class="flex-none" v-if="notify.replyImg">
                    <image class="card-title-avatar size65" mode="aspectFill"
                           :src="getTalkSmallImgUrl(notify.talkUserId,notify.replyImg)">
                    </image>
                </view>
                <view v-else class="size65 bg-gray text-df pl-4px row-all-center flex-none">
                    <text class="text-df text-ellipsis-3">
                        {{notify.replyContent}}
                    </text>
                </view>
            </view>
        </view>
    </view>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import { namespace } from 'vuex-class'
import UnreadNotifyVO from 'socialuni/model/UnreadNotifyVO'
import ImgUtil from 'socialuni/utils/ImgUtil'
import PagePath from 'socialuni/const/PagePath'
import PageUtil from 'socialuni/utils/PageUtil'
import RouterUtil from 'socialuni/utils/RouterUtil'

const appStore = namespace('app')

  /**
   * todo 需要支持发表情,数据库字段，img字段img改为了src, 所有被封禁的用户允许登录查看，但是不允许操作，提示账号已被封禁
   * 发说说的时候限制字符长度 140个字
   * 取消身份认证功能
   */
  @Component
export default class NotifyPage extends Vue {
    @appStore.State('notifies') notifies: UnreadNotifyVO[]

    toTalkDetailVue (talkId: number) {
      RouterUtil.navigateTo(PagePath.talkDetail + '?talkId=' + talkId)
    }

    getTalkSmallImgUrl (userId: string, src: string) {
      return ImgUtil.getTalkSmallImgUrl(userId, src)
    }
}
</script>
