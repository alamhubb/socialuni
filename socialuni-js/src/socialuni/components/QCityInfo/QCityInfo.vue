<template>
  <div>
    <div v-if="value" class="q-tag-warn bd-round row-col-center" @click="openCityPicker">
      <q-icon icon="map-fill" size="14" class="mb-nn"/>
      <template v-if="value.adCode">
        {{ value.provinceName }}
        <template v-if="value.cityName">
          -{{ value.cityName }}
        </template>
        <template v-if="value.districtName">
          -{{ value.districtName }}
        </template>
      </template>
      <text v-else>
        {{ value.adName }}
      </text>
    </div>
    <q-city-picker v-if="picker" ref="cityPicker" :value="value" @input="input"></q-city-picker>
  </div>
</template>

<script lang="ts">
import { Component, Emit, Prop, Vue } from 'vue-property-decorator'
import DistrictVO from '@/socialuni/model/DistrictVO'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import QCityPicker from '@/socialuni/components/QCityPicker/QCityPicker.vue'

@Component({
  components: {
    QCityPicker,
    QIcon
  }
})
export default class SocialCityInfo extends Vue {
  $refs: {
    cityPicker: QCityPicker
  }

  @Prop() value: DistrictVO

  @Prop({
    default: false,
    type: Boolean
  }) picker: boolean

  openCityPicker () {
    if (this.picker) {
      this.$refs.cityPicker.open()
    }
  }

  @Emit()
  input (value: DistrictVO) {
    return value
  }
}
</script>
