<template>
    <main class="h100p mt-sm row-all-center">
        <div class="w800 h500">
            <div>123</div>6668989
        </div>
    </main>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator';
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";

@Component({})
export default class IndexView extends Vue {

    sendAuthCode(){
        PhoneAPI.sendAuthCodeAPI('1111')
    }
}
</script>
