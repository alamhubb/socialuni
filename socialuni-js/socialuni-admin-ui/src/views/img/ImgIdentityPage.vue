<template>
  <el-container fluid>

    <div>
      <el-row>
        <el-col
          cols="12"
        >
          <el-row>
            <el-col cols="3">页数：</el-col>
            <el-col cols="5">
              <el-text-field
                v-model="page"
                label="Label"
              />
            </el-col>
            <el-col cols="4">
              <el-btn @click="initQuery">查询</el-btn>
            </el-col>
          </el-row>
        </el-col>
      </el-row>

    </div>
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
import { Component, Vue , toNative} from 'vue-facing-decorator'
import Talk from '@/model/talk/Talk'
import ReportVO from '@/model/report/ReportVO'
import Identity from '@/model/user/Identity'
import ImgIdentityAPI from '@/api/ImgIdentityAPI'
import ViolateType from '@/constant/ViolateType'


@Component
class ImgIdentity extends Vue {
    page = 0

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

    initQuery() {
      if (!this.page) {
        this.page = 0
      }
      ImgIdentityAPI.getIdentityPage(this.page).then((res) => {
        this.identities = res.data
      })
    }

    created() {
      this.initQuery()
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
          imgUrl = this.imgUrl + 'user/' + userId + '/home/normal/' + src
        }
      }
      return imgUrl
    }
}
export default toNative(ImgIdentity)
</script>

<style scoped>

</style>
