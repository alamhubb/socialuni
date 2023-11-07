<template>
  <br>
  <section>
    <el-input
        :autofocus="focus"
        :clearable="true"
        type="textarea"
        placeholder="请输入内容"
        v-model="value"
        maxlength="140"
        show-word-limit>
    </el-input>
    <div class="button">
      <el-button type="success" round @click="sendComment">评论</el-button>
    </div>
    <br>
    <el-descriptions v-if="comment.length" title="精彩评论" direction="vertical" :column="2">
      <template v-for="item in comment" :key="item?.commentId">
        <el-descriptions-item>
          <main class="left">
            <div><el-avatar :size="50" :src="item?.user.avatarUrl"></el-avatar></div>
            <div class="comment">
              <p><span class="user">{{item?.user.nickname}}: </span>{{item.content}}</p>
              <p class="time">{{$formatTime(item.time)}}</p>
            </div>
          </main>
        </el-descriptions-item>
        <el-descriptions-item align="right">
          <div class="right">
            <i class="iconfont icon-zan"><span class="good">{{item.likedCount}}</span></i>
            <span class="solid">|</span><i class="iconfont icon-fenxiang1"></i>
            <span class="solid">|</span><i @click="reply(item)" class="iconfont icon-pinglun"></i>
          </div>
        </el-descriptions-item>
      </template>

      <template #extra>
        <el-button type="info" round @click="changeComment" size="mini" icon="el-icon-refresh-left">
          {{boolean ? '最新评论' : '热门评论'}}
        </el-button>
      </template>
    </el-descriptions>
    <el-empty v-else description="还没有评论, 快来抢沙发~"></el-empty>
  </section>
  <div class="pages">
    <el-pagination v-if="comment.length" background
                   @current-change="change"
                   layout="prev, pager, next"
                   :total="total">
    </el-pagination>
  </div>
</template>

<script setup>
import {computed, onMounted,ref} from "vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {ElMessage} from "element-plus";
import {getComment,getSendComment} from '@/network/comment.js'

const store = useStore()
const id = computed(() => store.state.songDetail.commentID)

let params = {
  id:id.value,
  type:2,
  limit:10,
  offset:0,
  timestamp:Date.now()
}
let comment = ref([])
let hotComment = ref([])
let newComment = ref([])
let total = ref()

getComment(params).then(res => {
  console.log(res)
  newComment.value = res.data.comments
  comment.value = res.data.comments
  hotComment.value = res.data.hotComments
  total.value = res.data.total
  store.commit('setCount',res.data.total)
})

const change = value => {
  params.offset = value
  getComment(params).then(res => {
    comment.value = res.data.comments
  })
}
//切换热评评论
let boolean = ref(false)
const changeComment = () => {
  boolean.value = !boolean.value
  if (boolean.value){
   comment.value = hotComment.value
  }else {
    comment.value = newComment.value
  }
}
//发送评论
const router = useRouter()
let value = ref('')
let focus = ref(true)
let commentParams = {
  t:1,
  type:2,
  id:id.value,
  content:value.value,
  timestamp:Date.now(),
  commentId:''
}
const sendComment = async () => {
  commentParams.content = value.value
  await getSendComment(commentParams)
  let res = await getComment(params)
  comment.value = res.data.comments
  value.value = ''
  commentParams.commentId = ''
  ElMessage.success({
    message: '评论成功',
    type: 'success'
  })
}
const reply = item => {
  value.value = '回复'+item.user.nickname+': '
  commentParams.commentId = item.commentId
  focus.value = true
}
</script>

<style scoped lang="scss">
.pages{
  display:  flex;
  justify-content: center;
}
section{
  padding: 0 10px;
  .button{
    margin-top: 10px;
    text-align: right;
  }
  &:deep(.el-textarea__inner){
    min-height: 80px !important;
  }
  .left{
    height: 70px;
    display: flex;
    justify-content: flex-start;
    align-items: center;
    .comment{
      margin-left: 10px;
      .user{
        color: #85b9e6;
      }
      .time{
        color: #949191;
        font-size: 12px;
      }
    }
  }
  .right{
    display: flex;
    align-items: center;
    justify-content: flex-end;
    .good{
      font-size: 15px;
    }
    .solid{
      color: silver;
    }
    .iconfont{
      font-size: 20px;
      margin: 0 8px;
    }
  }
}
</style>
