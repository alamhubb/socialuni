<template>
  <div class="flex-col h100p overflow-hidden color-black">
    <div class="flex-row h100p overflow-hidden">
      <div class="w200 h100p flex-none br bg-default ">
        <!--        <div class="size50 bg-red"></div>-->
      </div>

      <div class="h100p flex-row flex-1 bg-white pd">
        <!--          <vue-drag-resize>-->
        <div>
          <div>
            支持文件夹和压缩包的前端文件，未来会支持图片等，不登录七天有效期，登录后永久有效，内容大小最大100m
          </div>
          <div class="flex-row">
            <el-form ref="form" :model="formData" label-position="top">
              <div class="row-col-end">
                <el-form-item required label="项目名">
                  <template #label>
                      <span>
                        <span class="mr-sm">路径</span>
                        (
                        <span v-if="projectNameHasError" class="color-red">{{ projectNameHasError }}</span>
                        <span v-else class="color-green">项目名可以使用</span>
                        )
                      </span>
                  </template>
                  <el-input class="w370 mb-1" v-model="formData.projectName" clearable
                            @change="checkProjectNameHandler" @input="checkProjectNameHandler"></el-input>
                </el-form-item>
              </div>
              <el-form-item prop="mainFile" label="入口文件">
                <el-input class="w300 mr-sm" v-model="formData.mainFile"
                          :disabled="!editable"></el-input>
                <el-button plain @click="editable=!editable" :type="editable ? 'warning' : 'default'">
                  {{ editable ? '取消' : '编辑' }}
                </el-button>
              </el-form-item>

              <div class="w100p row-end-center mt">
                <el-button type="primary" @click="deploy" :disabled="projectNameHasError">部署</el-button>
              </div>

              <div class="mt-sm" v-if="deployUrl">
                <div class="color-green">
                  部署成功，访问地址:
                  <el-link :href="deployUrl" target="_blank" type="primary">{{ deployUrl }}</el-link>
                </div>
              </div>
            </el-form>
            <q-row>
              <q-upload ref="upload" class="ml-sm mt-30" only-folder v-model="formData.files" btn-text="选择项目"
                        show-file-list
                        @change="upload">
                <el-icon class="ml-xs" :size="16">
                  <FolderOpened></FolderOpened>
                </el-icon>
              </q-upload>
              <el-checkbox class="ml-sm mt-30" v-model="autoCreateCanUseName">自动生成可用名称</el-checkbox>
            </q-row>
          </div>


        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';
import {FolderOpened, UploadFilled} from "@element-plus/icons-vue";
import CommonUtil from "qing-util/src/util/CommonUtil.ts";
import SocialuniDeployAPI from "@/views/deploy/SocialuniDeployAPI.ts";
import QUpload from "qingjs-ui-h5/src/components/QUpload/QUpload.vue";
import QRow from "qingjs-ui-h5/src/components/QRow.vue";
import PinyinUtil from "@/util/PinyinUtil.ts";
import RegConst from "qing-util/src/constant/RegConst.ts";
import AlertUtil from "qing-compat-js-h5/src/util/AlertUtil.ts";
import ToastUtil from "qing-compat-js-h5/src/util/ToastUtil.ts";
import WindowUtil from "@/util/WindowUtil.ts";


@Component({
  components: {
    UploadFilled, FolderOpened, QRow, QUpload
  }
})
class MessageView extends Vue {
  $refs: {
    upload: QUpload
    form: ElForm
  }

  projectNameCanUse = false

  autoCreateCanUseName = true
  editable = false

  formData = {
    projectName: null,
    mainFile: 'index.html',
    files: null
  }

  get projectNameHasError() {
    const value: string = this.formData.projectName
    if (!this.formData.files) {
      return '请选择项目'
    } else if (!value) {
      return '请输入项目名'
    } else if (!this.projectNameCanUse) {
      return '项目名已存在，请修改'
    } else if (RegConst.startWithNumber.test(value)) {
      return '项目名不能为数字开头'
    } else if (value.length < 3) {
      return '项目名需大于2个字符'
    } else if (value.length > 16) {
      return '项目名需小于17个字符'
    }
  }

  created() {
    this.clearDeployUrl()
    this.querySysDomain()
  }

  domainName = null

  querySysDomain() {
    SocialuniDeployAPI.queryDomainName().then((res) => {
      this.domainName = res.data
    })
  }

  clearDeployUrl() {
    this.deployUrl = null
    this.$nextTick(() => {
      this.$refs.upload.clearFileList()
    })
  }

  files = []

  async checkProjectName() {
    this.projectNameCanUse = false
    if (this.formData.projectName) {
      try {
        const res = await SocialuniDeployAPI.checkProjectName(this.formData.projectName)
        this.projectNameCanUse = res.data
      } catch (e) {
        this.projectNameCanUse = false
      }
    }
  }

  checkProjectNameHandler = CommonUtil.debounceDelay(this.checkProjectName, 300)


  async checkAndAutoCreateCanUseProjectName() {
    await this.checkProjectName()
    if (!this.projectNameCanUse && this.autoCreateCanUseName) {
      this.formData.projectName = await this.autoCreateCanUseProjectName()
      await this.checkProjectName()
    }
  }

  deployUrl = null

  upload() {
    this.clearDeployUrl()
    if (!this.formData.projectName) {
      this.formData.projectName = PinyinUtil.convertToFirstUpperPinyin(this.formData.files[0].root)
      if (this.formData.projectName === 'dist') {
        this.formData.projectName = this.domainName
      }
      this.checkAndAutoCreateCanUseProjectName()
    }
  }

  //自动生成可用项目名路径
  async autoCreateCanUseProjectName() {
    const res = await SocialuniDeployAPI.autoCreateCanUseProjectName()
    return res.data
  }


  deploy() {
    if (this.projectNameHasError) {
      ToastUtil.throwError(this.projectNameHasError)
    }
    AlertUtil.confirm(`是否确认部署项目${this.formData.projectName}，项目部署后即可通过互联网访问`).then(() => {
      SocialuniDeployAPI.deployProject(this.formData).then((res) => {
        this.deployUrl = res.data
        AlertUtil.success('部署成功，是否立即访问？').then(() => {
          WindowUtil.open(this.deployUrl)
        })
      })
    })
  }
}
export default toNative(MessageView)
</script>
<style lang="scss">
h2 {
  font: 14px Arial, sans-serif;
  color: #fff;
  padding: 10px;
  text-align: center;
}

html, body {
  height: 100%;
}

* {
  margin: 0;
  padding: 0;
  list-style-type: none;
}

#wrapper {
  display: flex;
  height: 100%
}

#menuContainer {
  flex: 0 0 auto;
  margin-right: 3px;
}

#menuContainer li {
  border-bottom: 1px solid #000;
  border-top: 1px solid #333;
  cursor: pointer;
  padding: 10px 10px;
  color: #BBB;
  background: #1a1a1a;
  font: 12px Arial, sans-serif;
}

#menuContainer li:hover {
  background: #111;
  color: #CCC;
}

#layoutContainer {
  flex: 1 1 auto;
  height: 100%;
}
</style>
