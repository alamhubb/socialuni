<template>
  <q-popup ref="schoolDialog" bottom>
    <div class="h500 pd">
      <q-search class="flex-1 mx-sm bg-default">
        <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
        <input v-model.trim="schoolNameValue" :adjust-position="false" type="text" focus
               placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
        <q-icon v-if="schoolNameValue" class="mr text-gray row-all-center" icon="close" size="16"
                @click="clearSchoolNameValue"
        ></q-icon>
      </q-search>
      <div>
        <div v-for="circle in filterCircles">{{ circle.name }}</div>
      </div>
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import QPopup from "@/qing-ui/components/QPopup/QPopup.vue";
import QSearch from "@/qing-ui/components/QSearch/QSearch.vue";
import QIcon from "@/qing-ui/components/QLocation/QIcon.vue";
import CircleAPI from "@/socialuni/api/socialuni/CircleAPI";
import SocialCircleRO from "@/socialuni/model/community/circle/SocialCircleRO";

@Component({
  components: {QIcon, QPopup, QSearch}
})
export default class UserSchoolEditDialog extends Vue {
  $refs!: {
    reportDialog: any;
    editPopup: any;
    schoolDialog: QPopup;
  }

  schoolNameValue = null
  circles: SocialCircleRO[] = []

  open() {
    this.querySchoolCircles()
    this.$refs.schoolDialog.open()
  }

  async querySchoolCircles() {
    const res = await CircleAPI.queryCirclesByTypeAPI('大学')
    this.circles = res.data
  }

  clearSchoolNameValue() {
    this.schoolNameValue = ''
  }

  get filterCircles() {
    if (this.schoolNameValue) {
      return this.circles.filter(item => item.name.includes(this.schoolNameValue))
    }
    return this.circles
  }
}
</script>
