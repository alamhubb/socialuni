<template>
  <div class="h100p">
    添加客服微信，申请圈主
    123
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import QNavbar from 'socialuni-ui/src/components/QNavbar/QNavbar.vue'
import QSearch from 'socialuni-ui/src/components/QSearch/QSearch.vue'
import QIcon from 'socialuni-ui/src/components/QIcon/QIcon.vue'
import QSlider from 'socialuni-ui/src/components/QSlider/QSlider.vue'
import QSidebar from 'socialuni-ui/src/components/QSidebar/QSidebar.vue'
import QRowItem from 'socialuni-ui/src/components/QRowItem/QRowItem.vue'
import QTabs from 'socialuni-ui/src/components/QTabs/QTabs.vue'
import QTab from 'socialuni-ui/src/components/QTab/QTab.vue'
import CircleSearch from 'socialuni-ui/src/components/SocialCirclePicker.vue'


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
