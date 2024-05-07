<template>
  <el-menu router>
    <template v-for="menu in menus">
      <q-enum-link v-if="!menu.hidden" :to="menu.path">
        <el-menu-item :index="menu.path">
          {{ menu.meta.title }}
        </el-menu-item>
      </q-enum-link>
    </template>
  </el-menu>
</template>

<script lang="ts">
import {Component, Vue, toNative, Prop} from 'vue-facing-decorator'
import QEnumLink from 'qing-ui-h5/src/components/QComponents/QEnumLink.vue'

@toNative
@Component({components:{QEnumLink}})
export default class QNavMenu extends Vue {
  @Prop() menus: any[]
}
</script>

