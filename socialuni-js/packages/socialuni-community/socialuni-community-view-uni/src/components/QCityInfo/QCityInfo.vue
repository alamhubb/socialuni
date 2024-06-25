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
        <span v-else>
          {{ value.adName }}
        </span>
      </div>
      <div v-else>中国</div>
    </div>
    <q-city-picker v-if="picker" ref="cityPicker" :value="value" @input="input"></q-city-picker>
  </div>
</template>

<script lang="ts">
import {Emit, Model, Component, Prop, Vue, toNative} from 'vue-facing-decorator'
import QCityPicker from "../QCityPicker/QCityPicker.vue";
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import DistrictVO from "socialuni-api-base/src/model/DistrictVO";

@toNative
@Component({
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
