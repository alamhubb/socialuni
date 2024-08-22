<template>
  <div class="h100p">
    <div>
<!--      {{ data }}-->
    </div>
    <div>总积分1.1亿</div>

    已分配积分，，待分配积分， 已销毁积分，每日销毁 2w积分
    您目前拥有的积分xxx，您的积分排名，


    每日从


    积分剩余

    <div>

    </div>
    您今日的贡献值为 {{data.contributeValue}} ， 今日贡献值排名第 {{data.contributeRank}}，名 ，按当前贡献值，您今日可获得积分，{{ data.assignPoints}}，

    计算方式为， 每日登录100积分 + 在线时长 {{data.onlineMinute}} 分钟 * 10 + 发送消息数{{ data.sendMsgNum}} * 30 * (1+ {{ data.useMultiple / 10}}(连续每日登录加成) + (今日充值加成))
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator';
import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest.ts";
import SocialuniPartnerUserContributeRO from "@/views/partner/model/SocialuniPartnerUserContributeRO.ts";

@toNative
@Component({
  components: {}
})
export default class MusicHome extends Vue {

  data: SocialuniPartnerUserContributeRO = null

  created() {
    this.queryInit()
  }

  async queryInit() {
    const res = await socialuniUserRequest.get('socialuni/partner/getMinePointsAssignDetail')
    console.log(res.data)
    this.data = res.data
  }
}
</script>
