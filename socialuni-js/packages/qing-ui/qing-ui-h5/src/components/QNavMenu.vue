<template>
  <el-menu :default-active="defaultActive">
    <template v-for="menu in menus">
      <q-enum-link v-if="!menu?.meta?.hidden" :to="menu.path">
        <el-menu-item :index="menu.path">
          {{ menu.meta.title }}
        </el-menu-item>
      </q-enum-link>
    </template>
  </el-menu>
</template>

<script lang="ts">
import {Component, Vue, toNative, Prop} from 'vue-facing-decorator'
import QEnumLink from 'qing-ui-h5/src/components/QEnumLink.vue'
import CommonUtil from "qing-util/src/util/CommonUtil";

@toNative
@Component({components: {QEnumLink}})
export default class QNavMenu extends Vue {
  get defaultActive(){
    return this.$route.path
  }

  mounted() {
  }

  // @Prop() menus: any[]
  get menus() {
    return this.$router.getRoutes()
  }
}
</script>

