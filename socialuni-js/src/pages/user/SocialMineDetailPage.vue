<template>
  <social-mine-detail class="h100p"></social-mine-detail>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import SocialMineDetail from '../../components/SocialUser/SocialMineDetail.vue'

@Component({
  components: {SocialMineDetail}
})
export default class SocialMineDetailPage extends Vue {

}
</script>
