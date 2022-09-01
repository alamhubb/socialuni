<template>
  <div>
    <div class="md:ml-15% md:mr-5%">
      <div class="font-32px sm:font-44px font-bold color-theme mt-20">社交联盟</div>
      <div class="color-blue-gray700 font-32 sm:text-44px font-bold">开源社交软件开发集成解决方案</div>
      <div class="color-grey400 font-18 sm:text-20px mt-sm">提供免费的社交软件模板，丰富的用户非隐私数据，运营盈利支持
      </div>

      <div class="flex-row mt-md">
        <router-link to="/guide">
          <div class="q-button-theme">快速入门</div>
        </router-link>

        <!--      <div class="q-button ml bg-gray100">体验 demo</div>-->
      </div>

      <div class="row-wrap pt-30px">
        <div class="w100% sm:w50% lg:w33% mt-20px">
          <div class="bg-default pd-20 bd-radius  mr-20px">
            <div class="font-md font-bold">开发提效</div>
            <div class="color-grey15 mt-10px">社交领域前后端业务组件、模板，约定优于配置原则，支持自定义</div>
          </div>
        </div>
        <div class="w100% sm:w50% lg:w33% mt-20px">
          <div class="bg-default pd-20 bd-radius  mr-20px">
            <div class="font-md font-bold">免费的数据支持</div>
            <div class="color-grey15 mt-10px">免费提供社交联盟中其他社交软件的用户授权开放的数据供您使用</div>
          </div>
        </div>
        <div class="w100% sm:w50% lg:w33% mt-20px">
          <div class="bg-default pd-20 bd-radius  mr-20px">
            <div class="font-md font-bold">运营支持</div>
            <div class="color-grey15 mt-10px">提供社交领域运营解决方案，用户拉新、盈利方案、垃圾信息过滤等</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import Markdown from "@/components/Markdown.vue";
</script>
