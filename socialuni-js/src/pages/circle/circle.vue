<template>
  <view>
    <u-form :model="circleForm" ref="uForm" label-width="auto" class="pd">
      <u-form-item label="圈子名称：" prop="circleName">
        <u-input v-model="circleForm.circleName"/>
      </u-form-item>
      <u-form-item label="圈子描述：">
        <u-input type="textarea" height="140" v-model="circleForm.circleDesc"/>
      </u-form-item>
      <u-form-item label="圈子分类："  prop="tagTypeId">
        <q-select v-model="circleForm.tagTypeId" :list="selectTagTypes" label-name="name" value-name="id"></q-select>

        <!--        <u-input type="select" :select-open="selectShow" :value="circleForm.tagTypeId"
                         placeholder="请选择商品类型" @click="selectShow = true"></u-input>-->
      </u-form-item>
    </u-form>

    <u-select mode="single-column" :list="selectTagTypes" value-name="id" label-name="name" v-model="selectShow"
              @confirm="selectConfirm"></u-select>
    <div class="row-around">
      <u-button size="medium">取消</u-button>
      <u-button type="primary" size="medium" @click="submit">创建</u-button>
    </div>
  </view>
</template>


<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import CircleCreateQO from '@/socialuni/model/community/circle/CircleCreateQO'
import CircleAPI from '@/socialuni/api/CircleAPI'
import { socialTagStore } from '@/socialuni/store'
import TagTypeVO from '@/socialuni/model/tag/TagTypeVO'
import QIcon from '@/socialuni/components/QIcon/QIcon.vue'
import QSelect from '@/socialuni/components/QSelect/QSelect.vue'

@Component({
  components: { QSelect, QIcon }
})
export default class CirclePage extends Vue {
  @socialTagStore.State('tagTypes') readonly tagTypes: TagTypeVO[]

  get selectTagTypes () {
    return this.tagTypes.slice(1, this.tagTypes.length - 1)
  }

  $refs: {
    uForm: any
  }

  selectList = [
    {
      value: '电子产品',
      label: '电子产品'
    },
    {
      value: '服装',
      label: '服装'
    },
    {
      value: '工艺品',
      label: '工艺品'
    }
  ]
  selectShow = false

  circleForm = new CircleCreateQO()

  selectConfirm (values: { value: number }[]) {
    this.circleForm.tagTypeId = values[0].value
  }

  // 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
  onReady () {
    this.$refs.uForm.setRules(this.rules)
  }

  rules = {
    circleName: [
      {
        required: true,
        message: '请输入圈子名称',
        trigger: 'blur'
      }
    ],
    tagTypeId: [
      {
        required: true,
        message: '请选择圈子分类',
        trigger: 'blur'
      }
    ]
  }


  submit () {
    this.$refs.uForm.validate(valid => {
      if (valid) {
        CircleAPI.createCircleAPI(this.circleForm)
        console.log('验证通过')
      } else {
        console.log('验证失败')
      }
    })
  }

}
</script>
