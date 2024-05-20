<template>
  <!--  可点击的行-->
  <div class="row-between-center" :class="{'bb':border}">
    <slot name="title"></slot>
    <div class="use-click flex-1" @click="click" :class="[hasTitle?'row-end-center':'row-between-center']">
      <div>
        <slot></slot>
      </div>
      <q-icon icon="arrow-right" size="12" class="color-sub flex-none"></q-icon>
    </div>
  </div>
</template>
<script lang="ts">
import {Emit, Component, Vue, toNative, Prop} from 'vue-facing-decorator'
import QIcon from "./QIcon.vue";

//和row-line区别，这个有上下pd那个没有
/*
显示出来已经选了的城市，给她画上钩
* */
@toNative
@Component({
  components: {QIcon}
})
export default class QRowItem extends Vue {
  @Prop({default: false, type: Boolean}) border: boolean

  hasTitle = false

  mounted() {
    this.hasTitle = !!this.$slots.title
  }

  @Emit()
  click() {
    return
  }
}
</script>

