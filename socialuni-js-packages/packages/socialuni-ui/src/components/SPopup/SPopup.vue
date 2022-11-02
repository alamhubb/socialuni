<template>
  <div v-if="dialogVisible || modelValue"
       class="position-fixed overflow-hidden z-999 h100vh w100vw">
    <!--        ios端不支持prevent需要用stop代替-->
    <view v-if="modal" class="position-fixed z-1003 h100vh w100vw bg-modal" @click="cancel"
          @touchmove.stop.prevent
    >
      <div class="h100%" :class="bottom?'col-end':'row-all-center'">
        <div class="q-popup bg-white position-absolute z-1005 flex-col"
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
              <s-button v-if="!hideConfirm" @click="confirm" theme text>{{ confirmText }}</s-button>
              <s-button class="ml-sm" v-if="!hideCancel" @click="cancel" text info>{{ cancelText }}</s-button>
            </div>
          </div>
          <slot></slot>
        </div>
      </div>
    </view>
  </div>
</template>
<script setup lang="ts">
import SButton from "../SButton/SButton.vue";
import {defineProps, defineEmits, ref} from "vue"

const props = defineProps({
  //如果是头顶或者底部则不为width100
  position: {default: ''},
  title: {default: ''},
  modelValue: {default: false},
  bottom: {default: false},
  top: {default: false},
  modal: {default: true},
  hideModal: {default: false},
  hideConfirm: {default: false},
  hideButton: {default: false},
  hideCancel: {default: false},
  customClose: {default: false},
  cancelText: {default: '关 闭'},
  confirmText: {default: '确 定'},
})

const emit = defineEmits(['update:modelValue'])

const dialogVisible = ref(false)

function open() {
  dialogVisible.value = true
}

function close() {
  input(false)
  dialogVisible.value = false
}


function cancel() {
  close()
}


function confirm() {
  if (props.customClose) {
    return
  }
  close()
}


function input(visible: boolean) {
  emit('update:modelValue', visible)
}

defineExpose({
  open, close
});
//下的话宽度100，中的话宽度90
//下的话col-end 中的话 row-all-center
</script>
<style>
.bg-modal {
  background: rgba(0, 0, 0, 0.5);
}
</style>
