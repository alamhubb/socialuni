<template>
  <div class="flex-col">
    <div class="flex-none row-between-center">
      <div class="row-col-center">
        <el-button v-if="onlyFolder" plain type="primary" size="mini" @click="uploadFolderLabelClick"
        >
          {{ btnText }}
          <slot></slot>
          <i
              class="el-icon-upload el-icon--right"></i></el-button>
        <el-dropdown
            v-else-if="folder"
            size="small"
            :show-timeout="100"
            :hide-timeout="250"
        >
          <el-button  plain type="primary" size="mini">
            <i class="el-icon-upload2"></i>
            {{ btnText }}
            <slot></slot>
          </el-button>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="uploadFileLabelClick">上传文件</el-dropdown-item>
              <el-dropdown-item @click="uploadFolderLabelClick">上传文件夹</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
        <el-button v-else plain type="primary" size="mini" @click="uploadFileLabelClick">
          {{ btnText }}
          <slot></slot>
          <i
              class="el-icon-upload el-icon--right"></i></el-button>


      </div>

      <div class="flex-1 overflow-auto pr-sm" v-if='showFileList'>
        <div v-for="file in fileList" :key="file.name" class="text-ellipsis row-col-center px-xs">
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
          <el-button type="text" class="ml-sm">
            <i class="mdi mdi-close-circle-outline use-click" @click="deleteFile(file)"></i>
          </el-button>
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
  </div>
</template>

<script lang="ts">
import {Component, Emit, Model, Prop, Vue, Watch, toNative} from 'vue-facing-decorator';
import DomFile from "./DomFile";
import UploadFileVO from "./UploadFileVO";
import YUploadFileType from "./YUploadFileType";
import AlertUtil from "qing-compat-js-h5/src/util/AlertUtil";
import Arrays from "qing-util/src/util/Arrays";
import ToastUtil from "qing-compat-js-h5/src/util/ToastUtil";
import ObjectUtil from "qing-util/src/util/ObjectUtil";
import SocialuniAxios from "socialuni-api-base/src/SocialuniAxios";
import {
  UploadFilled, FolderOpened
} from "@element-plus/icons-vue";



@Component({
  components: {
    UploadFilled, FolderOpened
  }
})
class QUpload extends Vue {
  $refs: {
    uploadFileLabel: HTMLLabelElement;
    uploadFolderLabel: HTMLLabelElement;
    fileInput: HTMLInputElement;
    folderInput: HTMLInputElement;
  }


  @Model files!: DomFile[]
  @Prop({default: false, type: Boolean}) folder: boolean
  @Prop({default: false, type: Boolean}) onlyFolder: boolean
  @Prop({default: false, type: Boolean}) showFileList: boolean
  @Prop({default: '上传'}) btnText: string

  fileList: UploadFileVO[] = []


  get YUploadFileType() {
    return YUploadFileType
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

  clearFies() {
    if (this.fileList.length) {
      AlertUtil.warning('是否清空文件列表？').then(() => {
        this.clearFileList()
        this.change()
      })
    }
  }

  clearFileList() {
    this.fileList = []
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

  deleteFile(file: UploadFileVO) {
    AlertUtil.warning('是否移除文件：' + file.name + '?').then(() => {
      Arrays.deleteByPropName(this.fileList, file.name, 'name')
      this.change()
    })
  }

  uploadChange({target}) {
    const files: DomFile[] = []
    for (const file of target.files) {
      files.push(file)
    }
    console.log(target.files)
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
        console.log(filePathJoinAry)
        // 翻转数组，从最长的开始校验
        filePathJoinAry.reverse()
        // 找到每个都包含的路径
        filePath = filePathJoinAry.find(item => files.every(file => {
          file.root = item.substring(0, item.length - 1)
          return file.webkitRelativePath.includes(item)
        }))
        this.fileList.push(new UploadFileVO(YUploadFileType.folder, filePath.substring(0, filePath.length - 1), files))
      } else {
        // 文件形式
        for (const file of files) {
          // @ts-ignore
          this.fileList.push(new UploadFileVO(YUploadFileType.file, file.name, [file]))
        }
      }
    }
    // 获取到文件之后就可以清楚input框里面的文件里
    this.$refs.fileInput.value = null
    this.$refs.folderInput.value = null
    this.change()
  }
}
export default toNative(QUpload)
</script>
