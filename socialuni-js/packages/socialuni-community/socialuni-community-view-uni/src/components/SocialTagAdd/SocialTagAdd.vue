<template>
  <view class="article">
    <q-navbar>创建话题</q-navbar>
    <view class="pd-sm pt-0 bg-white solid-bottom">
      <view>
        <view class="cu-form-group">
          <view class="title">
            <text class="color-red">*</text>
            标签：
          </view>
          <input :cursor-spacing="20" maxlength="4" v-model.trim="tagName" placeholder="必填"/>
          <view class="uni-icon uni-icon-clear" v-if="tagName" @click="clearTagName"></view>
          <view class="color-red">最多四个字</view>
        </view>

        <view class="cu-form-group align-start">
          <view class="title">
            描述：
          </view>
          <textarea class="h150" maxlength="300" v-model.trim="tagDescription" placeholder="非必填"
                    :show-confirm-bar="false"
          ></textarea>
        </view>
      </view>
    </view>
    <view class="cu-bar bg-white">
      <view class="action ma-0 flex-sub">
        <view class="action ma-0 flex-sub mr" @click="closePopup">取消</view>
        <view class="action ma-0 flex-sub text-green solid-left" :disabled="tagName" @click="addTagClick">
          创建
        </view>
      </view>
    </view>
  </view>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import TagVO from 'socialuni-api-base/src/model/community/tag/TagVO'
import ErrorConst from 'socialuni-constant/constant/ErrorConst'
import ResultRO from 'socialuni-api-base/src/model/social/ResultRO'
import TagAPI from "socialuni-community-api/src/api/TagAPI";
import QingAppUtil from "qingjs/src/util/QingAppUtil";

@toNative
@Component({})
export default class SocialTagAdd extends Vue {
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
</script>
