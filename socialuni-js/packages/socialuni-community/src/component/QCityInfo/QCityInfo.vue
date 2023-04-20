<template>
  <div>
    <div @click="openCityPicker" class="row-col-center use-click">
      <q-icon icon="map-fill" size="14" class="mb-nm"/>
      <div v-if="value" class="q-tag-warn bd-round row-col-center">
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
      <div v-else>中国</div>
    </div>
    <q-city-picker v-if="picker" ref="cityPicker" :value="value" @input="input"></q-city-picker>
  </div>
</template>

<script lang="ts">
import {Emit, Model, Options, Prop, Vue} from 'vue-property-decorator'
import QCityPicker from "../QCityPicker/QCityPicker.vue";
import QIcon from "socialuni-ui/src/components/QIcon/QIcon.vue";
import DistrictVO from "socialuni/src/model/DistrictVO";

@Options({
  components: {
    QCityPicker,
    QIcon
  }
})
export default class QCityInfo extends Vue {
  $refs: {
    cityPicker: QCityPicker
  }

  @Model('modelValue') value: DistrictVO

  @Prop({
    default: false, type: Boolean
  }) picker: boolean

  openCityPicker() {
    if (this.picker) {
      this.$refs.cityPicker.open()
    }
  }

  @Emit('update:modelValue')
  input(value: DistrictVO) {
    return value
  }
}
</script>
