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

    <div class="flex-row">
      <div class="bg-red line-h1 box-mn">你哈奥</div>
    </div>
    <div class="flex-row">
      <div class="bg-green line-h1 box-sm">你哈奥</div>
    </div>
    <div class="flex-row">
      <div class="bg-blue line-h1 box-df">你哈奥</div>
    </div>
    <div class="flex-row">
      <div class="bg-red line-h1 px box-md">你哈奥</div>
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

@Component({
  components: { QButton }
})
export default class StarPage extends Vue {
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
