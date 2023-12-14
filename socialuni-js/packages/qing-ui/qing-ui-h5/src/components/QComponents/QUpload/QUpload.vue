<template>
  <div class="flex-col">
    <div class="flex-none row-between-center">
      <div class="row-col-center">
        <el-button v-if="onlyFolder" type="primary" size="mini" @click="uploadFolderLabelClick"
                   :disabled="!canOperateByProgress">
          {{ btnText }}
          <el-icon class="ml-sm" :size="20">
            <UploadFilled></UploadFilled>
          </el-icon>
          <i
              class="el-icon-upload el-icon--right"></i></el-button>
        <el-dropdown
            v-else-if="folder"
            size="small"
            :show-timeout="100"
            :hide-timeout="250"
        >
          <el-button type="primary" size="mini" :disabled="!canOperateByProgress">
            <i class="el-icon-upload2"></i>
            {{ btnText }}
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="uploadFileLabelClick">上传文件</el-dropdown-item>
              <el-dropdown-item @click="uploadFolderLabelClick">上传文件夹</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <el-button v-else type="primary" size="mini" @click="uploadFileLabelClick" :disabled="!canOperateByProgress">
          {{ btnText }}
          <i
              class="el-icon-upload el-icon--right"></i></el-button>

        <div v-if="showFileList && files" class="ml">文件总数：{{ files.length }}</div>
      </div>

      <el-button
          v-show="showFileList && fileList.length && canOperateByProgress"
          size="mini"
          class="ml"
          type="text"
          icon="el-icon-circle-close"
          @click="clearFies">清空
      </el-button>
    </div>

    <div class="h20">
      <div v-if="uploadPercent.uploadPercent!==null" class="row-col-center">
        <div class="w70p">
          <el-progress :percentage="uploadPercent.uploadPercent" :format="uploadProgressFormat"></el-progress>
        </div>
        <div>
          <i v-show="uploadPercent.uploadPercent!==100" class="size14 el-icon-loading"></i>
        </div>
      </div>
    </div>

    <div class="flex-1 overflow-auto mt-xs pr-sm" v-if='showFileList'>
      <div v-for="file in fileList" :key="file.name" class="bg-click text-ellipsis row-between-center px-xs">
        <div class="text-ellipsis">
          <template v-if="file.fileType===YUploadFileType.folder">
            <i class="el-icon-folder-opened"></i>
            {{ file.name }} <span class="ml-sm">(文件数量：{{ file.files.length }})</span>
          </template>
          <template v-else>
            <i class="el-icon-document"></i>
            {{ file.name }}
          </template>
        </div>
        <el-button
            v-show="canOperateByProgress"
            type="text"
            icon="el-icon-circle-close"
            @click="deleteFile(file)"></el-button>
      </div>
    </div>

    <label class="bg-opacity position-absolute size0" ref="uploadFileLabel" for="fileUpload">
      <input
          ref="fileInput"
          class="bg-opacity position-absolute size0"
          type="file"
          id="fileUpload"
          multiple="multiple"
          @change="uploadChange"/>
    </label>

    <label class="bg-opacity position-absolute size0" ref="uploadFolderLabel" for="folderUpload">
      <input
          class="bg-opacity position-absolute size0"
          type="file"
          ref="folderInput"
          id="folderUpload"
          webkitdirectory
          multiple="multiple"
          @change="uploadChange"/>
    </label>
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, toNative, Vue, Watch} from "vue-facing-decorator";
import DomFile from "./DomFile";
import UploadPercentageVO from "./UploadPercentageVO";
import UploadFileVO from "./UploadFileVO";
import YUploadFileType from "./YUploadFileType";
import AlertUtil from "qingjs-h5/src/util/AlertUtil";
import Arrays from "qing-util/src/util/Arrays";
import ToastUtil from "qingjs-h5/src/util/ToastUtil";
import ObjectUtil from "qing-util/src/util/ObjectUtil";
import SocialuniAxios from "socialuni-api-base/src/SocialuniAxios";
import {UploadFilled} from "@element-plus/icons-vue";


@Component({
  components: {
    UploadFilled
  }
})
export default class QUpload extends Vue {
  $refs: {
    uploadFileLabel: HTMLLabelElement;
    uploadFolderLabel: HTMLLabelElement;
    fileInput: HTMLInputElement;
    folderInput: HTMLInputElement;
  }


  @Model files!: DomFile[]
  @Prop({default: false, type: Boolean}) folder: boolean
  @Prop({default: false, type: Boolean}) onlyFolder: boolean
  @Prop({default: true, type: Boolean}) showFileList: boolean
  @Prop({default: '上传'}) btnText: string
  // 上传进度
  uploadPercent: UploadPercentageVO = new UploadPercentageVO()

  fileList: UploadFileVO[] = []


  get YUploadFileType() {
    return YUploadFileType
  }

  // 为null或100可操作，否则代表上传中不可操作
  get canOperateByProgress() {
    return this.uploadPercent.uploadPercent === null || this.uploadPercent.uploadPercent === 100
  }

  @Watch('modelValue')
  filesWatch(files: []) {
    if (!files || !files.length) {
      this.clearFileList()
    }
  }

  created() {
    this.clearFileList()
  }

  dropdownCommand(uploadFileType: string) {
    if (uploadFileType === YUploadFileType.file) {
      this.uploadFileLabelClick()
    } else if (uploadFileType === YUploadFileType.folder) {
      this.uploadFolderLabelClick()
    }
  }

  uploadFileLabelClick() {
    this.$refs.uploadFileLabel.click()
  }

  uploadFolderLabelClick() {
    this.$refs.uploadFolderLabel.click()
  }

  @Emit('update:modelValue')
  change() {
    const files = this.fileList.map(item => item.files).flat()
    console.log(files)
    return files
  }

  clearFies() {
    if (this.fileList.length) {
      AlertUtil.warning('是否清空文件列表？').then(() => {
        this.clearFileList()
        this.change()
      })
    }
  }

  clearFileList() {
    this.uploadPercent = new UploadPercentageVO()
    this.fileList = []
  }

  uploadProgressFormat(percentage) {
    return percentage === 100 ? '完成' : `${percentage}%`
  }

  deleteFile(file: UploadFileVO) {
    AlertUtil.warning('是否移除文件：' + file.name + '?').then(() => {
      Arrays.deleteByPropName(this.fileList, file.name, 'name')
      this.change()
    })
  }

  async upload(request: AxiosStatic, uploadUrl: string, formData: any) {
    // 只记录当前的上传进度，考虑清空情况，清空以后uploadPercent就变了，这个改值界面上也不会变化，则正确
    const uploadPercent = this.uploadPercent
    return request.post(uploadUrl, ObjectUtil.toFormData(formData), {
      // 超时时间设置为24小时
      timeout: 24 * 60 * 60000,
      onUploadProgress: (progressEvent) => {
        uploadPercent.uploadPercent = Math.floor(((progressEvent.loaded * 100 / progressEvent.total)))
      }
    }).then((res: any) => {
      ToastUtil.success(res.msg)
    })
  }

  uploadChange({target}) {
    const files: DomFile[] = []
    for (const file of target.files) {
      files.push(file)
    }
    let filePath: string
    if (files.length) {
      // 用第一个路径就行，然后遍历
      // 取第一个就行，因为全部包含，所以肯定一个也包含
      const firstFilePath = files[0].webkitRelativePath
      // 文件夹形式
      if (firstFilePath) {
        const filePathAry: string[] = firstFilePath.split('/')
        const filePathJoinAry: string[] = []
        filePathAry.forEach((item, index) => {
          // 最后一个为文件名，返回
          if (index === filePathAry.length - 1) {
            return
          }
          if (index) {
            filePathJoinAry.push(item + '/' + filePathJoinAry[index - 1])
          } else {
            filePathJoinAry.push(item + '/')
          }
        })
        // 翻转数组，从最长的开始校验
        filePathJoinAry.reverse()
        // 找到每个都包含的路径
        filePath = filePathJoinAry.find(item => files.every(file => file.webkitRelativePath.includes(item)))
        this.fileList.push(new UploadFileVO(YUploadFileType.folder, filePath, files))
      } else {
        // 文件形式
        for (const file of files) {
          // @ts-ignore
          this.fileList.push(new UploadFileVO(YUploadFileType.file, file.name, file))
        }
      }
    }
    // 获取到文件之后就可以清楚input框里面的文件里
    this.$refs.fileInput.value = null
    this.$refs.folderInput.value = null
    this.change()
  }
}
</script>
