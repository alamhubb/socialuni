<template>
  <div class="article">
    <q-navbar>创建话题</q-navbar>
    <div class="pd-sm pt-0 bg-white solid-bottom">
      <div>
        <div class="cu-form-group">
          <div class="title">
            <span class="color-red">*</span>
            标签：
          </div>
          <input :cursor-spacing="20" maxlength="4" v-model.trim="tagName" placeholder="必填"/>
          <div class="uni-icon uni-icon-clear" v-if="tagName" @click="clearTagName"></div>
          <div class="color-red">最多四个字</div>
        </div>

        <div class="cu-form-group align-start">
          <div class="title">
            描述：
          </div>
          <textarea class="h150" maxlength="300" v-model.trim="tagDescription" placeholder="非必填"
                    :show-confirm-bar="false"
          ></textarea>
        </div>
      </div>
    </div>
    <div class="cu-bar bg-white">
      <div class="action ma-0 flex-sub">
        <div class="action ma-0 flex-sub mr" @click="closePopup">取消</div>
        <div class="action ma-0 flex-sub text-green solid-left" :disabled="tagName" @click="addTagClick">
          创建
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import TagVO from 'socialuni-api-base/src/model/community/tag/TagVO'
import ErrorConst from 'socialuni-constant/constant/ErrorConst'
import ResultRO from 'socialuni-api-base/src/model/social/ResultRO'
import TagAPI from "socialuni-community-api/src/api/TagAPI";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";


@Component({})
class SocialTagAdd extends Vue {
  // tag名称
  tagName = ''
  // tag描述
  tagDescription = ''

  closePopup() {
    this.$emit('close')
  }

  checkTag(tag: TagVO) {
    this.tagName = ''
    this.tagDescription = ''
    this.$emit('change', tag)
  }

  addTagClick() {
    TagAPI.addTagAPI(this.tagName, this.tagDescription).then((res: any) => {
      this.checkTag(res.data)
    }).catch((res: ResultRO<TagVO>) => {
      if (res.errorCode === ErrorConst.custom) {
        QingAppUtil.AlertUtil.confirm(res.errorMsg, '使用').then(() => {
          this.checkTag(res.data)
        })
      }
    })
  }

  clearTagName() {
    this.tagName = ''
  }
}
export default toNative(SocialTagAdd)
</script>
