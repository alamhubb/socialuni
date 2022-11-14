<template>
  <div v-if="dialogVisible || value"
       class="position-fixed overflow-hidden index-999 h100vh w100vw top-0 bottom-0 left-0 right-0">
<!--    <q-model @click="cancel" v-if="modal">
      &lt;!&ndash;        ios端不支持prevent需要用stop代替&ndash;&gt;
    </q-model>-->
    <div class="h100%" :class="bottom?'col-end':'row-all-center'">
      <div class="q-popup bg-white position-absolute index-1005 flex-col"
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
  </div>
</template>
<script setup lang="ts">
import SButton from "../SButton/SButton.vue";

</script>
