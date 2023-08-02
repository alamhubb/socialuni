<template>
    <div class="h100p overflow-hidden flex-col bg-default">
      <el-button>sdafd</el-button>
        <nav-bar/>
        <div class="flex-1 overflow-hidden pd-sm">
            <RouterView/>
        </div>
    </div>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import NavBar from "@/layout/NavBar.vue";

@Options({
    components: {NavBar}
})
export default class App extends Vue {
}
</script>
