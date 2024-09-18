<template>
  <div>
    <div class="row-between-center shadow-bottom index-sm px-smm h50 bg-white flex-none">
      <div class="flex-none row-col-center mr-40 bg-click" @click="toHome">
        <!--      <img src="@/assets/img/logo.jpg" class="h40" alt="logo">-->
        <div class="font-19 font-bold">{{ title}}</div>
      </div>

      <slot name="default"></slot>

    </div>
  </div>

</template>

<script lang="ts">
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator'


@Component({
})
class QNavBar extends Vue {
  @Prop() title: string
}
export default toNative(QNavBar)
</script>
