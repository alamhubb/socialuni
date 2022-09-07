<template>
  <div class="h100p">
    添加客服微信，申请圈主
    123
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import QNavbar from '@/qing-ui/components/QNavbar/QNavbar.vue'
import QSearch from '@/qing-ui/components/QSearch/QSearch.vue'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import QSlider from '@/qing-ui/components/QSlider/QSlider.vue'
import QSidebar from '@/qing-ui/components/QSidebar/QSidebar.vue'
import QRowItem from '@/qing-ui/components/QRowItem/QRowItem.vue'
import QTabs from '@/qing-ui/components/QTabs/QTabs.vue'
import QTab from '@/qing-ui/components/QTab/QTab.vue'
import CircleSearch from '@/components/SocialCirclePicker.vue'


@Component({
  components: {
    CircleSearch,
    QTab,
    QTabs,
    QRowItem,
    QSidebar,
    QSlider,
    QIcon,
    QSearch,
    QNavbar
  }
})
export default class CirclePage extends Vue {

}
</script>
