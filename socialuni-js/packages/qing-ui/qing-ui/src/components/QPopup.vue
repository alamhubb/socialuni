<template>
  <div v-if="dialogVisible || value"
       class="position-fixed overflow-hidden index-999 h100vh w100vw top-0 bottom-0 left-0 right-0">
    <q-model @click="cancel" v-if="modal">
      <!--        ios端不支持prevent需要用stop代替-->
    </q-model>
    <div class="h100p" :class="bottom?'col-end':'row-all-center'">
      <div class="q-popup bg-white position-absolute index-1005 flex-col overflow-hidden"
           :class="bottom?'w100vw bt-radius-20':'w90vw bd-radius-20'"
           @touchmove.stop.prevent
           @click.stop
      >
        <div class="row-between-center bb-1 py-smm pd-xs">
          <div class="flex-row flex-1">
            <slot name="headerLeft">
              <div class="row-all-center w100p font-md font-bold">
                {{ title }}
              </div>
            </slot>
          </div>
          <div class="flex-row flex-none px-sm" v-if="!hideButton">
            <button v-if="!hideConfirm" @click="confirm" theme text>{{ confirmText }}</button>
            <button class="ml-sm" v-if="!hideCancel" @click="cancel" text info>{{ cancelText }}</button>
          </div>
        </div>
        <slot></slot>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import {Component, Emit, Model, Prop, Vue, toNative} from 'vue-facing-decorator'
import QModel from "./QModel.vue";

/*
显示出来已经选了的城市，给她画上钩
* */
@toNative
@Component({
  components: {
    QModel
  }
})
export default class QPopup extends Vue {
  //如果是头顶或者底部则不为width100
  @Prop({default: ''}) readonly position: string
  @Prop({default: ''}) readonly title: string
  @Prop({default: false, type: Boolean}) readonly bottom: boolean
  @Prop({default: false, type: Boolean}) readonly top: boolean
  @Prop({default: true, type: Boolean}) readonly modal: boolean
  @Prop({default: false, type: Boolean}) readonly hideModal: boolean
  @Prop({default: false, type: Boolean}) readonly hideConfirm: boolean
  @Prop({default: false, type: Boolean}) readonly hideButton: boolean
  @Prop({default: false, type: Boolean}) readonly hideCancel: boolean
  @Prop({default: false, type: Boolean}) readonly customClose: boolean
  @Prop({default: '关 闭'}) readonly cancelText: boolean
  @Prop({default: '确 定'}) readonly confirmText: boolean

  @Prop() value: boolean

  dialogVisible: boolean = false

  open() {
    console.log(this.bottom)
    this.dialogVisible = true
  }

  close() {
    this.input(false)
    this.dialogVisible = false
  }


  @Emit()
  cancel() {
    this.close()
  }

  @Emit()
  confirm() {
    if (this.customClose) {
      return
    }
    this.close()
  }

  @Emit('update:modelValue')
  input(visible) {
    return visible
  }

  //下的话宽度100，中的话宽度90
  //下的话col-end 中的话 row-all-center
}
</script>
<style lang="scss">
//model index 以100开始
//因为h5端navbar和tabbar为998
.q-popup {
  transition: all 0.3s linear;
  box-shadow: 0 8px 10px -5px rgb(0 0 0 / 20%), 0 16px 24px 2px rgb(0 0 0 / 14%), 0 6px 30px 5px rgb(0 0 0 / 12%);
}
</style>
