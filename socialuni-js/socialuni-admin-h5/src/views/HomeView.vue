<template>
    <main class="h100p mt-sm row-all-center">
        <div class="w800 h500">
            <div>123</div>456
            <el-button @click="sendAuthCode">123</el-button>
        </div>
    </main>
</template>

<script lang="ts">
import {Options, Vue} from "vue-property-decorator";
import PhoneAPI from "@socialuni/socialuni-user-api/src/api/PhoneAPI";

@Options({})
export default class IndexView extends Vue {

    sendAuthCode(){
        PhoneAPI.sendAuthCodeAPI('1111')
    }
}
</script>
