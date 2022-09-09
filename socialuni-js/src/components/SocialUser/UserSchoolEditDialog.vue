<template>
  <q-popup ref="schoolDialog" bottom @confirm="confirm">
    <div class="h550 pd">
      <q-search class="mx-sm bg-default">
        <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
        <input v-model.trim="filterSchoolNameText" :adjust-position="false" type="text" focus
               placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
        <q-icon v-if="filterSchoolNameText" class="mr text-gray row-all-center" icon="close" size="16"
                @click="clearSchoolNameValue"
        ></q-icon>
      </q-search>
      <div class="px-sm mt">
        已选校园名称： {{ schoolNameValue || null }}
      </div>
      <!--      <q-select v-model="curSchoolName" :list="schools" label-name="name" value-name="name"></q-select>-->
      <scroll-view class="mt-sm h380 bt" scroll-y>
        <div v-for="circle in filterCircles" class="bb px-sm py-sm row-between-center"
             @click="schoolNameValue = circle.name">
          <div>{{
              circle.name
            }}
          </div>
          <q-icon v-if="circle.name === schoolNameValue" icon="checkmark" size="16"></q-icon>
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
import SocialuniCircleAPI from "@/socialuni/api/socialuni/SocialuniCircleAPI";
import SocialCircleRO from "@/socialuni/model/community/circle/SocialCircleRO";
import {socialUserModule} from "@/socialuni/store";
import QSelect from "@/qing-ui/components/QSelect/QSelect.vue";
import AlertUtil from "@/socialuni/utils/AlertUtil";
import SocialuniUserExpandAPI from "@/socialuni/api/socialuni/SocialuniUserExpandAPI";

@Component({
  components: {QSelect, QIcon, QPopup, QSearch}
})
export default class UserSchoolEditDialog extends Vue {
  $refs!: {
    reportDialog: any;
    editPopup: any;
    schoolDialog: QPopup;
  }

  schoolNameValue = null

  filterSchoolNameText = null
  schools: SocialCircleRO[] = []

  open() {
    if (socialUserModule.user.schoolName) {
      this.schoolNameValue = socialUserModule.user.schoolName
      this.filterSchoolNameText = this.schoolNameValue
    }
    this.querySchoolCircles()
    this.$refs.schoolDialog.open()
  }

  async querySchoolCircles() {
    const res = await SocialuniCircleAPI.queryCirclesByTypeAPI('大学')
    this.schools = res.data
  }

  clearSchoolNameValue() {
    this.filterSchoolNameText = ''
  }

  get filterCircles() {
    if (this.filterSchoolNameText) {
      return this.schools.filter(item => item.name.includes(this.filterSchoolNameText))
    }
    return this.schools
  }

  async confirm() {
    try {
      await AlertUtil.confirm('是否确认将学校名称设置为' + this.schoolNameValue + '确认后不可变更')
      this.$refs.schoolDialog.close()
      const res = await SocialuniUserExpandAPI.editUserSchoolNameAPI(this.schoolNameValue)
      socialUserModule.setUser(res.data)
    } finally {

    }
  }
}
</script>
