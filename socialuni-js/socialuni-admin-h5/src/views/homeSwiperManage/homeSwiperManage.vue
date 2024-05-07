<template>
  <div class="h100p flex-col">
    <div class="row-between-center flex-none px py-sm bb">
      <div class="font-bold font-16">
        轮播图配置
      </div>
      <div>
        <y-button type="primary" @click="saveData">保存</y-button>
        <el-button type="success" @click="addData">新增</el-button>
      </div>
    </div>

    <y-scrollbar class="flex-1">
      <div class="pd-sm">
        <div v-for="(swiper,index) in homeSwipers" class="bd bd-radius py mt-sm mr-sm pr-sm">
          <el-form>
            <div class="flex-row">
              <y-form-input v-model="swiper.name" label="轮播图名称" class="flex-1 ml-sm">

              </y-form-input>
              <y-form-input v-model="swiper.imgUrl" label="图片地址" class="flex-1 ml-sm">

              </y-form-input>
              <y-form-input v-model="swiper.topLevel" label="置顶级别(0-1000)" class="flex-1 ml-sm">

              </y-form-input>
              <el-form-item label="是否启用" class="flex-1 ml-sm">
                <div class="w100p row-between-center">
                  <el-switch class="w100p mt-xs" v-model="swiper.enable" active-text="启用" inactive-text="关闭"></el-switch>
                  <!--                  <el-button type="danger" @click="deleteData(index)">关闭</el-button>-->
                </div>
              </el-form-item>
            </div>
            <div class="flex-row">
              <y-form-input v-model="swiper.skipUrl" label="跳转地址" class="flex-1 ml-sm">

              </y-form-input>
              <y-form-select v-model="swiper.skipType" :options="$const.SkipType.enums" option-label="name" clearable
                             label="跳转类型" class="flex-1 ml-sm"
              >

              </y-form-select>
              <y-form-input v-model="swiper.standUrl" label="备用跳转地址" class="flex-1 ml-sm">

              </y-form-input>
              <y-form-select v-model="swiper.standType" :options="$const.SkipType.enums" option-label="name" clearable
                             label="备用跳转类型" class="flex-1 ml-sm"
              >

              </y-form-select>
            </div>
          </el-form>
        </div>
      </div>
    </y-scrollbar>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import request from '@/plugins/request'
import ConfigVO from '@/model/config/ConfigVO'
import SocialuniHomeAdminSwiperRO from '@/model/config/SocialuniHomeAdminSwiperRO'
import YTableInput from '@/components/YComponent/YTableInput/YTableInput.vue'
import YTable from '@/components/YComponent/YTable/YTable.vue'
import YFormInput from '@/components/YComponent/YFormInput/YFormInput.vue'
import YFormSelect from '@/components/YComponent/YFormSelect/YFormSelect.vue'
import ToastUtil from '@/utils/ToastUtil'

@toNative
@Component({
  components: { YFormSelect, YFormInput, YTable, YTableInput }
})
export default class HomeSwiperManageView extends Vue {
  homeSwipers: SocialuniHomeAdminSwiperRO[] = [new SocialuniHomeAdminSwiperRO()]

  created() {
    this.queryData()
  }

  addData() {
    this.homeSwipers.push(new SocialuniHomeAdminSwiperRO())
  }

  async queryData() {
    const res = await request.get('socialuni/admin/homeSwiper/queryhomeSwipers')
    this.homeSwipers = res.data
    if (!this.homeSwipers.length) {
      this.homeSwipers = [new SocialuniHomeAdminSwiperRO()]
    }
  }

  saveData() {
    //this.configs保存到后台
    request.post('socialuni/admin/homeSwiper/updateHomeSwipers', this.homeSwipers).then(res => {
      ToastUtil.success('更新成功')
      this.queryData()
    })
  }

  deleteData(index) {
    this.homeSwipers[index].enable = false
  }
}
</script>

<style scoped>

</style>
