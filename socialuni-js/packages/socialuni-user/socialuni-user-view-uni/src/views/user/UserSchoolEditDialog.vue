<template>
  <q-popup ref="schoolDialog" bottom @confirm="confirm">
    <div class="h80vh pd flex-col">
      <q-search class="mx-sm bg-default flex-none">
        <q-icon class="mx-xs text-gray" icon="search" size="16"></q-icon>
        <input v-model.trim="filterSchoolNameText" :adjust-position="false" type="text"
               placeholder="输入话题中文名称进行筛选" confirm-type="search"/>
        <q-icon v-if="filterSchoolNameText" class="mr text-gray row-all-center" icon="close" size="16"
                @click="clearSchoolNameValue"
        ></q-icon>
      </q-search>
      <div class="px-sm mt flex-none">
        已选校园名称： {{ schoolNameValue || "" }}
      </div>
      <div class="flex-1 overflow-hidden">
        <!--      <q-select v-model="curSchoolName" :list="schools" label-name="name" value-name="name"></q-select>-->
        <scroll-view class="mt-sm h100p bt" scroll-y>
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
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import QIcon from "qing-compat-js-ui/src/components/QIcon.vue";
import QPopup from "qing-compat-js-ui/src/components/QPopup.vue";
import QSearch from "qing-compat-js-ui-uni/src/components/QSearch/QSearch.vue";
import QSelect from "qing-compat-js-ui-uni/src/components/QSelect/QSelect.vue";
import SocialCircleRO from "socialuni-api-base/src/model/community/circle/SocialCircleRO";
import SocialuniCircleAPI from "socialuni-community-api/src/api/SocialuniCircleAPI";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import SocialuniUserExpandAPI from "socialuni-user-api/src/api/SocialuniUserExpandAPI";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";

@toNative
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
    if (socialuniUserModule.mineUser.schoolName) {
      this.schoolNameValue = socialuniUserModule.mineUser.schoolName
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
    if (!this.schoolNameValue) {
        QingAppUtil.ToastUtil.error('学校名称不能为空')
    }
    try {
      await QingAppUtil.AlertUtil.confirm('是否确认将学校名称设置为' + this.schoolNameValue + '确认后不可变更')
      this.$refs.schoolDialog.close()
      const res = await SocialuniUserExpandAPI.editUserSchoolNameAPI(this.schoolNameValue)
      socialuniUserModule.setUser(res.data)
    } finally {

    }
  }
}
</script>
