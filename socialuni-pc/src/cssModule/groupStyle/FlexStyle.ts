import Display from "../singleStyle/Display"
import FlexDirection from "../singleStyle/FlexDirection"
import CssClass from '@/cssModule/CssClass'

export default class FlexStyle implements CssClass {
    static get flexRow() {
        return {
            ...Display.flex,
            ...FlexDirection.row,
        }
    }
}
