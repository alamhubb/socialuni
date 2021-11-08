<template>
  <view>
    <u-form :model="circleForm" ref="uForm" label-width="auto" class="pd">
      <u-form-item label="圈子名称：" prop="circleName">
        <u-input v-model="circleForm.circleName"/>
      </u-form-item>
      <u-form-item label="圈子描述：">
        <u-input type="textarea" height="140" v-model="circleForm.circleDesc"/>
      </u-form-item>
      <u-form-item label="圈子分类：">
        <u-input :border="border" type="select" :select-open="selectShow" v-model="model.goodsType"
                 placeholder="请选择商品类型" @click="selectShow = true"></u-input>
      </u-form-item>
    </u-form>

    <u-select mode="single-column" :list="selectList" v-model="selectShow" @confirm="selectConfirm"></u-select>
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

@Component
export default class CirclePage extends Vue {

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

  circleForm = new CircleCreateQO()

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
