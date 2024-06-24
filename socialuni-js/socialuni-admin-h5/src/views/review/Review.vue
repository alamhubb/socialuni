<template>
  <div>
    <query>
      <div>查询条件：
        <el-select v-model="value" placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </div>
    </query>

    <el-table
      height="100"
      class="flex-1 mt-10px"
      :data="userIdCards"
      border
      style="width: 100%"
      :row-class-name="tableRowClassName"
    >
      <el-table-column
        prop="userId"
        label="用户id"
        width="60"
      />
      <el-table-column
        prop="nickname"
        label="昵称"
        width="100"
      />
      <el-table-column
        prop="gender"
        label="性别"
        width="60"
      />
      <el-table-column
        label="时间"
        width="200"
      >
        <template #default="{row}">
          {{ row.createDate.toLocaleString() }}
        </template>
      </el-table-column>
      <el-table-column label="照片" width="830">
        <template #default="{row}">
          <div class="flex-row">
            <div v-for="(img,index) in row.imgs">
              <div class="h225 mr-10" @click="img.checked=!img.checked">
                <el-image
                  class="w150"
                  :src="img.img"
                  :preview-src-list="previewImgs"
                  @click="preImg(index+1,row)"
                />
              </div>
              <div class="row-center pt-20" @click="img.checked=!img.checked">
                <el-checkbox
                  class="bg-danger"
                  :value="img.checked"
                  size="medium"
                  border
                  @change="img.checked=!img.checked"
                />
              </div>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column
        label="身份证"
        width="320"
      >
        <template #default="{row}">
          <div>
            <el-checkbox
              class="bg-warning"
              size="medium"
              border
              :value="getRowChecked(row)"
              @change="row.checked = !row.checked"
            />
            <el-image
              class="w300 h200"
              :src="row.idCard"
              :preview-src-list="previewImgs"
              @click="preImg(0,row)"
            />
          </div>
        </template>
      </el-table-column>

      <!--      如果不选择照片点击通过，则仅是身份证认证通过，其他照片均未通过认证，删除其他照片
            如果选择几张照片通过，则其他照片未通过。
            如果选择全选通过，则全部通过

            认证以后，每次上传照片都需要审核，第一次认证，未通过30天无法认证，已认证通过，未通过，则删除照片

            如果为已认证，则颜值加20分，根据用户的认证状态
      -->

      <!--    todo  上传身份证界面有问题，如果自己跳过去的，操作会有问题，后台需要加限制，如果已经上传则不允许再次上传-->

      <el-table-column label="操作" width="300">
        <template #default="{row}">
          <el-button type="danger" @click="auditRejectIdCards(row)">拒绝</el-button>
          <el-button type="warning" @click="checkAllImg(row)" @dblclick.native="rowAllImgPass(row)">全选/通过</el-button>
          <el-button type="primary" @click="auditPassIdCards(row)">通过</el-button>
        </template>
      </el-table-column>

      <el-table-column label="选中" width="300">
        <template #default="{row}" />
      </el-table-column>
    </el-table>
    <bottom class="pb-50">
      <div class="col-center mr-10">
        已选{{ checkedIdCards.length }}/共{{ userIdCards.length }}
      </div>
      <el-button type="danger" @click="auditRejectIdCards">拒绝</el-button>
      <el-button type="success" @click="auditPassIdCards">通过</el-button>
    </bottom>
  </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator'
import UserIdCard from '@/model/UserIdCard'
import CommonStatus from '@/constant/CommonStatus'
import ObjectUtils from '@/utils/ObjectUtils'

@toNative
@Component({})
export default class Review extends Vue {
    data: number [] = [1, 2, 3, 4, 5]
    options: number [] = [1, 2, 3, 4, 5]
    value = 1
    previewImgs: string [] = []

    userIdCards: UserIdCard [] = []

    created() {
      /* this.$post('idCard/queryIdCards').then(res => {
        this.userIdCards = UserIdCard.getUsersByVOs(res.data)
      })*/
    }

    tableRowClassName({ row }) {
      if (this.getRowChecked(row)) {
        return 'bg-success'
      }
    }

    // 单行全部通过
    rowAllImgPass(row: UserIdCard) {
      this.checkAllImg(row)
      this.auditPassIdCards(row)
    }

    // 查看本行是否选中
    getRowChecked(row: UserIdCard) {
      return row.imgs.some(item => item.checked) || row.checked
    }

    // 选中本行全部图片
    checkAllImg(row: UserIdCard) {
      // 如果有没选中的全选，否则反选
      if (row.imgs.some(item => !item.checked)) {
        row.imgs.forEach(item => item.checked = true)
      } else {
        row.imgs.forEach(item => item.checked = !item.checked)
      }
    }

    // 拒绝本行
    auditRejectIdCards(row?: UserIdCard) {
      if (row) {
        row.checked = true
      }
      this.auditIdCards(CommonStatus.notCertified)
    }

    // 通过本行
    auditPassIdCards(row?: UserIdCard) {
      if (row) {
        row.checked = true
      }
      this.auditIdCards(CommonStatus.certified)
    }

    get checkedIdCards() {
      return this.userIdCards.filter(item => this.getRowChecked(item))
    }

    auditIdCards(auditType: string) {
      if (this.checkedIdCards.length) {
        // 获取勾选的内容
        const idCards: UserIdCard[] = ObjectUtils.deepClone(this.checkedIdCards)
        for (const idCard of idCards) {
          idCard.imgs = idCard.imgs.filter(item => item.checked)
        }
        /* this.$confirm(`请确认是否进行审核:${auditType}`).then(() => {
          this.$post('idCard/audit', {
            idCards: idCards,
            auditResultType: auditType
          })
        })*/
      } else {
        this.$alert('请至少选择一条记录审核')
      }
    }

    // 根据当前点击的图片进行展示
    preImg(index: number, user: UserIdCard) {
      const previewImgs = user.previewImgs
      // 得到当前索引之后的数组
      const nextAry: string [] = previewImgs.slice(index)
      // 得到当前索引之前的数组
      const preAry: string [] = previewImgs.slice(0, index + 1)
      this.previewImgs = nextAry.concat(preAry)
    }
}
</script>
