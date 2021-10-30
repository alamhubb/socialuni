<template>
  <div v-if="!route.hidden">
    <!--    如果router没有子节点，或者仅仅有一个展示的子节点 且 ，且不为总展示-->
    <!--    且 （没有子节点 或者 不展示子节点） && 不为总展示-->
    <!--1.如果总展示父节点，则走这个
    2.如果没有子节点，或者不展示子节点也走这个
    3.如果仅有一个展示的子节点，或者没有子节点也走这个-->
    <template
      v-if="isOnlyOneChildShow(route)"
    >
      <div v-show="false">
        {{ onlyShowRoute = getOnlyShowRoute(route) }}
      </div>
      <app-link v-if="onlyShowRoute.meta" :to="resolvePath(onlyShowRoute.path)">
        <el-menu-item
          :index="resolvePath(onlyShowRoute.path)"
        >
          <!--            <img v-if="onlyShowRoute.meta.icon" class="mr-sm" :src="require('@/assets/img/navMenu/router/'+onlyShowRoute.meta.icon+'.png')">-->
          {{ onlyShowRoute.meta.title }}
        </el-menu-item>
      </app-link>
    </template>
    <el-submenu v-else ref="subMenu" :index="resolvePath(route.path)" popper-append-to-body>
      <template slot="title">
        <div class="row-col-center">
          <!--          <img v-if="route.meta.icon" class="mr-sm" :src="require('@/assets/img/navMenu/router/'+route.meta.icon+'.png')">-->
          <span class="pr-md">{{ route.meta.title }}</span>
        </div>
      </template>

      <nav-menu-item
        v-for="child in route.children"
        :key="child.path"
        :is-nest="true"
        :route="child"
        :base-path="resolvePath(route.path)"
        class="nest-menu"
      />
    </el-submenu>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import AppLink from '@/components/layout/components/HorizontalSidebar/Link.vue'
import MenuItem from '@/components/layout/components/HorizontalSidebar/Item.vue'
// @ts-ignore
import path from 'path'

@Component({
  components: { MenuItem, AppLink }
})
export default class NavMenuItem extends Vue {
  @Prop() route
  @Prop() isNest
  @Prop() basePath

  onlyShowRoute = null

  // 是否仅有一个展示的子节点
  isOnlyOneChildShow(route) {
    // 小于两个就代表只有一个展示    获取展示的子节点
    return this.showRootRoute(route) || route.children.filter(child => {
      // 不隐藏的就是展示的
      return !child.hidden
    }).length < 2
  }

  // 获取展示的子节点
  getOnlyShowRoute(route) {
    if (this.showRootRoute(route)) {
      return route
    }
    return route.children.find(child => !child.hidden) || route
  }

  showRootRoute(route) {
    return route.alwaysShow || route.noShowingChildren || !route.children || !route.children.length
  }

  resolvePath(routePath) {
    /* if (isExternal(routePath)) {
      return routePath
    }
    if (isExternal(this.basePath)) {
      return this.basePath
    }*/
    return path.resolve(this.basePath, routePath)
  }
}
</script>
