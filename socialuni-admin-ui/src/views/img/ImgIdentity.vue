<template>
  <el-container fluid>
    <!--<div>
      <div>
        {{identity.src}}
        用户
        昵称，id
      </div>
      <div>
        审核照片
      </div>
      <div>
        照片墙照片
      </div>
      <div>
        是否通过，通过绿色，没通过红色
      </div>
    </div>-->
    <el-row>
      <el-col cols="12">
        <el-row v-for="identity in identities">
          <el-col cols="2">
            <div>
              <el-avatar>
                <img
                  :src="identity.user.avatar"
                  alt="John"
                >
              </el-avatar>
              {{ identity.user.nickname }}
            </div>
          </el-col>
          <el-col cols="2">
            {{ identity.status }}
          </el-col>
          <el-col cols="2">
            <el-img
              :src="getImgUrl(identity.src,identity.user.id)"
              aspect-ratio="1"
            />
          </el-col>
          <el-col cols="6">
            <el-row v-if="identity.user.imgs">
              <el-col
                v-for="img in identity.user.imgs"
                :key="img.id"
                cols="4"
              >
                <el-img
                  :src="getImgUrl(img.src,identity.user.id)"
                  aspect-ratio="1"
                />
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </el-container>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'
import ReportAPI from '@/api/ReportAPI'
import Talk from '@/model/talk/Talk'
import ReportVO from '@/model/report/ReportVO'
import ViolateType from '../audit/ViolateType'
import TalkAPI from '@/api/TalkAPI'
import Identity from '@/model/user/Identity'
import ImgIdentityAPI from '@/api/ImgIdentityAPI'

  @Component
export default class ImgIdentity extends Vue {
    reports: ReportVO[] = []
    imgUrl: string = process.env.VUE_APP_COS_URL
    reportType: string = ViolateType.pornInfo
    reportTypes: string[] = []
    auditNote = '未发现违规内容'
    talkId: number = null
    userId: number = null
    talk: Talk = null
    userReports: ReportVO[] = []
    identities: Identity[] = []

    initQueryData() {
      this.talk = null
      this.userReports = []
    }

    created() {
      console.log('查询')
      ImgIdentityAPI.queryIdentities().then((res) => {
        this.identities = res.data
      })
    }

    getImgUrl(src: string, userId: number): string {
      console.log(src)
      // 如果包含'/'则代表是新逻辑
      let imgUrl
      if (src.indexOf('https') > -1) {
        imgUrl = src
      } else {
        if (src.split('/').length > 1) {
          imgUrl = this.imgUrl + src
        } else {
          imgUrl = this.imgUrl + 'user/' + userId + '/talk/normal/' + src
        }
      }
      return imgUrl
    }
}
</script>

<style scoped>

</style>
