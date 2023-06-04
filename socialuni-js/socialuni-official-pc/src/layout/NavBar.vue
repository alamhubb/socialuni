<template>
    <div class="row-between shadow-bottom index-sm px-smm h50">
        <div class="flex-none row-col-center mr-40 bg-click" @click="toHome">
            <!--      <img src="@/assets/img/logo.jpg" class="h40" alt="logo">-->
            <div class="font-19 font-bold">演示系统</div>
        </div>

        <div class="flex-1 row-end-center">
            <div class="flex-none row-col-center mr">
                <a href="https://socialuni.cn" target="_blank" class="mr-sm md:mr bg-click">
                    <div class="row-all-center">官网文档</div>
                </a>
                <el-divider direction="vertical" class="mr"/>
                <a href="https://socialuni.cn/demo" target="_blank" class="mr-sm md:mr bg-click">
                    <div class="row-all-center">demo演示</div>
                </a>
                <el-divider direction="vertical" class="mr"/>
                <div class="row-col-center">
                    <a href="https://gitee.com/socialuni/socialuni" target="_blank" class="mr-sm md:mr">
                        <div class="row-all-center"><img src="@/assets/imgs/giteelogo.png" class="use-click size31"/>
                        </div>
                    </a>
                    <a href="https://github.com/social-uni/socialuni" target="_blank" class="md:mr-sm">
                        <div class="row-all-center"><i class="mdi mdi-github font-36 use-click color-black"/></div>
                    </a>
                </div>
            </div>
            <div class="row-col-center">
                <el-avatar v-if="!mineUser" class="use-click" @click="toLogin">登录</el-avatar>
                <div v-else>
                    <el-tag class="mr-10" type="warning" effect="dark">{{ mineUser.nickname }}
                    </el-tag>
                    <el-dropdown>
                        <el-avatar shape="square"
                                   src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"/>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item divided @click.native="longinOut">退出登陆</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import {ArrowDown} from "@element-plus/icons-vue";

@Options({
    components: {ArrowDown}
})
export default class NavBar extends Vue {


    toHome() {
        this.$router.push('/')
    }

    toLogin(){
        this.$router.push('/login')
    }

    get mineUser() {
        console.log(socialuniUserModule.mineUser)
        return socialuniUserModule.mineUser
    }
}
</script>
