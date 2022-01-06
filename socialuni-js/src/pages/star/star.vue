<template>
  <div>
    <u-field
      label="星名称"
      placeholder="请填写星名称"
      required
    >
    </u-field>

    <u-field
      type="textarea"
      label="星描述"
      required
      placeholder="请填写星名称"
    >
    </u-field>
    <u-picker mode="multiSelector" :default-selector='[0, 1, 3]'></u-picker>

<!--    可以让用户选择，需要关联的话题的话题
    <div></div>
    可以让用户选择，不显示哪些话题的内容，
    都不做数量限制，可以编辑-->

    <div class="flex-row">
      <u-button size="medium">取消</u-button>
      <u-button type="primary" size="medium" @click="consoletagtype">创建</u-button>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import ResultRO from '@/socialuni/model/social/ResultRO'
import TagAPI from '@/socialuni/api/TagAPI'
import Alert from '@/socialuni/utils/Alert'
import ErrorConst from '@/socialuni/const/ErrorConst'
import QButton from '@/socialuni/components/q-button/QButton.vue'
import { socialTagStore } from '@/socialuni/store'
import TagVO from '@/socialuni/model/tag/TagVO'
import TagTypeVO from '@/socialuni/model/tag/TagTypeVO'

@Component({
  components: { QButton }
})
export default class StarPage extends Vue {
  @socialTagStore.State('tags') readonly tags: TagVO []
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO []

  consoletagtype(){
    console.log(this.tagTypes)
  }

  // tag名称
  tagName = ''
  // tag描述
  tagDescription = ''

  closePopup () {
    this.$emit('close')
  }


  test () {
    console.log(123)
  }

  checkTag (tag: TagVO) {
    this.tagName = ''
    this.tagDescription = ''
    this.$emit('change', tag)
  }

  addTagClick () {
    TagAPI.addTagAPI(this.tagName, this.tagDescription).then((res: any) => {
      this.checkTag(res.data)
    }).catch((res: ResultRO<TagVO>) => {
      if (res.errorCode === ErrorConst.custom) {
        Alert.confirm(res.errorMsg, '使用').then(() => {
          this.checkTag(res.data)
        })
      }
    })
  }

  clearTagName () {
    this.tagName = ''
  }
}
</script>
