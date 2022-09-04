<template>
  <div class="divHeight overflow-x-hidden flex-col">
    <el-alert type="warning" effect="dark" class="mb">
<!--      <template #title>
        当前页面仅为面向开发者快速体验使用，更完整的功能请点击前往：
        <a href="https://socialuni.cn/app" target="_blank" class="color-white">
          https://socialuni.cn/app
        </a>
      </template>-->
      <template #title>
        当前页面仅为面向开发者快速体验使用，本页面项目代码详见：
        <a href="https://gitee.com/socialuni/socialuni/tree/master/socialuni-embed-demo" target="_blank" class="color-white">
          socialuni-embed-demo /（-ui前端)
        </a>
      </template>
    </el-alert>
    <el-row :gutter="20" class="flex-1 overflow-auto">
      <el-col :xs="24" :sm="12" class="h100% overflow-auto pb-10">
        <el-collapse v-model="loginAPIActive" accordion class="by-0">
          <el-collapse-item :name="1">
            <template #title>
              <div class="font-bold font-md row-col-center">一、登录接口</div>
            </template>
            <div class="note-primary">
              <div class="flex-none mb-xs font-bold font-16">请求方式：GET</div>
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
            <star-filled class="mr-xs color-red4 w7"></star-filled>
            请输入用户名，然后点击登录
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
              <div class="note-primary font-bold font-md">
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
                <div>
                  <star-filled class="mr-xs color-red4 w7"></star-filled>
                  请输入您要发布的动态，点击发布
                </div>
                <el-button class="ml-sm" @click="postTalk">发布</el-button>
              </div>
              <div class="row-col-center">
                <el-input type="textarea" rows="3" v-model="talkContent"></el-input>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="发布评论" name="comment">
            <div>
              <div class="note-primary font-bold font-md">
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
                <div>
                  <star-filled class="mr-xs color-red4 w7"></star-filled>
                  请输入您要评论的内容，点击评论
                </div>
                <el-button class="ml-sm" @click="postComment">评论</el-button>
              </div>
              <div class="row-col-center">
                <el-input type="textarea" rows="3" v-model="commentContent" placeholder="默认评论第一条动态"></el-input>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-col>

      <el-col v-if="talks" :xs="24" :sm="12" class="h100% overflow-hidden talkDataTabsDiv">
        <div class="font-bold font-md mt-sm">三、查询动态</div>

        <div class="note-primary font-bold font-md mt-sm">
          <div class="flex-none mb-xs">请求方式：GET</div>
          <el-link type="warning"
                   :href="AppConst.baseUrl+'/queryTalks'"
                   target="_blank">
            {{ AppConst.baseUrl }}/queryTalks
          </el-link>
        </div>
        <el-tabs v-model="talkActiveName" class="h100%" v-loading="talksLoading" element-loading-text="动态刷新中...">
          <el-tab-pane label="动态展示" name="preview">
            <div class="h100% overflow-auto pr-xs">
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
                <div v-if="talk.comments.length" class="bg-grey6-light pd-sm bd-radius mt-sm">
                  <div>评论内容（仅显示最近三条评论）：</div>
                  <div class="mt-xs" v-for="comment in talk.comments.slice(0,3)">
                    {{ comment.user.nickname }}：{{ comment.content }}
                  </div>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="动态JSON预览" name="json">
            <div class="h100% overflow-auto pr-xs pb-sm">
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
import {computed, ref} from "vue";
import AppConst from "@/constant/AppConst";
import UserStore from "@/store/UserStore";
import ToastUtil from "@/utils/ToastUtil";
import request from "@/plugins/request";
import TokenUtil from "@/utils/TokenUtil";
import ObjectUtil from "@/utils/ObjectUtil";

const userName = ref('')

const user = computed(() => {
  return UserStore.user
})

const talkContent = ref('')
const commentContent = ref('')
const activeName = ref('talk')
const talkActiveName = ref('preview')
const loginAPIActive = ref(1)
const talksLoading = ref(false)
const userJson = computed(() => {
  return ObjectUtil.toJson(UserStore.user)
})
const token = TokenUtil.get()
if (token) {
  request.get('/getMineUser').then((res) => {
    UserStore.setUser(res.data)
  })
}

async function userLogin() {
  if (!userName.value) {
    ToastUtil.throwError('请输入用户名')
  }
  const loginRO: { user: any, token: string } = await request.get('/login', {params: {name: userName.value}})
  UserStore.setUser(loginRO.user)
  TokenUtil.set(loginRO.token)
  loginAPIActive.value = 2
  ToastUtil.success('登录成功')
  userName.value = ''
  queryTalks()
}

//第一块用户信息，登录不登录各一种形式
//第二块发表动态
//三块展示

const talks: any = ref([])
queryTalks()

async function queryTalks() {
  talksLoading.value = true
  const res: any = await request.get('/queryTalks') as any
  const talkList: any[] = res.data
  talks.value = talkList
  talksLoading.value = false
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

