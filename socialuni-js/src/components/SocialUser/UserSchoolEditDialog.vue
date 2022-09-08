<template>
  <q-popup ref="schoolDialog" bottom>
    <div class="h550 pd">
      <q-search class="flex-1 mx-sm bg-default">
        <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
        <input v-model.trim="schoolNameValue" :adjust-position="false" type="text" focus
               placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
        <q-icon v-if="schoolNameValue" class="mr text-gray row-all-center" icon="close" size="16"
                @click="clearSchoolNameValue"
        ></q-icon>
      </q-search>
      <div class="px-sm mt">
        已选校园名称： {{ curSchoolName || null }}
      </div>
      <!--      <q-select v-model="curSchoolName" :list="schools" label-name="name" value-name="name"></q-select>-->
      <scroll-view class="mt-sm h380 bt" scroll-y>
        <div v-for="circle in filterCircles" class="bb px-sm py-sm row-between-center"
             @click="curSchoolName = circle.name">
          <div>{{
              circle.name
            }}
          </div>
          <q-icon v-if="circle.name === curSchoolName" icon="checkmark" size="16"></q-icon>
        </div>
      </scroll-view>
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
import {socialUserModule} from "@/socialuni/store";
import QSelect from "@/qing-ui/components/QSelect/QSelect.vue";

@Component({
  components: {QSelect, QIcon, QPopup, QSearch}
})
export default class UserSchoolEditDialog extends Vue {
  $refs!: {
    reportDialog: any;
    editPopup: any;
    schoolDialog: QPopup;
  }

  curSchoolName = null

  schoolNameValue = null
  schools: SocialCircleRO[] = []

  open() {
    if (socialUserModule.user.schoolName) {
      this.curSchoolName = socialUserModule.user.schoolName
      this.schoolNameValue = this.curSchoolName
    }
    this.querySchoolCircles()
    this.$refs.schoolDialog.open()
  }

  async querySchoolCircles() {
    const res = await CircleAPI.queryCirclesByTypeAPI('大学')
    this.schools = res.data
  }

  clearSchoolNameValue() {
    this.schoolNameValue = ''
  }

  get filterCircles() {
    if (this.schoolNameValue) {
      return this.schools.filter(item => item.name.includes(this.schoolNameValue))
    }
    return this.schools
  }
}
</script>
