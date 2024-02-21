<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <s-nav-bar title="维洛斯"/>
    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
    <div>
      <div class="flex-none text-tip body-2 flex-col">
        <div class="row-center row-wrap">
          <div class="text-black">©2023-2024 维洛斯</div>
          <div class="mx-xs">|</div>
          <div>清池科技滦州市有限公司</div>
          <div class="mx-xs">|</div>
          <div><a href="https://beian.miit.gov.cn/" target="_blank">冀ICP备19028721号-6</a></div>



          <!--            <div class="mx-1">|</div>
                      <div>公众号：<a
                          href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI3NTE5NzE3NA==&scene=124#wechat_redirect"
                          target="_blank">qingchicn</a></div>-->
        </div>
        <div class="mt-2 row-center row-wrap">
          <!--            <div>
                        客服微博：
                        <a href="https://weibo.com/u/2724054747" target="_blank">清池恋爱交友app</a>
                      </div>
                      <div class="mx-1">|</div>-->
          <div>
            客服微信：
            <a target="_blank"
               href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI3NTE5NzE3NA==&scene=124#wechat_redirect">
              alamhubb
            </a>
          </div>
          <div class="mx-1">|</div>
          <div>
            客服QQ：
            <a target="_blank"
               href="https://wpa.qq.com/msgrd?v=3&uin=491369310&site=qq&menu=yes">
              491369310
            </a>
          </div>
          <div class="mx-1">|</div>
          <div>
            联系邮箱：
            <a href="mailto:qingchiapp@163.com">qingchiapp@163.com</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator';
import SNavBar from "socialuni-user-view-h5/src/components/SNavBar.vue";

@Component({
  components: {SNavBar}
})
export default class App extends Vue {
}
</script>
