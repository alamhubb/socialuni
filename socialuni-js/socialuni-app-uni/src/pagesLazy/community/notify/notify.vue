<template>
  <div>
    <div class="card" v-for="(notify,index) in notifies" :key="index">
      <div class="card-title row-between" @click="toTalkDetailVue(notify.talkId)">
        <div class="flex-row flex-1">
          <img class="card-title-avatar flex-none" mode="aspectFill"
                 :src="notify.avatar"></img>
          <div class="flex-1">
            <div :class="notify.vipFlag?'color-red':'color-blue'" class="row-col-center py-mn">
              <span>{{ notify.nickname }}</span>
              <div v-if="notify.vipFlag" class="ml-5px cu-tag bg-red radius sm text-sm font-bold">
                VIP
              </div>
            </div>
            <div>
              {{ notify.content }}
            </div>
            <div class="mt-5px text-gray">
              {{ formatTime(notify.createTime) }}
            </div>
          </div>
        </div>

        <div class="flex-none" v-if="notify.replyImg">
          <img class="card-title-avatar size65" mode="aspectFill"
                 :src="getTalkSmallImgUrl(notify.talkUserId,notify.replyImg)">
          </img>
        </div>
        <div v-else class="size65 bg-grey9 font-df pl-4 row-all-center flex-none">
          <span class="font-df text-ellipsis3">
            {{ notify.replyContent }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import CommunityPageUtil from "socialuni-community-sdk/src/util/CommunityPageUtil";
import DateUtil from "qing-util/src/util/DateUtil";
import {socialNotifyModule} from "socialuni-community-sdk/src/store/SocialNotifyModule";


/**
 * 发说说的时候限制字符长度 140个字
 * 取消身份认证功能
 * todo 需要支持发表情,数据库字段，img字段img改为了src, 所有被封禁的用户允许登录查看，但是不允许操作，提示账号已被封禁
 */
@toNative
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
