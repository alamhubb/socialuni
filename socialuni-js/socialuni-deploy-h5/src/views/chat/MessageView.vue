<template>
  <div class="flex-col h100p overflow-hidden color-black">
    <div class="flex-row h100p overflow-hidden">
      <div class="w200 h100p flex-none br bg-default ">
        <!--        <div class="size50 bg-red"></div>-->
      </div>

      <div class="h100p flex-col flex-1 bg-white pd">
        <!--          <vue-drag-resize>-->
        <div>
          <el-form :model="formData" :rules="rules" label-position="top">
            <el-form-item prop="projectName" label="项目名">
              <el-input class="w300" v-model="formData.projectName" @change="checkProjectName"></el-input>
            </el-form-item>
            <el-form-item prop="mainFile" label="入口文件">
              <el-input class="w300 mr-sm" v-model="formData.mainFile" @change="checkProjectName"
                        :disabled="!editable"></el-input>
              <el-button @click="editable=!editable">{{ editable ? '取消编辑' : '编辑' }}</el-button>
            </el-form-item>
            <el-form-item label="" prop="files">
              <q-upload ref="upload" class="w100p h50vh" only-folder v-model="formData.files"
                        @change="upload"></q-upload>
            </el-form-item>
          </el-form>

          <div>
            <el-button type="primary" @click="deploy">部署</el-button>
          </div>


        </div>
      </div>
    </div>
  </div>

  <!--      </vue-golden-layout-row>-->
  <!--    </vue-golden-layout>-->

  <!--    <el-button @click="active++">active&#45;&#45;</el-button>-->
  <!--    <child-com>123</child-com>
      <el-button @click="addc">add</el-button>
      <div ref="container"></div>
      <child-com ref="container2">{{active}}</child-com>-->


  <!--    <div class="flex-row overflow-hidden flex-none">
        <div v-for="i in 20" class="flex-1 w200 h30" :class="[active===i?'bg-blue_light bb-2 bb-blue':'bg-default']"
             @click="active=i"  draggable="true">
          {{ i }}
        </div>
      </div>-->
  <!--    <div id="wrapper">
        <ul id="menuContainer">
          <li id="addMenuItem">Add another component</li>
        </ul>
        <div id="layoutContainer"></div>
      </div>-->
  <!--    <div class="flex-1 overflow-hidden bg-green_light pd" @dragover="allowDrop">
        {{ active }}
      </div>-->
  <!--  </div>-->
</template>

<script lang="ts">
import {Component, Vue, Watch} from 'vue-facing-decorator';
import VueGoldenLayout from 'vue3-golden-layout/src/components/VueGoldenLayout.vue';
import VueGoldenLayoutRow from 'vue3-golden-layout/src/components/VueGoldenLayoutRow.vue';
import VueGoldenLayoutColumn from 'vue3-golden-layout/src/components/VueGoldenLayoutColumn.vue';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';
import ChildCom from "@/views/chat/ChildCom.vue";
import MusicView from "@/views/chat/MusicView.vue";
import SocialuniChatViewH5 from "socialuni-im-view-h5/src/views/SocialuniChatViewH5.vue";
import SocialuniMsgViewH5 from "socialuni-im-view-h5/src/views/SocialuniMsgViewH5.vue";
import VueDragResize from "vue-drag-resize3/src/components/VueDragResize.vue";
import MusicPlayer from "@/components/MusicPlayer.vue";
import VueInteract from "@/components/vue-interact/VueInteract.vue";
import QUpload from "qing-ui-h5/src/components/QComponents/QUpload/QUpload.vue";
import QLabelItem from "qing-ui-h5/src/components/QComponents/QLabelItem.vue";
import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest.ts";
import SocialuniDeployAPI from "@/views/chat/SocialuniDeployAPI.ts";
import RegConst from "qing-util/src/constant/RegConst.ts";
import AlertUtil from "qingjs-h5/src/util/AlertUtil.ts";


@Component({
  components: {
    VueDragResize,
    VueInteract,
    QUpload,
    QLabelItem,
    MusicPlayer,
    SocialuniChatViewH5,
    SocialuniMsgViewH5,
    VueGoldenLayoutColumn,
    MusicView,
    ChildCom,
    VueGoldenLayout,
    VueGoldenLayoutRow
  }
})
export default class MessageView extends Vue {

  $refs: {
    upload: QUpload
  }

  projectNameCanUse = true

  editable = false

  formData = {
    projectName: null,
    mainFile: 'index.html',
    files: null
  }

  rules = {
    projectName: [
      {
        required: true,
        validator: (rule, value, callback) => {
          const error = this.projectNameHasError(value)
          if (error) {
            callback(new Error(error))
          }
          callback()
        },
        trigger: 'blur'
      }
    ]
  }

  projectNameHasError(value) {
    console.log(value)
    if (!value) {
      return '请输入项目名'
    } else if (!this.projectNameCanUse) {
      return '项目名已存在，请修改'
    } else if (!RegConst.isAllLetter.test(value)) {
      return '项目名需为拼音或英文'
    } else if (value.length < 6) {
      return '项目名需大于5个字符'
    } else if (value.length > 16) {
      return '项目名需小于16个字符'
    }
  }


  files = []

  checkProjectName() {
    if (!this.projectName) {

    }
    SocialuniDeployAPI.checkProjectName(this.projectName).then(res => {
      this.projectNameCanUse = res.data
    })
  }

  mounted() {

  }

  upload() {
    // this.$refs.upload.upload(socialuniUserRequest, 'upload/uploadFiles', {files: this.files})
  }

  deploy() {
    AlertUtil.confirm(`是否确认部署项目${this.formData.projectName}，部署后项目即可通过互联网访问`).then(() => {
      this.$refs.upload.upload(socialuniUserRequest, 'upload/uploadFiles', this.formData)
    })
  }
}
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
