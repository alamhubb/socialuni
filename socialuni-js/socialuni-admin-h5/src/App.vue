<template>
    <div class="h100p overflow-hidden flex-col bg-default">
<!--        <nav-bar/>-->
        <div class="flex-1 overflow-hidden pd-sm">
            <RouterView/>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue} from "vue-facing-decorator";
import NavBar from "@/layout/NavBar.vue";

@Component({
    components: {NavBar}
})
export default class App extends Vue {
}
</script>
