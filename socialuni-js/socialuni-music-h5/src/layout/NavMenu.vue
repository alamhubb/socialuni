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
import { Component, Vue , toNative} from 'vue-facing-decorator'
import { constantRoutes } from '@/router'
import QEnumLink from 'qing-ui-h5/src/components/QComponents/QEnumLink.vue'

@toNative
@Component({components:{QEnumLink}})
export default class NavMenu extends Vue {
  // 是x(横向,水平)轴布局
  isXAxisLayout = false

  get menus(){
    return constantRoutes
  }

}
</script>

