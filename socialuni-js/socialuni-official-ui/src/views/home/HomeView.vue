<template>
  <div class="col-between-center h100p">
    <div style="max-width: 900px">
      <div>
        <div class="font-32 font-bold color-theme mt-50">社交联盟</div>
        <div class="color-main font-32 font-bold">社交软件解决方案</div>
<!--        <div class="color-content font-18 mt-10">An approachable, performant and versatile framework for building web user interfaces.</div>-->
        <div class="color-content font-18 mt-10">提供免费的社交软件模板，丰富的用户非隐私数据，运营盈利支持</div>

        <div class="flex-row mt-30">
          <router-link to="/guide">
            <q-button round theme md>快速入门</q-button>
          </router-link>
          <router-link to="/demo" class="ml-10">
            <q-button round md color="grey10" class="color-content">体验demo</q-button>
<!--            <div class="btn-gray-light use-click ml-20 font-16">Get Started</div>-->
          </router-link>
        </div>
      </div>

      <div class="row-wrap row-between-center pt-30">
        <div class="w33p mt-20px">
          <div class="bg-default pd bd-radius mr-20">
            <div class="font-md font-bold">开发提效</div>
            <div class="color-content mt-10px">社交领域前后端业务组件、模板，约定优于配置原则，支持自定义</div>
          </div>
        </div>
        <div class="w33p mt-20px">
          <div class="bg-default pd bd-radius mr-20">
            <div class="font-md font-bold">免费的数据支持</div>
            <div class="color-content mt-10px">免费提供社交联盟中其他社交软件的用户授权开放的数据供您使用</div>
          </div>
        </div>
        <div class="w33p mt-20px">
          <div class="bg-default pd bd-radius mr-20">
            <div class="font-md font-bold">运营支持</div>
            <div class="color-content mt-10px">提供社交领域运营解决方案，用户拉新、盈利方案、垃圾信息过滤等</div>
          </div>
        </div>
      </div>


    </div>

    <div class="flex-none text-tip body-2 flex-col mt">
      <div class="row-center row-wrap">
        <div class="text-black">©2019-2023 社交联盟</div>
        <div class="mx-xs">|</div>
        <div>清池科技滦州市有限公司</div>
        <div class="mx-xs">|</div>
        <div><a href="https://beian.miit.gov.cn/#/Integrated/index" target="_blank">冀ICP备19028721号-4</a></div>
        <!--            <div class="mx-1">|</div>
                    <div>公众号：<a
                        href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI3NTE5NzE3NA==&scene=124#wechat_redirect"
                        target="_blank">qingchicn</a></div>-->
      </div>
      <div class="mt-xs row-center row-wrap">
        <!--            <div>
                      客服微博：
                      <a href="https://weibo.com/u/2724054747" target="_blank">清池恋爱交友app</a>
                    </div>
                    <div class="mx-1">|</div>-->
        <div>
          联系微信：
          <a target="_blank"
             href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI3NTE5NzE3NA==&scene=124#wechat_redirect">
            491369310
          </a>
        </div>
        <div class="mx-xs">|</div>
        <div>
          联系QQ：
          <a target="_blank"
             href="https://wpa.qq.com/msgrd?v=3&uin=491369310&site=qq&menu=yes">
            491369310
          </a>
        </div>
        <div class="mx-xs">|</div>
        <div>
          联系邮箱：
          <a href="mailto:socialuni@163.com">socialuni@163.com</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import socialuniAppRequest from "socialuni-app-api/src/socialuniAppRequest.ts";
import QButton from "qingjs-ui/src/components/QButton.vue";


@Component({
  components: {QButton}
})
class HomeView extends Vue {
}
export default toNative(HomeView)
</script>
