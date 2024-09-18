<template>
    <el-table-column
            :label="label||prop"
            :prop="prop"
            :type="DataTableColumnTypeEnum.values.includes(type)?'':type"
            show-overflow-tooltip
            v-bind="$attrs"
            :align="align"
    >
        <template slot="header">
            <slot name="header"></slot>
        </template>
        <template #default="{row,$index}" v-if="!DataTableColumnTypeEnum.elTableTypes.includes(type)">
            <slot name="default" :row="row" :$index="$index">
                <template v-if="DataTableColumnTypeEnum.values.includes(type)">
                    <!--<el-checkbox
                      v-if="type === ColumnType.check || column.type === ColumnType.check"
                      v-model="row.checked"
                      @click.native.stop
                      v-on="$listeners"
                  />-->
                    <!--      :label="optionLabel|| (column.option?column.option.label:null)"-->
                    <!--      因为内不支持不输入label默认使用value-->
                    <q-select
                            v-if="type === DataTableColumnTypeEnum.select"
                            v-model="row[prop]"
                            :disabled="readonly"
                            :options="options"
                            :label="optionLabel"
                            :value="optionValue"
                            @change.native.stop
                    />
                    <!--      ? option.label : optionValue-->
                    <el-input
                            v-else-if="type === DataTableColumnTypeEnum.input"
                            v-model="row[prop]"
                            :readonly="readonly"
                            size="small"
                            placeholder="请输入"
                            @click.native.stop
                    />
                    <template v-else-if="type===DataTableColumnTypeEnum.label">
                        <!--        这里逻辑太重了，最好优化-->
                        <slot name="default" :row="row">
                            <el-tooltip
                                    v-if="tipProp && row[tipProp] && showMsg(row)"
                                    effect="dark"
                                    :content="row[tipProp] || (propFun ? propFun(row[prop], row) : row[prop])"
                                    placement="top-start">
                                <div>
                                    <span :class="labelClass">{{ propFun ? propFun(row[prop], row) : row[prop] }}</span>
                                    <i class="el-icon-question ml-xs"></i>
                                </div>
                            </el-tooltip>
                            <span v-else :class="labelClass">{{ propFun ? propFun(row[prop], row) : row[prop] }}</span>
                        </slot>
                        <!--        <slot name="default" :row="row">{{  row[prop] }}</slot>-->
                    </template>

                    <el-button
                            v-else-if="type===DataTableColumnTypeEnum.delete"
                            @click="deleteEvent($index)"
                            size="small"
                            :disabled="deleteDisabled"
                            type="danger"
                            plain>
                        {{
                        deleteTitle
                        }}
                    </el-button>
                </template>
            </slot>
        </template>
    </el-table-column>
</template>

<script lang="ts">
import {Component, Emit, Prop, Vue, toNative} from 'vue-facing-decorator'
import DataTableColumnTypeEnum from './DataTableColumnTypeEnum'
import SSelect from "../SSelect/SSelect.vue";
/**
 * @author 秦开远
 * @date 2021/1/21 15:25
 *
 * 在数据源业务基础上，封装基础table
 */

@Component({
    components: {SSelect}
})
class STableColumn extends Vue {
    @Prop({default: 'label'}) readonly type: string
    // @Prop({ default: false, type: Boolean }) readonly disabled: boolean

    @Prop() readonly prop: string
    @Prop({default: 'left'}) readonly align: string
    @Prop() readonly tipProp: string
    @Prop() readonly propFun: Function
    @Prop() readonly label: string
    @Prop() readonly labelClass: string
    @Prop({default: '请选择'}) readonly hint: string

    @Prop() readonly options: []
    @Prop() readonly optionLabel: string
    @Prop() readonly readonly: boolean
    @Prop() readonly optionValue: string
    @Prop({default: '删除'}) readonly deleteTitle: string
    @Prop({default: false, type: Boolean}) readonly deleteDisabled: boolean

    DataTableColumnTypeEnum: typeof DataTableColumnTypeEnum = DataTableColumnTypeEnum

    @Prop({
        default(row: any) {
            return true
        }, type: Function
    }) readonly showMsg: Function

    @Emit()
    deleteEvent(index) {
        return index
    }
}
export default toNative(STableColumn)
</script>
