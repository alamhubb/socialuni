<template>
    <main class="h100p mt-sm row-all-center">
        <div class="w800 h500">
            <div>123</div>456
            <el-button @click="sendAuthCode">123</el-button>
        </div>
    </main>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator';
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";


@Component({})
class IndexView extends Vue {

    sendAuthCode(){
        PhoneAPI.sendAuthCodeAPI('1111')
    }
}
export default toNative(IndexView)
</script>
