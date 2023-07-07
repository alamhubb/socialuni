<template>
    <main class="h100p overflow-hidden row-all-center">
        用户详情
    </main>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import SocialuniChatViewLogic, {SocialuniChatViewLogicRefs} from "@/views/chat/SocialuniChatViewLogic";
import YScrollbar from "@/components/YScrollbar.vue";

@Options({
    components: {YScrollbar}
})
export default class MinePage extends Vue {


}
</script>
