<template>
    <div class="h100p overflow-hidden flex-col bg-default">
        <nav-bar/>
        <div class="flex-1 overflow-hidden pd-sm row-center">
            <div class="w900">
              <RouterView/>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from "vue-facing-decorator";
import NavBar from "@/layout/NavBar.vue";

@toNative
@Component({
    components: {NavBar}
})
export default class App extends Vue {
}
</script>
