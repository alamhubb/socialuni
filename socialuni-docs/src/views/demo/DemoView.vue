<template>
  <div class="px-smm divHeight overflow-x-hidden flex-col">
    <hello></hello>
    <el-alert type="warning" effect="dark" class="my-sm">
      <template #title>
        当前页面仅为面向开发者快速体验使用，更完整的功能请点击前往：
        <a href="https://socialuni.cn/app" target="_blank" class="color-white">
          https://socialuni.cn/app
        </a>
      </template>
    </el-alert>

    <div v-if="user" class="row-between-center font-bold font-md bb pb-sm flex-none">
      <div>用户名：{{ user ? user.nickname : '未登录' }}</div>
      <el-button :disabled="!user" @click="logout">退出</el-button>
    </div>

    <el-row :gutter="20" class="flex-1 overflow-hidden">
      <el-col :xs="24" :sm="12" class="h100p overflow-auto pb-sm">
        <el-collapse v-model="loginAPIActive" accordion class="by-none">
          <el-collapse-item :name="1">
            <template #title>
              <div class="font-bold font-md row-col-center">一、登录接口</div>
            </template>
            <div class="custom-block font-bold font-md">
              <div class="flex-none mb-xs">请求方式：GET</div>
              <el-link type="warning"
                       :href="AppConst.baseUrl+'/login?name='+ (userName ? userName : (user && user.nickname || 'xxx'))"
                       target="_blank">
                <div>
                  <div>
                    {{ AppConst.baseUrl }}/login?
                  </div>
                  <div>
                    name={{ userName ? userName : (user && user.nickname || 'xxx') }}
                  </div>
                </div>
              </el-link>
            </div>
          </el-collapse-item>
          <el-collapse-item v-if="user" :name="2" title="登录接口返回用户数据预览">
            <JsonViewer :value="user"/>
          </el-collapse-item>
        </el-collapse>
        <div v-if="!user" class="mb-sm">
          <div class="row-col-center my-sm">
            <star-filled class="mr-xs color-red4 w7"></star-filled>请输入用户名，然后点击登录
          </div>
          <div class="row-col-center">
            <el-input v-model="userName" placeholder="请输入任意用户名"></el-input>
            <el-button :disabled="user" class="ml-sm" @click="userLogin">登录</el-button>
          </div>
        </div>
        <div class="mt-md font-bold font-md row-col-center">
          二、发布内容
        </div>
        <el-tabs v-model="activeName" class="mb-sm">
          <el-tab-pane label="发布动态" name="talk">
            <div>
              <div class="custom-block font-bold font-md">
                <div class="flex-none mb-xs">请求方式：GET</div>
                <el-link type="warning" :href="AppConst.baseUrl+'/postTalk?content=?content='+talkContent"
                         target="_blank">
                  <div>
                    <div>
                      {{ AppConst.baseUrl }}/postTalk?
                    </div>
                    <div>
                      content={{ talkContent }}
                    </div>
                  </div>
                </el-link>
              </div>
              <div class="row-between-center my-sm">
                <div><star-filled class="mr-xs color-red4 w7"></star-filled>请输入您要发布的动态，点击发布</div>
                <el-button class="ml-sm" @click="postTalk">发布</el-button>
              </div>
              <div class="row-col-center">
                <el-input type="textarea" rows="3" v-model="talkContent"></el-input>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="发布评论" name="comment">
            <div>
              <div class="custom-block font-bold font-md">
                <div class="flex-none mb-xs">请求方式：GET</div>
                <el-link type="warning"
                         :href="AppConst.baseUrl+'/postComment?talkId='+((talks && talks.length && talks[0].id||'xxx'))+'&content='+commentContent"
                         target="_blank">
                  <div>
                    <div>
                      {{ AppConst.baseUrl }}/postComment?
                    </div>
                    <div>
                      talkId={{
                        (talks && talks.length && talks[0].id || 'xxx')
                      }}&
                    </div>
                    <div>
                      content={{ commentContent }}
                    </div>
                  </div>
                </el-link>
              </div>
              <div class="row-between-center my-sm">
                <div><star-filled class="mr-xs color-red4 w7"></star-filled>请输入您要评论的内容，点击评论</div>
                <el-button class="ml-sm" @click="postComment">评论</el-button>
              </div>
              <div class="row-col-center">
                <el-input type="textarea" rows="3" v-model="commentContent" placeholder="默认评论第一条动态"></el-input>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-col>

      <el-col v-if="talks" :xs="24" :sm="12" class="h100p overflow-hidden talkDataTabsDiv">
        <div class="font-bold font-md mt-sm">三、查询动态</div>

        <div class="custom-block font-bold font-md mt-sm">
          <div class="flex-none mb-xs">请求方式：GET</div>
          <el-link type="warning"
                   :href="AppConst.baseUrl+'/queryTalks'"
                   target="_blank">
            {{ AppConst.baseUrl }}/queryTalks
          </el-link>
        </div>
        <el-tabs v-model="talkActiveName" class="h100p" v-loading="talksLoading" element-loading-text="动态刷新中...">
          <el-tab-pane label="动态展示" name="preview">
            <div class="h100p overflow-auto pr-xs">
              <div v-for="talk in talks" class="mb-sm bd bd-radius pd-sm elevation-bottom">
                <div class="mb-sm row-col-center">
                  <img class="size40 mr-sm" :src="talk.user.avatar">
                  <div>
                    用户名：{{ talk.user.nickname }}
                  </div>
                </div>
                <div class="ml-xs">
                  动态内容：{{ talk.content }}
                </div>
                <div v-if="talk.comments.length" class="bg-grey6_light pd-sm bd-radius mt-sm">
                  <div>评论内容（仅显示最近三条评论）：</div>
                  <div class="mt-xs" v-for="comment in talk.comments.slice(0,3)">
                    {{ comment.user.nickname }}：{{ comment.content }}
                  </div>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="动态JSON预览" name="json">
            <div class="h100p overflow-auto pr-xs pb-sm">
              <JsonViewer :value="talks"/>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
  StarFilled
} from '@element-plus/icons-vue'
import {ref} from "vue";
import request from "@/plugins/request";
import TokenUtil from "@/utils/TokenUtil";
import ObjectUtil from "@/utils/ObjectUtil";
import ToastUtil from "@/utils/ToastUtil";
import AlertUtil from "@/utils/AlertUtil";
import AppConst from "@/constant/AppConst";
import Hello from "@/views/home/home.md";

const userName = ref('')
const user = ref(null)
const userJson = ref('')
const token = ref(TokenUtil.get())
const talkContent = ref('')
const commentContent = ref('')
const activeName = ref('talk')
const talkActiveName = ref('preview')
const loginAPIActive = ref(1)
const talksLoading = ref(false)


if (token.value) {
  const userRO: any = await request.get('/getMineUser', {params: {token: token.value}})
  user.value = userRO
  userJson.value = ObjectUtil.toJson(user.value)
}

//第一块用户信息，登录不登录各一种形式
//第二块发表动态
//三块展示

const talks: any = ref(await request.get('/queryTalks'))

async function queryTalks() {
  talksLoading.value= true
  const talkList: any[] = await request.get('/queryTalks') as any
  talks.value = talkList
  talksLoading.value= false
}

async function userLogin() {
  if (!userName.value) {
    ToastUtil.throwError('请输入用户名')
  }
  const loginRO: { user: any, token: string } = await request.get('/login', {params: {name: userName.value}})
  user.value = loginRO.user
  TokenUtil.set(loginRO.token)
  token.value = loginRO.token
  userJson.value = ObjectUtil.toJson(user.value)
  loginAPIActive.value = 2
  ToastUtil.success('登录成功')
  userName.value = ''
  await queryTalks()
}

async function logout() {
  await AlertUtil.confirm('是否确认退出')
  user.value = null
  TokenUtil.remove()
  ToastUtil.success('退出成功')
}

async function postTalk() {
  if (!user.value) {
    ToastUtil.throwError('请进行登录')
  }
  if (!talkContent.value) {
    ToastUtil.throwError('请填写动态内容')
  }
  await request.get('/postTalk', {params: {content: talkContent.value}})
  ToastUtil.success('发布动态成功')
  talkContent.value = ''
  await queryTalks()
}

async function postComment() {
  if (!user.value) {
    ToastUtil.throwError('请进行登录')
  }
  if (!talks.value.length) {
    ToastUtil.throwError('请先发布动态再进行评论')
  }
  if (!commentContent.value) {
    ToastUtil.throwError('请填写评论内容')
  }
  await request.get('/postComment', {params: {talkId: talks.value[0].id, content: commentContent.value}})
  ToastUtil.success('评论成功')
  commentContent.value = ''
  await queryTalks()
}

//如果未登录，可以登录不可以退出

</script>

<style lang="scss">
.jv-container .jv-code {
  padding: 0 10px !important;
}

.talkDataTabsDiv {
  .el-tabs {
    display: flex;
    flex-direction: column;

    .el-tabs__header {
      flex: none;
    }

    .el-tabs__content {
      flex: 1;
      overflow: hidden;

      .el-tab-pane {
        height: 100%;
      }
    }
  }
}

.divHeight {
  height: 100%;
}

@media (max-width: 767px) {
  .divHeight {
    height: auto;
  }
}
</style>

