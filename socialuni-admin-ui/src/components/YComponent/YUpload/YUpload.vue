<template>
  <div class="flex-col">
    <div class="flex-none row-between-center">
      <div class="row-col-center">
        <el-dropdown
          v-if="folder"
          size="small"
          @command="dropdownCommand"
          :show-timeout="100"
          :hide-timeout="250"
        >
          <!--        @click="uploadFileLabelClick"-->
          <el-button type="primary" size="mini" :disabled="!canOperateByProgress">
            <i class="el-icon-upload2"></i>
            上传
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item :command="$const.UploadFileType.file">上传文件</el-dropdown-item>
            <el-dropdown-item :command="$const.UploadFileType.folder">上传文件夹
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-button v-else type="primary" size="mini" @click="uploadFileLabelClick" :disabled="!canOperateByProgress">
          上传<i
          class="el-icon-upload el-icon--right"></i></el-button>

        <div v-if="files" class="ml">文件总数：{{ files.length }}</div>
      </div>

      <el-button v-show="fileList.length && canOperateByProgress" size="mini" class="ml"
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

    <div class="flex-1 overflow-scroll mt-xs pr-sm">
      <div v-for="file in fileList" :key="file.name" class="bg-click text-ellipsis row-between-center px-xs">
        <div class="text-ellipsis">
          <template v-if="file.fileType===$const.UploadFileType.folder">
            <i class="el-icon-folder-opened"></i>
            {{ file.name }} <span class="ml-sm">(文件数量：{{ file.files.length }})</span>
          </template>
          <template v-else>
            <i class="el-icon-document"></i>
            {{ file.name }}
          </template>
        </div>
        <el-button v-show="canOperateByProgress" type="text" icon="el-icon-circle-close"
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
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'
import YDialog from '@/components/YComponent/YDialog/YDialog.vue'
import YUploadFileType from '@/components/YComponent/YUpload/YUploadFileType'
import UploadFileVO from '@/model/common/UploadFileVO'
import DomFile from '@/model/base/DomFile'
import Arrays from '@/utils/Arrays'
import AlertUtil from '@/utils/AlertUtil'
import request from '@/plugins/request'
import JsonUtil from '@/plugins/utils/JsonUtil'
import { Message } from 'element-ui'
import FileUploadVO from '@/model/resourceManagement/fileManage/mineFile/FileUploadVO'
import UploadPercentageVO from '@/model/resourceManagement/fileManage/mineFile/UploadPercentageVO'

@Component({
  components: { YDialog }
})
export default class YUpload extends Vue {
  $refs: {
    uploadFileLabel: HTMLLabelElement;
    uploadFolderLabel: HTMLLabelElement;
    fileInput: HTMLInputElement;
    folderInput: HTMLInputElement;
  }

  @Model('change') readonly files!: DomFile[]
  @Prop({ default: false, type: Boolean }) folder: boolean
  //上传进度
  uploadPercent: UploadPercentageVO = new UploadPercentageVO()

  fileList: UploadFileVO[] = []

  @Watch('files')
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

  @Emit()
  change() {
    const files = this.fileList.map(item => item.files).flat()
    return files
  }

  //为null或100可操作，否则代表上传中不可操作
  get canOperateByProgress() {
    return this.uploadPercent.uploadPercent === null || this.uploadPercent.uploadPercent === 100
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
      Arrays.delete(this.fileList, file.name, 'name')
      this.change()
    })
  }

  async upload(uploadUrl: string, formData: FileUploadVO) {
    // 只记录当前的上传进度，考虑清空情况，清空以后uploadPercent就变了，这个改值界面上也不会变化，则正确
    const uploadPercent = this.uploadPercent
    return request.post(uploadUrl, JsonUtil.toFormData(formData), {
      // 超时时间设置为24小时
      timeout: 24 * 60 * 60000,
      onUploadProgress: (progressEvent) => {
        uploadPercent.uploadPercent = Math.floor(((progressEvent.loaded * 100 / progressEvent.total)))
      }
    }).then((res: any) => {
      Message.success(res.msg)
    })
  }

  uploadChange({ target }) {
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
    //获取到文件之后就可以清楚input框里面的文件里
    this.$refs.fileInput.value = null
    this.$refs.folderInput.value = null
    this.change()
  }
}
</script>
