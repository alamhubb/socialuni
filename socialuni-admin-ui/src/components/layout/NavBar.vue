<template>
  <div
    class="row-between box-shadow px"
  >
    <div class="flex-none row-col-center mr-lg">
      <img src="@/assets/img/logo.jpg" class="h40" alt="logo">
      <div class="font-lgg ml-mn mb-mn font-bold color-social">社交软件联盟</div>
    </div>

    <div class="flex-1 row-end">
      <nav-menu class="flex-none bb-0" />
      <div class="flex-none row-col-center pointer mr">
        <el-divider direction="vertical" class="mr" />
        <i class="mdi mdi-github font-sl" />
      </div>
      <div class="flex-1 row-end-center">
        <div v-if="user" class="row-col-center">
          <el-tag class="mr-sm" type="warning" effect="dark">内测账号</el-tag>
          <el-dropdown>
            <div>
              <el-avatar shape="square" src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png" />
              <i class="el-icon-caret-bottom el-icon--right" />
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>{{
                user ? user.username : '未登录'
              }}
              </el-dropdown-item>
              <el-dropdown-item divided @click.native="longinOut">退出登陆</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>

      <!--        <el-dropdown trigger="click" class="flex-none">
        <div class="el-dropdown-link color-white pointer row-end-center h100p pr-md">
          {{ user ? user.username : '18600' }}
          <i class="el-icon-arrow-down el-icon&#45;&#45;right" />
        </div>
        <el-dropdown-menu slot="dropdown">

        </el-dropdown-menu>
      </el-dropdown>-->
    </div>
  </div>
  <!--    <nav-breadcrumb />-->
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import NavMenu from '@/components/layout/NavMenu.vue'
import NavBreadcrumb from '@/components/layout/NavBreadcrumb.vue'
import UserVO from '@/model/base/UserVO'

import { namespace } from 'vuex-class'
import { userModule } from '@/store'

const userStore = namespace('user')

@Component({
  components: { NavBreadcrumb, NavMenu }
})
export default class NavBar extends Vue {
  @userStore.State('user') user: UserVO

  longinOut() {
    userModule.userLoginOut()
  }
}
</script>
