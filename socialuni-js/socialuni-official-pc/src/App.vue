<template>
    <div class="h100p flex-col bg-default">
        <nav-bar/>
        <RouterView/>
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
