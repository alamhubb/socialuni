import DataTableColumnOptionVO from '@/components/dataTable/DataTableColumnOptionVO'

export default class DataTableColumnVO {
    type?: 'label' | 'check' | 'input' | 'select'

    // 为select时有效
    option?: DataTableColumnOptionVO
    prop?: string
    label?: string
    width?: string | number
}
