<template>
    <s-table-column
            :label="label||prop"
    >
        <template #default="{row}">
            <el-input
                    v-model="row[prop]"
            />
        </template>
    </s-table-column>
</template>

<script lang="ts">
import {Component, Prop, Vue} from 'vue-facing-decorator'
import STableColumn from "../STableColumn/STableColumn.vue";

/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */
@Component({
    components: {STableColumn}
})
export default class STableInput extends Vue {
    @Prop() readonly prop: string
    @Prop() readonly tipProp: string
    @Prop() readonly propFun: Function
    @Prop() readonly label: string
    @Prop() readonly labelClass: string
    @Prop({default: '请输入'}) readonly placeholder: string
    @Prop({default: true, type: Boolean}) readonly clearable: boolean
}
</script>
