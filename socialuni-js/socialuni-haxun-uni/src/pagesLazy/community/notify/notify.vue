<template>
  <div>
    <div class="bg-white py-10" v-for="(notify,index) in notifies" :key="index">
      <div v-if="notify.domainType === SocialuniNotifyCpDomainType.apply_cp">
        <div class="row-between px-sm">
          <div class="flex-row flex-1">
            <img class="card-title-avatar flex-none" mode="aspectFill"
                 :src="notify.user.avatar"/>
            <div class="flex-1">
              <div class="color-blue row-col-center py-mn">
                <span>{{ notify.user.nickname }}</span>
              </div>
              <div>
                {{ notify.hintMsg }}
              </div>
              <div class="mt-5 text-gray">
                {{ formatTime(notify.createTime) }}
              </div>
            </div>
          </div>

          <div class="flex-none row-col-center">
            <div class="row-col-center row-nowrap" v-if="getCpNotifyRO(notify).status === SocialuniConstStatus.pending">
              <q-button type="success" @click="acceptCpApply(notify.data)">同意</q-button>
              <q-button class="ml-sm" type="error">拒绝</q-button>
            </div>
            <div v-else-if="getCpNotifyRO(notify).status === SocialuniConstStatus.accept">
              已同意
            </div>
            <div v-else-if="getCpNotifyRO(notify).status === SocialuniConstStatus.refuse">
              已拒绝
            </div>
            <div v-else-if="getCpNotifyRO(notify).status === SocialuniConstStatus.expired">
              已过期
            </div>
          </div>
        </div>
      </div>


      <!--      <div class="card-title row-between" @click="toTalkDetailVue(notify.talkId)">
              <div class="flex-row flex-1">
                <img class="card-title-avatar flex-none" mode="aspectFill"
                     :src="notify.avatar"/>
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
                     :src="getTalkSmallImgUrl(notify.talkUserId,notify.replyImg)"/>
              </div>
              <div v-else class="size65 bg-grey9 font-df pl-4 row-all-center flex-none">
                      <span class="font-df text-ellipsis3">
                        {{ notify.replyContent }}
                      </span>
              </div>
            </div>-->
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import CommunityPageUtil from "socialuni-community-sdk/src/util/CommunityPageUtil";
import DateUtil from "qing-util/src/util/DateUtil";
import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import NotifyAPI from "socialuni-app-api/src/api/NotifyAPI";
import SocialuniNotifyCpDomainType from "socialuni-constant/constant/notify/SocialuniNotifyCpDomainType";
import QButton from "qingjs-ui/src/components/QButton.vue";
import SocialuniNotifyCpRO from "@/pagesLazy/community/notify/SocialuniNotifyCpRO";
import SocialuniConstStatus from "socialuni-constant/constant/status/SocialuniConstStatus";
import socialuniImRequest from "socialuni-im-api/src/api/socialuniImRequest";
import {socialuniChatModule} from "socialuni-im-sdk/src/store/SocialuniChatModule";


/**
 * 发说说的时候限制字符长度 140个字
 * 取消身份认证功能
 * todo 需要支持发表情,数据库字段，img字段img改为了src, 所有被封禁的用户允许登录查看，但是不允许操作，提示账号已被封禁
 */

@Component({components: {QButton}})
class NotifyPage extends Vue {
  notifies: NotifyVO<any>[] = []

  SocialuniConstStatus = SocialuniConstStatus

  SocialuniNotifyCpDomainType = SocialuniNotifyCpDomainType

  created() {
    this.queryNotifies()
  }

  queryNotifies() {
    NotifyAPI.queryNotifiesAndUpdateHasReadAPI().then(res => {
      this.notifies = res.data
    })
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

  getCpNotifyRO(notify: NotifyVO<SocialuniNotifyCpRO>): SocialuniNotifyCpRO {
    return notify.data
  }

  //接受cp申请
  async acceptCpApply(notifyCp: SocialuniNotifyCpRO) {
    await socialuniImRequest.post('haxun/cp/acceptCp/' + notifyCp.id)
    this.queryNotifies()
  }

}
export default toNative(NotifyPage)
</script>
