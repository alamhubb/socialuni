<template>
  <div class="h100p">
    添加客服微信，申请圈主
    123
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import QNavbar from 'socialuni-view/src/components/QNavbar/QNavbar.vue'
import QSearch from 'socialuni-view/src/components/QSearch/QSearch.vue'
import QIcon from 'socialuni-view/src/components/QIcon/QIcon.vue'
import QSlider from 'socialuni-view/src/components/QSlider/QSlider.vue'
import QSidebar from 'socialuni-view/src/components/QSidebar/QSidebar.vue'
import QRowItem from 'socialuni-view/src/components/QRowItem/QRowItem.vue'
import QTabs from 'socialuni-view/src/components/QTabs/QTabs.vue'
import QTab from 'socialuni-view/src/components/QTab/QTab.vue'
import CircleSearch from 'socialuni-view/src/components/SocialCirclePicker.vue'


@Options({
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
