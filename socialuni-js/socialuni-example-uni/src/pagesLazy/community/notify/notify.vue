<template>
  <view>
    <view class="card" v-for="(notify,index) in notifies" :key="index">
      <view class="card-title row-between" @click="toTalkDetailVue(notify.talkId)">
        <view class="flex-row flex-1">
          <image class="card-title-avatar flex-none" mode="aspectFill"
                 :src="notify.avatar"></image>
          <view class="flex-1">
            <view :class="notify.vipFlag?'color-red':'color-blue'" class="row-col-center py-mn">
              <text>{{ notify.nickname }}</text>
              <view v-if="notify.vipFlag" class="ml-5px cu-tag bg-red radius sm text-sm font-bold">
                VIP
              </view>
            </view>
            <view>
              {{ notify.content }}
            </view>
            <view class="mt-5px text-gray">
              {{ formatTime(notify.createTime) }}
            </view>
          </view>
        </view>

        <view class="flex-none" v-if="notify.replyImg">
          <image class="card-title-avatar size65" mode="aspectFill"
                 :src="getTalkSmallImgUrl(notify.talkUserId,notify.replyImg)">
          </image>
        </view>
        <view v-else class="size65 bg-grey9 font-df pl-4 row-all-center flex-none">
          <text class="font-df text-ellipsis3">
            {{ notify.replyContent }}
          </text>
        </view>
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import CommunityPageUtil from "socialuni-community-sdk/src/util/CommunityPageUtil";
import DateUtil from "qing-util/src/util/DateUtil";
import {socialNotifyModule} from "socialuni-community-sdk/src/store/SocialNotifyModule";


/**
 * 发说说的时候限制字符长度 140个字
 * 取消身份认证功能
 * todo 需要支持发表情,数据库字段，img字段img改为了src, 所有被封禁的用户允许登录查看，但是不允许操作，提示账号已被封禁
 */
@Component({})
export default class NotifyPage extends Vue {
  get notifies() {
    return socialNotifyModule.notifies
  }

  formatTime(dateStr) {
    return DateUtil.formatTime(dateStr)
  }

  toTalkDetailVue(talkId: string) {
    CommunityPageUtil.toTalkDetail(talkId)

  }

  getTalkSmallImgUrl(userId: string, src: string) {
    return SocialuniImgUtil.getTalkSmallImgUrl(userId, src)
  }
}
</script>
